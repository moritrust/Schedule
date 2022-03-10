package com.example.schedule;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public OpenHelper helper;
    public SQLiteDatabase db;
    public int key;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CompactCalendarView compactcalendarview = findViewById(R.id.calendar);
        Date curDate = Calendar.getInstance().getTime();
        compactcalendarview.setCurrentDate(curDate); // 現在の日時をセット
        compactcalendarview.setFirstDayOfWeek(Calendar.SUNDAY); // 日曜日始まりに設定
        compactcalendarview.shouldDrawIndicatorsBelowSelectedDays(true); // 選択した日にEventがある場合でもEventを表示
        //compactcalendarview.setLocale(Locale.JAPANESE);
        compactcalendarview.setUseThreeLetterAbbreviation(true);


        compactcalendarview.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                Toast.makeText(getApplicationContext(),
                        DateFormat.getDateInstance().format(dateClicked),
                        Toast.LENGTH_SHORT
                ).show();

                String date = DateFormat.getDateInstance().format(dateClicked);

                Intent intent = new Intent(getApplication(), List_Schedule_Activity.class);
                intent.putExtra("DATE_DATA", date);
                startActivity(intent);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // 表示する月が変わったときの処理

            }
        });
    }
}