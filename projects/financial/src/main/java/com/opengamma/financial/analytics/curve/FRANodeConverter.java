/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 *
 * Modified by McLeod Moores Software Limited.
 *
 * Copyright (C) 2015-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.financial.analytics.curve;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.threeten.bp.Period;
import org.threeten.bp.ZonedDateTime;

import com.opengamma.DataNotFoundException;
import com.opengamma.OpenGammaRuntimeException;
import com.opengamma.analytics.financial.instrument.InstrumentDefinition;
import com.opengamma.analytics.financial.instrument.fra.ForwardRateAgreementDefinition;
import com.opengamma.analytics.financial.instrument.index.IborIndex;
import com.opengamma.analytics.financial.schedule.ScheduleCalculator;
import com.opengamma.core.convention.ConventionSource;
import com.opengamma.core.holiday.HolidaySource;
import com.opengamma.core.marketdatasnapshot.SnapshotDataBundle;
import com.opengamma.core.region.RegionSource;
import com.opengamma.core.security.Security;
import com.opengamma.core.security.SecuritySource;
import com.opengamma.financial.analytics.conversion.CalendarUtils;
import com.opengamma.financial.analytics.ircurve.strips.FRANode;
import com.opengamma.financial.convention.IborIndexConvention;
import com.opengamma.financial.convention.calendar.Calendar;
import com.opengamma.id.ExternalId;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.time.Tenor;

/**
 * Convert a FRA node into a {@link ForwardRateAgreementDefinition}.
 * The dates of the FRA are computed in the following way:
 * <ul>
 *  <li> The spot date is computed from the valuation date by adding the number of settlement days (i.e. the number of business days) defined in the underlying ibor index convention.
 *  <li> The accrual start date is computed from the spot date by adding the fixing start tenor of the node and using the business-day-convention, calendar and EOM of the ibor index convention.
 *  <li> The accrual end date is computed from the spot date adding the fixing end tenor of the node and using the business-day-convention, calendar and EOM of the ibor index convention.
 * </ul>
 * The FRA notional is 1.
 */
public class FRANodeConverter extends CurveNodeVisitorAdapter<InstrumentDefinition<?>> {
  /** The logger */
  private static final Logger LOGGER = LoggerFactory.getLogger(FRANodeConverter.class);
  /** The security source */
  private final SecuritySource _securitySource;
  /** The convention source */
  private final ConventionSource _conventionSource;
  /** The holiday source */
  private final HolidaySource _holidaySource;
  /** The region source */
  private final RegionSource _regionSource;
  /** The market data */
  private final SnapshotDataBundle _marketData;
  /** The market data id */
  private final ExternalId _dataId;
  /** The valuation time */
  private final ZonedDateTime _valuationTime;

  /**
   * @param securitySource  the security source, not null
   * @param conventionSource  the convention source, not null
   * @param holidaySource  the holiday source, not null
   * @param regionSource  the region source, not null
   * @param marketData  the market data, not null
   * @param dataId  the id of the market data, not null
   * @param valuationTime  the valuation time, not null
   */
  public FRANodeConverter(final SecuritySource securitySource, final ConventionSource conventionSource, final HolidaySource holidaySource, final RegionSource regionSource,
      final SnapshotDataBundle marketData, final ExternalId dataId, final ZonedDateTime valuationTime) {
    _securitySource = ArgumentChecker.notNull(securitySource, "securitySource");
    _conventionSource = ArgumentChecker.notNull(conventionSource, "conventionSource");
    _holidaySource = ArgumentChecker.notNull(holidaySource, "holidaySource");
    _regionSource = ArgumentChecker.notNull(regionSource, "regionSource");
    _marketData = ArgumentChecker.notNull(marketData, "marketData");
    _dataId = ArgumentChecker.notNull(dataId, "dataId");
    _valuationTime = ArgumentChecker.notNull(valuationTime, "valuationTime");
  }

  //TODO check calendars
  @Override
  public InstrumentDefinition<?> visitFRANode(final FRANode fraNode) {
    final Double rate = _marketData.getDataPoint(_dataId);
    if (rate == null) {
      throw new OpenGammaRuntimeException("Could not get market data for " + _dataId);
    }
    Security sec = null;
    IborIndexConvention indexConvention = null;
    Tenor indexTenor = null;
    // try the security source first, then look for the convention directly
    // if the convention is used, the tenor is implied from the FRA node
    try {
      sec = _securitySource.getSingle(fraNode.getConvention().toBundle());
    } catch (final DataNotFoundException e) {
      LOGGER.info(e.getMessage());
    }
    if (sec != null) {
      if (!(sec instanceof com.opengamma.financial.security.index.IborIndex)) {
        throw new OpenGammaRuntimeException("Only supported underlying indices are IborIndex: have " + sec);
      }
      final com.opengamma.financial.security.index.IborIndex indexSecurity = (com.opengamma.financial.security.index.IborIndex) sec;
      indexTenor = indexSecurity.getTenor();
      indexConvention = _conventionSource.getSingle(indexSecurity.getConventionId(), IborIndexConvention.class);
    }
    final Period startPeriod = fraNode.getFixingStart().getPeriod();
    final Period endPeriod = fraNode.getFixingEnd().getPeriod();
    if (indexConvention == null) {
      // try with convention
      indexConvention = _conventionSource.getSingle(fraNode.getConvention(), IborIndexConvention.class);
      if (indexConvention == null) {
        throw new OpenGammaRuntimeException("Convention with id " + fraNode.getConvention() + " was null");
      }
      indexTenor = Tenor.of(Period.ofMonths((int) (fraNode.getFixingEnd().getPeriod().toTotalMonths() - fraNode.getFixingStart().getPeriod().toTotalMonths())));
    }
    final IborIndex index = ConverterUtils.indexIbor(indexConvention.getName(), indexConvention, indexTenor);
    final Calendar fixingCalendar = CalendarUtils.getCalendar(_regionSource, _holidaySource, indexConvention.getFixingCalendar());
    final Calendar regionCalendar = CalendarUtils.getCalendar(_regionSource, _holidaySource, indexConvention.getRegionCalendar());
    final int spotLag = indexConvention.getSettlementDays();
    final ZonedDateTime spotDate = ScheduleCalculator.getAdjustedDate(_valuationTime, spotLag, regionCalendar);
    final ZonedDateTime accrualStartDate = ScheduleCalculator.getAdjustedDate(spotDate, startPeriod, index, regionCalendar);
    final ZonedDateTime accrualEndDate = ScheduleCalculator.getAdjustedDate(spotDate, endPeriod, index, regionCalendar);
    return ForwardRateAgreementDefinition.from(accrualStartDate, accrualEndDate, 1, index, rate, fixingCalendar);
  }

}
