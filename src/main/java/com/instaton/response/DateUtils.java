package com.instaton.response;

import java.time.LocalDate;

public class DateUtils {

  private DateUtils() {}

  public static String getPreviousMounthStartDate() {
    LocalDate initial = LocalDate.now().minusMonths(1);
    LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static String getPreviousMounthEndDate() {
    LocalDate initial = LocalDate.now().minusMonths(1);
    LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
    return end.toString();
  }

  public static String getPreviousYearMonthStartDate() {
    LocalDate initial = LocalDate.now().minusYears(1);
    LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static String getPreviousYearMonthEndDate() {
    LocalDate initial = LocalDate.now().minusYears(1).minusMonths(1);
    LocalDate end = initial.withDayOfMonth(initial.lengthOfMonth());
    return end.toString();
  }

  public static String getPreviousYearAndPreviousMonthStartDate() {
    LocalDate initial = LocalDate.now().minusYears(1).minusMonths(1);
    LocalDate start = initial.withDayOfMonth(1);
    return start.toString();
  }

  public static String getCurrentDayOfCurrentMonth() {
    LocalDate currentDayOfCurrentMonth = LocalDate.now();
    return currentDayOfCurrentMonth.toString();
  }

  public static String getFirstDayOfCurrentMonth() {
    LocalDate firstDayOfCurrentMonth = LocalDate.now().withDayOfMonth(1);
    return firstDayOfCurrentMonth.toString();
  }
}
