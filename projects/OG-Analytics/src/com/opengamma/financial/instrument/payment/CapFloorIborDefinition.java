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

import org.apache.commons.lang.Validate;

import com.opengamma.financial.convention.daycount.DayCount;
import com.opengamma.financial.convention.daycount.DayCountFactory;
import com.opengamma.financial.instrument.index.IborIndex;
import com.opengamma.financial.interestrate.payments.CapFloorIbor;
import com.opengamma.financial.interestrate.payments.CouponFixed;
import com.opengamma.financial.interestrate.payments.Payment;
import com.opengamma.util.money.Currency;

/**
 * Class describing a caplet/floorlet on Ibor. The notional is positive for long the option and negative for short the option.
 */
public class CapFloorIborDefinition extends CouponIborDefinition implements CapFloor {

  /**
   * The cap/floor strike.
   */
  private final double _strike;
  /**
   * The cap (true) / floor (false) flag.
   */
  private final boolean _isCap;

  /**
   * Constructor from all the cap/floor details.
   * @param currency The payment currency.
   * @param paymentDate Coupon payment date.
   * @param accrualStartDate Start date of the accrual period.
   * @param accrualEndDate End date of the accrual period.
   * @param accrualFactor Accrual factor of the accrual period.
   * @param notional Coupon notional.
   * @param fixingDate The coupon fixing date.
   * @param index The coupon Ibor index. The index currency should be the same as the payment currency.
   * @param strike The strike
   * @param isCap The cap/floor flag.
   */
  public CapFloorIborDefinition(final Currency currency, final ZonedDateTime paymentDate, final ZonedDateTime accrualStartDate, final ZonedDateTime accrualEndDate, final double accrualFactor,
      final double notional,
      final ZonedDateTime fixingDate, final IborIndex index, final double strike, final boolean isCap) {
    super(currency, paymentDate, accrualStartDate, accrualEndDate, accrualFactor, notional, fixingDate, index);
    Validate.isTrue(currency == index.getCurrency(), "payment currency should be same as the index currency");
    _strike = strike;
    _isCap = isCap;
  }

  /**
   * Builder from all the cap/floor details.
   * @param paymentDate Coupon payment date.
   * @param accrualStartDate Start date of the accrual period.
   * @param accrualEndDate End date of the accrual period.
   * @param accrualFactor Accrual factor of the accrual period.
   * @param notional Coupon notional.
   * @param fixingDate The coupon fixing date.
   * @param index The coupon Ibor index.
   * @param strike The strike
   * @param isCap The cap/floor flag.
   * @return The cap/floor.
   */
  public static CapFloorIborDefinition from(final ZonedDateTime paymentDate, final ZonedDateTime accrualStartDate, final ZonedDateTime accrualEndDate, final double accrualFactor,
      final double notional, final ZonedDateTime fixingDate,
      final IborIndex index, final double strike, final boolean isCap) {
    Validate.notNull(index, "index");
    return new CapFloorIborDefinition(index.getCurrency(), paymentDate, accrualStartDate, accrualEndDate, accrualFactor, notional, fixingDate, index, strike, isCap);
  }

  /**
   * Builder from a Ibor coupon the cap/floor strike and isCap flag.
   * @param couponIbor The underlying Ibor coupon.
   * @param strike The strike
   * @param isCap The cap/floor flag.
   * @return The cap/floor
   */
  public static CapFloorIborDefinition from(final CouponIborDefinition couponIbor, final double strike, final boolean isCap) {
    Validate.notNull(couponIbor, "coupon Ibor");
    return new CapFloorIborDefinition(couponIbor.getCurrency(), couponIbor.getPaymentDate(), couponIbor.getAccrualStartDate(), couponIbor.getAccrualEndDate(), couponIbor.getPaymentYearFraction(),
        couponIbor.getNotional(), couponIbor.getFixingDate(), couponIbor.getIndex(), strike, isCap);
  }

  /**
   * Builder from notional, fixing date, index, strike and cap flag.
   * @param notional Coupon notional.
   * @param fixingDate The coupon fixing date.
   * @param index The coupon Ibor index.
   * @param strike The strike
   * @param isCap The cap/floor flag.
   * @return The cap/floor
   */
  public static CapFloorIborDefinition from(final double notional, final ZonedDateTime fixingDate, final IborIndex index, final double strike, final boolean isCap) {
    return from(CouponIborDefinition.from(notional, fixingDate, index), strike, isCap);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double geStrike() {
    return _strike;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isCap() {
    return _isCap;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public double payOff(final double fixing) {
    final double omega = (_isCap) ? 1.0 : -1.0;
    return Math.max(omega * (fixing - _strike), 0);
  }

  @Override
  public Payment toDerivative(final LocalDate date, final String... yieldCurveNames) {
    Validate.notNull(date, "date");
    Validate.notNull(yieldCurveNames, "yield curve names");
    Validate.isTrue(yieldCurveNames.length > 1, "at least one curve required");
    Validate.isTrue(!date.isAfter(getPaymentDate().toLocalDate()), "date is after payment date");
    final DayCount actAct = DayCountFactory.INSTANCE.getDayCount("Actual/Actual ISDA");
    final String fundingCurveName = yieldCurveNames[0];
    final String forwardCurveName = yieldCurveNames[1];
    final ZonedDateTime zonedDate = ZonedDateTime.of(LocalDateTime.ofMidnight(date), TimeZone.UTC);
    final double paymentTime = actAct.getDayCountFraction(zonedDate, getPaymentDate());
    if (isFixed()) { // The Ibor cap/floor has already fixed, it is now a fixed coupon.
      return new CouponFixed(getCurrency(), paymentTime, fundingCurveName, getPaymentYearFraction(), getNotional(), payOff(getFixedRate()));
    }
    // Ibor is not fixed yet, all the details are required.
    final double fixingTime = actAct.getDayCountFraction(zonedDate, getFixingDate());
    final double fixingPeriodStartTime = actAct.getDayCountFraction(zonedDate, getFixindPeriodStartDate());
    final double fixingPeriodEndTime = actAct.getDayCountFraction(zonedDate, getFixindPeriodEndDate());
    //TODO: Definition has no spread and time version has one: to be standardized.
    return new CapFloorIbor(getCurrency(), paymentTime, fundingCurveName, getPaymentYearFraction(), getNotional(), fixingTime, fixingPeriodStartTime, fixingPeriodEndTime,
          getFixingPeriodAccrualFactor(), forwardCurveName, _strike, _isCap);
  }

  @Override
  public String toString() {
    return super.toString() + ", IsCap = " + _isCap + ", Strike = " + _strike;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (_isCap ? 1231 : 1237);
    long temp;
    temp = Double.doubleToLongBits(_strike);
    result = prime * result + (int) (temp ^ (temp >>> 32));
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
    final CapFloorIborDefinition other = (CapFloorIborDefinition) obj;
    if (_isCap != other._isCap) {
      return false;
    }
    if (Double.doubleToLongBits(_strike) != Double.doubleToLongBits(other._strike)) {
      return false;
    }
    return true;
  }

}
