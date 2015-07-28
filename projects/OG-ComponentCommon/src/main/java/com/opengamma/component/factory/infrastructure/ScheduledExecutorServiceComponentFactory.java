/**
 * Copyright (C) 2013 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.infrastructure;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;

/**
 * Component Factory for a shared ScheduledExecutorService.
 * <p>
 * A reasonable default thread name prefix will be used if it is not provided.
 * <p>
 * This class is designed to allow protected methods to be overridden.
 */
@BeanDefinition
public class ScheduledExecutorServiceComponentFactory extends AbstractComponentFactory {

  /**
   * The default thread prefix.
   */
  private static final String DEFAULT_THREAD_PREFIX = "StandardInfra-";

  /**
   * The classifier that the factory should publish under.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The thread prefix to use in the executor.
   */
  @PropertyDefinition(validate = "notNull")
  private String _threadNamePrefix = DEFAULT_THREAD_PREFIX;

  //-------------------------------------------------------------------------
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) throws Exception {
    ScheduledExecutorService component = createScheduledExecutorService(repo);
    ComponentInfo info = new ComponentInfo(ScheduledExecutorService.class, getClassifier());
    repo.registerComponent(info, component);
  }

  /**
   * Creates the executor service without registering it.
   * 
   * @param repo  the component repository, only used to register secondary items like lifecycle, not null
   * @return the executor service, not null
   */
  protected ScheduledExecutorService createScheduledExecutorService(ComponentRepository repo) {
    ScheduledExecutorFactoryBean factoryBean = new ScheduledExecutorFactoryBean();
    factoryBean.setThreadNamePrefix(getThreadNamePrefix());
    factoryBean.afterPropertiesSet();
    return factoryBean.getObject();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ScheduledExecutorServiceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ScheduledExecutorServiceComponentFactory.Meta meta() {
    return ScheduledExecutorServiceComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ScheduledExecutorServiceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ScheduledExecutorServiceComponentFactory.Meta metaBean() {
    return ScheduledExecutorServiceComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the classifier that the factory should publish under.
   * @return the value of the property, not null
   */
  public String getClassifier() {
    return _classifier;
  }

  /**
   * Sets the classifier that the factory should publish under.
   * @param classifier  the new value of the property, not null
   */
  public void setClassifier(String classifier) {
    JodaBeanUtils.notNull(classifier, "classifier");
    this._classifier = classifier;
  }

  /**
   * Gets the the {@code classifier} property.
   * @return the property, not null
   */
  public final Property<String> classifier() {
    return metaBean().classifier().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the thread prefix to use in the executor.
   * @return the value of the property, not null
   */
  public String getThreadNamePrefix() {
    return _threadNamePrefix;
  }

  /**
   * Sets the thread prefix to use in the executor.
   * @param threadNamePrefix  the new value of the property, not null
   */
  public void setThreadNamePrefix(String threadNamePrefix) {
    JodaBeanUtils.notNull(threadNamePrefix, "threadNamePrefix");
    this._threadNamePrefix = threadNamePrefix;
  }

  /**
   * Gets the the {@code threadNamePrefix} property.
   * @return the property, not null
   */
  public final Property<String> threadNamePrefix() {
    return metaBean().threadNamePrefix().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ScheduledExecutorServiceComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ScheduledExecutorServiceComponentFactory other = (ScheduledExecutorServiceComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          JodaBeanUtils.equal(getThreadNamePrefix(), other.getThreadNamePrefix()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(getThreadNamePrefix());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("ScheduledExecutorServiceComponentFactory{");
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
    buf.append("classifier").append('=').append(JodaBeanUtils.toString(getClassifier())).append(',').append(' ');
    buf.append("threadNamePrefix").append('=').append(JodaBeanUtils.toString(getThreadNamePrefix())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ScheduledExecutorServiceComponentFactory}.
   */
  public static class Meta extends AbstractComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code classifier} property.
     */
    private final MetaProperty<String> _classifier = DirectMetaProperty.ofReadWrite(
        this, "classifier", ScheduledExecutorServiceComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code threadNamePrefix} property.
     */
    private final MetaProperty<String> _threadNamePrefix = DirectMetaProperty.ofReadWrite(
        this, "threadNamePrefix", ScheduledExecutorServiceComponentFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "threadNamePrefix");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return _classifier;
        case -1101780313:  // threadNamePrefix
          return _threadNamePrefix;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ScheduledExecutorServiceComponentFactory> builder() {
      return new DirectBeanBuilder<ScheduledExecutorServiceComponentFactory>(new ScheduledExecutorServiceComponentFactory());
    }

    @Override
    public Class<? extends ScheduledExecutorServiceComponentFactory> beanType() {
      return ScheduledExecutorServiceComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code classifier} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> classifier() {
      return _classifier;
    }

    /**
     * The meta-property for the {@code threadNamePrefix} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> threadNamePrefix() {
      return _threadNamePrefix;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((ScheduledExecutorServiceComponentFactory) bean).getClassifier();
        case -1101780313:  // threadNamePrefix
          return ((ScheduledExecutorServiceComponentFactory) bean).getThreadNamePrefix();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((ScheduledExecutorServiceComponentFactory) bean).setClassifier((String) newValue);
          return;
        case -1101780313:  // threadNamePrefix
          ((ScheduledExecutorServiceComponentFactory) bean).setThreadNamePrefix((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ScheduledExecutorServiceComponentFactory) bean)._classifier, "classifier");
      JodaBeanUtils.notNull(((ScheduledExecutorServiceComponentFactory) bean)._threadNamePrefix, "threadNamePrefix");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
