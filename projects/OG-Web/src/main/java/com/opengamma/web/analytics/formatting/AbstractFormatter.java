/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 *
 * Please see distribution for license.
 */
package com.opengamma.web.analytics.formatting;

import java.util.Map;

import com.google.common.collect.Maps;
import com.opengamma.engine.value.ValueSpecification;
import com.opengamma.engine.view.calcnode.MissingInput;
import com.opengamma.util.ArgumentChecker;

/**
 * Abstract {@link TypeFormatter} that implements {@link #getDataTypeForValue} by delegating to {@link #getDataType()}.
 * @param <T> Type of object formatted by the formatter
 */
public abstract class AbstractFormatter<T> implements TypeFormatter<T> {

  private final Class<T> _type;
  private final Map<Format, Formatter<T>> _formatters = Maps.newHashMap();
  
  protected AbstractFormatter(Class<T> type) {
    ArgumentChecker.notNull(type, "type");
    _type = type;
  }

  protected void addFormatter(Formatter<T> formatter) {
    _formatters.put(formatter.getFormat(), formatter);
  }

  @Override
  public Object format(T value, ValueSpecification valueSpec, Format format) {
    if (format == Format.CELL) {
      return formatCell(value, valueSpec);
    }
    Formatter<T> formatter = _formatters.get(format);
    if (formatter != null) {
      return formatter.format(value, valueSpec);
    } else {
      return new MissingFormatter("Unable to format value " + value + " of type " + value.getClass().getSimpleName() +
                                      " with format " + format);
    }
  }

  @Override
  public Class<T> getType() {
    return _type;
  }

  /**
   * Returns the same format type as {@link #getDataType()}.
   * @param value The value
   * @return The format type returned by {@link #getDataType()}
   */
  @Override
  public DataType getDataTypeForValue(T value) {
    return getDataType();
  }
  
  static abstract class Formatter<T> {
    
    private final Format _format;

    Formatter(Format format) {
      _format = format;
    }

    Format getFormat() {
      return _format;
    }

    abstract Object format(T value, ValueSpecification valueSpec);
  }

  /**
   * Error value returned to the client for values that can't be formatted.
   */
  private static class MissingFormatter implements MissingInput {

    private final String _message;

    private MissingFormatter(String message) {
      ArgumentChecker.notEmpty(message, "message");
      _message = message;
    }

    @Override
    public String toString() {
      return _message;
    }
  }
}
