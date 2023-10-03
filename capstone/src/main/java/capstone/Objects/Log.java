package capstone.Objects;

import org.joda.time.DateTime;

public class Log {
  /** The Log ID associated with each log. */
  private int id;

  /** The user name associated with each network User. */
  private int user_id;

  /** The datetime object of the log */
  private DateTime date;

  /** The user name involved in the failed attempt */
  private String user_name;

  /** The task of the failed attempt */
  private String task_name;

  /** The error msg of the failed attempt */
  private String error_msg;

  /**
   * Initialiser for the
   *
   * @param id The id of the transaction
   * @param user_id The user id involved in this log
   * @param date The date of the transaction
   * @param user_name The user name involved in this log
   * @param task_name The task name of the failed attempt
   * @param error_msg The error linked to the log
   */
  public Log(
      int id, int user_id, DateTime date, String user_name, String task_name, String error_msg) {
    this.id = id;
    this.user_id = user_id;
    this.task_name = task_name;
    this.date = date;
    this.user_name = user_name;
    this.error_msg = error_msg;
  }

  /**
   * retrieves the id of the log
   *
   * @return the ID of the log
   */
  public int getID() {
    return this.id;
  }

  /**
   * retrieves the transaction reference number
   *
   * @return the Transaction Reference
   */
  public int getUser_ID() {
    return this.user_id;
  }

  /**
   * retrieves the log
   *
   * @return the Date of the log
   */
  public DateTime getDate() {
    return this.date;
  }

  /**
   * retrieves the user name in that log
   *
   * @return the user name in that log
   */
  public String getUser_Name() {
    return this.user_name;
  }

  /**
   * retrieves the task name
   *
   * @return the task of this log
   */
  public String getTask_Name() {
    return this.task_name;
  }

  /**
   * retrieves the error msg
   *
   * @return the error msg of this log
   */
  public String getError_Msg() {
    return this.error_msg;
  }
}
