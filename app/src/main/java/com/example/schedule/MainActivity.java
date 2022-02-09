package com.example.schedule;

import static java.util.Locale.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

                                //Intent intent = new Intent(MainActivity.this, InputSchedule.class);
                                //intent.putExtra("DATE_DATA", message);

                               // startActivity(intent);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                // 表示する月が変わったときの処理

            }
        });
    }
}