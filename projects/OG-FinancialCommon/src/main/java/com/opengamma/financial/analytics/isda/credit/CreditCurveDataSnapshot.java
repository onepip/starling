/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.financial.analytics.isda.credit;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.ImmutableMap;
import com.opengamma.core.marketdatasnapshot.NamedSnapshot;
import com.opengamma.id.UniqueId;

/**
 * A snapshot containing credit curve market data. This defines a set of credit curves 
 * which can be bootstrapped and used in a pricing environment. Curves are indexed
 * using {@link CreditCurveDataKey}s.
 */
@BeanDefinition
public final class CreditCurveDataSnapshot implements NamedSnapshot, ImmutableBean {
  
  /**
   * The unique id of the snapshot.
   */
  @PropertyDefinition
  private final UniqueId _uniqueId;
  
  /**
   * The name of the snapshot.
   */
  @PropertyDefinition(validate = "notNull")
  private final String _name;
  
  /**
   * The full set of credit curves defined by the snapshot.
   */
  @PropertyDefinition(validate = "notNull")
  private final ImmutableMap<CreditCurveDataKey, CreditCurveData> _creditCurves;
  
  @Override
  public NamedSnapshot withUniqueId(UniqueId uniqueId) {
    return new Builder(this).uniqueId(uniqueId).build();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code CreditCurveDataSnapshot}.
   * @return the meta-bean, not null
   */
  public static CreditCurveDataSnapshot.Meta meta() {
    return CreditCurveDataSnapshot.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(CreditCurveDataSnapshot.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static CreditCurveDataSnapshot.Builder builder() {
    return new CreditCurveDataSnapshot.Builder();
  }

  private CreditCurveDataSnapshot(
      UniqueId uniqueId,
      String name,
      Map<CreditCurveDataKey, CreditCurveData> creditCurves) {
    JodaBeanUtils.notNull(name, "name");
    JodaBeanUtils.notNull(creditCurves, "creditCurves");
    this._uniqueId = uniqueId;
    this._name = name;
    this._creditCurves = ImmutableMap.copyOf(creditCurves);
  }

  @Override
  public CreditCurveDataSnapshot.Meta metaBean() {
    return CreditCurveDataSnapshot.Meta.INSTANCE;
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
   * Gets the unique id of the snapshot.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the snapshot.
   * @return the value of the property, not null
   */
  public String getName() {
    return _name;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the full set of credit curves defined by the snapshot.
   * @return the value of the property, not null
   */
  public ImmutableMap<CreditCurveDataKey, CreditCurveData> getCreditCurves() {
    return _creditCurves;
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
      CreditCurveDataSnapshot other = (CreditCurveDataSnapshot) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getCreditCurves(), other.getCreditCurves());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getName());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCreditCurves());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("CreditCurveDataSnapshot{");
    buf.append("uniqueId").append('=').append(getUniqueId()).append(',').append(' ');
    buf.append("name").append('=').append(getName()).append(',').append(' ');
    buf.append("creditCurves").append('=').append(JodaBeanUtils.toString(getCreditCurves()));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code CreditCurveDataSnapshot}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofImmutable(
        this, "uniqueId", CreditCurveDataSnapshot.class, UniqueId.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofImmutable(
        this, "name", CreditCurveDataSnapshot.class, String.class);
    /**
     * The meta-property for the {@code creditCurves} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<ImmutableMap<CreditCurveDataKey, CreditCurveData>> _creditCurves = DirectMetaProperty.ofImmutable(
        this, "creditCurves", CreditCurveDataSnapshot.class, (Class) ImmutableMap.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "name",
        "creditCurves");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case -1612130883:  // creditCurves
          return _creditCurves;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public CreditCurveDataSnapshot.Builder builder() {
      return new CreditCurveDataSnapshot.Builder();
    }

    @Override
    public Class<? extends CreditCurveDataSnapshot> beanType() {
      return CreditCurveDataSnapshot.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code uniqueId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code creditCurves} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ImmutableMap<CreditCurveDataKey, CreditCurveData>> creditCurves() {
      return _creditCurves;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((CreditCurveDataSnapshot) bean).getUniqueId();
        case 3373707:  // name
          return ((CreditCurveDataSnapshot) bean).getName();
        case -1612130883:  // creditCurves
          return ((CreditCurveDataSnapshot) bean).getCreditCurves();
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
   * The bean-builder for {@code CreditCurveDataSnapshot}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<CreditCurveDataSnapshot> {

    private UniqueId _uniqueId;
    private String _name;
    private Map<CreditCurveDataKey, CreditCurveData> _creditCurves = ImmutableMap.of();

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(CreditCurveDataSnapshot beanToCopy) {
      this._uniqueId = beanToCopy.getUniqueId();
      this._name = beanToCopy.getName();
      this._creditCurves = beanToCopy.getCreditCurves();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case 3373707:  // name
          return _name;
        case -1612130883:  // creditCurves
          return _creditCurves;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          this._uniqueId = (UniqueId) newValue;
          break;
        case 3373707:  // name
          this._name = (String) newValue;
          break;
        case -1612130883:  // creditCurves
          this._creditCurves = (Map<CreditCurveDataKey, CreditCurveData>) newValue;
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
    public CreditCurveDataSnapshot build() {
      return new CreditCurveDataSnapshot(
          _uniqueId,
          _name,
          _creditCurves);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the unique id of the snapshot.
     * @param uniqueId  the new value
     * @return this, for chaining, not null
     */
    public Builder uniqueId(UniqueId uniqueId) {
      this._uniqueId = uniqueId;
      return this;
    }

    /**
     * Sets the name of the snapshot.
     * @param name  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder name(String name) {
      JodaBeanUtils.notNull(name, "name");
      this._name = name;
      return this;
    }

    /**
     * Sets the full set of credit curves defined by the snapshot.
     * @param creditCurves  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder creditCurves(Map<CreditCurveDataKey, CreditCurveData> creditCurves) {
      JodaBeanUtils.notNull(creditCurves, "creditCurves");
      this._creditCurves = creditCurves;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(128);
      buf.append("CreditCurveDataSnapshot.Builder{");
      buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(_uniqueId)).append(',').append(' ');
      buf.append("name").append('=').append(JodaBeanUtils.toString(_name)).append(',').append(' ');
      buf.append("creditCurves").append('=').append(JodaBeanUtils.toString(_creditCurves));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
