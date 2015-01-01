package machete.db.model.dto;

import java.sql.Date;

public class Report {

  private double r;
  private Date date;
  private long month;
  private long year;

  public Report() {

  }

  public Report(Date date, double r) {
    super();
    this.date = date;
    this.r = r;
  }

  public Report(long year, double r) {
    super();
    this.year = year;
    this.r = r;
  }

  public Report(long month, long year, double r) {
    this.month = month;
    this.year = year;
    this.r = r;
  }

  public double getR() {
    return r;
  }

  public void setR(double r) {
    this.r = r;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public long getMonth() {
    return month;
  }

  public void setMonth(long month) {
    this.month = month;
  }

  public long getYear() {
    return year;
  }

  public void setYear(long year) {
    this.year = year;
  }

}
