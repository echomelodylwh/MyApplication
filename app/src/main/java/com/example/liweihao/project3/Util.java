package com.example.liweihao.project3;

import android.location.Location;

import com.google.android.gms.location.DetectedActivity;
import com.google.android.gms.maps.model.LatLng;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liweihao on 10/19/17.
 */

public class Util {
    public static final int LOCATION_UPDATE_MIN_DISTANCE = 1;
    public static final int LOCATION_UPDATE_MIN_TIME = 5000;

    static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 60 * 60 * 1000;
    static final float GEOFENCE_RADIUS_IN_METERS = 50;

    static final HashMap<String, LatLng> LANDMARKS = new HashMap<>();

    static {
        LANDMARKS.put("LIBRARY", new LatLng(42.274277, -71.806711));

       LANDMARKS.put("FULLER LAB", new LatLng(42.274865,-71.806689));
    }


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

//    public Map.Entry<String, int[]> getGeofencLocation(){
//
//    }
}
