/**
 * Copyright (C) 2017 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.util.money;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.regex.Pattern;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableConstructor;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.convert.FromString;

import com.opengamma.id.ObjectId;
import com.opengamma.id.ObjectIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdentifiable;
import com.opengamma.util.ArgumentChecker;

/**
 *
 */
@BeanDefinition(builderScope = "private")
public final class OrderedCurrencyPair implements ImmutableBean, UniqueIdentifiable, ObjectIdentifiable, Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * The scheme to use in the identifiers.
   */
  public static final String OBJECT_SCHEME = "OrderedCurrencyPair";

  /**
   * The first currency.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency _firstCurrency;
  /**
   * The second currency.
   */
  @PropertyDefinition(validate = "notNull")
  private final Currency _secondCurrency;
  private final String _idValue;

  public static OrderedCurrencyPair of(final Currency ccy1, final Currency ccy2) {
    return new OrderedCurrencyPair(ccy1, ccy2);
  }

  public static OrderedCurrencyPair of(final UniqueId uniqueId) {
    ArgumentChecker.notNull(uniqueId, "uniqueId");
    if (uniqueId.getScheme().equals(OBJECT_SCHEME)) {
      final Pattern validate = Pattern.compile("[A-Z]{6}");
      final String value = uniqueId.getValue();
      if (validate.matcher(value).matches()) {
        final Currency ccy1 = Currency.of(value.substring(0, 3));
        final Currency ccy2 = Currency.of(value.substring(3));
        return new OrderedCurrencyPair(ccy1, ccy2);
      }
    }
    throw new IllegalArgumentException("Cannot create an OrderedCurrencyPair from this "
        + "UniqueId; " + uniqueId.getScheme());
  }

  @FromString
  public static OrderedCurrencyPair parse(final String string) {
    ArgumentChecker.notNull(string, "string");
    ArgumentChecker.isTrue(string.length() == 6, "Unable to parse OrderedCurrencyPair, invalid format: {}", string);
    try {
      final Currency cur1 = Currency.parse(string.substring(0, 3));
      final Currency cur2 = Currency.parse(string.substring(3));
      return new OrderedCurrencyPair(cur1, cur2);
    } catch (final RuntimeException ex) {
      throw new IllegalArgumentException("Unable to parse string: " + string, ex);
    }
  }

  @ImmutableConstructor
  private OrderedCurrencyPair(final Currency ccy1, final Currency ccy2) {
    _firstCurrency = ArgumentChecker.notNull(ccy1, "ccy1");
    _secondCurrency = ArgumentChecker.notNull(ccy2, "ccy2");
    _idValue = _firstCurrency.getCode() + _secondCurrency.getCode();
  }

  @Override
  public ObjectId getObjectId() {
    return ObjectId.of(OBJECT_SCHEME, _idValue);
  }

  @Override
  public UniqueId getUniqueId() {
    return UniqueId.of(OBJECT_SCHEME, _idValue);
  }

  public boolean isInverse(final OrderedCurrencyPair other) {
    ArgumentChecker.notNull(other, "other");
    return other._firstCurrency.equals(_secondCurrency) && other._secondCurrency.equals(_firstCurrency);
  }

  public OrderedCurrencyPair getInverse() {
    return new OrderedCurrencyPair(_secondCurrency, _firstCurrency);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code OrderedCurrencyPair}.
   * @return the meta-bean, not null
   */
  public static OrderedCurrencyPair.Meta meta() {
    return OrderedCurrencyPair.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(OrderedCurrencyPair.Meta.INSTANCE);
  }

  @Override
  public OrderedCurrencyPair.Meta metaBean() {
    return OrderedCurrencyPair.Meta.INSTANCE;
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
  public Currency getFirstCurrency() {
    return _firstCurrency;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the second currency.
   * @return the value of the property, not null
   */
  public Currency getSecondCurrency() {
    return _secondCurrency;
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      OrderedCurrencyPair other = (OrderedCurrencyPair) obj;
      return JodaBeanUtils.equal(getFirstCurrency(), other.getFirstCurrency()) &&
          JodaBeanUtils.equal(getSecondCurrency(), other.getSecondCurrency());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getFirstCurrency());
    hash = hash * 31 + JodaBeanUtils.hashCode(getSecondCurrency());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("OrderedCurrencyPair{");
    buf.append("firstCurrency").append('=').append(getFirstCurrency()).append(',').append(' ');
    buf.append("secondCurrency").append('=').append(JodaBeanUtils.toString(getSecondCurrency()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code OrderedCurrencyPair}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code firstCurrency} property.
     */
    private final MetaProperty<Currency> _firstCurrency = DirectMetaProperty.ofImmutable(
        this, "firstCurrency", OrderedCurrencyPair.class, Currency.class);
    /**
     * The meta-property for the {@code secondCurrency} property.
     */
    private final MetaProperty<Currency> _secondCurrency = DirectMetaProperty.ofImmutable(
        this, "secondCurrency", OrderedCurrencyPair.class, Currency.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "firstCurrency",
        "secondCurrency");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1878034719:  // firstCurrency
          return _firstCurrency;
        case 564126885:  // secondCurrency
          return _secondCurrency;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends OrderedCurrencyPair> builder() {
      return new OrderedCurrencyPair.Builder();
    }

    @Override
    public Class<? extends OrderedCurrencyPair> beanType() {
      return OrderedCurrencyPair.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code firstCurrency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> firstCurrency() {
      return _firstCurrency;
    }

    /**
     * The meta-property for the {@code secondCurrency} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Currency> secondCurrency() {
      return _secondCurrency;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1878034719:  // firstCurrency
          return ((OrderedCurrencyPair) bean).getFirstCurrency();
        case 564126885:  // secondCurrency
          return ((OrderedCurrencyPair) bean).getSecondCurrency();
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
   * The bean-builder for {@code OrderedCurrencyPair}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<OrderedCurrencyPair> {

    private Currency _firstCurrency;
    private Currency _secondCurrency;

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1878034719:  // firstCurrency
          return _firstCurrency;
        case 564126885:  // secondCurrency
          return _secondCurrency;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -1878034719:  // firstCurrency
          this._firstCurrency = (Currency) newValue;
          break;
        case 564126885:  // secondCurrency
          this._secondCurrency = (Currency) newValue;
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
    public OrderedCurrencyPair build() {
      return new OrderedCurrencyPair(
          _firstCurrency,
          _secondCurrency);
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(96);
      buf.append("OrderedCurrencyPair.Builder{");
      buf.append("firstCurrency").append('=').append(JodaBeanUtils.toString(_firstCurrency)).append(',').append(' ');
      buf.append("secondCurrency").append('=').append(JodaBeanUtils.toString(_secondCurrency));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
