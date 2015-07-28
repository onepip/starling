/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.batch.domain;

import java.util.Map;

import org.joda.beans.Bean;
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

/**
 * Data model for a failure reason.
 */
@BeanDefinition
public class FailureReason extends DirectBean {

  @PropertyDefinition
  private long _id;

  @PropertyDefinition
  private RiskFailure _riskFailure;

  @PropertyDefinition
  private long _computeFailureId;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code FailureReason}.
   * @return the meta-bean, not null
   */
  public static FailureReason.Meta meta() {
    return FailureReason.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(FailureReason.Meta.INSTANCE);
  }

  @Override
  public FailureReason.Meta metaBean() {
    return FailureReason.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the id.
   * @return the value of the property
   */
  public long getId() {
    return _id;
  }

  /**
   * Sets the id.
   * @param id  the new value of the property
   */
  public void setId(long id) {
    this._id = id;
  }

  /**
   * Gets the the {@code id} property.
   * @return the property, not null
   */
  public final Property<Long> id() {
    return metaBean().id().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the riskFailure.
   * @return the value of the property
   */
  public RiskFailure getRiskFailure() {
    return _riskFailure;
  }

  /**
   * Sets the riskFailure.
   * @param riskFailure  the new value of the property
   */
  public void setRiskFailure(RiskFailure riskFailure) {
    this._riskFailure = riskFailure;
  }

  /**
   * Gets the the {@code riskFailure} property.
   * @return the property, not null
   */
  public final Property<RiskFailure> riskFailure() {
    return metaBean().riskFailure().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the computeFailureId.
   * @return the value of the property
   */
  public long getComputeFailureId() {
    return _computeFailureId;
  }

  /**
   * Sets the computeFailureId.
   * @param computeFailureId  the new value of the property
   */
  public void setComputeFailureId(long computeFailureId) {
    this._computeFailureId = computeFailureId;
  }

  /**
   * Gets the the {@code computeFailureId} property.
   * @return the property, not null
   */
  public final Property<Long> computeFailureId() {
    return metaBean().computeFailureId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public FailureReason clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      FailureReason other = (FailureReason) obj;
      return (getId() == other.getId()) &&
          JodaBeanUtils.equal(getRiskFailure(), other.getRiskFailure()) &&
          (getComputeFailureId() == other.getComputeFailureId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getRiskFailure());
    hash = hash * 31 + JodaBeanUtils.hashCode(getComputeFailureId());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("FailureReason{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("id").append('=').append(JodaBeanUtils.toString(getId())).append(',').append(' ');
    buf.append("riskFailure").append('=').append(JodaBeanUtils.toString(getRiskFailure())).append(',').append(' ');
    buf.append("computeFailureId").append('=').append(JodaBeanUtils.toString(getComputeFailureId())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code FailureReason}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code id} property.
     */
    private final MetaProperty<Long> _id = DirectMetaProperty.ofReadWrite(
        this, "id", FailureReason.class, Long.TYPE);
    /**
     * The meta-property for the {@code riskFailure} property.
     */
    private final MetaProperty<RiskFailure> _riskFailure = DirectMetaProperty.ofReadWrite(
        this, "riskFailure", FailureReason.class, RiskFailure.class);
    /**
     * The meta-property for the {@code computeFailureId} property.
     */
    private final MetaProperty<Long> _computeFailureId = DirectMetaProperty.ofReadWrite(
        this, "computeFailureId", FailureReason.class, Long.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "riskFailure",
        "computeFailureId");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return _id;
        case -1471715397:  // riskFailure
          return _riskFailure;
        case 1803609550:  // computeFailureId
          return _computeFailureId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends FailureReason> builder() {
      return new DirectBeanBuilder<FailureReason>(new FailureReason());
    }

    @Override
    public Class<? extends FailureReason> beanType() {
      return FailureReason.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code id} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> id() {
      return _id;
    }

    /**
     * The meta-property for the {@code riskFailure} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<RiskFailure> riskFailure() {
      return _riskFailure;
    }

    /**
     * The meta-property for the {@code computeFailureId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Long> computeFailureId() {
      return _computeFailureId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((FailureReason) bean).getId();
        case -1471715397:  // riskFailure
          return ((FailureReason) bean).getRiskFailure();
        case 1803609550:  // computeFailureId
          return ((FailureReason) bean).getComputeFailureId();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          ((FailureReason) bean).setId((Long) newValue);
          return;
        case -1471715397:  // riskFailure
          ((FailureReason) bean).setRiskFailure((RiskFailure) newValue);
          return;
        case 1803609550:  // computeFailureId
          ((FailureReason) bean).setComputeFailureId((Long) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
