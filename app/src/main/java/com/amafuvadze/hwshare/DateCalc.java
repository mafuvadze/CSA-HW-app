package com.amafuvadze.hwshare;

import java.util.Date;

/**
 * Created by Anesu on 5/9/2016.
 */
public class DateCalc {

    public static class TimeStamp{
        String type;
        int num;
        public TimeStamp(int num, String type){
            this.num = num;
            this.type = type;
        }
    }

    public static int daysBetween(Date date1, Date date2){
        int hr1 = date1.getHours();
        int hr2 = date2.getHours();
        int diff = Math.abs(hr1 - hr2);
        int days = diff / 24;
        return days;
    }

    public static TimeStamp getTimeStamp(int days){
        if(days == 0){
            return new TimeStamp(-1, "Today");
        }
        else if(days < 7){
            return new TimeStamp(days, "Days");
        }
        else if(days < 30){
            return new TimeStamp(days/7, "Weeks");
        }
        else{
            return  new TimeStamp(days/30, "Months");
        }
    }

}
