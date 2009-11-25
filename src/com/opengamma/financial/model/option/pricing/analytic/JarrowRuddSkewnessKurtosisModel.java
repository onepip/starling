/**
 * Copyright (C) 2009 - 2009 by OpenGamma Inc.
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.model.option.pricing.analytic;

import com.opengamma.financial.model.option.definition.EuropeanVanillaOptionDefinition;
import com.opengamma.financial.model.option.definition.OptionDefinition;
import com.opengamma.financial.model.option.definition.SkewKurtosisOptionDataBundle;
import com.opengamma.financial.model.option.definition.StandardOptionDataBundle;
import com.opengamma.math.function.Function1D;

/**
 * 
 * @author emcleod
 */

public class JarrowRuddSkewnessKurtosisModel extends AnalyticOptionModel<OptionDefinition, SkewKurtosisOptionDataBundle> {
  protected final AnalyticOptionModel<OptionDefinition, StandardOptionDataBundle> _bsm = new BlackScholesMertonModel();

  @Override
  public Function1D<SkewKurtosisOptionDataBundle, Double> getPricingFunction(final OptionDefinition definition) {
    if (definition == null)
      throw new IllegalArgumentException("Definition was null");
    final Function1D<SkewKurtosisOptionDataBundle, Double> pricingFunction = new Function1D<SkewKurtosisOptionDataBundle, Double>() {

      @Override
      public Double evaluate(final SkewKurtosisOptionDataBundle data) {
        if (data == null)
          throw new IllegalArgumentException("Data bundle was null");
        final double s = data.getSpot();
        final double k = definition.getStrike();
        final double t = definition.getTimeToExpiry(data.getDate());
        final double sigma = data.getVolatility(t, k);
        final double r = data.getInterestRate(t);
        final double b = data.getCostOfCarry();
        final double skew = data.getAnnualizedSkew();
        final double kurtosis = data.getAnnualizedFisherKurtosis();
        final OptionDefinition callDefinition = definition.isCall() ? definition : new EuropeanVanillaOptionDefinition(k, definition.getExpiry(), true);
        final Function1D<StandardOptionDataBundle, Double> bsm = _bsm.getPricingFunction(callDefinition);
        final double bsmCall = bsm.evaluate(data);
        final double d2 = getD2(getD1(s, k, t, sigma, b), sigma, t);
        final double a = getA(d2, k, sigma, t);
        final double call = bsmCall + getLambda1(sigma, t, skew) * getQ3(s, k, sigma, t, r, a, d2) + getLambda2(sigma, t, kurtosis) * getQ4(s, k, sigma, t, r, a, d2);
        if (!definition.isCall())
          return call - s * Math.exp((b - r) * t) + k * Math.exp(-r * t);
        return call;
      }
    };
    return pricingFunction;
  }

  double getA(final double d2, final double k, final double sigma, final double t) {
    return Math.exp(-d2 * d2 / 2.) / (k * sigma * Math.sqrt(2 * Math.PI * t));
  }

  double getLambda1(final double sigma, final double t, final double skew) {
    final double q = Math.sqrt(Math.exp(sigma * sigma * t) - 1);
    final double skewDistribution = q * (3 + q * q);
    return skew - skewDistribution;
  }

  double getLambda2(final double sigma, final double t, final double kurtosis) {
    final double q = Math.sqrt(Math.exp(sigma * sigma * t) - 1);
    final double q2 = q * q;
    final double q4 = q2 * q2;
    final double q6 = q4 * q2;
    final double q8 = q6 * q2;
    final double kurtosisDistribution = 16 * q2 + 15 * q4 + 6 * q6 + q8;
    return kurtosis - kurtosisDistribution;
  }

  double getQ3(final double s, final double k, final double sigma, final double t, final double r, final double a, final double d2) {
    final double sigmaT = sigma * Math.sqrt(t);
    final double da = a * (d2 - sigmaT) / (k * sigmaT);
    final double df = Math.exp(-r * t);
    return -Math.pow(s * df, 3) * Math.pow(Math.exp(sigmaT * sigmaT - 1), 1.5) * df * da / 6.;
  }

  double getQ4(final double s, final double k, final double sigma, final double t, final double r, final double a, final double d2) {
    final double sigmaT = sigma * Math.sqrt(t);
    final double sigmaTSq = sigma * sigma * t;
    final double x = d2 - sigmaT;
    final double da2 = a * (x * x - sigmaT * x - 1) / (k * k * sigmaTSq);
    final double df = Math.exp(-r * t);
    return Math.pow(s * df, 4) * Math.pow(Math.exp(sigmaTSq) - 1, 2) * df * da2 / 24.;
  }
}
