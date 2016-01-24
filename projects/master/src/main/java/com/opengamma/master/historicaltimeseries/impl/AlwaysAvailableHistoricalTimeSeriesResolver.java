/**
 * Copyright (C) 2016 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.master.historicaltimeseries.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.threeten.bp.LocalDate;

import com.opengamma.id.ExternalIdBundle;
import com.opengamma.id.ExternalIdBundleWithDates;
import com.opengamma.id.ObjectIdSupplier;
import com.opengamma.id.UniqueId;
import com.opengamma.id.UniqueIdSupplier;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesMaster;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolutionResult;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolver;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesResolverWithBasicChangeManager;
import com.opengamma.master.historicaltimeseries.HistoricalTimeSeriesSelector;
import com.opengamma.master.historicaltimeseries.ManageableHistoricalTimeSeriesInfo;

/**
 * A time series resolver that will always be able to resolve a time series for the identifiers. If
 * the series is available from the underlying historical time series master, the resolution result contains
 * this information. Otherwise, a unique id for the identifiers is generated and the meta-data of
 * the requested time series (e.g. data source, data provider) is stored internally.
 * <p>
 * This class should only be used for testing (e.g. determining which time series are required to complete
 * a calculation).
 */
public class AlwaysAvailableHistoricalTimeSeriesResolver extends HistoricalTimeSeriesResolverWithBasicChangeManager {
  /**
   * The scheme for time series that are not available from the master.
   */
  public static final String SCHEME = "AlwaysAvailableHts";
  /** The underlying resolver */
  private final HistoricalTimeSeriesResolver _underlyingResolver;
  /** A unique id supplier */
  private final UniqueIdSupplier _uidSupplier = new UniqueIdSupplier(SCHEME);
  /** An object id supplier */
  private final ObjectIdSupplier _oidSupplier = new ObjectIdSupplier(SCHEME);
  /** A map of id bundle to time series info */
  private final ConcurrentHashMap<UniqueId, ManageableHistoricalTimeSeriesInfo> _idBundleMap = new ConcurrentHashMap<>();

  /**
   * Creates a resolver.
   * @param selector  a time series selector, not null
   * @param master  a time series master, not null
   */
  public AlwaysAvailableHistoricalTimeSeriesResolver(final HistoricalTimeSeriesSelector selector, final HistoricalTimeSeriesMaster master) {
    _underlyingResolver = new DefaultHistoricalTimeSeriesResolver(selector, master);
  }

  @Override
  public HistoricalTimeSeriesResolutionResult resolve(final ExternalIdBundle identifierBundle, final LocalDate identifierValidityDate, final String dataSource, final String dataProvider,
      final String dataField, final String resolutionKey) {
    final HistoricalTimeSeriesResolutionResult resultFromUnderlying = _underlyingResolver.resolve(identifierBundle, identifierValidityDate, dataSource, dataProvider, dataField, resolutionKey);
    if (resultFromUnderlying != null) {
      return resultFromUnderlying;
    }
    final ManageableHistoricalTimeSeriesInfo info = new ManageableHistoricalTimeSeriesInfo();
    final UniqueId uniqueId = _uidSupplier.get();
    info.setDataField(dataField);
    info.setDataProvider(dataProvider);
    info.setDataSource(dataSource);
    info.setExternalIdBundle(ExternalIdBundleWithDates.of(identifierBundle));
    info.setUniqueId(uniqueId);
    info.setTimeSeriesObjectId(_oidSupplier.get());
    _idBundleMap.put(uniqueId, info);
    return new HistoricalTimeSeriesResolutionResult(info);
  }

  /**
   * Gets the time series info (data field, data provider, data source and external ids) for a time series that
   * could not be resolved by the underlying resolver using the unique id that is generated by this resolver.
   * This method returns null if the scheme of the unique id does not match that used for dummy identifiers or
   * if the time series is available from the underlying master.
   * @param uniqueId  the unique id
   * @return  the time series info or null
   */
  public ManageableHistoricalTimeSeriesInfo getMissingTimeSeriesInfoForUniqueId(final UniqueId uniqueId) {
    return _idBundleMap.get(uniqueId);
  }
}