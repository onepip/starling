/**
 * Copyright (C) 2016 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.starling.client.portfolio.fpml5_8;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableValidator;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.util.money.Currency;

/**
 * An object containing information about an FX cross. It defines the two currencies and the basis for the quote order e.g.
 * EUR/USD, quoted as USD/EUR. The two currencies must be different.
 */
@BeanDefinition
public class QuotedCurrencyPair implements ImmutableBean {

  /**
   * The first currency.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency _currency1;

  /**
   * The second currency.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency _currency2;

  /**
   * The quote basis; currency1/currency2 or currency2/currency1.
   */
  @PropertyDefinition(validate = "notNull")
  private final QuoteBasis _quoteBasis;

  /**
   * Ensures that the two currencies are different.
   */
  @ImmutableValidator
  private void validate() {
    if (_currency1.equals(_currency2)) {
      throw new IllegalArgumentException("Currencies in quoted currency pair must be different: have " + _currency1 + " on both sides");
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code QuotedCurrencyPair}.
   * @return the meta-bean, not null
   */
  public static QuotedCurrencyPair.Meta meta() {
    return QuotedCurrencyPair.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(QuotedCurrencyPair.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static QuotedCurrencyPair.Builder builder() {
    return new QuotedCurrencyPair.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected QuotedCurrencyPair(QuotedCurrencyPair.Builder builder) {
    JodaBeanUtils.notNull(builder._currency1, "currency1");
    JodaBeanUtils.notNull(builder._currency2, "currency2");
    JodaBeanUtils.notNull(builder._quoteBasis, "quoteBasis");
    this._currency1 = builder._currency1;
    this._currency2 = builder._currency2;
    this._quoteBasis = builder._quoteBasis;
    validate();
  }

  @Override
  public QuotedCurrencyPair.Meta metaBean() {
    return QuotedCurrencyPair.Meta.INSTANCE;
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
   * Gets the first currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency1() {
    return _currency1;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the second currency.
   * @return the value of the property, not null
   */
  public Currency getCurrency2() {
    return _currency2;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the quote basis; currency1/currency2 or currency2/currency1.
   * @return the value of the property, not null
   */
  public QuoteBasis getQuoteBasis() {
    return _quoteBasis;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      QuotedCurrencyPair other = (QuotedCurrencyPair) obj;
      return JodaBeanUtils.equal(getCurrency1(), other.getCurrency1()) &&
          JodaBeanUtils.equal(getCurrency2(), other.getCurrency2()) &&
          JodaBeanUtils.equal(getQuoteBasis(), other.getQuoteBasis());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency1());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCurrency2());
    hash = hash * 31 + JodaBeanUtils.hashCode(getQuoteBasis());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("QuotedCurrencyPair{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("currency1").append('=').append(JodaBeanUtils.toString(getCurrency1())).append(',').append(' ');
    buf.append("currency2").append('=').append(JodaBeanUtils.toString(getCurrency2())).append(',').append(' ');
    buf.append("quoteBasis").append('=').append(JodaBeanUtils.toString(getQuoteBasis())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code QuotedCurrencyPair}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code currency1} property.
     */
    private final MetaProperty<Currency> _currency1 = DirectMetaProperty.ofImmutable(
        this, "currency1", QuotedCurrencyPair.class, Currency.class);
    /**
     * The meta-property for the {@code currency2} property.
     */
    private final MetaProperty<Currency> _currency2 = DirectMetaProperty.ofImmutable(
        this, "currency2", QuotedCurrencyPair.class, Currency.class);
    /**
     * The meta-property for the {@code quoteBasis} property.
     */
    private final MetaProperty<QuoteBasis> _quoteBasis = DirectMetaProperty.ofImmutable(
        this, "quoteBasis", QuotedCurrencyPair.class, QuoteBasis.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "currency1",
        "currency2",
        "quoteBasis");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 657592896:  // currency1
          return _currency1;
        case 657592897:  // currency2
          return _currency2;
        case 1255166754:  // quoteBasis
          return _quoteBasis;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public QuotedCurrencyPair.Builder builder() {
      return new QuotedCurrencyPair.Builder();
    }

    @Override
    public Class<? extends QuotedCurrencyPair> beanType() {
      return QuotedCurrencyPair.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code currency1} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency1() {
      return _currency1;
    }

    /**
     * The meta-property for the {@code currency2} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Currency> currency2() {
      return _currency2;
    }

    /**
     * The meta-property for the {@code quoteBasis} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<QuoteBasis> quoteBasis() {
      return _quoteBasis;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 657592896:  // currency1
          return ((QuotedCurrencyPair) bean).getCurrency1();
        case 657592897:  // currency2
          return ((QuotedCurrencyPair) bean).getCurrency2();
        case 1255166754:  // quoteBasis
          return ((QuotedCurrencyPair) bean).getQuoteBasis();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code QuotedCurrencyPair}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<QuotedCurrencyPair> {

    private Currency _currency1;
    private Currency _currency2;
    private QuoteBasis _quoteBasis;

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(QuotedCurrencyPair beanToCopy) {
      this._currency1 = beanToCopy.getCurrency1();
      this._currency2 = beanToCopy.getCurrency2();
      this._quoteBasis = beanToCopy.getQuoteBasis();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 657592896:  // currency1
          return _currency1;
        case 657592897:  // currency2
          return _currency2;
        case 1255166754:  // quoteBasis
          return _quoteBasis;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 657592896:  // currency1
          this._currency1 = (Currency) newValue;
          break;
        case 657592897:  // currency2
          this._currency2 = (Currency) newValue;
          break;
        case 1255166754:  // quoteBasis
          this._quoteBasis = (QuoteBasis) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public QuotedCurrencyPair build() {
      return new QuotedCurrencyPair(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the first currency.
     * @param currency1  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder currency1(Currency currency1) {
      JodaBeanUtils.notNull(currency1, "currency1");
      this._currency1 = currency1;
      return this;
    }

    /**
     * Sets the second currency.
     * @param currency2  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder currency2(Currency currency2) {
      JodaBeanUtils.notNull(currency2, "currency2");
      this._currency2 = currency2;
      return this;
    }

    /**
     * Sets the quote basis; currency1/currency2 or currency2/currency1.
     * @param quoteBasis  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder quoteBasis(QuoteBasis quoteBasis) {
      JodaBeanUtils.notNull(quoteBasis, "quoteBasis");
      this._quoteBasis = quoteBasis;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("QuotedCurrencyPair.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("currency1").append('=').append(JodaBeanUtils.toString(_currency1)).append(',').append(' ');
      buf.append("currency2").append('=').append(JodaBeanUtils.toString(_currency2)).append(',').append(' ');
      buf.append("quoteBasis").append('=').append(JodaBeanUtils.toString(_quoteBasis)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
