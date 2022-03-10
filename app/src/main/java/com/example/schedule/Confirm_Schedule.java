package com.example.schedule;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Confirm_Schedule extends List_Schedule_Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm_schedule);

        Intent intent = getIntent();
        String id = intent.getStringExtra("SELECTED_ID_DATA");
        String date = intent.getStringExtra("SELECTED_DATE_DATA");
        String title = intent.getStringExtra("SELECTED_TITLE_DATA");
        String start_time = intent.getStringExtra("SELECTED_START_TIME_DATA");
        String end_time = intent.getStringExtra("SELECTED_END_TIME_DATA");

        TextView confirmTextViewDate = findViewById(R.id.confirmTextViewDate);
        confirmTextViewDate.setText(date);
        TextView confirmEditText = findViewById(R.id.confirmEditText);
        confirmEditText.setText(title);
        TextView confirmEditTextStartTime = findViewById(R.id.confirmEditTextStartTime);
        confirmEditTextStartTime.setText(start_time);
        TextView confirmEditTextEndTime = findViewById(R.id.confirmEditTextEndTime);
        confirmEditTextEndTime.setText(end_time);

        Button confirmDeleteScheduleButton = findViewById(R.id.confirmDeleteScheduleButton);

        View.OnClickListener deleteButtonclickListener = new View.OnClickListener(){
            public void onClick(View v){

                if(helper == null){
                    helper = new OpenHelper(getApplicationContext());
                }
                //getWritableDatabase()呼び出して初めてDBが起動する。
                if(db == null){
                    db = helper.getWritableDatabase();
                }

                db.beginTransaction();
                try {
                    //WHERE句は、第2引数の「name = ?」のパラメータとなっている「?」のところに、第3引数の「id]め込まれた形となります。
                    db.delete("Schedule", "id = ?", new String[]{id});
                    //setTransactionSuccessful()を呼び出すと、データベースへの変更がコミットされ、呼び出さなければロールバックされます
                    db.setTransactionSuccessful();

                    Log.i("SQLITE", "id : " + key + " " +
                            "date : " + date + " " +
                            "title : " + title + " " +
                            "start_time : " + start_time + " " +
                            "end_time : "+ end_time);
                } finally {
                    db.endTransaction();
                }

                Intent intent = new Intent(getApplication(), List_Schedule_Activity.class);
                intent.putExtra("DATE_DATA", date);
                startActivity(intent);
            }
        };
        confirmDeleteScheduleButton.setOnClickListener(deleteButtonclickListener);

        Button confirmChangeScheduleButton = findViewById(R.id.confirmChangeScheduleButton);


        View.OnClickListener changeButtonclickListener = new View.OnClickListener(){
            public void onClick(View v){
                //テキストボックスに入力された文字列を取得します。「EditText」クラスで用意されている「getText」メソッドを使います。
                //.toString()メソッドを使用しないと、Stringにキャストできず、クラスエラーとなる。
                //String changeDate =  confirmTextViewDate.getText().toString();
                String changeTitle = confirmEditText.getText().toString();
                String changeStart_time = confirmEditTextStartTime.getText().toString();
                String changeEnd_time = confirmEditTextEndTime.getText().toString();

                if(helper == null){
                    helper = new OpenHelper(getApplicationContext());
                }
                //getWritableDatabase()呼び出して初めてDBが起動する。
                if(db == null){
                    db = helper.getWritableDatabase();
                }

                db.beginTransaction();
                try {
                    ContentValues cv = new ContentValues();
                    //cv.put("date", changeDate);
                    cv.put("title", changeTitle);
                    cv.put("start_time", changeStart_time);
                    cv.put("end_time", changeEnd_time);
                    // 2番目の引数には更新するデータの各カラムに対する値を保持している「ContentValues」クラスのオブジェクトを指定します。
                    // WHERE句は、第３引数の「name = ?」のパラメータとなっている「?」のところに、第４引数の「id]が組込まれた形となります。
                    db.update("Schedule", cv, "id = ?", new String[]{id});
                    //setTransactionSuccessful()を呼び出すと、データベースへの変更がコミットされ、呼び出さなければロールバックされます
                    db.setTransactionSuccessful();

                    Log.i("SQLITE", "id : " + key + " " +
                            "date : " + date + " " +
                            "title : " + title + " " +
                            "start_time : " + start_time + " " +
                            "end_time : "+ end_time);
                } finally {
                    db.endTransaction();
                }

                Intent intent = new Intent(getApplication(), List_Schedule_Activity.class);
                intent.putExtra("DATE_DATA", date);
                startActivity(intent);
            }
        };
        confirmChangeScheduleButton.setOnClickListener(changeButtonclickListener);
    }
}
