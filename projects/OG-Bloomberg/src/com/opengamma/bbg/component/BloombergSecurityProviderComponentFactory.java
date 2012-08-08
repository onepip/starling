/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.bbg.component;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.bbg.BloombergSecurityProvider;
import com.opengamma.bbg.ReferenceDataProvider;
import com.opengamma.component.factory.provider.SecurityProviderComponentFactory;
import com.opengamma.financial.timeseries.exchange.DefaultExchangeDataProvider;
import com.opengamma.financial.timeseries.exchange.ExchangeDataProvider;

/**
 * Component factory for the Bloomberg security provider.
 */
@BeanDefinition
public class BloombergSecurityProviderComponentFactory extends SecurityProviderComponentFactory {

  /**
   * The Bloomberg reference data.
   */
  @PropertyDefinition(validate = "notNull")
  private ReferenceDataProvider _bloombergReferenceData;

  //-------------------------------------------------------------------------
  /**
   * Creates the provider.
   * 
   * @return the provider, not null
   */
  @Override
  protected BloombergSecurityProvider initSecurityProvider() {
    ExchangeDataProvider exchangeDataProvider = initExchangeDataProvider();
    return new BloombergSecurityProvider(getBloombergReferenceData(), exchangeDataProvider);
  }

  protected ExchangeDataProvider initExchangeDataProvider() {
    return new DefaultExchangeDataProvider();
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code BloombergSecurityProviderComponentFactory}.
   * @return the meta-bean, not null
   */
  public static BloombergSecurityProviderComponentFactory.Meta meta() {
    return BloombergSecurityProviderComponentFactory.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(BloombergSecurityProviderComponentFactory.Meta.INSTANCE);
  }

  @Override
  public BloombergSecurityProviderComponentFactory.Meta metaBean() {
    return BloombergSecurityProviderComponentFactory.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1886112538:  // bloombergReferenceData
        return getBloombergReferenceData();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 1886112538:  // bloombergReferenceData
        setBloombergReferenceData((ReferenceDataProvider) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_bloombergReferenceData, "bloombergReferenceData");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      BloombergSecurityProviderComponentFactory other = (BloombergSecurityProviderComponentFactory) obj;
      return JodaBeanUtils.equal(getBloombergReferenceData(), other.getBloombergReferenceData()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash += hash * 31 + JodaBeanUtils.hashCode(getBloombergReferenceData());
    return hash ^ super.hashCode();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the Bloomberg reference data.
   * @return the value of the property, not null
   */
  public ReferenceDataProvider getBloombergReferenceData() {
    return _bloombergReferenceData;
  }

  /**
   * Sets the Bloomberg reference data.
   * @param bloombergReferenceData  the new value of the property, not null
   */
  public void setBloombergReferenceData(ReferenceDataProvider bloombergReferenceData) {
    JodaBeanUtils.notNull(bloombergReferenceData, "bloombergReferenceData");
    this._bloombergReferenceData = bloombergReferenceData;
  }

  /**
   * Gets the the {@code bloombergReferenceData} property.
   * @return the property, not null
   */
  public final Property<ReferenceDataProvider> bloombergReferenceData() {
    return metaBean().bloombergReferenceData().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code BloombergSecurityProviderComponentFactory}.
   */
  public static class Meta extends SecurityProviderComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code bloombergReferenceData} property.
     */
    private final MetaProperty<ReferenceDataProvider> _bloombergReferenceData = DirectMetaProperty.ofReadWrite(
        this, "bloombergReferenceData", BloombergSecurityProviderComponentFactory.class, ReferenceDataProvider.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
      this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "bloombergReferenceData");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 1886112538:  // bloombergReferenceData
          return _bloombergReferenceData;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends BloombergSecurityProviderComponentFactory> builder() {
      return new DirectBeanBuilder<BloombergSecurityProviderComponentFactory>(new BloombergSecurityProviderComponentFactory());
    }

    @Override
    public Class<? extends BloombergSecurityProviderComponentFactory> beanType() {
      return BloombergSecurityProviderComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code bloombergReferenceData} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ReferenceDataProvider> bloombergReferenceData() {
      return _bloombergReferenceData;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
