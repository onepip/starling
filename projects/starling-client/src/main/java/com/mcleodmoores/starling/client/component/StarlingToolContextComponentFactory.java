package com.mcleodmoores.starling.client.component;

import java.util.HashMap;
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

import com.mcleodmoores.starling.client.config.ConfigManager;
import com.mcleodmoores.starling.client.marketdata.MarketDataManager;
import com.mcleodmoores.starling.client.portfolio.PortfolioManager;
import com.mcleodmoores.starling.client.results.AnalyticService;
import com.mcleodmoores.starling.client.stateless.StatelessAnalyticService;
import com.opengamma.component.ComponentRepository;
import com.opengamma.component.factory.tool.ToolContextComponentFactory;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolver;

/**
 * Component factory for Starling tool context.
 */
@BeanDefinition
public class StarlingToolContextComponentFactory extends ToolContextComponentFactory {
  /**
   * The historical time series resolver.
   */
  @PropertyDefinition(validate = "notNull")
  private HistoricalTimeSeriesResolver _historicalTimeSeriesResolver;

  /**
   * Analytic service working on persistent portfolios.
   */
  @PropertyDefinition(validate = "notNull")
  private AnalyticService _analyticService;

  /**
   * Analytic service that takes transient portfolios.
   */
  @PropertyDefinition(validate = "notNull")
  private StatelessAnalyticService _statelessAnalyticService;

  /**
   * Market data manager.
   */
  @PropertyDefinition(validate = "notNull")
  private MarketDataManager _marketDataManager;

  /**
   * Portfolio manager.
   */
  @PropertyDefinition(validate = "notNull")
  private PortfolioManager _portfolioManager;

  /**
   * Config manager.
   */
  @PropertyDefinition(validate = "notNull")
  private ConfigManager _configManager;

  @Override
  public void init(final ComponentRepository repo, final LinkedHashMap<String, String> configuration) {
    final StarlingToolContext context = new StarlingToolContext();
    final Map<String, MetaProperty<?>> mapTarget = new HashMap<>(context.metaBean().metaPropertyMap());
    mapTarget.keySet().retainAll(this.metaBean().metaPropertyMap().keySet());
    for (final MetaProperty<?> mp : mapTarget.values()) {
      mp.set(context, mp.get(this));
    }
    context.setContextManager(repo);
    repo.registerComponent(StarlingToolContext.class, getClassifier(), context);
  }

  //------------------------- AUTOGENERATED START -------------------------
  ///CLOVER:OFF
  /**
   * The meta-bean for {@code StarlingToolContextComponentFactory}.
   * @return the meta-bean, not null
   */
  public static StarlingToolContextComponentFactory.Meta meta() {
    return StarlingToolContextComponentFactory.Meta.INSTANCE;
  }

  static {
    JodaBeanUtils.registerMetaBean(StarlingToolContextComponentFactory.Meta.INSTANCE);
  }

  @Override
  public StarlingToolContextComponentFactory.Meta metaBean() {
    return StarlingToolContextComponentFactory.Meta.INSTANCE;
  }

  //-----------------------------------------------------------------------
  /**
   * Gets the historical time series resolver.
   * @return the value of the property, not null
   */
  public HistoricalTimeSeriesResolver getHistoricalTimeSeriesResolver() {
    return _historicalTimeSeriesResolver;
  }

  /**
   * Sets the historical time series resolver.
   * @param historicalTimeSeriesResolver  the new value of the property, not null
   */
  public void setHistoricalTimeSeriesResolver(HistoricalTimeSeriesResolver historicalTimeSeriesResolver) {
    JodaBeanUtils.notNull(historicalTimeSeriesResolver, "historicalTimeSeriesResolver");
    this._historicalTimeSeriesResolver = historicalTimeSeriesResolver;
  }

  /**
   * Gets the the {@code historicalTimeSeriesResolver} property.
   * @return the property, not null
   */
  public final Property<HistoricalTimeSeriesResolver> historicalTimeSeriesResolver() {
    return metaBean().historicalTimeSeriesResolver().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets analytic service working on persistent portfolios.
   * @return the value of the property, not null
   */
  public AnalyticService getAnalyticService() {
    return _analyticService;
  }

  /**
   * Sets analytic service working on persistent portfolios.
   * @param analyticService  the new value of the property, not null
   */
  public void setAnalyticService(AnalyticService analyticService) {
    JodaBeanUtils.notNull(analyticService, "analyticService");
    this._analyticService = analyticService;
  }

  /**
   * Gets the the {@code analyticService} property.
   * @return the property, not null
   */
  public final Property<AnalyticService> analyticService() {
    return metaBean().analyticService().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets analytic service that takes transient portfolios.
   * @return the value of the property, not null
   */
  public StatelessAnalyticService getStatelessAnalyticService() {
    return _statelessAnalyticService;
  }

  /**
   * Sets analytic service that takes transient portfolios.
   * @param statelessAnalyticService  the new value of the property, not null
   */
  public void setStatelessAnalyticService(StatelessAnalyticService statelessAnalyticService) {
    JodaBeanUtils.notNull(statelessAnalyticService, "statelessAnalyticService");
    this._statelessAnalyticService = statelessAnalyticService;
  }

  /**
   * Gets the the {@code statelessAnalyticService} property.
   * @return the property, not null
   */
  public final Property<StatelessAnalyticService> statelessAnalyticService() {
    return metaBean().statelessAnalyticService().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets market data manager.
   * @return the value of the property, not null
   */
  public MarketDataManager getMarketDataManager() {
    return _marketDataManager;
  }

  /**
   * Sets market data manager.
   * @param marketDataManager  the new value of the property, not null
   */
  public void setMarketDataManager(MarketDataManager marketDataManager) {
    JodaBeanUtils.notNull(marketDataManager, "marketDataManager");
    this._marketDataManager = marketDataManager;
  }

  /**
   * Gets the the {@code marketDataManager} property.
   * @return the property, not null
   */
  public final Property<MarketDataManager> marketDataManager() {
    return metaBean().marketDataManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets portfolio manager.
   * @return the value of the property, not null
   */
  public PortfolioManager getPortfolioManager() {
    return _portfolioManager;
  }

  /**
   * Sets portfolio manager.
   * @param portfolioManager  the new value of the property, not null
   */
  public void setPortfolioManager(PortfolioManager portfolioManager) {
    JodaBeanUtils.notNull(portfolioManager, "portfolioManager");
    this._portfolioManager = portfolioManager;
  }

  /**
   * Gets the the {@code portfolioManager} property.
   * @return the property, not null
   */
  public final Property<PortfolioManager> portfolioManager() {
    return metaBean().portfolioManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  /**
   * Gets config manager.
   * @return the value of the property, not null
   */
  public ConfigManager getConfigManager() {
    return _configManager;
  }

  /**
   * Sets config manager.
   * @param configManager  the new value of the property, not null
   */
  public void setConfigManager(ConfigManager configManager) {
    JodaBeanUtils.notNull(configManager, "configManager");
    this._configManager = configManager;
  }

  /**
   * Gets the the {@code configManager} property.
   * @return the property, not null
   */
  public final Property<ConfigManager> configManager() {
    return metaBean().configManager().createProperty(this);
  }

  //-----------------------------------------------------------------------
  @Override
  public StarlingToolContextComponentFactory clone() {
    return JodaBeanUtils.cloneAlways(this);
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }
    if (obj != null && obj.getClass() == this.getClass()) {
      StarlingToolContextComponentFactory other = (StarlingToolContextComponentFactory) obj;
      return JodaBeanUtils.equal(getHistoricalTimeSeriesResolver(), other.getHistoricalTimeSeriesResolver()) &&
          JodaBeanUtils.equal(getAnalyticService(), other.getAnalyticService()) &&
          JodaBeanUtils.equal(getStatelessAnalyticService(), other.getStatelessAnalyticService()) &&
          JodaBeanUtils.equal(getMarketDataManager(), other.getMarketDataManager()) &&
          JodaBeanUtils.equal(getPortfolioManager(), other.getPortfolioManager()) &&
          JodaBeanUtils.equal(getConfigManager(), other.getConfigManager()) &&
          super.equals(obj);
    }
    return false;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = hash * 31 + JodaBeanUtils.hashCode(getHistoricalTimeSeriesResolver());
    hash = hash * 31 + JodaBeanUtils.hashCode(getAnalyticService());
    hash = hash * 31 + JodaBeanUtils.hashCode(getStatelessAnalyticService());
    hash = hash * 31 + JodaBeanUtils.hashCode(getMarketDataManager());
    hash = hash * 31 + JodaBeanUtils.hashCode(getPortfolioManager());
    hash = hash * 31 + JodaBeanUtils.hashCode(getConfigManager());
    return hash ^ super.hashCode();
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder(224);
    buf.append("StarlingToolContextComponentFactory{");
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
    buf.append("historicalTimeSeriesResolver").append('=').append(JodaBeanUtils.toString(getHistoricalTimeSeriesResolver())).append(',').append(' ');
    buf.append("analyticService").append('=').append(JodaBeanUtils.toString(getAnalyticService())).append(',').append(' ');
    buf.append("statelessAnalyticService").append('=').append(JodaBeanUtils.toString(getStatelessAnalyticService())).append(',').append(' ');
    buf.append("marketDataManager").append('=').append(JodaBeanUtils.toString(getMarketDataManager())).append(',').append(' ');
    buf.append("portfolioManager").append('=').append(JodaBeanUtils.toString(getPortfolioManager())).append(',').append(' ');
    buf.append("configManager").append('=').append(JodaBeanUtils.toString(getConfigManager())).append(',').append(' ');
  }

  //-----------------------------------------------------------------------
  /**
   * The meta-bean for {@code StarlingToolContextComponentFactory}.
   */
  public static class Meta extends ToolContextComponentFactory.Meta {
    /**
     * The singleton instance of the meta-bean.
     */
    static final Meta INSTANCE = new Meta();

    /**
     * The meta-property for the {@code historicalTimeSeriesResolver} property.
     */
    private final MetaProperty<HistoricalTimeSeriesResolver> _historicalTimeSeriesResolver = DirectMetaProperty.ofReadWrite(
        this, "historicalTimeSeriesResolver", StarlingToolContextComponentFactory.class, HistoricalTimeSeriesResolver.class);
    /**
     * The meta-property for the {@code analyticService} property.
     */
    private final MetaProperty<AnalyticService> _analyticService = DirectMetaProperty.ofReadWrite(
        this, "analyticService", StarlingToolContextComponentFactory.class, AnalyticService.class);
    /**
     * The meta-property for the {@code statelessAnalyticService} property.
     */
    private final MetaProperty<StatelessAnalyticService> _statelessAnalyticService = DirectMetaProperty.ofReadWrite(
        this, "statelessAnalyticService", StarlingToolContextComponentFactory.class, StatelessAnalyticService.class);
    /**
     * The meta-property for the {@code marketDataManager} property.
     */
    private final MetaProperty<MarketDataManager> _marketDataManager = DirectMetaProperty.ofReadWrite(
        this, "marketDataManager", StarlingToolContextComponentFactory.class, MarketDataManager.class);
    /**
     * The meta-property for the {@code portfolioManager} property.
     */
    private final MetaProperty<PortfolioManager> _portfolioManager = DirectMetaProperty.ofReadWrite(
        this, "portfolioManager", StarlingToolContextComponentFactory.class, PortfolioManager.class);
    /**
     * The meta-property for the {@code configManager} property.
     */
    private final MetaProperty<ConfigManager> _configManager = DirectMetaProperty.ofReadWrite(
        this, "configManager", StarlingToolContextComponentFactory.class, ConfigManager.class);
    /**
     * The meta-properties.
     */
    private final Map<String, MetaProperty<?>> _metaPropertyMap$ = new DirectMetaPropertyMap(
        this, (DirectMetaPropertyMap) super.metaPropertyMap(),
        "historicalTimeSeriesResolver",
        "analyticService",
        "statelessAnalyticService",
        "marketDataManager",
        "portfolioManager",
        "configManager");

    /**
     * Restricted constructor.
     */
    protected Meta() {
    }

    @Override
    protected MetaProperty<?> metaPropertyGet(String propertyName) {
      switch (propertyName.hashCode()) {
        case -946313676:  // historicalTimeSeriesResolver
          return _historicalTimeSeriesResolver;
        case -1626792088:  // analyticService
          return _analyticService;
        case 702666846:  // statelessAnalyticService
          return _statelessAnalyticService;
        case 253426375:  // marketDataManager
          return _marketDataManager;
        case 1824104773:  // portfolioManager
          return _portfolioManager;
        case 317085195:  // configManager
          return _configManager;
      }
      return super.metaPropertyGet(propertyName);
    }

    @Override
    public BeanBuilder<? extends StarlingToolContextComponentFactory> builder() {
      return new DirectBeanBuilder<StarlingToolContextComponentFactory>(new StarlingToolContextComponentFactory());
    }

    @Override
    public Class<? extends StarlingToolContextComponentFactory> beanType() {
      return StarlingToolContextComponentFactory.class;
    }

    @Override
    public Map<String, MetaProperty<?>> metaPropertyMap() {
      return _metaPropertyMap$;
    }

    //-----------------------------------------------------------------------
    /**
     * The meta-property for the {@code historicalTimeSeriesResolver} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<HistoricalTimeSeriesResolver> historicalTimeSeriesResolver() {
      return _historicalTimeSeriesResolver;
    }

    /**
     * The meta-property for the {@code analyticService} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<AnalyticService> analyticService() {
      return _analyticService;
    }

    /**
     * The meta-property for the {@code statelessAnalyticService} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<StatelessAnalyticService> statelessAnalyticService() {
      return _statelessAnalyticService;
    }

    /**
     * The meta-property for the {@code marketDataManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<MarketDataManager> marketDataManager() {
      return _marketDataManager;
    }

    /**
     * The meta-property for the {@code portfolioManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<PortfolioManager> portfolioManager() {
      return _portfolioManager;
    }

    /**
     * The meta-property for the {@code configManager} property.
     * @return the meta-property, not null
     */
    public final MetaProperty<ConfigManager> configManager() {
      return _configManager;
    }

    //-----------------------------------------------------------------------
    @Override
    protected Object propertyGet(Bean bean, String propertyName, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -946313676:  // historicalTimeSeriesResolver
          return ((StarlingToolContextComponentFactory) bean).getHistoricalTimeSeriesResolver();
        case -1626792088:  // analyticService
          return ((StarlingToolContextComponentFactory) bean).getAnalyticService();
        case 702666846:  // statelessAnalyticService
          return ((StarlingToolContextComponentFactory) bean).getStatelessAnalyticService();
        case 253426375:  // marketDataManager
          return ((StarlingToolContextComponentFactory) bean).getMarketDataManager();
        case 1824104773:  // portfolioManager
          return ((StarlingToolContextComponentFactory) bean).getPortfolioManager();
        case 317085195:  // configManager
          return ((StarlingToolContextComponentFactory) bean).getConfigManager();
      }
      return super.propertyGet(bean, propertyName, quiet);
    }

    @Override
    protected void propertySet(Bean bean, String propertyName, Object newValue, boolean quiet) {
      switch (propertyName.hashCode()) {
        case -946313676:  // historicalTimeSeriesResolver
          ((StarlingToolContextComponentFactory) bean).setHistoricalTimeSeriesResolver((HistoricalTimeSeriesResolver) newValue);
          return;
        case -1626792088:  // analyticService
          ((StarlingToolContextComponentFactory) bean).setAnalyticService((AnalyticService) newValue);
          return;
        case 702666846:  // statelessAnalyticService
          ((StarlingToolContextComponentFactory) bean).setStatelessAnalyticService((StatelessAnalyticService) newValue);
          return;
        case 253426375:  // marketDataManager
          ((StarlingToolContextComponentFactory) bean).setMarketDataManager((MarketDataManager) newValue);
          return;
        case 1824104773:  // portfolioManager
          ((StarlingToolContextComponentFactory) bean).setPortfolioManager((PortfolioManager) newValue);
          return;
        case 317085195:  // configManager
          ((StarlingToolContextComponentFactory) bean).setConfigManager((ConfigManager) newValue);
          return;
      }
      super.propertySet(bean, propertyName, newValue, quiet);
    }

    @Override
    protected void validate(Bean bean) {
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._historicalTimeSeriesResolver, "historicalTimeSeriesResolver");
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._analyticService, "analyticService");
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._statelessAnalyticService, "statelessAnalyticService");
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._marketDataManager, "marketDataManager");
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._portfolioManager, "portfolioManager");
      JodaBeanUtils.notNull(((StarlingToolContextComponentFactory) bean)._configManager, "configManager");
      super.validate(bean);
    }

  }

  ///CLOVER:ON
  //-------------------------- AUTOGENERATED END --------------------------
}
