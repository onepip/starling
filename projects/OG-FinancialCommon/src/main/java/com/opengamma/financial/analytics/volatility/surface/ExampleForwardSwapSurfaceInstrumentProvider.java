/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.volatility.surface;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.LocalDate;
import org.threeten.bp.Period;

import com.opengamma.core.id.ExternalSchemes;
import com.opengamma.core.value.MarketDataRequirementNames;
import com.opengamma.id.ExternalId;
import com.opengamma.util.time.Tenor;

/**
 * Generates {@link ExternalId}s for the forward swap rate tickers in the simulated market
 * data fields.
 */
@BeanDefinition
public class ExampleForwardSwapSurfaceInstrumentProvider implements Bean, SurfaceInstrumentProvider<Tenor, Tenor>, Serializable {

  /** The serialization version */
  private static final long serialVersionUID = 1L;

  /**
   * Month prefixes.
   */
  private static final Map<Tenor, String> MONTHS;

  /**
   * The instrument type.
   */
  private static final String TYPE = "FS";

  /**
   * The ticker postfix.
   */
  private static final String POSTFIX = "STRIKE";

  static {
    MONTHS = new HashMap<>();
    MONTHS.put(Tenor.ONE_MONTH, "A");
    MONTHS.put(Tenor.TWO_MONTHS, "B");
    MONTHS.put(Tenor.THREE_MONTHS, "C");
    MONTHS.put(Tenor.FOUR_MONTHS, "D");
    MONTHS.put(Tenor.FIVE_MONTHS, "E");
    MONTHS.put(Tenor.SIX_MONTHS, "F");
    MONTHS.put(Tenor.SEVEN_MONTHS, "G");
    MONTHS.put(Tenor.EIGHT_MONTHS, "H");
    MONTHS.put(Tenor.NINE_MONTHS, "I");
    MONTHS.put(Tenor.TEN_MONTHS, "J");
    MONTHS.put(Tenor.ELEVEN_MONTHS, "K");
    MONTHS.put(Tenor.ofMonths(15), "1C");
    MONTHS.put(Tenor.ofMonths(18), "1F");
  }
  /**
   * The country prefix.
   */
  @PropertyDefinition(validate = "notNull")
  private String _country;

  /**
   * For the builder.
   */
  /* package */ ExampleForwardSwapSurfaceInstrumentProvider() {
  }

  /**
   * @param country The country, not null
   */
  public ExampleForwardSwapSurfaceInstrumentProvider(final String country) {
    setCountry(country);
  }


  @Override
  public ExternalId getInstrument(final Tenor xAxis, final Tenor yAxis) {
    final String xString, yString;
    final Period xPeriod = xAxis.getPeriod();
    final int xMonths = xPeriod.getMonths();
    final int xYears = xPeriod.getYears();
    final Period yPeriod = yAxis.getPeriod();
    final int yMonths = yPeriod.getMonths();
    final int yYears = yPeriod.getYears();
    if (xMonths != 0 && xYears == 0) {
      if (MONTHS.containsKey(xAxis)) {
        xString = xMonths < 12 ? "0" + MONTHS.get(xAxis) : MONTHS.get(xAxis);
      } else {
        throw new IllegalArgumentException("Cannot get string for " + xAxis);
      }
    } else if (xYears != 0) {
      xString = xYears < 10 ? "0" + Integer.toString(xYears) : Integer.toString(xYears);
    } else {
      throw new IllegalArgumentException("Cannot generate code for " + xAxis);
    }
    if (yMonths != 0 && yYears == 0) {
      if (MONTHS.containsKey(yAxis)) {
        yString = MONTHS.get(yAxis);
      } else {
        throw new IllegalArgumentException("Cannot get string for " + yAxis);
      }
    } else if (yYears != 0) {
      yString = Integer.toString(yYears);
    } else {
      throw new IllegalArgumentException("Cannot generate code for " + xAxis);
    }
    final StringBuilder sb = new StringBuilder(_country);
    sb.append(TYPE);
    sb.append(xString);
    sb.append(yString);
    sb.append(POSTFIX);
    return ExternalSchemes.syntheticSecurityId(sb.toString());
  }

  @Override
  public ExternalId getInstrument(final Tenor xAxis, final Tenor yAxis, final LocalDate surfaceDate) {
    return getInstrument(xAxis, yAxis);
  }

  @Override
  public String getDataFieldName() {
    return MarketDataRequirementNames.MARKET_VALUE;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExampleForwardSwapSurfaceInstrumentProvider}.
   * @return the meta-bean, not null
   */
  public static ExampleForwardSwapSurfaceInstrumentProvider.Meta meta() {
    return ExampleForwardSwapSurfaceInstrumentProvider.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ExampleForwardSwapSurfaceInstrumentProvider.Meta.INSTANCE);
  }

  @Override
  public ExampleForwardSwapSurfaceInstrumentProvider.Meta metaBean() {
    return ExampleForwardSwapSurfaceInstrumentProvider.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the country prefix.
   * @return the value of the property, not null
   */
  public String getCountry() {
    return _country;
  }

  /**
   * Sets the country prefix.
   * @param country  the new value of the property, not null
   */
  public void setCountry(String country) {
    JodaBeanUtils.notNull(country, "country");
    this._country = country;
  }

  /**
   * Gets the the {@code country} property.
   * @return the property, not null
   */
  public final Property<String> country() {
    return metaBean().country().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ExampleForwardSwapSurfaceInstrumentProvider clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExampleForwardSwapSurfaceInstrumentProvider other = (ExampleForwardSwapSurfaceInstrumentProvider) obj;
      return JodaBeanUtils.equal(getCountry(), other.getCountry());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getCountry());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("ExampleForwardSwapSurfaceInstrumentProvider{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("country").append('=').append(JodaBeanUtils.toString(getCountry())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExampleForwardSwapSurfaceInstrumentProvider}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code country} property.
     */
    private final MetaProperty<String> _country = DirectMetaProperty.ofReadWrite(
        this, "country", ExampleForwardSwapSurfaceInstrumentProvider.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "country");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 957831062:  // country
          return _country;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExampleForwardSwapSurfaceInstrumentProvider> builder() {
      return new DirectBeanBuilder<ExampleForwardSwapSurfaceInstrumentProvider>(new ExampleForwardSwapSurfaceInstrumentProvider());
    }

    @Override
    public Class<? extends ExampleForwardSwapSurfaceInstrumentProvider> beanType() {
      return ExampleForwardSwapSurfaceInstrumentProvider.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code country} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> country() {
      return _country;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 957831062:  // country
          return ((ExampleForwardSwapSurfaceInstrumentProvider) bean).getCountry();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 957831062:  // country
          ((ExampleForwardSwapSurfaceInstrumentProvider) bean).setCountry((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ExampleForwardSwapSurfaceInstrumentProvider) bean)._country, "country");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
