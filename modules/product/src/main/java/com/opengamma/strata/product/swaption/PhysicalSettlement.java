/**
 * Copyright (C) 2015 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */
package com.opengamma.strata.product.swaption;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;
import org.joda.beans.BeanBuilder;

/**
 * Defines the settlement type and settlement method of swaptions.
 * <p>
 * The settlement type is {@link SettlementType#PHYSICAL}. This means that the two
 * parties enter into the actual interest rate swap at the expiry date of the option. 
 */
@BeanDefinition(builderScope = "private")
public final class PhysicalSettlement
    implements SwaptionSettlement, ImmutableBean, Serializable {

  /**
   * Default instance.
   */
  public static final PhysicalSettlement DEFAULT = new PhysicalSettlement();

  @Override
  public SettlementType getSettlementType() {
    return SettlementType.PHYSICAL;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code PhysicalSettlement}.
   * @return the meta-bean, not null
   */
  public static PhysicalSettlement.Meta meta() {
    return PhysicalSettlement.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(PhysicalSettlement.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  private PhysicalSettlement() {
  }

  @Override
  public PhysicalSettlement.Meta metaBean() {
    return PhysicalSettlement.Meta.INSTANCE;
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
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(32);
    buf.append("PhysicalSettlement{");
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code PhysicalSettlement}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null);

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    public BeanBuilder<? extends PhysicalSettlement> builder() {
      return new PhysicalSettlement.Builder();
    }

    @Override
    public Class<? extends PhysicalSettlement> beanType() {
      return PhysicalSettlement.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
  }

  //-----------------------------------------------------------------------
  /**
   * The bean-builder for {@code PhysicalSettlement}.
   */
  private static final class Builder extends DirectFieldsBeanBuilder<PhysicalSettlement> {

    /**
     * Restricted constructor.
     */
    private Builder() {
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      throw new NoSuchElementException("Unknown property: " + propertyName);
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
    public PhysicalSettlement build() {
      return new PhysicalSettlement();
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      return "PhysicalSettlement.Builder{}";
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
