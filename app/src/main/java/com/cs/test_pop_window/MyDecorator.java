package com.cs.test_pop_window;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Calendar;

/**
 * Created by chenshuai on 2016/8/14.
 */
public class MyDecorator implements DayViewDecorator {
    private final Calendar calendar = Calendar.getInstance();
    @Override
    public boolean shouldDecorate(CalendarDay day) {
        day.copyTo(calendar);
       /* day.getDate();//D/HHH: Fri Oct 07 00:00:00 GMT+08:00 2016
        Log.d("HHH", String.valueOf(day.getDay()));//11111*/
        int weekDay=calendar.get(Calendar.DAY_OF_WEEK);

        return weekDay == Calendar.SATURDAY || weekDay == Calendar.SUNDAY;//7
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setDaysDisabled(true);

    }
}
