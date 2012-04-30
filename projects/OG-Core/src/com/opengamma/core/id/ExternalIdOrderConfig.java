/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core.id;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBean;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.google.common.collect.Maps;
import com.opengamma.id.ExternalScheme;

/**
 * Class to hold configuration of custom display ordering of external ids.
 */
@BeanDefinition
public class ExternalIdOrderConfig extends DirectBean {
  @PropertyDefinition(validate = "notNull")
  private Map<ExternalScheme, Integer> _rateMap;
  /**
   * Default config object.  Do not use directly.
   */
  public static final ExternalIdOrderConfig DEFAULT_CONFIG = new ExternalIdOrderConfig();
  
  private static Map<ExternalScheme, Integer> s_defaultScoreMap = Maps.newHashMap();
  
  static {
    s_defaultScoreMap.put(ExternalSchemes.BLOOMBERG_TCM, 20); // Because if there's both ticker and TCM, you want to see TCM.
    s_defaultScoreMap.put(ExternalSchemes.BLOOMBERG_TICKER, 19);
    s_defaultScoreMap.put(ExternalSchemes.RIC, 17);
    s_defaultScoreMap.put(ExternalSchemes.BLOOMBERG_TICKER_WEAK, 16);
    s_defaultScoreMap.put(ExternalSchemes.ACTIVFEED_TICKER, 15);
    s_defaultScoreMap.put(ExternalSchemes.SURF, 14);
    s_defaultScoreMap.put(ExternalSchemes.ISIN, 13);
    s_defaultScoreMap.put(ExternalSchemes.CUSIP, 12);
    s_defaultScoreMap.put(ExternalSchemes.SEDOL1, 11);
    s_defaultScoreMap.put(ExternalSchemes.OG_SYNTHETIC_TICKER, 10);
    s_defaultScoreMap.put(ExternalSchemes.BLOOMBERG_BUID, 5);
    s_defaultScoreMap.put(ExternalSchemes.BLOOMBERG_BUID_WEAK, 4);
    DEFAULT_CONFIG.setRateMap(s_defaultScoreMap);
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExternalIdOrderConfig}.
   * @return the meta-bean, not null
   */
  public static ExternalIdOrderConfig.Meta meta() {
    return ExternalIdOrderConfig.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(ExternalIdOrderConfig.Meta.INSTANCE);
  }

  @Override
  public ExternalIdOrderConfig.Meta metaBean() {
    return ExternalIdOrderConfig.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 983446620:  // rateMap
        return getRateMap();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case 983446620:  // rateMap
        setRateMap((Map<ExternalScheme, Integer>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_rateMap, "rateMap");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExternalIdOrderConfig other = (ExternalIdOrderConfig) obj;
      return JodaBeanUtils.equal(getRateMap(), other.getRateMap());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getRateMap());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the rateMap.
   * @return the value of the property, not null
   */
  public Map<ExternalScheme, Integer> getRateMap() {
    return _rateMap;
  }

  /**
   * Sets the rateMap.
   * @param rateMap  the new value of the property, not null
   */
  public void setRateMap(Map<ExternalScheme, Integer> rateMap) {
    JodaBeanUtils.notNull(rateMap, "rateMap");
    this._rateMap = rateMap;
  }

  /**
   * Gets the the {@code rateMap} property.
   * @return the property, not null
   */
  public final Property<Map<ExternalScheme, Integer>> rateMap() {
    return metaBean().rateMap().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExternalIdOrderConfig}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code rateMap} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Map<ExternalScheme, Integer>> _rateMap = DirectMetaProperty.ofReadWrite(
        this, "rateMap", ExternalIdOrderConfig.class, (Class) Map.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "rateMap");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 983446620:  // rateMap
          return _rateMap;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExternalIdOrderConfig> builder() {
      return new DirectBeanBuilder<ExternalIdOrderConfig>(new ExternalIdOrderConfig());
    }

    @Override
    public Class<? extends ExternalIdOrderConfig> beanType() {
      return ExternalIdOrderConfig.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code rateMap} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Map<ExternalScheme, Integer>> rateMap() {
      return _rateMap;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
