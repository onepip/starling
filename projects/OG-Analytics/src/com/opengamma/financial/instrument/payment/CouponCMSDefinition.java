/**
 * Copyright (C) 2011 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.instrument.payment;

import javax.time.calendar.LocalDate;
import javax.time.calendar.LocalDateTime;
import javax.time.calendar.TimeZone;
import javax.time.calendar.ZonedDateTime;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.Validate;

import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.instrument.FixedIncomeInstrumentDefinitionVisitor;
import com.opengamma.financial.instrument.index.CMSIndex;
import com.opengamma.financial.instrument.swap.SwapFixedIborDefinition;
import com.opengamma.financial.interestrate.payments.CouponCMS;
import com.opengamma.financial.interestrate.payments.CouponFixed;
import com.opengamma.financial.interestrate.payments.Payment;
import com.opengamma.financial.interestrate.swap.definition.FixedCouponSwap;
import com.opengamma.financial.schedule.ScheduleCalculator;
import com.opengamma.util.money.Currency;

/**
 * Class describing a Constant Maturity Swap coupon.
 */
public class CouponCMSDefinition extends CouponFloatingDefinition {

  /**
   * The swap underlying the CMS coupon.
   */
  private final SwapFixedIborDefinition _underlyingSwap;
  /**
   * The CMS index associated to the coupon.
   */
  private final CMSIndex _cmsIndex;

  /**
   * Constructor of a CMS coupon from all the details.
   * @param currency The payment currency.
   * @param paymentDate Coupon payment date.
   * @param accrualStartDate Start date of the accrual period.
   * @param accrualEndDate End date of the accrual period.
   * @param accrualFactor Accrual factor of the accrual period.
   * @param notional Coupon notional.
   * @param fixingDate The coupon fixing date.
   * @param underlyingSwap A swap describing the CMS underlying. The rate and notional are not used.
   * @param cmsIndex The CMS index associated to the coupon.
   */
  public CouponCMSDefinition(final Currency currency, final ZonedDateTime paymentDate, final ZonedDateTime accrualStartDate, final ZonedDateTime accrualEndDate, final double accrualFactor,
      final double notional,
      final ZonedDateTime fixingDate, final SwapFixedIborDefinition underlyingSwap, final CMSIndex cmsIndex) {
    super(currency, paymentDate, accrualStartDate, accrualEndDate, accrualFactor, notional, fixingDate);
    Validate.notNull(underlyingSwap, "underlying swap");
    Validate.notNull(cmsIndex, "CMS index");
    _underlyingSwap = underlyingSwap;
    _cmsIndex = cmsIndex;
  }

  /**
   * Constructor of a CMS coupon from all the details.
   * @param paymentDate Coupon payment date.
   * @param accrualStartDate Start date of the accrual period.
   * @param accrualEndDate End date of the accrual period.
   * @param accrualFactor Accrual factor of the accrual period.
   * @param notional Coupon notional.
   * @param fixingDate The coupon fixing date.
   * @param underlyingSwap A swap describing the CMS underlying. The rate and notional are not used.
   * @param cmsIndex The CMS index associated to the coupon.
   * @return The CMS coupon.
   */
  public static CouponCMSDefinition from(final ZonedDateTime paymentDate, final ZonedDateTime accrualStartDate, final ZonedDateTime accrualEndDate, final double accrualFactor, final double notional,
      final ZonedDateTime fixingDate,
      final SwapFixedIborDefinition underlyingSwap, final CMSIndex cmsIndex) {
    Validate.notNull(underlyingSwap, "underlying swap");
    return new CouponCMSDefinition(underlyingSwap.getCurrency(), paymentDate, accrualStartDate, accrualEndDate, accrualFactor, notional, fixingDate, underlyingSwap, cmsIndex);
  }

  /**
   * Builder from a floating coupon and an underlying swap.
   * @param coupon A floating coupon with the details of the coupon to construct.
   * @param underlyingSwap A swap describing the CMS underlying. The rate and notional are not used.
   * @param cmsIndex The CMS index associated to the coupon.
   * @return The constructed CMS coupon.
   */
  public static CouponCMSDefinition from(final CouponFloatingDefinition coupon, final SwapFixedIborDefinition underlyingSwap, final CMSIndex cmsIndex) {
    Validate.notNull(coupon, "floating coupon");
    Validate.notNull(underlyingSwap, "underlying swap");
    return new CouponCMSDefinition(coupon.getCurrency(), coupon.getPaymentDate(), coupon.getAccrualStartDate(), coupon.getAccrualEndDate(), coupon.getPaymentYearFraction(), coupon.getNotional(),
        coupon.getFixingDate(), underlyingSwap, cmsIndex);
  }

  /**
   * Builder from a floating coupon and a CMS Index.
   * @param coupon A floating coupon with the details of the coupon to construct.
   * @param cmsIndex The CMS index associated to the coupon.
   * @return The constructed CMS coupon.
   */
  public static CouponCMSDefinition from(final CouponFloatingDefinition coupon, final CMSIndex cmsIndex) {
    Validate.notNull(coupon, "floating coupon");
    Validate.notNull(cmsIndex, "CMS index");
    final ZonedDateTime settlementDate = ScheduleCalculator.getAdjustedDate(coupon.getFixingDate(), cmsIndex.getIborIndex().getBusinessDayConvention(), cmsIndex.getIborIndex().getCalendar(), cmsIndex
        .getIborIndex().getSettlementDays());
    // Implementation comment: the underlying swap is used for forward. The notional, rate and payer flag are irrelevant.
    final SwapFixedIborDefinition underlyingSwap = SwapFixedIborDefinition.from(settlementDate, cmsIndex, 1.0, 1.0, true);
    return new CouponCMSDefinition(coupon.getCurrency(), coupon.getPaymentDate(), coupon.getAccrualStartDate(), coupon.getAccrualEndDate(), coupon.getPaymentYearFraction(), coupon.getNotional(),
        coupon.getFixingDate(), underlyingSwap, cmsIndex);
  }

  /**
   * Gets the underlying swap.
   * @return The underlying swap
   */
  public SwapFixedIborDefinition getUnderlyingSwap() {
    return _underlyingSwap;
  }

  /**
   * Gets the CMS index associated to the coupon.
   * @return The CMS index.
   */
  public CMSIndex getCmsIndex() {
    return _cmsIndex;
  }

  @Override
  public String toString() {
    return super.toString() + ", Swap = " + _underlyingSwap.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + _underlyingSwap.hashCode();
    return result;
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final CouponCMSDefinition other = (CouponCMSDefinition) obj;
    if (!ObjectUtils.equals(_underlyingSwap, other._underlyingSwap)) {
      return false;
    }
    return true;
  }

  @Override
  public Payment toDerivative(final LocalDate date, final String... yieldCurveNames) {
    Validate.notNull(date, "date");
    Validate.notNull(yieldCurveNames, "yield curve names");
    Validate.isTrue(yieldCurveNames.length > 1, "at least two curves required");
    Validate.isTrue(!date.isAfter(getPaymentDate().toLocalDate()), "date is after payment date");
    final DayCount actAct = DayCountFactory.INSTANCE.getDayCount("Actual/Actual ISDA");
    final String fundingCurveName = yieldCurveNames[0];
    final ZonedDateTime zonedDate = ZonedDateTime.of(LocalDateTime.ofMidnight(date), TimeZone.UTC);
    final double paymentTime = actAct.getDayCountFraction(zonedDate, getPaymentDate());
    if (isFixed()) { // The CMS coupon has already fixed, it is now a fixed coupon.
      return new CouponFixed(getCurrency(), paymentTime, fundingCurveName, getPaymentYearFraction(), getNotional(), getFixedRate());
    }
    // CMS is not fixed yet, all the details are required.
    final double fixingTime = actAct.getDayCountFraction(zonedDate, getFixingDate());
    final double settlementTime = actAct.getDayCountFraction(zonedDate, _underlyingSwap.getFixedLeg().getNthPayment(0).getAccrualStartDate());
    final FixedCouponSwap<Payment> swap = _underlyingSwap.toDerivative(date, yieldCurveNames);
    //Implementation remark: SwapFixedIbor can not be used as the first coupon may have fixed already and one CouponIbor is now fixed.
    return new CouponCMS(getCurrency(), paymentTime, getPaymentYearFraction(), getNotional(), fixingTime, swap, settlementTime);
  }

  @Override
  public <U, V> V accept(final FixedIncomeInstrumentDefinitionVisitor<U, V> visitor, final U data) {
    return visitor.visitCouponCMS(this, data);
  }

  @Override
  public <V> V accept(final FixedIncomeInstrumentDefinitionVisitor<?, V> visitor) {
    return visitor.visitCouponCMS(this);
  }

}
