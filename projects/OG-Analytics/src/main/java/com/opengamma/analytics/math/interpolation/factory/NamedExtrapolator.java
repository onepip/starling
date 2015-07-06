/**
 * Copyright (C) 2015-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.opengamma.analytics.math.interpolation.factory;

import com.opengamma.analytics.math.interpolation.Interpolator;
import com.opengamma.util.NamedInstance;

/**
 * An interface for named instances of {@link Interpolator} that perform extrapolation. Classes that implement this interface can
 * be obtained from a {@link NamedExtrapolator1dFactory}.
 * @param <S> The type of the data
 * @param <T> The type of the result
 */
public interface NamedExtrapolator<S, T> extends Interpolator<S, T>, NamedInstance {

}
