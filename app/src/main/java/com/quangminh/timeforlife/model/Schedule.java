package com.quangminh.timeforlife.model;

public class Schedule {
    int bookingDate;
    int bookingMonth;
    int bookingYear;
    String note;
    String timeStart;
    String timeFinish;
    int priority;
    Announce announce;
    int id;


    public Schedule(int bookingDate, int bookingMonth, int bookingYear,  String note, String timeStart, String timeFinish, int priority, Announce announce, int id) {
        this.bookingDate = bookingDate;
        this.bookingMonth=bookingMonth;
        this.bookingYear=bookingYear;
        this.note = note;
        this.timeStart = timeStart;
        this.timeFinish = timeFinish;
        this.priority = priority;
        this.announce = announce;
        this.id = id;
    }

    public int getBookingYear() {
        return bookingYear;
    }

    public void setBookingYear(int bookingYear) {
        this.bookingYear = bookingYear;
    }

    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingMonth() {
        return bookingMonth;
    }

    public void setBookingMonth(int bookingMonth) {
        this.bookingMonth = bookingMonth;
    }
    public int getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(int bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeFinish() {
        return timeFinish;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Announce getAnnounce() {
        return announce;
    }

    public void setAnnounce(Announce announce) {
        this.announce = announce;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "bookingDate=" + bookingDate +
                ", bookingMonth=" + bookingMonth +
                ", bookingYear=" + bookingYear +
                ", note='" + note + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", timeFinish='" + timeFinish + '\'' +
                ", priority=" + priority +
                ", announce=" + announce +
                ", id=" + id +
                '}';
    }
}
