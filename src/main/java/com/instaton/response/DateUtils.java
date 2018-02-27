package com.instaton.response;

import java.time.LocalDate;

public class DateUtils {

  public static String getCurrentDayOfCurrentMonth() {
    final LocalDate currentDayOfCurrentMonth = LocalDate.now();
    return currentDayOfCurrentMonth.toString();
  }

  public static String getFirstDayOfCurrentMonth() {
    final LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
    return firstDayOfCurrentMonth.toString();
  }

  public static String getPreviousMounthEndDate() {
    final LocalDate initial = LocalDate.now().minusMonths(1);
    final LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
    return end.toString();
  }

  public static String getPreviousMounthStartDate() {
    final LocalDate initial = LocalDate.now().minusMonths(1);
    final LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static String getPreviousYearAndPreviousMonthStartDate() {
    final LocalDate initial = LocalDate.now().minusYears(1).minusMonths(1);
    final LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static String getPreviousYearMonthEndDate() {
    final LocalDate initial = LocalDate.now().minusYears(1).minusMonths(1);
    final LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
    return end.toString();
  }

  public static String getPreviousYearMonthStartDate() {
    final LocalDate initial = LocalDate.now().minusYears(1);
    final LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static java.sql.Date getSqlDateNow() {

    return new java.sql.Date(DateUtils.getUtilDateNow().getTime());
  }

  public static java.util.Date getUtilDateNow() {
    return new java.util.Date();
  }

  private DateUtils() {
    throw new IllegalAccessError();
  }
}
