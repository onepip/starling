/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.component.factory.master;

import java.util.LinkedHashMap;
import java.util.Map;

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
import com.opengamma.id.ObjectIdSupplier;
import com.opengamma.master.portfolio.PortfolioMaster;
import com.opengamma.master.portfolio.impl.DataPortfolioMasterResource;
import com.opengamma.master.portfolio.impl.InMemoryPortfolioMaster;
import com.opengamma.master.portfolio.impl.RemotePortfolioMaster;

/**
 * Component factory for an in-memory portfolio master.
 */
@BeanDefinition
public class InMemoryPortfolioMasterComponentFactory extends AbstractComponentFactory {

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
   * Optional scheme to use for unique and object ids on this master.  If not set, the default will be used.
   */
  @PropertyDefinition
  private String _idScheme;
  /**
   * Whether to clone results in the underlying master. True by default.
   */
  @PropertyDefinition
  private boolean _cloneResults = true;
  
  

  @Override
  public void init(final ComponentRepository repo, final LinkedHashMap<String, String> configuration) {
    final InMemoryPortfolioMaster master;
    if (_idScheme == null) {
      master = new InMemoryPortfolioMaster();
    } else {
      master = new InMemoryPortfolioMaster(new ObjectIdSupplier(_idScheme));
    }
    master.setCloneResults(isCloneResults());
    final ComponentInfo info = new ComponentInfo(PortfolioMaster.class, getClassifier());
    info.addAttribute(ComponentInfoAttributes.LEVEL, 1);
    info.addAttribute(ComponentInfoAttributes.REMOTE_CLIENT_JAVA, RemotePortfolioMaster.class);
    if (_idScheme == null) {
      info.addAttribute(ComponentInfoAttributes.UNIQUE_ID_SCHEME, InMemoryPortfolioMaster.DEFAULT_OID_SCHEME);
    } else {
      info.addAttribute(ComponentInfoAttributes.UNIQUE_ID_SCHEME, _idScheme);
    }
    repo.registerComponent(info, master);

    if (isPublishRest()) {
      repo.getRestComponents().publish(info, new DataPortfolioMasterResource(master));
    }
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code InMemoryPortfolioMasterComponentFactory}.
   * @return the meta-bean, not null
   */
  public static InMemoryPortfolioMasterComponentFactory.Meta meta() {
    return InMemoryPortfolioMasterComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(InMemoryPortfolioMasterComponentFactory.Meta.INSTANCE);
  }

  @Override
  public InMemoryPortfolioMasterComponentFactory.Meta metaBean() {
    return InMemoryPortfolioMasterComponentFactory.Meta.INSTANCE;
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
   * Gets optional scheme to use for unique and object ids on this master.  If not set, the default will be used.
   * @return the value of the property
   */
  public String getIdScheme() {
    return _idScheme;
  }

  /**
   * Sets optional scheme to use for unique and object ids on this master.  If not set, the default will be used.
   * @param idScheme  the new value of the property
   */
  public void setIdScheme(String idScheme) {
    this._idScheme = idScheme;
  }

  /**
   * Gets the the {@code idScheme} property.
   * @return the property, not null
   */
  public final Property<String> idScheme() {
    return metaBean().idScheme().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets whether to clone results in the underlying master. True by default.
   * @return the value of the property
   */
  public boolean isCloneResults() {
    return _cloneResults;
  }

  /**
   * Sets whether to clone results in the underlying master. True by default.
   * @param cloneResults  the new value of the property
   */
  public void setCloneResults(boolean cloneResults) {
    this._cloneResults = cloneResults;
  }

  /**
   * Gets the the {@code cloneResults} property.
   * @return the property, not null
   */
  public final Property<Boolean> cloneResults() {
    return metaBean().cloneResults().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public InMemoryPortfolioMasterComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      InMemoryPortfolioMasterComponentFactory other = (InMemoryPortfolioMasterComponentFactory) obj;
      return JodaBeanUtils.equal(getClassifier(), other.getClassifier()) &&
          (isPublishRest() == other.isPublishRest()) &&
          JodaBeanUtils.equal(getIdScheme(), other.getIdScheme()) &&
          (isCloneResults() == other.isCloneResults()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getClassifier());
    hash = hash * 31 + JodaBeanUtils.hashCode(isPublishRest());
    hash = hash * 31 + JodaBeanUtils.hashCode(getIdScheme());
    hash = hash * 31 + JodaBeanUtils.hashCode(isCloneResults());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(160);
    buf.append("InMemoryPortfolioMasterComponentFactory{");
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
    buf.append("idScheme").append('=').append(JodaBeanUtils.toString(getIdScheme())).append(',').append(' ');
    buf.append("cloneResults").append('=').append(JodaBeanUtils.toString(isCloneResults())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code InMemoryPortfolioMasterComponentFactory}.
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
        this, "classifier", InMemoryPortfolioMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code publishRest} property.
     */
    private final MetaProperty<Boolean> _publishRest = DirectMetaProperty.ofReadWrite(
        this, "publishRest", InMemoryPortfolioMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-property for the {@code idScheme} property.
     */
    private final MetaProperty<String> _idScheme = DirectMetaProperty.ofReadWrite(
        this, "idScheme", InMemoryPortfolioMasterComponentFactory.class, String.class);
    /**
     * The meta-property for the {@code cloneResults} property.
     */
    private final MetaProperty<Boolean> _cloneResults = DirectMetaProperty.ofReadWrite(
        this, "cloneResults", InMemoryPortfolioMasterComponentFactory.class, Boolean.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "classifier",
        "publishRest",
        "idScheme",
        "cloneResults");

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
        case -661606752:  // idScheme
          return _idScheme;
        case 199795673:  // cloneResults
          return _cloneResults;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends InMemoryPortfolioMasterComponentFactory> builder() {
      return new DirectBeanBuilder<InMemoryPortfolioMasterComponentFactory>(new InMemoryPortfolioMasterComponentFactory());
    }

    @Override
    public Class<? extends InMemoryPortfolioMasterComponentFactory> beanType() {
      return InMemoryPortfolioMasterComponentFactory.class;
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
     * The meta-property for the {@code idScheme} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<String> idScheme() {
      return _idScheme;
    }

    /**
     * The meta-property for the {@code cloneResults} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Boolean> cloneResults() {
      return _cloneResults;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          return ((InMemoryPortfolioMasterComponentFactory) bean).getClassifier();
        case -614707837:  // publishRest
          return ((InMemoryPortfolioMasterComponentFactory) bean).isPublishRest();
        case -661606752:  // idScheme
          return ((InMemoryPortfolioMasterComponentFactory) bean).getIdScheme();
        case 199795673:  // cloneResults
          return ((InMemoryPortfolioMasterComponentFactory) bean).isCloneResults();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -281470431:  // classifier
          ((InMemoryPortfolioMasterComponentFactory) bean).setClassifier((String) newValue);
          return;
        case -614707837:  // publishRest
          ((InMemoryPortfolioMasterComponentFactory) bean).setPublishRest((Boolean) newValue);
          return;
        case -661606752:  // idScheme
          ((InMemoryPortfolioMasterComponentFactory) bean).setIdScheme((String) newValue);
          return;
        case 199795673:  // cloneResults
          ((InMemoryPortfolioMasterComponentFactory) bean).setCloneResults((Boolean) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((InMemoryPortfolioMasterComponentFactory) bean)._classifier, "classifier");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
