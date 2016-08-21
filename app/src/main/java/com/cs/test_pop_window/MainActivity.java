package com.cs.test_pop_window;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;
import com.prolificinteractive.materialcalendarview.OnRangeSelectedListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, OnDateSelectedListener, OnRangeSelectedListener, OnMonthChangedListener {
    private static final DateFormat FORMATTER = SimpleDateFormat.getDateInstance();

    private MaterialCalendarView widget;
    private Button btn_cancle;
    private PopupWindow mPopWindow;
    private Button btn_commit;
    private Button btn_popWindow;
    private String start,end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_popWindow= (Button) findViewById(R.id.btn_popwindow);
        btn_popWindow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showPopupWindow();

    }

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(MainActivity.this).inflate(R.layout.canlendar, null);
        mPopWindow = new PopupWindow(contentView,
                LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);

        btn_commit= (Button) contentView.findViewById(R.id.btn_cancle);
        btn_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"我是高手", Toast.LENGTH_SHORT).show();

            }
        });

        btn_cancle= (Button) contentView.findViewById(R.id.btn_cancle);
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                widget.clearSelection();
            }
        });
        widget= (MaterialCalendarView) contentView.findViewById(R.id.weight);
        widget.setOnDateChangedListener(this);
        widget.setOnRangeSelectedListener(this);
        widget.setOnMonthChangedListener(this);
        widget.addDecorator(new MyDecorator());
        widget.setSelectionMode(MaterialCalendarView.SELECTION_MODE_RANGE);
        widget.setArrowColor(Color.RED);
        widget.setTileSizeDp(42);



        View rootview=LayoutInflater.from(MainActivity.this).inflate(R.layout.main,null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM,0,0);


    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {

    }

    @Override
    public void onRangeSelected(@NonNull MaterialCalendarView widget, @NonNull List<CalendarDay> dates) {
        for (int i = 0; i <dates.size() ; i++) {
            //widget.setDateSelected(dates.get(i),false);
            //Log.i("TAG", String.valueOf(dates.get(0).getDay()));//具体为选择的天数为几号
            //Log.d("TAG", String.valueOf(dates.get(dates.size()-1)));//CalendarDay{2016-11-22}
            start= String.valueOf(dates.get(0));
            end= String.valueOf(dates.get(dates.size()-1));


            Calendar calendar=dates.get(i).getCalendar();
            int week=calendar.get(Calendar.DAY_OF_WEEK);
            if (week==Calendar.SATURDAY || week==Calendar.SUNDAY){
                calendar.clear();
                dates.get(i).copyTo(calendar);
                widget.setDateSelected(dates.get(i),false);
                // Log.d("CAG", String.valueOf(calendar));
            }

        }

    }

    @Override
    public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {
        //getSupportActionBar().setTitle(FORMATTER.format(date.getDate()));

    }
}
