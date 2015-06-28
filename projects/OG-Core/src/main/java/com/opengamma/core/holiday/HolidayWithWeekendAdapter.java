/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.core.holiday;

import java.io.Serializable;
import java.util.List;
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
import org.threeten.bp.LocalDate;

import com.opengamma.id.ExternalId;
import com.opengamma.id.MutableUniqueIdentifiable;
import com.opengamma.id.UniqueId;
import com.opengamma.util.money.Currency;

/**
 * An adapter for {@link Holiday}s that do not contain information about which days are weekends.
 */
@BeanDefinition
public class HolidayWithWeekendAdapter extends DirectBean implements Holiday, WeekendTypeProvider, Serializable, MutableUniqueIdentifiable {

  /** Serialization version. */
  private static final long serialVersionUID = 1L;

  /**
   * The unique identifier of the holiday.
   * This must be null when adding to a master and not null when retrieved from a master.
   */
  @PropertyDefinition
  private UniqueId _uniqueId;

  /**
   * The underlying holiday.
   */
  @PropertyDefinition(validate = "notNull")
  private Holiday _holiday;

  /**
   * The weekend type.
   */
  @PropertyDefinition(validate = "notNull")
  private WeekendType _weekendType;

  /**
   * Creates an empty instance.
   */
  public HolidayWithWeekendAdapter() {
  }

  /**
   * Creates an instance, setting the weekend type to {@link WeekendType#SATURDAY_SUNDAY}. This constructor is intended
   * to be used to reproduce the behaviour in many holiday sources, where the weekends were hard-coded to Saturday and
   * Sunday.
   * @param holiday  the holiday, not null
   */
  public HolidayWithWeekendAdapter(final Holiday holiday) {
    setHoliday(holiday);
    setWeekendType(WeekendType.SATURDAY_SUNDAY);
  }

  /**
   * Creates an instance.
   * @param holiday  the holiday, not null
   * @param weekendType  the weekend type, not null
   */
  public HolidayWithWeekendAdapter(final Holiday holiday, final WeekendType weekendType) {
    setHoliday(holiday);
    setWeekendType(weekendType);
  }

  @Override
  public Currency getCurrency() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getCurrency();
  }

  @Override
  public HolidayType getType() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getType();
  }

  @Override
  public ExternalId getRegionExternalId() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getRegionExternalId();
  }

  @Override
  public ExternalId getExchangeExternalId() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getExchangeExternalId();
  }

  @Override
  public ExternalId getCustomExternalId() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getCustomExternalId();
  }

  @Override
  public List<LocalDate> getHolidayDates() {
    if (getHoliday() == null) {
      throw new IllegalStateException("Holiday was not set");
    }
    return getHoliday().getHolidayDates();
  }
  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code HolidayWithWeekendAdapter}.
   * @return the meta-bean, not null
   */
  public static HolidayWithWeekendAdapter.Meta meta() {
    return HolidayWithWeekendAdapter.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(HolidayWithWeekendAdapter.Meta.INSTANCE);
  }

  @Override
  public HolidayWithWeekendAdapter.Meta metaBean() {
    return HolidayWithWeekendAdapter.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the unique identifier of the holiday.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the value of the property
   */
  public UniqueId getUniqueId() {
    return _uniqueId;
  }

  /**
   * Sets the unique identifier of the holiday.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @param uniqueId  the new value of the property
   */
  public void setUniqueId(UniqueId uniqueId) {
    this._uniqueId = uniqueId;
  }

  /**
   * Gets the the {@code uniqueId} property.
   * This must be null when adding to a master and not null when retrieved from a master.
   * @return the property, not null
   */
  public final Property<UniqueId> uniqueId() {
    return metaBean().uniqueId().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying holiday.
   * @return the value of the property, not null
   */
  public Holiday getHoliday() {
    return _holiday;
  }

  /**
   * Sets the underlying holiday.
   * @param holiday  the new value of the property, not null
   */
  public void setHoliday(Holiday holiday) {
    JodaBeanUtils.notNull(holiday, "holiday");
    this._holiday = holiday;
  }

  /**
   * Gets the the {@code holiday} property.
   * @return the property, not null
   */
  public final Property<Holiday> holiday() {
    return metaBean().holiday().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the weekend type.
   * @return the value of the property, not null
   */
  public WeekendType getWeekendType() {
    return _weekendType;
  }

  /**
   * Sets the weekend type.
   * @param weekendType  the new value of the property, not null
   */
  public void setWeekendType(WeekendType weekendType) {
    JodaBeanUtils.notNull(weekendType, "weekendType");
    this._weekendType = weekendType;
  }

  /**
   * Gets the the {@code weekendType} property.
   * @return the property, not null
   */
  public final Property<WeekendType> weekendType() {
    return metaBean().weekendType().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public HolidayWithWeekendAdapter clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      HolidayWithWeekendAdapter other = (HolidayWithWeekendAdapter) obj;
      return JodaBeanUtils.equal(getUniqueId(), other.getUniqueId()) &&
          JodaBeanUtils.equal(getHoliday(), other.getHoliday()) &&
          JodaBeanUtils.equal(getWeekendType(), other.getWeekendType());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(getUniqueId());
    hash = hash * 31 + JodaBeanUtils.hashCode(getHoliday());
    hash = hash * 31 + JodaBeanUtils.hashCode(getWeekendType());
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(128);
    buf.append("HolidayWithWeekendAdapter{");
    int len = buf.length();
    toString(buf);
    if (buf.length() > len) {
      buf.setLength(buf.length() - 2);
    }
    buf.append('}');
    return buf.toString();
  }

  protected void toString(StringBuilder buf) {
    buf.append("uniqueId").append('=').append(JodaBeanUtils.toString(getUniqueId())).append(',').append(' ');
    buf.append("holiday").append('=').append(JodaBeanUtils.toString(getHoliday())).append(',').append(' ');
    buf.append("weekendType").append('=').append(JodaBeanUtils.toString(getWeekendType())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code HolidayWithWeekendAdapter}.
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
        this, "uniqueId", HolidayWithWeekendAdapter.class, UniqueId.class);
    /**
     * The meta-property for the {@code holiday} property.
     */
    private final MetaProperty<Holiday> _holiday = DirectMetaProperty.ofReadWrite(
        this, "holiday", HolidayWithWeekendAdapter.class, Holiday.class);
    /**
     * The meta-property for the {@code weekendType} property.
     */
    private final MetaProperty<WeekendType> _weekendType = DirectMetaProperty.ofReadWrite(
        this, "weekendType", HolidayWithWeekendAdapter.class, WeekendType.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "uniqueId",
        "holiday",
        "weekendType");

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
        case 1091905624:  // holiday
          return _holiday;
        case 563735617:  // weekendType
          return _weekendType;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends HolidayWithWeekendAdapter> builder() {
      return new DirectBeanBuilder<HolidayWithWeekendAdapter>(new HolidayWithWeekendAdapter());
    }

    @Override
    public Class<? extends HolidayWithWeekendAdapter> beanType() {
      return HolidayWithWeekendAdapter.class;
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
     * The meta-property for the {@code holiday} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<Holiday> holiday() {
      return _holiday;
    }

    /**
     * The meta-property for the {@code weekendType} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<WeekendType> weekendType() {
      return _weekendType;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          return ((HolidayWithWeekendAdapter) bean).getUniqueId();
        case 1091905624:  // holiday
          return ((HolidayWithWeekendAdapter) bean).getHoliday();
        case 563735617:  // weekendType
          return ((HolidayWithWeekendAdapter) bean).getWeekendType();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -294460212:  // uniqueId
          ((HolidayWithWeekendAdapter) bean).setUniqueId((UniqueId) newValue);
          return;
        case 1091905624:  // holiday
          ((HolidayWithWeekendAdapter) bean).setHoliday((Holiday) newValue);
          return;
        case 563735617:  // weekendType
          ((HolidayWithWeekendAdapter) bean).setWeekendType((WeekendType) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((HolidayWithWeekendAdapter) bean)._holiday, "holiday");
      JodaBeanUtils.notNull(((HolidayWithWeekendAdapter) bean)._weekendType, "weekendType");
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
