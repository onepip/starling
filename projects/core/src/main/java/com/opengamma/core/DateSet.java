/**
 * Copyright (C) 2014 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.core;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.threeten.bp.LocalDate;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.opengamma.core.config.Config;
import com.opengamma.util.ArgumentChecker;

/**
 * Trivial config object for storing a set of dates.
 */
@Config(description = "Date set")
@BeanDefinition
public class DateSet implements ImmutableBean {

  @PropertyDefinition(validate = "notNull")
  private final SortedSet<LocalDate> _dates;
  
  public static DateSet of(Set<LocalDate> dates) {
    return builder().dates(new TreeSet<LocalDate>(dates)).build();
  }
  
  /**
   * Gets the next date in the set strictly after a given date.
   * 
   * @param date  the date after which to look, not null
   * @return the next date in the set after the given date, or null if no such date exists
   */
  public LocalDate getNextDate(LocalDate date) {
    return getNextDate(date, 1);
  }
  
  /**
   * Gets the nth date in the set strictly after a given date.
   * 
   * @param date  the date after which to look, not null
   * @param n  the 1-based index of the date to retrieve after the given date
   * @return the nth date in the set after the given date, or null if no such date exists
   */
  public LocalDate getNextDate(LocalDate date, int n) {
    ArgumentChecker.notNull(date, "date");
    if (n <= 0) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
    SortedSet<LocalDate> tailSet = _dates.tailSet(date.plusDays(1));
    if (tailSet.isEmpty() || tailSet.size() < n) {
      return null;
    }
    return Iterables.get(tailSet, n - 1);
  }
  
  /**
   * Gets the previous date in the set before the given date.
   * 
   * @param date  the date before which to look, not null
   * @return the previous date in the set before the given date, or null if no such date exists
   */
  public LocalDate getPreviousDate(LocalDate date) {
    ArgumentChecker.notNull(date, "date");
    SortedSet<LocalDate> headSet = _dates.headSet(date);
    if (headSet.isEmpty()) {
      return null;
    }
    return headSet.last();
  }
  
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code DateSet}.
   * @return the meta-bean, not null
   */
  public static DateSet.Meta meta() {
    return DateSet.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(DateSet.Meta.INSTANCE);
  }

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static DateSet.Builder builder() {
    return new DateSet.Builder();
  }

  /**
   * Restricted constructor.
   * @param builder  the builder to copy from, not null
   */
  protected DateSet(DateSet.Builder builder) {
    JodaBeanUtils.notNull(builder._dates, "dates");
    this._dates = ImmutableSortedSet.copyOfSorted(builder._dates);
  }

  @Override
  public DateSet.Meta metaBean() {
    return DateSet.Meta.INSTANCE;
  }

  @Override
  public <R> Property<R> property(String propertyName) {
    return metaBean().<R>metaProperty(propertyName).createProperty(this);
  }

  @Override
  public Set<String> propertyNames() {
    return metaBean().metaPropertyMap().keySet();
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the dates.
   * @return the value of the property, not null
   */
  public SortedSet<LocalDate> getDates() {
    return _dates;
  }

  //-----------------------------------------------------------------------
  /**
   * Returns a builder that allows this bean to be mutated.
   * @return the mutable builder, not null
   */
  public Builder toBuilder() {
    return new Builder(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      DateSet other = (DateSet) obj;
      return JodaBeanUtils.equal(getDates(), other.getDates());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getDates());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(64);
    buf.append("DateSet{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("dates").append('=').append(JodaBeanUtils.toString(getDates())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code DateSet}.
   */
  public static class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code dates} property.
     */
    @SuppressWarnings({"unchecked", "rawtypes" })
    private final MetaProperty<SortedSet<LocalDate>> _dates = DirectMetaProperty.ofImmutable(
        this, "dates", DateSet.class, (Class) SortedSet.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "dates");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return _dates;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public DateSet.Builder builder() {
      return new DateSet.Builder();
    }

    @Override
    public Class<? extends DateSet> beanType() {
      return DateSet.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code dates} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<SortedSet<LocalDate>> dates() {
      return _dates;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return ((DateSet) bean).getDates();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      metaProperty(propertyName);
      if (quiet) {
        return;
      }
      throw new UnsupportedOperationException("Property cannot be written: " + propertyName);
    }

  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code DateSet}.
   */
  public static class Builder extends DirectFieldsBeanBuilder<DateSet> {

    private SortedSet<LocalDate> _dates = ImmutableSortedSet.of();

    /**
     * Restricted constructor.
     */
    protected Builder() {
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    protected Builder(DateSet beanToCopy) {
      this._dates = ImmutableSortedSet.copyOfSorted(beanToCopy.getDates());
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          return _dates;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 95356549:  // dates
          this._dates = (SortedSet<LocalDate>) newValue;
          break;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
      return this;
    }

    @Override
    public Builder set(MetaProperty<?> property, Object value) {
      super.set(property, value);
      return this;
    }

    @Override
    public Builder setString(String propertyName, String value) {
      setString(meta().metaProperty(propertyName), value);
      return this;
    }

    @Override
    public Builder setString(MetaProperty<?> property, String value) {
      super.setString(property, value);
      return this;
    }

    @Override
    public Builder setAll(Map<String, ? extends Object> propertyValueMap) {
      super.setAll(propertyValueMap);
      return this;
    }

    @Override
    public DateSet build() {
      return new DateSet(this);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the dates.
     * @param dates  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder dates(SortedSet<LocalDate> dates) {
      JodaBeanUtils.notNull(dates, "dates");
      this._dates = dates;
      return this;
    }

    /**
     * Sets the {@code dates} property in the builder
     * from an array of objects.
     * @param dates  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder dates(LocalDate... dates) {
      return dates(ImmutableSortedSet.copyOf(dates));
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(64);
      buf.append("DateSet.Builder{");
      int len = buf.length();
      toString(buf);
      if (buf.length() > len) {
        buf.setLength(buf.length() - 2);
      }
      buf.append('}');
      return buf.toString();
    }

    protected void toString(StringBuilder buf) {
      buf.append("dates").append('=').append(JodaBeanUtils.toString(_dates)).append(',').append(' ');
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
