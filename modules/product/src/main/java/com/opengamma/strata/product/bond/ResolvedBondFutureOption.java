/**
 * Copyright (C) 2016 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.strata.product.bond;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.joda.beans.Bean;
import org.joda.beans.BeanDefinition;
import org.joda.beans.ImmutableBean;
import org.joda.beans.ImmutableDefaults;
import org.joda.beans.ImmutableValidator;
import org.joda.beans.JodaBeanUtils;
import org.joda.beans.MetaProperty;
import org.joda.beans.Property;
import org.joda.beans.PropertyDefinition;
import org.joda.beans.impl.direct.DirectFieldsBeanBuilder;
import org.joda.beans.impl.direct.DirectMetaBean;
import org.joda.beans.impl.direct.DirectMetaProperty;
import org.joda.beans.impl.direct.DirectMetaPropertyMap;

import com.opengamma.strata.basics.PutCall;
import com.opengamma.strata.basics.market.ReferenceData;
import com.opengamma.strata.basics.value.Rounding;
import com.opengamma.strata.collect.ArgChecker;
import com.opengamma.strata.collect.id.StandardId;
import com.opengamma.strata.product.ResolvedProduct;
import com.opengamma.strata.product.common.FutureOptionPremiumStyle;

/**
 * A futures option contract based on a basket of fixed coupon bonds, resolved for pricing.
 * <p>
 * This is the resolved form of {@link BondFutureOption} and is an input to the pricers.
 * Applications will typically create a {@code ResolvedBondFutureOption} from a {@code BondFutureOption}
 * using {@link BondFutureOption#resolve(ReferenceData)}.
 * <p>
 * A {@code ResolvedBondFutureOption} is bound to data that changes over time, such as holiday calendars.
 * If the data changes, such as the addition of a new holiday, the resolved form will not be updated.
 * Care must be taken when placing the resolved form in a cache or persistence layer.
 */
@BeanDefinition
public final class ResolvedBondFutureOption
    implements ResolvedProduct, ImmutableBean, Serializable {

  /**
   * Whether the option is put or call.
   * <p>
   * A call gives the owner the right, but not obligation, to buy the underlying at
   * an agreed price in the future. A put gives a similar option to sell.
   */
  @PropertyDefinition
  private final PutCall putCall;
  /**
   * The strike price, represented in decimal form.
   * <p>
   * This is the price at which the option applies and refers to the price of the underlying future.
   * This must be represented in decimal form.
   */
  @PropertyDefinition
  private final double strikePrice;
  /**
   * The expiry of the option.
   * <p>
   * The date must not be after last trade date of the underlying future.
   */
  @PropertyDefinition(validate = "notNull")
  private final ZonedDateTime expiry;
  /**
   * The style of the option premium.
   * <p>
   * The two options are daily margining and upfront premium.
   */
  @PropertyDefinition(validate = "notNull")
  private final FutureOptionPremiumStyle premiumStyle;
  /**
   * The definition of how to round the option price, defaulted to no rounding.
   * <p>
   * The price is represented in decimal form, not percentage form.
   * As such, the decimal places expressed by the rounding refers to this decimal form.
   * For example, the common market price of 99.7125 is represented as 0.997125 which
   * has 6 decimal places.
   */
  @PropertyDefinition(validate = "notNull")
  private final Rounding rounding;
  /**
   * The underlying future.
   */
  @PropertyDefinition(validate = "notNull")
  private final ResolvedBondFuture underlying;
  /**
   * The identifier of the underlying future security.
   */
  @PropertyDefinition(validate = "notNull")
  private final StandardId underlyingSecurityStandardId;

  //-------------------------------------------------------------------------
  @ImmutableValidator
  private void validate() {
    LocalDate lastTradeDate = underlying.getLastTradeDate();
    ArgChecker.inOrderOrEqual(expiry.toLocalDate(), lastTradeDate, "expiry.date", "underlying.lastTradeDate");
  }

  @ImmutableDefaults
  private static void applyDefaults(Builder builder) {
    builder.rounding(Rounding.none());
  }

  //-------------------------------------------------------------------------
  /**
   * Gets the expiry date of the option.
   * 
   * @return the expiry date
   */
  public LocalDate getExpiryDate() {
    return expiry.toLocalDate();
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code ResolvedBondFutureOption}.
   * @return the meta-bean, not null
   */
  public static ResolvedBondFutureOption.Meta meta() {
    return ResolvedBondFutureOption.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(ResolvedBondFutureOption.Meta.INSTANCE);
  }

  /**
   * The serialization version id.
   */
  private static final long serialVersionUID = 1L;

  /**
   * Returns a builder used to create an instance of the bean.
   * @return the builder, not null
   */
  public static ResolvedBondFutureOption.Builder builder() {
    return new ResolvedBondFutureOption.Builder();
  }

  private ResolvedBondFutureOption(
      PutCall putCall,
      double strikePrice,
      ZonedDateTime expiry,
      FutureOptionPremiumStyle premiumStyle,
      Rounding rounding,
      ResolvedBondFuture underlying,
      StandardId underlyingSecurityStandardId) {
    JodaBeanUtils.notNull(expiry, "expiry");
    JodaBeanUtils.notNull(premiumStyle, "premiumStyle");
    JodaBeanUtils.notNull(rounding, "rounding");
    JodaBeanUtils.notNull(underlying, "underlying");
    JodaBeanUtils.notNull(underlyingSecurityStandardId, "underlyingSecurityStandardId");
    this.putCall = putCall;
    this.strikePrice = strikePrice;
    this.expiry = expiry;
    this.premiumStyle = premiumStyle;
    this.rounding = rounding;
    this.underlying = underlying;
    this.underlyingSecurityStandardId = underlyingSecurityStandardId;
    validate();
  }

  @Override
  public ResolvedBondFutureOption.Meta metaBean() {
    return ResolvedBondFutureOption.Meta.INSTANCE;
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
   * Gets whether the option is put or call.
   * <p>
   * A call gives the owner the right, but not obligation, to buy the underlying at
   * an agreed price in the future. A put gives a similar option to sell.
   * @return the value of the property
   */
  public PutCall getPutCall() {
    return putCall;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the strike price, represented in decimal form.
   * <p>
   * This is the price at which the option applies and refers to the price of the underlying future.
   * This must be represented in decimal form.
   * @return the value of the property
   */
  public double getStrikePrice() {
    return strikePrice;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the expiry of the option.
   * <p>
   * The date must not be after last trade date of the underlying future.
   * @return the value of the property, not null
   */
  public ZonedDateTime getExpiry() {
    return expiry;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the style of the option premium.
   * <p>
   * The two options are daily margining and upfront premium.
   * @return the value of the property, not null
   */
  public FutureOptionPremiumStyle getPremiumStyle() {
    return premiumStyle;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the definition of how to round the option price, defaulted to no rounding.
   * <p>
   * The price is represented in decimal form, not percentage form.
   * As such, the decimal places expressed by the rounding refers to this decimal form.
   * For example, the common market price of 99.7125 is represented as 0.997125 which
   * has 6 decimal places.
   * @return the value of the property, not null
   */
  public Rounding getRounding() {
    return rounding;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the underlying future.
   * @return the value of the property, not null
   */
  public ResolvedBondFuture getUnderlying() {
    return underlying;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the identifier of the underlying future security.
   * @return the value of the property, not null
   */
  public StandardId getUnderlyingSecurityStandardId() {
    return underlyingSecurityStandardId;
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
      ResolvedBondFutureOption other = (ResolvedBondFutureOption) obj;
      return JodaBeanUtils.equal(putCall, other.putCall) &&
          JodaBeanUtils.equal(strikePrice, other.strikePrice) &&
          JodaBeanUtils.equal(expiry, other.expiry) &&
          JodaBeanUtils.equal(premiumStyle, other.premiumStyle) &&
          JodaBeanUtils.equal(rounding, other.rounding) &&
          JodaBeanUtils.equal(underlying, other.underlying) &&
          JodaBeanUtils.equal(underlyingSecurityStandardId, other.underlyingSecurityStandardId);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = getClass().hashCode();
    hash = hash * 31 + JodaBeanUtils.hashCode(putCall);
    hash = hash * 31 + JodaBeanUtils.hashCode(strikePrice);
    hash = hash * 31 + JodaBeanUtils.hashCode(expiry);
    hash = hash * 31 + JodaBeanUtils.hashCode(premiumStyle);
    hash = hash * 31 + JodaBeanUtils.hashCode(rounding);
    hash = hash * 31 + JodaBeanUtils.hashCode(underlying);
    hash = hash * 31 + JodaBeanUtils.hashCode(underlyingSecurityStandardId);
    return hash;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(256);
    buf.append("ResolvedBondFutureOption{");
    buf.append("putCall").append('=').append(putCall).append(',').append(' ');
    buf.append("strikePrice").append('=').append(strikePrice).append(',').append(' ');
    buf.append("expiry").append('=').append(expiry).append(',').append(' ');
    buf.append("premiumStyle").append('=').append(premiumStyle).append(',').append(' ');
    buf.append("rounding").append('=').append(rounding).append(',').append(' ');
    buf.append("underlying").append('=').append(underlying).append(',').append(' ');
    buf.append("underlyingSecurityStandardId").append('=').append(JodaBeanUtils.toString(underlyingSecurityStandardId));
    buf.append('}');
    return buf.toString();
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code ResolvedBondFutureOption}.
   */
  public static final class Meta extends DirectMetaBean {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code putCall} property.
     */
    private final MetaProperty<PutCall> putCall = DirectMetaProperty.ofImmutable(
        this, "putCall", ResolvedBondFutureOption.class, PutCall.class);
    /**
     * The meta-property for the {@code strikePrice} property.
     */
    private final MetaProperty<Double> strikePrice = DirectMetaProperty.ofImmutable(
        this, "strikePrice", ResolvedBondFutureOption.class, Double.TYPE);
    /**
     * The meta-property for the {@code expiry} property.
     */
    private final MetaProperty<ZonedDateTime> expiry = DirectMetaProperty.ofImmutable(
        this, "expiry", ResolvedBondFutureOption.class, ZonedDateTime.class);
    /**
     * The meta-property for the {@code premiumStyle} property.
     */
    private final MetaProperty<FutureOptionPremiumStyle> premiumStyle = DirectMetaProperty.ofImmutable(
        this, "premiumStyle", ResolvedBondFutureOption.class, FutureOptionPremiumStyle.class);
    /**
     * The meta-property for the {@code rounding} property.
     */
    private final MetaProperty<Rounding> rounding = DirectMetaProperty.ofImmutable(
        this, "rounding", ResolvedBondFutureOption.class, Rounding.class);
    /**
     * The meta-property for the {@code underlying} property.
     */
    private final MetaProperty<ResolvedBondFuture> underlying = DirectMetaProperty.ofImmutable(
        this, "underlying", ResolvedBondFutureOption.class, ResolvedBondFuture.class);
    /**
     * The meta-property for the {@code underlyingSecurityStandardId} property.
     */
    private final MetaProperty<StandardId> underlyingSecurityStandardId = DirectMetaProperty.ofImmutable(
        this, "underlyingSecurityStandardId", ResolvedBondFutureOption.class, StandardId.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> metaPropertyMap$ = new DirectMetaPropertyMap(
        this, null,
        "putCall",
        "strikePrice",
        "expiry",
        "premiumStyle",
        "rounding",
        "underlying",
        "underlyingSecurityStandardId");

    /**
     * Restricted constructor.
     */
    private Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -219971059:  // putCall
          return putCall;
        case 50946231:  // strikePrice
          return strikePrice;
        case -1289159373:  // expiry
          return expiry;
        case -1257652838:  // premiumStyle
          return premiumStyle;
        case -142444:  // rounding
          return rounding;
        case -1770633379:  // underlying
          return underlying;
        case 1733588565:  // underlyingSecurityStandardId
          return underlyingSecurityStandardId;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public ResolvedBondFutureOption.Builder builder() {
      return new ResolvedBondFutureOption.Builder();
    }

    @Override
    public Class<? extends ResolvedBondFutureOption> beanType() {
      return ResolvedBondFutureOption.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code putCall} property.
     * @return the meta-property, not null
     */
    public MetaProperty<PutCall> putCall() {
      return putCall;
    }

    /**
     * The meta-property for the {@code strikePrice} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Double> strikePrice() {
      return strikePrice;
    }

    /**
     * The meta-property for the {@code expiry} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ZonedDateTime> expiry() {
      return expiry;
    }

    /**
     * The meta-property for the {@code premiumStyle} property.
     * @return the meta-property, not null
     */
    public MetaProperty<FutureOptionPremiumStyle> premiumStyle() {
      return premiumStyle;
    }

    /**
     * The meta-property for the {@code rounding} property.
     * @return the meta-property, not null
     */
    public MetaProperty<Rounding> rounding() {
      return rounding;
    }

    /**
     * The meta-property for the {@code underlying} property.
     * @return the meta-property, not null
     */
    public MetaProperty<ResolvedBondFuture> underlying() {
      return underlying;
    }

    /**
     * The meta-property for the {@code underlyingSecurityStandardId} property.
     * @return the meta-property, not null
     */
    public MetaProperty<StandardId> underlyingSecurityStandardId() {
      return underlyingSecurityStandardId;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -219971059:  // putCall
          return ((ResolvedBondFutureOption) bean).getPutCall();
        case 50946231:  // strikePrice
          return ((ResolvedBondFutureOption) bean).getStrikePrice();
        case -1289159373:  // expiry
          return ((ResolvedBondFutureOption) bean).getExpiry();
        case -1257652838:  // premiumStyle
          return ((ResolvedBondFutureOption) bean).getPremiumStyle();
        case -142444:  // rounding
          return ((ResolvedBondFutureOption) bean).getRounding();
        case -1770633379:  // underlying
          return ((ResolvedBondFutureOption) bean).getUnderlying();
        case 1733588565:  // underlyingSecurityStandardId
          return ((ResolvedBondFutureOption) bean).getUnderlyingSecurityStandardId();
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
   * The bean-builder for {@code ResolvedBondFutureOption}.
   */
  public static final class Builder extends DirectFieldsBeanBuilder<ResolvedBondFutureOption> {

    private PutCall putCall;
    private double strikePrice;
    private ZonedDateTime expiry;
    private FutureOptionPremiumStyle premiumStyle;
    private Rounding rounding;
    private ResolvedBondFuture underlying;
    private StandardId underlyingSecurityStandardId;

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
    private Builder(ResolvedBondFutureOption beanToCopy) {
      this.putCall = beanToCopy.getPutCall();
      this.strikePrice = beanToCopy.getStrikePrice();
      this.expiry = beanToCopy.getExpiry();
      this.premiumStyle = beanToCopy.getPremiumStyle();
      this.rounding = beanToCopy.getRounding();
      this.underlying = beanToCopy.getUnderlying();
      this.underlyingSecurityStandardId = beanToCopy.getUnderlyingSecurityStandardId();
    }

    //-----------------------------------------------------------------------
    @Override
    public Object get(String propertyName) {
      switch (propertyName.hashCode()) {
        case -219971059:  // putCall
          return putCall;
        case 50946231:  // strikePrice
          return strikePrice;
        case -1289159373:  // expiry
          return expiry;
        case -1257652838:  // premiumStyle
          return premiumStyle;
        case -142444:  // rounding
          return rounding;
        case -1770633379:  // underlying
          return underlying;
        case 1733588565:  // underlyingSecurityStandardId
          return underlyingSecurityStandardId;
        default:
          throw new NoSuchElementException("Unknown property: " + propertyName);
      }
    }

    @Override
    public Builder set(String propertyName, Object newValue) {
      switch (propertyName.hashCode()) {
        case -219971059:  // putCall
          this.putCall = (PutCall) newValue;
          break;
        case 50946231:  // strikePrice
          this.strikePrice = (Double) newValue;
          break;
        case -1289159373:  // expiry
          this.expiry = (ZonedDateTime) newValue;
          break;
        case -1257652838:  // premiumStyle
          this.premiumStyle = (FutureOptionPremiumStyle) newValue;
          break;
        case -142444:  // rounding
          this.rounding = (Rounding) newValue;
          break;
        case -1770633379:  // underlying
          this.underlying = (ResolvedBondFuture) newValue;
          break;
        case 1733588565:  // underlyingSecurityStandardId
          this.underlyingSecurityStandardId = (StandardId) newValue;
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
    public ResolvedBondFutureOption build() {
      return new ResolvedBondFutureOption(
          putCall,
          strikePrice,
          expiry,
          premiumStyle,
          rounding,
          underlying,
          underlyingSecurityStandardId);
    }

    //-----------------------------------------------------------------------
    /**
     * Sets whether the option is put or call.
     * <p>
     * A call gives the owner the right, but not obligation, to buy the underlying at
     * an agreed price in the future. A put gives a similar option to sell.
     * @param putCall  the new value
     * @return this, for chaining, not null
     */
    public Builder putCall(PutCall putCall) {
      this.putCall = putCall;
      return this;
    }

    /**
     * Sets the strike price, represented in decimal form.
     * <p>
     * This is the price at which the option applies and refers to the price of the underlying future.
     * This must be represented in decimal form.
     * @param strikePrice  the new value
     * @return this, for chaining, not null
     */
    public Builder strikePrice(double strikePrice) {
      this.strikePrice = strikePrice;
      return this;
    }

    /**
     * Sets the expiry of the option.
     * <p>
     * The date must not be after last trade date of the underlying future.
     * @param expiry  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder expiry(ZonedDateTime expiry) {
      JodaBeanUtils.notNull(expiry, "expiry");
      this.expiry = expiry;
      return this;
    }

    /**
     * Sets the style of the option premium.
     * <p>
     * The two options are daily margining and upfront premium.
     * @param premiumStyle  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder premiumStyle(FutureOptionPremiumStyle premiumStyle) {
      JodaBeanUtils.notNull(premiumStyle, "premiumStyle");
      this.premiumStyle = premiumStyle;
      return this;
    }

    /**
     * Sets the definition of how to round the option price, defaulted to no rounding.
     * <p>
     * The price is represented in decimal form, not percentage form.
     * As such, the decimal places expressed by the rounding refers to this decimal form.
     * For example, the common market price of 99.7125 is represented as 0.997125 which
     * has 6 decimal places.
     * @param rounding  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder rounding(Rounding rounding) {
      JodaBeanUtils.notNull(rounding, "rounding");
      this.rounding = rounding;
      return this;
    }

    /**
     * Sets the underlying future.
     * @param underlying  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder underlying(ResolvedBondFuture underlying) {
      JodaBeanUtils.notNull(underlying, "underlying");
      this.underlying = underlying;
      return this;
    }

    /**
     * Sets the identifier of the underlying future security.
     * @param underlyingSecurityStandardId  the new value, not null
     * @return this, for chaining, not null
     */
    public Builder underlyingSecurityStandardId(StandardId underlyingSecurityStandardId) {
      JodaBeanUtils.notNull(underlyingSecurityStandardId, "underlyingSecurityStandardId");
      this.underlyingSecurityStandardId = underlyingSecurityStandardId;
      return this;
    }

    //-----------------------------------------------------------------------
    @Override
    public String toString() {
      StringBuilder buf = new StringBuilder(256);
      buf.append("ResolvedBondFutureOption.Builder{");
      buf.append("putCall").append('=').append(JodaBeanUtils.toString(putCall)).append(',').append(' ');
      buf.append("strikePrice").append('=').append(JodaBeanUtils.toString(strikePrice)).append(',').append(' ');
      buf.append("expiry").append('=').append(JodaBeanUtils.toString(expiry)).append(',').append(' ');
      buf.append("premiumStyle").append('=').append(JodaBeanUtils.toString(premiumStyle)).append(',').append(' ');
      buf.append("rounding").append('=').append(JodaBeanUtils.toString(rounding)).append(',').append(' ');
      buf.append("underlying").append('=').append(JodaBeanUtils.toString(underlying)).append(',').append(' ');
      buf.append("underlyingSecurityStandardId").append('=').append(JodaBeanUtils.toString(underlyingSecurityStandardId));
      buf.append('}');
      return buf.toString();
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
