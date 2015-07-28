/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.source;

import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.ehcache.CacheManager;

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

import com.opengamma.component.ComponentInfo;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.AbstractComponentFactory;
import com.opengamma.component.factory.ComponentInfoAttributes;
import com.opengamma.core.exchange.ExchangeSource;
import com.opengamma.core.exchange.impl.DataExchangeSourceResource;
import com.opengamma.core.exchange.impl.RemoteExchangeSource;
import com.opengamma.master.exchange.ExchangeMaster;
import com.opengamma.master.exchange.impl.EHCachingExchangeSource;
import com.opengamma.master.exchange.impl.MasterExchangeSource;

/**
 * Component factory providing the {@code ExchangeSource}.
 */
@BeanDefinition
public class ExchangeSourceComponentFactory extends AbstractComponentFactory {

  /**
   * The classifier that the factory should publish under.
   */
  @PropertyDefinition(validate = "notNull")
  private String _classifier;
  /**
   * The flag determining whether the component should be published by REST (default true).
   */
  @PropertyDefinition
  private boolean _publishRest = true;
  /**
   * The cache manager.
   */
  @PropertyDefinition
  private CacheManager _cacheManager;
  /**
   * The underlying exchange master.
   */
  @PropertyDefinition(validate = "notNull")
  private ExchangeMaster _exchangeMaster;

  //-------------------------------------------------------------------------
  /**
   * Initializes the exchange source, setting up component information and REST.
   * Override using {@link #createExchangeSource(ComponentRepository)}.
   * 
   * @param repo  the component repository, not null
   * @param configuration  the remaining configuration, not null
   */
  @Override
  public void init(ComponentRepository repo, LinkedHashMap<String, String> configuration) {
    ExchangeSource source = createExchangeSource(repo);
    
    ComponentInfo info = new ComponentInfo(ExchangeSource.class, getClassifier());
    info.addAttribute(ComponentInfoAttributes.LEVEL, 1);
    if (isPublishRest()) {
      info.addAttribute(ComponentInfoAttributes.REMOTE_CLIENT_JAVA, RemoteExchangeSource.class);
    }
    repo.registerComponent(info, source);
    if (isPublishRest()) {
      repo.getRestComponents().publish(info, new DataExchangeSourceResource(source));
    }
  }

  /**
   * Creates the exchange source without registering it.
   * 
   * @param repo  the component repository, only used to register secondary items like lifecycle, not null
   * @return the exchange source, not null
   */
  protected ExchangeSource createExchangeSource(ComponentRepository repo) {
    ExchangeSource source = new MasterExchangeSource(getExchangeMaster());
    if (getCacheManager() != null) {
      source = new EHCachingExchangeSource(source, getCacheManager());
    }
    return source;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ExchangeSourceComponentFactory}.
   * @return the meta-bean, not null
   */
  public static ExchangeSourceComponentFactory.Meta meta() {
    return ExchangeSourceComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ExchangeSourceComponentFactory.Meta.INSTANCE);
  }

  @Override
  public ExchangeSourceComponentFactory.Meta metaBean() {
    return ExchangeSourceComponentFactory.Meta.INSTANCE;
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
   * Gets the flag determining whether the component should be published by REST (default true).
   * @return the value of the property
   */
  public boolean isPublishRest() {
    return _publishRest;
  }

  /**
   * Sets the flag determining whether the component should be published by REST (default true).
   * @param publishRest  the new value of the property
   */
  public void setPublishRest(boolean publishRest) {
    this._publishRest = publishRest;
  }

  /**
   * Gets the the {@code publishRest} property.
   * @return the property, not null
   */
  public final Property<Boolean> publishRest() {
    return metaBean().publishRest().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the cache manager.
   * @return the value of the property
   */
  public CacheManager getCacheManager() {
    return _cacheManager;
  }

  /**
   * Sets the cache manager.
   * @param cacheManager  the new value of the property
   */
  public void setCacheManager(CacheManager cacheManager) {
    this._cacheManager = cacheManager;
  }

  /**
   * Gets the the {@code cacheManager} property.
   * @return the property, not null
   */
  public final Property<CacheManager> cacheManager() {
    return metaBean().cacheManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying exchange master.
   * @return the value of the property, not null
   */
  public ExchangeMaster getExchangeMaster() {
    return _exchangeMaster;
  }

  /**
   * Sets the underlying exchange master.
   * @param exchangeMaster  the new value of the property, not null
   */
  public void setExchangeMaster(ExchangeMaster exchangeMaster) {
    JodaBeanUtils.notNull(exchangeMaster, "exchangeMaster");
    this._exchangeMaster = exchangeMaster;
  }

  /**
   * Gets the the {@code exchangeMaster} property.
   * @return the property, not null
   */
  public final Property<ExchangeMaster> exchangeMaster() {
    return metaBean().exchangeMaster().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public ExchangeSourceComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      ExchangeSourceComponentFactory other = (ExchangeSourceComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          (isPublishRest() == other.isPublishRest()) &&
          JodaBeanUtils.equal(getCacheManager(), other.getCacheManager()) &&
          JodaBeanUtils.equal(getExchangeMaster(), other.getExchangeMaster()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(isPublishRest());
    hash = hash * 31 + JodaBeanUtils.hashCode(getCacheManager());
    hash = hash * 31 + JodaBeanUtils.hashCode(getExchangeMaster());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("ExchangeSourceComponentFactory{");
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
    buf.append("publishRest").append('=').append(JodaBeanUtils.toString(isPublishRest())).append(',').append(' ');
    buf.append("cacheManager").append('=').append(JodaBeanUtils.toString(getCacheManager())).append(',').append(' ');
    buf.append("exchangeMaster").append('=').append(JodaBeanUtils.toString(getExchangeMaster())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ExchangeSourceComponentFactory}.
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
        this, "classifier", ExchangeSourceComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code publishRest} property.
     */
    private final MetaProperty<Boolean> _publishRest = DirectMetaProperty.ofReadWrite(
        this, "publishRest", ExchangeSourceComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code cacheManager} property.
     */
    private final MetaProperty<CacheManager> _cacheManager = DirectMetaProperty.ofReadWrite(
        this, "cacheManager", ExchangeSourceComponentFactory.class, CacheManager.class);
    /**
     * The meta-property for the {@code exchangeMaster} property.
     */
    private final MetaProperty<ExchangeMaster> _exchangeMaster = DirectMetaProperty.ofReadWrite(
        this, "exchangeMaster", ExchangeSourceComponentFactory.class, ExchangeMaster.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "publishRest",
        "cacheManager",
        "exchangeMaster");

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
        case -614707837:  // publishRest
          return _publishRest;
        case -1452875317:  // cacheManager
          return _cacheManager;
        case -652001691:  // exchangeMaster
          return _exchangeMaster;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends ExchangeSourceComponentFactory> builder() {
      return new DirectBeanBuilder<ExchangeSourceComponentFactory>(new ExchangeSourceComponentFactory());
    }

    @Override
    public Class<? extends ExchangeSourceComponentFactory> beanType() {
      return ExchangeSourceComponentFactory.class;
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
     * The meta-property for the {@code publishRest} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> publishRest() {
      return _publishRest;
    }

    /**
     * The meta-property for the {@code cacheManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CacheManager> cacheManager() {
      return _cacheManager;
    }

    /**
     * The meta-property for the {@code exchangeMaster} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExchangeMaster> exchangeMaster() {
      return _exchangeMaster;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((ExchangeSourceComponentFactory) bean).getClassifier();
        case -614707837:  // publishRest
          return ((ExchangeSourceComponentFactory) bean).isPublishRest();
        case -1452875317:  // cacheManager
          return ((ExchangeSourceComponentFactory) bean).getCacheManager();
        case -652001691:  // exchangeMaster
          return ((ExchangeSourceComponentFactory) bean).getExchangeMaster();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((ExchangeSourceComponentFactory) bean).setClassifier((String) newValue);
          return;
        case -614707837:  // publishRest
          ((ExchangeSourceComponentFactory) bean).setPublishRest((Boolean) newValue);
          return;
        case -1452875317:  // cacheManager
          ((ExchangeSourceComponentFactory) bean).setCacheManager((CacheManager) newValue);
          return;
        case -652001691:  // exchangeMaster
          ((ExchangeSourceComponentFactory) bean).setExchangeMaster((ExchangeMaster) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((ExchangeSourceComponentFactory) bean)._classifier, "classifier");
      JodaBeanUtils.notNull(((ExchangeSourceComponentFactory) bean)._exchangeMaster, "exchangeMaster");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
