package com.example.liweihao.project3;


import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by liweihao on 10/19/17.
 */

public class ActivityRecognizedService extends IntentService {


    public ActivityRecognizedService() {
        super("ActivityRecognizedService");
    }

    public ActivityRecognizedService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (ActivityRecognitionResult.hasResult(intent)) {
            ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
            handleDetectedActivities(result.getProbableActivities());
        }
    }

    private void handleDetectedActivities(List<DetectedActivity> probableActivities) {
        Map<Integer, Integer> map = new HashMap<>();
        for (DetectedActivity activity : probableActivities) {
            switch (activity.getType()) {
                case DetectedActivity.IN_VEHICLE: {
                    Log.e("weihaoli", "In Vehicle: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_BICYCLE: {
                    Log.e("weihaoli", "On Bicycle: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.ON_FOOT: {
                    Log.e("weihaoli", "On Foot: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.RUNNING: {
                    map.put(DetectedActivity.RUNNING, activity.getConfidence());
                    Log.e("weihaoli", "Running: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.STILL: {
                    Log.e("weihaoli", "Still: " + activity.getConfidence());
                    map.put(DetectedActivity.STILL, activity.getConfidence());
//                    if( activity.getConfidence() >= 75 ) {
////                        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
////                        builder.setContentText( "Are you Stilling?" );
////                        builder.setSmallIcon( R.mipmap.ic_launcher );
////                        builder.setContentTitle( getString( R.string.app_name ) );
////                        NotificationManagerCompat.from(this).notify(0, builder.build());
//                    }
                    break;
                }
                case DetectedActivity.TILTING: {
                    Log.e("weihaoli", "Tilting: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.WALKING: {
                    map.put(DetectedActivity.WALKING, activity.getConfidence());
                    Log.e("weihaoli", "Walking: " + activity.getConfidence());
                    break;
                }
                case DetectedActivity.UNKNOWN: {
                    Log.e("weihaoli", "Unknown: " + activity.getConfidence());
                    break;
                }
            }
        }
        int max = Integer.MIN_VALUE, key = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                key = entry.getKey();
            }
        }
        if (key != 0) {
            Intent i = new Intent("activity");
            i.putExtra("recognition", key);
            i.setFlags(FLAG_ACTIVITY_NEW_TASK);
            sendBroadcast(i);
        }
    }
}
