package com.orbitsoft.pdfbook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlarmFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlarmFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    TimePicker tp_time;
    TextView tv_display;
    Button btn_set, btn_reset;
    public AlarmFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlarmFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlarmFragment newInstance(String param1, String param2) {
        AlarmFragment fragment = new AlarmFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=  inflater.inflate(R.layout.activity_time, container, false);

        tv_display = (TextView)v.findViewById(R.id.tv_display);
        tp_time = (TimePicker)v.findViewById(R.id.tp_time);
        btn_set = (Button)v.findViewById(R.id.btn_set);
        btn_reset = (Button)v.findViewById(R.id.btn_reset);

        btn_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT >= 23) {

                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            tp_time.getHour(),
                            tp_time.getMinute(),
                            0
                    );



                }else{
                    calendar.set(
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            tp_time.getCurrentHour(),
                            tp_time.getCurrentMinute(),
                            0
                    );
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    setAlarm(calendar.getTimeInMillis(), calendar);
                }
            }


            @RequiresApi(api = Build.VERSION_CODES.S)
            private void setAlarm(long timeInMillis, Calendar c) {
                AlarmManager alarmManager = (AlarmManager) MainActivity.Instance.getSystemService(Context.ALARM_SERVICE);

                Intent intent = new Intent(MainActivity.Instance, AlarmAdapter.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.Instance, 0, intent, PendingIntent.FLAG_MUTABLE);

                alarmManager.setRepeating(AlarmManager.RTC, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

                Toast.makeText(MainActivity.Instance, "تنظیم زمان", Toast.LENGTH_SHORT).show();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                int ampm = c.get(Calendar.AM_PM);
                String day = "";
                if(ampm == Calendar.AM){
                    day = "AM";
                }else if(ampm == Calendar.PM){
                    day = "PM";
                }
                String timeText = "زمان مطالعه بعدی: ";
                timeText += minute +": " +  hour+ " " + day;
                tv_display.setText(timeText);

            }


        });




        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlarmManager alarmManager = (AlarmManager) MainActivity.Instance.getSystemService(Context.ALARM_SERVICE);

                Intent intent = new Intent(MainActivity.Instance, AlarmAdapter.class);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.Instance, 0, intent, PendingIntent.FLAG_MUTABLE);

                alarmManager.cancel(pendingIntent);

                tv_display.setText("زمان مطالعه تنظیم نشده");

            }
        });
        return v;
    }
}