/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.bond;

import java.io.Serializable;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableDefaults;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.market.ReferenceData;
import com.opengamma.strata.collect.id.StandardId;
import com.opengamma.strata.product.ResolvedTrade;
import com.opengamma.strata.product.TradeInfo;

/**
 * A trade in a futures contract based on a basket of fixed coupon bonds, resolved for pricing.
 * <p>
 * This is the resolved form of {@link BondFutureTrade} and is the primary input to the pricers.
 * Applications will typically create a {@code ResolvedBondFutureTrade} from a {@code BondFutureTrade}
 * using {@link BondFutureTrade#resolve(ReferenceData)}.
 * <p>
 * A {@code ResolvedBondFutureTrade} is bound to data that changes over time, such as holiday calendars.
 * If the data changes, such as the addition of a new holiday, the resolved form will not be updated.
 * Care must be taken when placing the resolved form in a cache or persistence layer.
 */
@BeanDefinition
public final class ResolvedBondFutureTrade
    implements ResolvedTrade, ImmutableBean, Serializable {

  /**
   * The additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   */
  @PropertyDefinition(overrideGet = true)
  private final TradeInfo tradeInfo;
  /**
   * The resolved futures product.
   * <p>
   * The product captures the contracted financial details of the trade.
   */
  @PropertyDefinition(validate = "notNull", overrideGet = true)
  private final ResolvedBondFuture product;
  /**
   * The identifier used to refer to the security.
   */
  @PropertyDefinition(validate = "notNull")
  private final StandardId securityStandardId;
  /**
   * The quantity, indicating the number of contracts in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   */
  @PropertyDefinition
  private final long quantity;
  /**
   * The initial price of the future, represented in decimal form.
   * <p>
   * This is the price agreed when the trade occurred.
   */
  @PropertyDefinition
  private final double initialPrice;

  //-------------------------------------------------------------------------
  @ImmutableDefaults
  private static void applyDefaults(Builder builder) {
    builder.tradeInfo = TradeInfo.EMPTY;
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedBondFutureTrade}.
   * @return the meta-bean, not null
   */
  public static ResolvedBondFutureTrade.Meta meta() {
    return ResolvedBondFutureTrade.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedBondFutureTrade.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ResolvedBondFutureTrade.Builder builder() {
    return new ResolvedBondFutureTrade.Builder();
  }

  private ResolvedBondFutureTrade(
      TradeInfo tradeInfo,
      ResolvedBondFuture product,
      StandardId securityStandardId,
      long quantity,
      double initialPrice) {
    JodaBeanUtils.notNull(product, "product");
    JodaBeanUtils.notNull(securityStandardId, "securityStandardId");
    this.tradeInfo = tradeInfo;
    this.product = product;
    this.securityStandardId = securityStandardId;
    this.quantity = quantity;
    this.initialPrice = initialPrice;
  }

  @Override
  public ResolvedBondFutureTrade.Meta metaBean() {
    return ResolvedBondFutureTrade.Meta.INSTANCE;
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
   * Gets the additional trade information, defaulted to an empty instance.
   * <p>
   * This allows additional information to be attached to the trade.
   * @return the value of the property
   */
  @Override
  public TradeInfo getTradeInfo() {
    return tradeInfo;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the resolved futures product.
   * <p>
   * The product captures the contracted financial details of the trade.
   * @return the value of the property, not null
   */
  @Override
  public ResolvedBondFuture getProduct() {
    return product;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifier used to refer to the security.
   * @return the value of the property, not null
   */
  public StandardId getSecurityStandardId() {
    return securityStandardId;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the quantity, indicating the number of contracts in the trade.
   * <p>
   * This will be positive if buying and negative if selling.
   * @return the value of the property
   */
  public long getQuantity() {
    return quantity;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the initial price of the future, represented in decimal form.
   * <p>
   * This is the price agreed when the trade occurred.
   * @return the value of the property
   */
  public double getInitialPrice() {
    return initialPrice;
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
      ResolvedBondFutureTrade other = (ResolvedBondFutureTrade) obj;
      return JodaBeanUtils.equal(tradeInfo, other.tradeInfo) &&
          JodaBeanUtils.equal(product, other.product) &&
          JodaBeanUtils.equal(securityStandardId, other.securityStandardId) &&
          (quantity == other.quantity) &&
          JodaBeanUtils.equal(initialPrice, other.initialPrice);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(tradeInfo);
    hash = hash * 31 + JodaBeanUtils.hashCode(product);
    hash = hash * 31 + JodaBeanUtils.hashCode(securityStandardId);
    hash = hash * 31 + JodaBeanUtils.hashCode(quantity);
    hash = hash * 31 + JodaBeanUtils.hashCode(initialPrice);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(192);
    buf.append("ResolvedBondFutureTrade{");
    buf.append("tradeInfo").append('=').append(tradeInfo).append(',').append(' ');
    buf.append("product").append('=').append(product).append(',').append(' ');
    buf.append("securityStandardId").append('=').append(securityStandardId).append(',').append(' ');
    buf.append("quantity").append('=').append(quantity).append(',').append(' ');
    buf.append("initialPrice").append('=').append(JodaBeanUtils.toString(initialPrice));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedBondFutureTrade}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code tradeInfo} property.
     */
    private final MetaProperty<TradeInfo> tradeInfo = DirectMetaProperty.ofImmutable(
        this, "tradeInfo", ResolvedBondFutureTrade.class, TradeInfo.class);
    /**
     * The meta-property for the {@code product} property.
     */
    private final MetaProperty<ResolvedBondFuture> product = DirectMetaProperty.ofImmutable(
        this, "product", ResolvedBondFutureTrade.class, ResolvedBondFuture.class);
    /**
     * The meta-property for the {@code securityStandardId} property.
     */
    private final MetaProperty<StandardId> securityStandardId = DirectMetaProperty.ofImmutable(
        this, "securityStandardId", ResolvedBondFutureTrade.class, StandardId.class);
    /**
     * The meta-property for the {@code quantity} property.
     */
    private final MetaProperty<Long> quantity = DirectMetaProperty.ofImmutable(
        this, "quantity", ResolvedBondFutureTrade.class, Long.TYPE);
    /**
     * The meta-property for the {@code initialPrice} property.
     */
    private final MetaProperty<Double> initialPrice = DirectMetaProperty.ofImmutable(
        this, "initialPrice", ResolvedBondFutureTrade.class, Double.TYPE);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "tradeInfo",
        "product",
        "securityStandardId",
        "quantity",
        "initialPrice");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case 752580658:  // tradeInfo
          return tradeInfo;
        case -309474065:  // product
          return product;
        case -593973224:  // securityStandardId
          return securityStandardId;
        case -1285004149:  // quantity
          return quantity;
        case -423406491:  // initialPrice
          return initialPrice;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedBondFutureTrade.Builder builder() {
      return new ResolvedBondFutureTrade.Builder();
    }

    @Override
    public Class<? extends ResolvedBondFutureTrade> beanType() {
      return ResolvedBondFutureTrade.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code tradeInfo} property.
     * @return the meta-property, not null
     */
    public MetaProperty<TradeInfo> tradeInfo() {
      return tradeInfo;
    }

    /**
     * The meta-property for the {@code product} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedBondFuture> product() {
      return product;
    }

    /**
     * The meta-property for the {@code securityStandardId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> securityStandardId() {
      return securityStandardId;
    }

    /**
     * The meta-property for the {@code quantity} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Long> quantity() {
      return quantity;
    }

    /**
     * The meta-property for the {@code initialPrice} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> initialPrice() {
      return initialPrice;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case 752580658:  // tradeInfo
          return ((ResolvedBondFutureTrade) bean).getTradeInfo();
        case -309474065:  // product
          return ((ResolvedBondFutureTrade) bean).getProduct();
        case -593973224:  // securityStandardId
          return ((ResolvedBondFutureTrade) bean).getSecurityStandardId();
        case -1285004149:  // quantity
          return ((ResolvedBondFutureTrade) bean).getQuantity();
        case -423406491:  // initialPrice
          return ((ResolvedBondFutureTrade) bean).getInitialPrice();
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
   * The bean-builder for {@code ResolvedBondFutureTrade}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ResolvedBondFutureTrade> {

    private TradeInfo tradeInfo;
    private ResolvedBondFuture product;
    private StandardId securityStandardId;
    private long quantity;
    private double initialPrice;

    /**
     * Restricted constructor.
     */
    private Builder() {
      applyDefaults(this);
    }

    /**
     * Restricted copy constructor.
     * @param beanToCopy  the bean to copy from, not null
     */
    private Builder(ResolvedBondFutureTrade beanToCopy) {
      this.tradeInfo = beanToCopy.getTradeInfo();
      this.product = beanToCopy.getProduct();
      this.securityStandardId = beanToCopy.getSecurityStandardId();
      this.quantity = beanToCopy.getQuantity();
      this.initialPrice = beanToCopy.getInitialPrice();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case 752580658:  // tradeInfo
          return tradeInfo;
        case -309474065:  // product
          return product;
        case -593973224:  // securityStandardId
          return securityStandardId;
        case -1285004149:  // quantity
          return quantity;
        case -423406491:  // initialPrice
          return initialPrice;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case 752580658:  // tradeInfo
          this.tradeInfo = (TradeInfo) newValue;
          break;
        case -309474065:  // product
          this.product = (ResolvedBondFuture) newValue;
          break;
        case -593973224:  // securityStandardId
          this.securityStandardId = (StandardId) newValue;
          break;
        case -1285004149:  // quantity
          this.quantity = (Long) newValue;
          break;
        case -423406491:  // initialPrice
          this.initialPrice = (Double) newValue;
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
    public ResolvedBondFutureTrade build() {
      return new ResolvedBondFutureTrade(
          tradeInfo,
          product,
          securityStandardId,
          quantity,
          initialPrice);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets the additional trade information, defaulted to an empty instance.
     * <p>
     * This allows additional information to be attached to the trade.
     * @param tradeInfo  the new value
     * @return this, for chaining, not null
     */
    public Builder tradeInfo(TradeInfo tradeInfo) {
      this.tradeInfo = tradeInfo;
      return this;
    }

    /**
     * Sets the resolved futures product.
     * <p>
     * The product captures the contracted financial details of the trade.
     * @param product  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder product(ResolvedBondFuture product) {
      JodaBeanUtils.notNull(product, "product");
      this.product = product;
      return this;
    }

    /**
     * Sets the identifier used to refer to the security.
     * @param securityStandardId  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder securityStandardId(StandardId securityStandardId) {
      JodaBeanUtils.notNull(securityStandardId, "securityStandardId");
      this.securityStandardId = securityStandardId;
      return this;
    }

    /**
     * Sets the quantity, indicating the number of contracts in the trade.
     * <p>
     * This will be positive if buying and negative if selling.
     * @param quantity  the new value
     * @return this, for chaining, not null
     */
    public Builder quantity(long quantity) {
      this.quantity = quantity;
      return this;
    }

    /**
     * Sets the initial price of the future, represented in decimal form.
     * <p>
     * This is the price agreed when the trade occurred.
     * @param initialPrice  the new value
     * @return this, for chaining, not null
     */
    public Builder initialPrice(double initialPrice) {
      this.initialPrice = initialPrice;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(192);
      buf.append("ResolvedBondFutureTrade.Builder{");
      buf.append("tradeInfo").append('=').append(JodaBeanUtils.toString(tradeInfo)).append(',').append(' ');
      buf.append("product").append('=').append(JodaBeanUtils.toString(product)).append(',').append(' ');
      buf.append("securityStandardId").append('=').append(JodaBeanUtils.toString(securityStandardId)).append(',').append(' ');
      buf.append("quantity").append('=').append(JodaBeanUtils.toString(quantity)).append(',').append(' ');
      buf.append("initialPrice").append('=').append(JodaBeanUtils.toString(initialPrice));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
