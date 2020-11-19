package com.example.eurekaclient;

import java.util.*;

/**
 *
 */
public class Time {

    /**
     * Default constructor
     */
    public Time() {
        time = System.currentTimeMillis() + 8 * 60 * 60 * 1000;
        //current得到的时间为格林威治时间,与北京时间即系统时间相差八小时
        hour = time / 1000 / 3600 % 24;
        //得到小时
        minute = time / 1000 % 3600 / 60;
        //得到分钟
        second = time / 1000 % 3600 % 60;
        //得到秒
    }

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    /**
     *
     */
    private long hour;

    /**
     *
     */
    private long minute;

    /**
     *
     */
    private long second;

    /**
     *
     */
    private long time;

    /**
     *
     */

    /**
     * @param time
     */
    public Time(long time) {
        // TODO implement here
        hour = time / 1000 / 3600 % 24;
        //得到小时
        minute = time / 1000 % 3600 / 60;
        //得到分钟
        second = time / 1000 % 3600 % 60;
        //得到秒
    }

    /**
     * @param hour
     * @param minute
     * @param second
     */
    public Time(long hour, long minute, long second) {
        // TODO implement here
        this.hour=hour;
        this.second=second;
        this.minute=minute;
    }

    /**
     * @param elapseTime
     * @return
     */
    public void setTime(long elapseTime) {
        // TODO implement here
        time = elapseTime;
    }

}
