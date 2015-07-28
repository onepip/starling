/**
 * Copyright (C) 2009 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

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

import com.opengamma.master.impl.AbstractRemoteMaster;
import com.opengamma.master.security.SecurityMaster;
import com.opengamma.master.security.impl.DataTrackingSecurityMaster;
import com.opengamma.master.security.impl.PermissionedSecurityMaster;
import com.opengamma.master.security.impl.RemoteSecurityMaster;
import com.opengamma.masterdb.security.DataDbSecurityMasterResource;
import com.opengamma.masterdb.security.DbSecurityMaster;
import com.opengamma.masterdb.security.EHCachingSecurityMasterDetailProvider;
import com.opengamma.masterdb.security.SecurityMasterDetailProvider;
import com.opengamma.masterdb.security.hibernate.HibernateSecurityMasterDetailProvider;
import com.opengamma.util.metric.OpenGammaMetricRegistry;
import com.opengamma.util.rest.AbstractDataResource;

/**
 * Component factory for the database security master.
 */
@BeanDefinition
public class DbSecurityMasterComponentFactory extends AbstractDocumentDbMasterComponentFactory<SecurityMaster, DbSecurityMaster> {

  /**
   * The cache manager.
   */
  @PropertyDefinition
  private CacheManager _cacheManager;
  /**
   * The detail provider.
   */
  @PropertyDefinition
  private Class<? extends SecurityMasterDetailProvider> _detailProvider = HibernateSecurityMasterDetailProvider.class;

  /**
   * Creates an instance.
   */
  public DbSecurityMasterComponentFactory() {
    super("sec", SecurityMaster.class);
  }

  @Override
  protected Class<? extends AbstractRemoteMaster> getRemoteInterface() {
    return RemoteSecurityMaster.class;
  }
  
  //-------------------------------------------------------------------------
  @Override
  protected DbSecurityMaster createDbDocumentMaster() throws Exception {
    DbSecurityMaster master = new DbSecurityMaster(getDbConnector());
    master.registerMetrics(OpenGammaMetricRegistry.getSummaryInstance(), OpenGammaMetricRegistry.getDetailedInstance(), "DbSecurityMaster-" + getClassifier());
    if (getDetailProvider() != null) {
      SecurityMasterDetailProvider dp = getDetailProvider().newInstance();
      if (getCacheManager() != null) {
        master.setDetailProvider(new EHCachingSecurityMasterDetailProvider(dp, getCacheManager()));
      } else {
        master.setDetailProvider(dp);
      }
    }
    return master;
  }

  @Override
  protected AbstractDataResource createPublishedResource(DbSecurityMaster dbMaster, SecurityMaster postProcessedMaster) {
    //only db instance is allowed by the constructor here:
    return new DataDbSecurityMasterResource(dbMaster);
  }

  @Override
  protected SecurityMaster wrapMasterWithTrackingInterface(SecurityMaster postProcessedMaster) {
    return new DataTrackingSecurityMaster(postProcessedMaster);
  }

  @Override
  protected SecurityMaster postProcess(DbSecurityMaster master) {
    return PermissionedSecurityMaster.wrap(master);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DbSecurityMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static DbSecurityMasterComponentFactory.Meta meta() {
    return DbSecurityMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DbSecurityMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public DbSecurityMasterComponentFactory.Meta metaBean() {
    return DbSecurityMasterComponentFactory.Meta.INSTANCE;
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
   * Gets the detail provider.
   * @return the value of the property
   */
  public Class<? extends SecurityMasterDetailProvider> getDetailProvider() {
    return _detailProvider;
  }

  /**
   * Sets the detail provider.
   * @param detailProvider  the new value of the property
   */
  public void setDetailProvider(Class<? extends SecurityMasterDetailProvider> detailProvider) {
    this._detailProvider = detailProvider;
  }

  /**
   * Gets the the {@code detailProvider} property.
   * @return the property, not null
   */
  public final Property<Class<? extends SecurityMasterDetailProvider>> detailProvider() {
    return metaBean().detailProvider().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public DbSecurityMasterComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      DbSecurityMasterComponentFactory other = (DbSecurityMasterComponentFactory) obj;
      return JodaBeanUtils.equal(getCacheManager(), other.getCacheManager()) &&
          JodaBeanUtils.equal(getDetailProvider(), other.getDetailProvider()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getCacheManager());
    hash = hash * 31 + JodaBeanUtils.hashCode(getDetailProvider());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(96);
    buf.append("DbSecurityMasterComponentFactory{");
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
    buf.append("cacheManager").append('=').append(JodaBeanUtils.toString(getCacheManager())).append(',').append(' ');
    buf.append("detailProvider").append('=').append(JodaBeanUtils.toString(getDetailProvider())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DbSecurityMasterComponentFactory}.
   */
  public static class Meta extends AbstractDocumentDbMasterComponentFactory.Meta<SecurityMaster, DbSecurityMaster> {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code cacheManager} property.
     */
    private final MetaProperty<CacheManager> _cacheManager = DirectMetaProperty.ofReadWrite(
        this, "cacheManager", DbSecurityMasterComponentFactory.class, CacheManager.class);
    /**
     * The meta-property for the {@code detailProvider} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<Class<? extends SecurityMasterDetailProvider>> _detailProvider = DirectMetaProperty.ofReadWrite(
        this, "detailProvider", DbSecurityMasterComponentFactory.class, (Class) Class.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "cacheManager",
        "detailProvider");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -1452875317:  // cacheManager
          return _cacheManager;
        case -1015570078:  // detailProvider
          return _detailProvider;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends DbSecurityMasterComponentFactory> builder() {
      return new DirectBeanBuilder<DbSecurityMasterComponentFactory>(new DbSecurityMasterComponentFactory());
    }

    @Override
    public Class<? extends DbSecurityMasterComponentFactory> beanType() {
      return DbSecurityMasterComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code cacheManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<CacheManager> cacheManager() {
      return _cacheManager;
    }

    /**
     * The meta-property for the {@code detailProvider} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Class<? extends SecurityMasterDetailProvider>> detailProvider() {
      return _detailProvider;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1452875317:  // cacheManager
          return ((DbSecurityMasterComponentFactory) bean).getCacheManager();
        case -1015570078:  // detailProvider
          return ((DbSecurityMasterComponentFactory) bean).getDetailProvider();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -1452875317:  // cacheManager
          ((DbSecurityMasterComponentFactory) bean).setCacheManager((CacheManager) newValue);
          return;
        case -1015570078:  // detailProvider
          ((DbSecurityMasterComponentFactory) bean).setDetailProvider((Class<? extends SecurityMasterDetailProvider>) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
