package com.example.liweihao.project3;

import com.google.android.gms.location.DetectedActivity;

import java.util.Date;
import java.util.HashMap;

/**
 * Created by liweihao on 10/19/17.
 */

public class Util {

    public static String getActivityString(int activity){
        if (activity == DetectedActivity.RUNNING)  return "ran";
        else if (activity == DetectedActivity.STILL) return "still";
        else if (activity == DetectedActivity.WALKING) return  "walked";
        else return "";
    }

    public static String calculateDuringTime(Date now, Date past){
        long l=now.getTime()-past.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        return  " "+hour+" hour "+min+" min "+s+" seconds.";
    }
}
