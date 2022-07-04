package com.example.voice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Dashboard extends AppCompatActivity {
    /*private DatePicker datePicker;
    private Calendar calendar;*/
    private TextView dob;
    private int year, month, day;
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



       /* dob = (TextView) findViewById(R.id.date);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);*/

        /*month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
*/

        myMethod2();
    }
    private void myMethod2() {
        dob = findViewById(R.id.date);
        Calendar calendar=Calendar.getInstance();


        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);


        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // To show current date in the datepicker
                final Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(
                        Dashboard.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker,
                                          int selectedyear, int selectedmonth,
                                          int selectedday) {

                        mcurrentDate.set(Calendar.YEAR, selectedyear);
                        mcurrentDate.set(Calendar.MONTH, selectedmonth);
                        mcurrentDate.set(Calendar.DAY_OF_MONTH, selectedday);
                        String currentDatestring= DateFormat.getDateInstance().format(calendar.getTime());
                        dob.setText(currentDatestring);
                        /*SimpleDateFormat sdf = new SimpleDateFormat(getResources().getString(R.string.date_card_formate), Locale.US);
                        dob.setText(sdf.format(mcurrentDate
                                .getTime()));*/
                    }
                }, mYear, mMonth, mDay);

                mDatePicker.setTitle("date select");
                mDatePicker.show();
            }
        });


       /* dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog=new DatePickerDialog(Dashboard.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        *//*dob.setText(SimpleDateFormat.getDateInstance().format(calendar.getTime()));*//*
                        Calendar calendar=Calendar.getInstance();
                        calendar.set(Calendar.MONTH,month);
                        calendar.set(Calendar.YEAR,year);
                        calendar.set(Calendar.DAY_OF_MONTH,day);
                        String currentDatestring= DateFormat.getDateInstance().format(calendar.getTime());
                        dob.setText(currentDatestring);
                    }
                },year,month,day);
                datePickerDialog.show();
                *//* DatePickerDialog.OnDateSetListener myDateListener = new
                        DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker arg0,
                                                  int arg1, int arg2, int arg3) {
                                // TODO Auto-generated method stub
                                // arg1 = year
                                // arg2 = month
                                // arg3 = day
                                showDate(arg1, arg2+1, arg3);
                            }
                        };
*//*
            }
        });*/

    }
    //Current date selector

   /* @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);
                }
            };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }*/
}