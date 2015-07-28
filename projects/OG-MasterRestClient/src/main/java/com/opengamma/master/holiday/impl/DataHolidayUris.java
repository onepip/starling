/**
 * Copyright (C) 2015 - present by McLeod Moores Software Limited.
 * 
 * Please see distribution for license.
 */
package com.opengamma.master.holiday.impl;

import com.opengamma.master.AbstractDataDocumentUris;

/**
 * RESTful URIs for holidays
 */
public class DataHolidayUris extends AbstractDataDocumentUris {
  @Override
  protected String getResourceName() {
    return "holidays";
  }
}
