package com.example.liweihao.project3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.DetectedActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liweihao on 10/19/17.
 */

public class ActivityRecognizedFragment extends Fragment {

    private ImageView mActionImage;
    private TextView mActionText;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_activity_recognized, container, false);
        mActionImage = v.findViewById(R.id.mActionImage);
        mActionText = v.findViewById(R.id.mActionText);
        return v;
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        int oldActivity = -1;
        Date olddate = null;
        @Override
        public void onReceive(Context context, Intent intent) {
            int activity = (int) intent.getExtras().get("recognition");
            Date now = new Date();
            if (olddate != null && oldActivity != -1 && oldActivity != activity){
                Toast.makeText(getActivity(),"You have just " + Util.getActivityString(oldActivity) + Util.calculateDuringTime(now, olddate) ,Toast.LENGTH_SHORT).show();
                olddate = now;
                oldActivity = activity;
            } else if (olddate == null && oldActivity == -1){
                olddate = now;
                oldActivity = activity;
            }
            switch (activity) {
                case DetectedActivity.RUNNING: {
                    mActionImage.setImageResource(R.drawable.running);
                    mActionText.setText("You are running !");
                    break;
                }
                case DetectedActivity.STILL: {
                    mActionImage.setImageResource(R.drawable.still);
                    mActionText.setText("You are stilling !");
                    break;
                }

                case DetectedActivity.WALKING: {
                    mActionImage.setImageResource(R.drawable.walking);
                    mActionText.setText("You are walking !");
                    break;
                }
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        getActivity().registerReceiver(receiver, new IntentFilter("activity"));
    }

    @Override
    public void onPause(){
        super.onPause();
        getActivity().unregisterReceiver(receiver);
    }

}
