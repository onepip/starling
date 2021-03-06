/**
 * Copyright (C) 2014 - present McLeod Moores Software Limited.  All rights reserved.
 */
package com.mcleodmoores.integration.adapter;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotSame;

import org.testng.annotations.Test;

import com.opengamma.util.test.TestGroup;

import net.finmath.time.businessdaycalendar.BusinessdayCalendarAny;
import net.finmath.time.businessdaycalendar.BusinessdayCalendarExcludingWeekends;

/**
 * Unit tests for {@link FinmathBusinessDay}.
 */
@Test(groups = TestGroup.UNIT)
public class FinmathBusinessDayTest {
  /** The instance to test */
  private static final FinmathBusinessDay BUSINESS_DAY = new AnyBusinessDayCalendar();

  /**
   * Tests the getters.
   */
  public void testGetters() {
    assertEquals("None", BUSINESS_DAY.getName());
    // no useful test for the business day calendar
  }

  /**
   * Tests the hashCode and equals methods.
   */
  @Test
  public void testHashCodeEquals() {
    FinmathBusinessDay businessDay = new AnyBusinessDayCalendar();
    assertEquals(BUSINESS_DAY, BUSINESS_DAY);
    assertNotEquals(null, BUSINESS_DAY);
    assertNotSame(BUSINESS_DAY, businessDay);
    assertEquals(BUSINESS_DAY, businessDay);
    assertEquals(BUSINESS_DAY.hashCode(), businessDay.hashCode());
    businessDay = new FinmathBusinessDay("None Test", new BusinessdayCalendarAny()) {
    };
    assertNotEquals(BUSINESS_DAY, businessDay);
    businessDay = new FinmathBusinessDay("None", new BusinessdayCalendarExcludingWeekends()) {
    };
    assertEquals(BUSINESS_DAY, businessDay);
  }
}
