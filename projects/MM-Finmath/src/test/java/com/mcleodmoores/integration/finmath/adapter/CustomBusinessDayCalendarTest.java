/**
 * Copyright (C) 2014-Present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.finmath.adapter;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.Collection;
import java.util.HashSet;

import org.testng.annotations.Test;
import org.threeten.bp.LocalDate;

import com.mcleodmoores.testutils.TestCalendar;

/**
 * Unit tests for {@link CustomBusinessDayCalendar}.
 */
@Test
public class CustomBusinessDayCalendarTest {
  /** Holiday dates */
  private static final Collection<LocalDate> HOLIDAYS;
  /** A test calendar */
  private static final TestCalendar CALENDAR;
  /** The adapter */
  private static final FinmathBusinessDay BUSINESS_DAY;

  static {
    HOLIDAYS = new HashSet<>();
    for (int i = 1; i < 13; i++) {
      HOLIDAYS.add(LocalDate.of(2014, i, 1));
    }
    CALENDAR = new TestCalendar("Test", HOLIDAYS);
    BUSINESS_DAY = new CustomBusinessDayCalendar(CALENDAR);
  }


  /**
   * Tests that holidays are identified correctly.
   */
  @Test
  public void test() {
    final LocalDate end = LocalDate.of(2015, 1, 1);
    LocalDate date = LocalDate.of(2014, 1, 1);
    while (date.isBefore(end)) {
      if (HOLIDAYS.contains(date)) {
        assertFalse(BUSINESS_DAY.isBusinessday(FinmathDateUtils.convertLocalDate(date)));
      } else {
        assertTrue(BUSINESS_DAY.isBusinessday(FinmathDateUtils.convertLocalDate(date)));
      }
      date = date.plusDays(1);
    }
  }
}
