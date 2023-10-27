package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import java.util.Calendar;

public class MyDatePicker extends DialogFragment {
    int currentYear, currentMonth, currentDay;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar mCalendar = Calendar.getInstance();
        int year = mCalendar.get(Calendar.YEAR);
        int month = mCalendar.get(Calendar.MONTH);
        int dayOfMonth = mCalendar.get(Calendar.DAY_OF_MONTH);

        currentYear = year;
        currentDay = dayOfMonth;
        currentMonth = month+1;

        return new DatePickerDialog(getActivity(),
                (DatePickerDialog.OnDateSetListener)
                        getActivity(), year, month, dayOfMonth);
    }
    public int getSelectedYear(){return this.currentYear;}
    public int getSelectedMonth() {return this.currentMonth;}
    public int getSelectedDay() { return this.currentDay;}
}