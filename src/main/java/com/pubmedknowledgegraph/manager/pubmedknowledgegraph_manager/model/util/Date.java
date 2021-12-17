package com.pubmedknowledgegraph.manager.pubmedknowledgegraph_manager.model.util;

public class Date {

    private String dateType;

    private String year;
    private String month;
    private String day;

    private String hour;
    private String minute;

    private String season;

    private String medlineDate;

    public Date() {
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getMedlineDate() {
        return medlineDate;
    }

    public void setMedlineDate(String medlineDate) {
        this.medlineDate = medlineDate;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (year != null) {
            stringBuilder.append(year).append("-");
        }
        if (month != null) {
            stringBuilder.append(month).append("-");
        }
        if (day != null) {
            stringBuilder.append(day).append("|");
        }
        if (hour != null) {
            stringBuilder.append("T").append(hour).append(":");
        }
        if (minute != null) {
            stringBuilder.append(minute).append("|");
        }
        if (season != null) {
            stringBuilder.append(season).append("|");
        }
        if (medlineDate != null) {
            stringBuilder.append(medlineDate).append("|");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }

}
