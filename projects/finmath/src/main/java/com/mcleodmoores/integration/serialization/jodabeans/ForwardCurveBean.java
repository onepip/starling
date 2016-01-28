/**
 * Copyright (C) 2014 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.serialization.jodabeans;

import java.util.Map;

import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

/**
 * Bean for Finmath {@link net.finmath.marketdata.model.curves.ForwardCurve}.
 */
@BeanDefinition
public class ForwardCurveBean extends AbstractForwardCurveBean {

  /** The serialization version. */
  private static final long serialVersionUID = 1L;


  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ForwardCurveBean}.
   * @return the meta-bean, not null
   */
  public static ForwardCurveBean.Meta meta() {
    return ForwardCurveBean.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ForwardCurveBean.Meta.INSTANCE);
  }

  @Override
  public ForwardCurveBean.Meta metaBean() {
    return ForwardCurveBean.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  @Override
  public ForwardCurveBean clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("ForwardCurveBean{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  @Override
  protected void toString(StringBuilder buf) {
    super.toString(buf);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ForwardCurveBean}.
   */
  public static class Meta extends AbstractForwardCurveBean.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap());

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    public BeanBuilder<? extends ForwardCurveBean> builder() {
      return new DirectBeanBuilder<ForwardCurveBean>(new ForwardCurveBean());
    }

    @Override
    public Class<? extends ForwardCurveBean> beanType() {
      return ForwardCurveBean.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
