package com.quangminh.timeforlife.model;

public class Announce {
    int before;
    boolean notification;
    boolean alarm;
    boolean kpi;

    public Announce(int before, boolean notification, boolean alarm, boolean kpi) {
        this.before = before;
        this.notification = notification;
        this.alarm = alarm;
        this.kpi = kpi;
    }

    public Announce() {
    }

    public int getBefore() {
        return before;
    }

    public void setBefore(int before) {
        this.before = before;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public boolean isAlarm() {
        return alarm;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public boolean isKpi() {
        return kpi;
    }

    public void setKpi(boolean kpi) {
        this.kpi = kpi;
    }

    @Override
    public String toString() {
        return "Announce{" +
                "before=" + before +
                ", notification=" + notification +
                ", alarm=" + alarm +
                ", kpi=" + kpi +
                '}';
    }
}
