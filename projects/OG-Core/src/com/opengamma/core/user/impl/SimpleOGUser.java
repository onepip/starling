/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.core.user.impl;

import java.io.Serializable;
import java.util.List;
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

import com.google.common.collect.Lists;
import com.opengamma.core.user.OGUser;
import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;

/**
 * Simple implementation of {@code OGUser}.
 * <p>
 * This is the simplest possible implementation of the {@link OGUser} interface.
 * <p>
 * This class is mutable and not thread-safe.
 * It is intended to be used in the engine via the read-only {@code OGUser} interface.
 */
@BeanDefinition
public class SimpleOGUser extends DirectBean implements OGUser, MutableUniqueIdentifiable, Serializable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The unique identifier of the user.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;
  /**
   * The bundle of external identifiers that define the user.
   */
  @PropertyDefinition(validate = "notNull")
  private ExternalIdBundle _externalIdBundle = ExternalIdBundle.EMPTY;
  /**
   * The name of the user intended for display purposes.
   */
  @PropertyDefinition
  private String _name;
  /**
   * The hashed version of the user password, null if no password.
   */
  @PropertyDefinition
  private String _passwordHash;
  /**
   * The entitlements for the user.
   * This is a list of entitlements that the user has, which enables access restriction.
   */
  @PropertyDefinition(validate = "notNull")
  private final List<String> _entitlements = Lists.newArrayList();

  /**
   * Creates a user.
   */
  public SimpleOGUser() {
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code SimpleOGUser}.
   * @return the meta-bean, not null
   */
  public static SimpleOGUser.Meta meta() {
    return SimpleOGUser.Meta.INSTANCE;
  }
  static {
    JodaBeanUtils.registerMetaBean(SimpleOGUser.Meta.INSTANCE);
  }

  @Override
  public SimpleOGUser.Meta metaBean() {
    return SimpleOGUser.Meta.INSTANCE;
  }

  @Override
  protected Object propertyGet(String propertyName, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        return getUniqueId();
      case -736922008:  // externalIdBundle
        return getExternalIdBundle();
      case 3373707:  // name
        return getName();
      case 566700617:  // passwordHash
        return getPasswordHash();
      case -1704576794:  // entitlements
        return getEntitlements();
    }
    return super.propertyGet(propertyName, quiet);
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void propertySet(String propertyName, Object newValue, boolean quiet) {
    switch (propertyName.hashCode()) {
      case -294460212:  // uniqueId
        setUniqueId((UniqueId) newValue);
        return;
      case -736922008:  // externalIdBundle
        setExternalIdBundle((ExternalIdBundle) newValue);
        return;
      case 3373707:  // name
        setName((String) newValue);
        return;
      case 566700617:  // passwordHash
        setPasswordHash((String) newValue);
        return;
      case -1704576794:  // entitlements
        setEntitlements((List<String>) newValue);
        return;
    }
    super.propertySet(propertyName, newValue, quiet);
  }

  @Override
  protected void validate() {
    JodaBeanUtils.notNull(_externalIdBundle, "externalIdBundle");
    JodaBeanUtils.notNull(_entitlements, "entitlements");
    super.validate();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      SimpleOGUser other = (SimpleOGUser) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getExternalIdBundle(), other.getExternalIdBundle()) &&
          JodaBeanUtils.equal(getName(), other.getName()) &&
          JodaBeanUtils.equal(getPasswordHash(), other.getPasswordHash()) &&
          JodaBeanUtils.equal(getEntitlements(), other.getEntitlements());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash += hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash += hash * 31 + JodaBeanUtils.hashCode(getExternalIdBundle());
    hash += hash * 31 + JodaBeanUtils.hashCode(getName());
    hash += hash * 31 + JodaBeanUtils.hashCode(getPasswordHash());
    hash += hash * 31 + JodaBeanUtils.hashCode(getEntitlements());
    return hash;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the user.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier of the user.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the bundle of external identifiers that define the user.
   * @return the value of the property, not null
   */
  public ExternalIdBundle getExternalIdBundle() {
    return _externalIdBundle;
  }

  /**
   * Sets the bundle of external identifiers that define the user.
   * @param externalIdBundle  the new value of the property, not null
   */
  public void setExternalIdBundle(ExternalIdBundle externalIdBundle) {
    JodaBeanUtils.notNull(externalIdBundle, "externalIdBundle");
    this._externalIdBundle = externalIdBundle;
  }

  /**
   * Gets the the {@code externalIdBundle} property.
   * @return the property, not null
   */
  public final Property<ExternalIdBundle> externalIdBundle() {
    return metaBean().externalIdBundle().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the name of the user intended for display purposes.
   * @return the value of the property
   */
  public String getName() {
    return _name;
  }

  /**
   * Sets the name of the user intended for display purposes.
   * @param name  the new value of the property
   */
  public void setName(String name) {
    this._name = name;
  }

  /**
   * Gets the the {@code name} property.
   * @return the property, not null
   */
  public final Property<String> name() {
    return metaBean().name().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the hashed version of the user password, null if no password.
   * @return the value of the property
   */
  public String getPasswordHash() {
    return _passwordHash;
  }

  /**
   * Sets the hashed version of the user password, null if no password.
   * @param passwordHash  the new value of the property
   */
  public void setPasswordHash(String passwordHash) {
    this._passwordHash = passwordHash;
  }

  /**
   * Gets the the {@code passwordHash} property.
   * @return the property, not null
   */
  public final Property<String> passwordHash() {
    return metaBean().passwordHash().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the entitlements for the user.
   * This is a list of entitlements that the user has, which enables access restriction.
   * @return the value of the property, not null
   */
  public List<String> getEntitlements() {
    return _entitlements;
  }

  /**
   * Sets the entitlements for the user.
   * This is a list of entitlements that the user has, which enables access restriction.
   * @param entitlements  the new value of the property
   */
  public void setEntitlements(List<String> entitlements) {
    this._entitlements.clear();
    this._entitlements.addAll(entitlements);
  }

  /**
   * Gets the the {@code entitlements} property.
   * This is a list of entitlements that the user has, which enables access restriction.
   * @return the property, not null
   */
  public final Property<List<String>> entitlements() {
    return metaBean().entitlements().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code SimpleOGUser}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code uniqueId} property.
     */
    private final MetaProperty<UniqueId> _uniqueId = DirectMetaProperty.ofReadWrite(
        this, "uniqueId", SimpleOGUser.class, UniqueId.class);
    /**
     * The meta-property for the {@code externalIdBundle} property.
     */
    private final MetaProperty<ExternalIdBundle> _externalIdBundle = DirectMetaProperty.ofReadWrite(
        this, "externalIdBundle", SimpleOGUser.class, ExternalIdBundle.class);
    /**
     * The meta-property for the {@code name} property.
     */
    private final MetaProperty<String> _name = DirectMetaProperty.ofReadWrite(
        this, "name", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code passwordHash} property.
     */
    private final MetaProperty<String> _passwordHash = DirectMetaProperty.ofReadWrite(
        this, "passwordHash", SimpleOGUser.class, String.class);
    /**
     * The meta-property for the {@code entitlements} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<List<String>> _entitlements = DirectMetaProperty.ofReadWrite(
        this, "entitlements", SimpleOGUser.class, (Class) List.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "externalIdBundle",
        "name",
        "passwordHash",
        "entitlements");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return _uniqueId;
        case -736922008:  // externalIdBundle
          return _externalIdBundle;
        case 3373707:  // name
          return _name;
        case 566700617:  // passwordHash
          return _passwordHash;
        case -1704576794:  // entitlements
          return _entitlements;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends SimpleOGUser> builder() {
      return new DirectBeanBuilder<SimpleOGUser>(new SimpleOGUser());
    }

    @Override
    public Class<? extends SimpleOGUser> beanType() {
      return SimpleOGUser.class;
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
    public final MetaProperty<UniqueId> uniqueId() {
      return _uniqueId;
    }

    /**
     * The meta-property for the {@code externalIdBundle} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ExternalIdBundle> externalIdBundle() {
      return _externalIdBundle;
    }

    /**
     * The meta-property for the {@code name} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> name() {
      return _name;
    }

    /**
     * The meta-property for the {@code passwordHash} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> passwordHash() {
      return _passwordHash;
    }

    /**
     * The meta-property for the {@code entitlements} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<List<String>> entitlements() {
      return _entitlements;
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
