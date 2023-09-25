package capstone.Enums;

/**
 * An Enum that keeps track of the day of the week to simplify time tracking.
 *
 * @author Bhargav
 * @version 1.0
 * @since 2020-11-1
 */
public enum DayOfWeek {

  /** Enum value representing Monday. */
  MONDAY(1, "MON"),

  /** Enum value representing Tuesday. */
  TUESDAY(2, "TUE"),

  /** Enum value representing Wednesday. */
  WEDNESDAY(3, "WED"),

  /** Enum value representing Thursday. */
  THURSDAY(4, "THU"),

  /** Enum value representing Friday. */
  FRIDAY(5, "FRI"),

  /** Enum value representing Saturday. */
  SATURDAY(6, "SAT"),

  /** Enum value representing Sunday. */
  SUNDAY(7, "SUN");

  /** The Integer value corresponding to the DayOfWeek in {@link org.joda.time.DateTime}. */
  public final Integer value;

  /** The String value of the day of the week to 3 Characters. */
  public final String label;

  /**
   * Initialiser for Enum DayOfWeek to ensure that all values have a label and a value.
   *
   * @param value Integer value representing a day of the week, starting with 1 for Monday.
   * @param label A String value corresponding to a day of the week, to 3 Characters.
   */
  private DayOfWeek(Integer value, String label) {
    this.value = value;
    this.label = label;
  }

  /**
   * Helper function that returns the corresponding DayOfWeek value for a given day in integer
   * format.
   *
   * @param day The day of the week in integer form.
   * @return The DayOfWeek, defaults to Monday if int day is out of range.
   */
  public static DayOfWeek getDayOfWeek(int day) {
    for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
      if (dayOfWeek.value == day) {
        return dayOfWeek;
      }
    }
    return DayOfWeek.MONDAY;
  }
}
