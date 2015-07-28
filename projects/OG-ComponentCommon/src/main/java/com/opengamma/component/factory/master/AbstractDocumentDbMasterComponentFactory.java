/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import static com.opengamma.component.factory.master.DBMasterComponentUtils.isValidJmsConfiguration;

import java.util.Map;

import org.joda.beans.Bean;
import org.joda.beans.BeanBuilder;
import org.joda.beans.BeanDefinition;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.core.change.JmsChangeManager;
import com.opengamma.master.impl.AbstractRemoteMaster;
import com.opengamma.masterdb.ConfigurableDbChangeProvidingMaster;
import com.opengamma.util.jms.JmsConnector;

/**
 * Component factory delegate for document-based masters.
 * @param <I> the master interface to use. this must be a super-interface of M and would typically
 * extend from AbstractDbMaster. (Not stated explicitly due to limitation in Joda-beans).
 * @param <M> the db master type
 */
@BeanDefinition
public abstract class AbstractDocumentDbMasterComponentFactory<I, M extends ConfigurableDbChangeProvidingMaster> extends AbstractDbMasterComponentFactory<I, M> {

  /**
   * Whether to use change management. If true, requires jms settings to be non-null.
   */
  @PropertyDefinition
  private boolean _enableChangeManagement = true;
  
  /**
   * The JMS connector.
   */
  @PropertyDefinition
  private JmsConnector _jmsConnector;
  
  /**
   * The JMS change manager topic.
   */
  @PropertyDefinition
  private String _jmsChangeManagerTopic;
  
  
  public AbstractDocumentDbMasterComponentFactory(String schemaName, Class<I> masterInterface) {
    super(schemaName, masterInterface);
  }

  @Override
  protected M createMaster(ComponentRepository repo, ComponentInfo info) throws Exception {
    M master = createDbDocumentMaster();
    
    if (isEnableChangeManagement() && isValidJmsConfiguration(getClassifier(), getClass(), getJmsConnector(), getJmsChangeManagerTopic())) {
      JmsChangeManager cm = new JmsChangeManager(getJmsConnector(), getJmsChangeManagerTopic());
      master.setChangeManager(cm);
      repo.registerLifecycle(cm);
      if (getJmsConnector().getClientBrokerUri() != null) {
        info.addAttribute(ComponentInfoAttributes.JMS_BROKER_URI, getJmsConnector().getClientBrokerUri().toString());
      }
      info.addAttribute(ComponentInfoAttributes.JMS_CHANGE_MANAGER_TOPIC, getJmsChangeManagerTopic());
    }
    
    return master;
  }

  protected abstract M createDbDocumentMaster() throws Exception;

    
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code AbstractDocumentDbMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  @SuppressWarnings("rawtypes")
  public static AbstractDocumentDbMasterComponentFactory.Meta meta() {
    return AbstractDocumentDbMasterComponentFactory.Meta.INSTANCE;
  }

  /**
   * The meta-bean for {@code AbstractDocumentDbMasterComponentFactory}.
   * @param <R>  the first generic type
   * @param <S>  the second generic type
   * @param cls1  the first generic type
   * @param cls2  the second generic type
   * @return the meta-bean, not null
   */
  @SuppressWarnings("unchecked")
  public static <R, S extends ConfigurableDbChangeProvidingMaster> AbstractDocumentDbMasterComponentFactory.Meta<R, S> metaAbstractDocumentDbMasterComponentFactory(Class<R> cls1, Class<S> cls2) {
    return AbstractDocumentDbMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(AbstractDocumentDbMasterComponentFactory.Meta.INSTANCE);
  }

  @SuppressWarnings("unchecked")
  @Override
  public AbstractDocumentDbMasterComponentFactory.Meta<I, M> metaBean() {
    return AbstractDocumentDbMasterComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to use change management. If true, requires jms settings to be non-null.
   * @return the value of the property
   */
  public boolean isEnableChangeManagement() {
    return _enableChangeManagement;
  }

  /**
   * Sets whether to use change management. If true, requires jms settings to be non-null.
   * @param enableChangeManagement  the new value of the property
   */
  public void setEnableChangeManagement(boolean enableChangeManagement) {
    this._enableChangeManagement = enableChangeManagement;
  }

  /**
   * Gets the the {@code enableChangeManagement} property.
   * @return the property, not null
   */
  public final Property<Boolean> enableChangeManagement() {
    return metaBean().enableChangeManagement().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS connector.
   * @return the value of the property
   */
  public JmsConnector getJmsConnector() {
    return _jmsConnector;
  }

  /**
   * Sets the JMS connector.
   * @param jmsConnector  the new value of the property
   */
  public void setJmsConnector(JmsConnector jmsConnector) {
    this._jmsConnector = jmsConnector;
  }

  /**
   * Gets the the {@code jmsConnector} property.
   * @return the property, not null
   */
  public final Property<JmsConnector> jmsConnector() {
    return metaBean().jmsConnector().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the JMS change manager topic.
   * @return the value of the property
   */
  public String getJmsChangeManagerTopic() {
    return _jmsChangeManagerTopic;
  }

  /**
   * Sets the JMS change manager topic.
   * @param jmsChangeManagerTopic  the new value of the property
   */
  public void setJmsChangeManagerTopic(String jmsChangeManagerTopic) {
    this._jmsChangeManagerTopic = jmsChangeManagerTopic;
  }

  /**
   * Gets the the {@code jmsChangeManagerTopic} property.
   * @return the property, not null
   */
  public final Property<String> jmsChangeManagerTopic() {
    return metaBean().jmsChangeManagerTopic().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      AbstractDocumentDbMasterComponentFactory<?, ?> other = (AbstractDocumentDbMasterComponentFactory<?, ?>) obj;
      return (isEnableChangeManagement() == other.isEnableChangeManagement()) &&
          JodaBeanUtils.equal(getJmsConnector(), other.getJmsConnector()) &&
          JodaBeanUtils.equal(getJmsChangeManagerTopic(), other.getJmsChangeManagerTopic()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(isEnableChangeManagement());
    hash = hash * 31 + JodaBeanUtils.hashCode(getJmsConnector());
    hash = hash * 31 + JodaBeanUtils.hashCode(getJmsChangeManagerTopic());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("AbstractDocumentDbMasterComponentFactory{");
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
    buf.append("enableChangeManagement").append('=').append(JodaBeanUtils.toString(isEnableChangeManagement())).append(',').append(' ');
    buf.append("jmsConnector").append('=').append(JodaBeanUtils.toString(getJmsConnector())).append(',').append(' ');
    buf.append("jmsChangeManagerTopic").append('=').append(JodaBeanUtils.toString(getJmsChangeManagerTopic())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code AbstractDocumentDbMasterComponentFactory}.
   * @param <I>  the type
   * @param <M>  the type
   */
  public static class Meta<I, M extends ConfigurableDbChangeProvidingMaster> extends AbstractDbMasterComponentFactory.Meta<I, M> {
    /**
     * The singleton instance of the meta-bean.
     */
    @SuppressWarnings("rawtypes")
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code enableChangeManagement} property.
     */
    private final MetaProperty<Boolean> _enableChangeManagement = DirectMetaProperty.ofReadWrite(
        this, "enableChangeManagement", AbstractDocumentDbMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code jmsConnector} property.
     */
    private final MetaProperty<JmsConnector> _jmsConnector = DirectMetaProperty.ofReadWrite(
        this, "jmsConnector", AbstractDocumentDbMasterComponentFactory.class, JmsConnector.class);
    /**
     * The meta-property for the {@code jmsChangeManagerTopic} property.
     */
    private final MetaProperty<String> _jmsChangeManagerTopic = DirectMetaProperty.ofReadWrite(
        this, "jmsChangeManagerTopic", AbstractDocumentDbMasterComponentFactory.class, String.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "enableChangeManagement",
        "jmsConnector",
        "jmsChangeManagerTopic");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 981110710:  // enableChangeManagement
          return _enableChangeManagement;
        case -1495762275:  // jmsConnector
          return _jmsConnector;
        case -758086398:  // jmsChangeManagerTopic
          return _jmsChangeManagerTopic;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends AbstractDocumentDbMasterComponentFactory<I, M>> builder() {
      throw new UnsupportedOperationException("AbstractDocumentDbMasterComponentFactory is an abstract class");
    }

    @SuppressWarnings({"unchecked", "rawtypes" })
    @Override
    public Class<? extends AbstractDocumentDbMasterComponentFactory<I, M>> beanType() {
      return (Class) AbstractDocumentDbMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code enableChangeManagement} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> enableChangeManagement() {
      return _enableChangeManagement;
    }

    /**
     * The meta-property for the {@code jmsConnector} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<JmsConnector> jmsConnector() {
      return _jmsConnector;
    }

    /**
     * The meta-property for the {@code jmsChangeManagerTopic} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> jmsChangeManagerTopic() {
      return _jmsChangeManagerTopic;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 981110710:  // enableChangeManagement
          return ((AbstractDocumentDbMasterComponentFactory<?, ?>) bean).isEnableChangeManagement();
        case -1495762275:  // jmsConnector
          return ((AbstractDocumentDbMasterComponentFactory<?, ?>) bean).getJmsConnector();
        case -758086398:  // jmsChangeManagerTopic
          return ((AbstractDocumentDbMasterComponentFactory<?, ?>) bean).getJmsChangeManagerTopic();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 981110710:  // enableChangeManagement
          ((AbstractDocumentDbMasterComponentFactory<I, M>) bean).setEnableChangeManagement((Boolean) newValue);
          return;
        case -1495762275:  // jmsConnector
          ((AbstractDocumentDbMasterComponentFactory<I, M>) bean).setJmsConnector((JmsConnector) newValue);
          return;
        case -758086398:  // jmsChangeManagerTopic
          ((AbstractDocumentDbMasterComponentFactory<I, M>) bean).setJmsChangeManagerTopic((String) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
