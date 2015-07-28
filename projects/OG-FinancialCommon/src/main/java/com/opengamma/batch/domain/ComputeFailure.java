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
 * Data model for a compute failure.
 */
@BeanDefinition
public class ComputeFailure extends DirectBean {

  @PropertyDefinition
  private long _id = -1;

  @PropertyDefinition
  private String _functionId;

  @PropertyDefinition
  private String _exceptionClass;

  @PropertyDefinition
  private String _exceptionMsg;

  @PropertyDefinition
  private String _stackTrace;

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ComputeFailure}.
   * @return the meta-bean, not null
   */
  public static ComputeFailure.Meta meta() {
    return ComputeFailure.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ComputeFailure.Meta.INSTANCE);
  }

  @Override
  public ComputeFailure.Meta metaBean() {
    return ComputeFailure.Meta.INSTANCE;
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
   * Gets the functionId.
   * @return the value of the property
   */
  public String getFunctionId() {
    return _functionId;
  }

  /**
   * Sets the functionId.
   * @param functionId  the new value of the property
   */
  public void setFunctionId(String functionId) {
    this._functionId = functionId;
  }

  /**
   * Gets the the {@code functionId} property.
   * @return the property, not null
   */
  public final Property<String> functionId() {
    return metaBean().functionId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exceptionClass.
   * @return the value of the property
   */
  public String getExceptionClass() {
    return _exceptionClass;
  }

  /**
   * Sets the exceptionClass.
   * @param exceptionClass  the new value of the property
   */
  public void setExceptionClass(String exceptionClass) {
    this._exceptionClass = exceptionClass;
  }

  /**
   * Gets the the {@code exceptionClass} property.
   * @return the property, not null
   */
  public final Property<String> exceptionClass() {
    return metaBean().exceptionClass().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the exceptionMsg.
   * @return the value of the property
   */
  public String getExceptionMsg() {
    return _exceptionMsg;
  }

  /**
   * Sets the exceptionMsg.
   * @param exceptionMsg  the new value of the property
   */
  public void setExceptionMsg(String exceptionMsg) {
    this._exceptionMsg = exceptionMsg;
  }

  /**
   * Gets the the {@code exceptionMsg} property.
   * @return the property, not null
   */
  public final Property<String> exceptionMsg() {
    return metaBean().exceptionMsg().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the stackTrace.
   * @return the value of the property
   */
  public String getStackTrace() {
    return _stackTrace;
  }

  /**
   * Sets the stackTrace.
   * @param stackTrace  the new value of the property
   */
  public void setStackTrace(String stackTrace) {
    this._stackTrace = stackTrace;
  }

  /**
   * Gets the the {@code stackTrace} property.
   * @return the property, not null
   */
  public final Property<String> stackTrace() {
    return metaBean().stackTrace().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ComputeFailure clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ComputeFailure other = (ComputeFailure) obj;
      return (getId() == other.getId()) &&
          JodaBeanUtils.equal(getFunctionId(), other.getFunctionId()) &&
          JodaBeanUtils.equal(getExceptionClass(), other.getExceptionClass()) &&
          JodaBeanUtils.equal(getExceptionMsg(), other.getExceptionMsg()) &&
          JodaBeanUtils.equal(getStackTrace(), other.getStackTrace());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getFunctionId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getExceptionClass());
    hash = hash * 31 + JodaBeanUtils.hashCode(getExceptionMsg());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStackTrace());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("ComputeFailure{");
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
    buf.append("functionId").append('=').append(JodaBeanUtils.toString(getFunctionId())).append(',').append(' ');
    buf.append("exceptionClass").append('=').append(JodaBeanUtils.toString(getExceptionClass())).append(',').append(' ');
    buf.append("exceptionMsg").append('=').append(JodaBeanUtils.toString(getExceptionMsg())).append(',').append(' ');
    buf.append("stackTrace").append('=').append(JodaBeanUtils.toString(getStackTrace())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ComputeFailure}.
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
        this, "id", ComputeFailure.class, Long.TYPE);
    /**
     * The meta-property for the {@code functionId} property.
     */
    private final MetaProperty<String> _functionId = DirectMetaProperty.ofReadWrite(
        this, "functionId", ComputeFailure.class, String.class);
    /**
     * The meta-property for the {@code exceptionClass} property.
     */
    private final MetaProperty<String> _exceptionClass = DirectMetaProperty.ofReadWrite(
        this, "exceptionClass", ComputeFailure.class, String.class);
    /**
     * The meta-property for the {@code exceptionMsg} property.
     */
    private final MetaProperty<String> _exceptionMsg = DirectMetaProperty.ofReadWrite(
        this, "exceptionMsg", ComputeFailure.class, String.class);
    /**
     * The meta-property for the {@code stackTrace} property.
     */
    private final MetaProperty<String> _stackTrace = DirectMetaProperty.ofReadWrite(
        this, "stackTrace", ComputeFailure.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "id",
        "functionId",
        "exceptionClass",
        "exceptionMsg",
        "stackTrace");

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
        case -62789869:  // functionId
          return _functionId;
        case -71056791:  // exceptionClass
          return _exceptionClass;
        case -268220238:  // exceptionMsg
          return _exceptionMsg;
        case 2026279837:  // stackTrace
          return _stackTrace;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ComputeFailure> builder() {
      return new DirectBeanBuilder<ComputeFailure>(new ComputeFailure());
    }

    @Override
    public Class<? extends ComputeFailure> beanType() {
      return ComputeFailure.class;
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
     * The meta-property for the {@code functionId} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> functionId() {
      return _functionId;
    }

    /**
     * The meta-property for the {@code exceptionClass} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> exceptionClass() {
      return _exceptionClass;
    }

    /**
     * The meta-property for the {@code exceptionMsg} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> exceptionMsg() {
      return _exceptionMsg;
    }

    /**
     * The meta-property for the {@code stackTrace} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> stackTrace() {
      return _stackTrace;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          return ((ComputeFailure) bean).getId();
        case -62789869:  // functionId
          return ((ComputeFailure) bean).getFunctionId();
        case -71056791:  // exceptionClass
          return ((ComputeFailure) bean).getExceptionClass();
        case -268220238:  // exceptionMsg
          return ((ComputeFailure) bean).getExceptionMsg();
        case 2026279837:  // stackTrace
          return ((ComputeFailure) bean).getStackTrace();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 3355:  // id
          ((ComputeFailure) bean).setId((Long) newValue);
          return;
        case -62789869:  // functionId
          ((ComputeFailure) bean).setFunctionId((String) newValue);
          return;
        case -71056791:  // exceptionClass
          ((ComputeFailure) bean).setExceptionClass((String) newValue);
          return;
        case -268220238:  // exceptionMsg
          ((ComputeFailure) bean).setExceptionMsg((String) newValue);
          return;
        case 2026279837:  // stackTrace
          ((ComputeFailure) bean).setStackTrace((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
