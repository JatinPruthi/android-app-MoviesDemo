package projects.jatin.moviesdemo;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class CalendarEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_event);
//
//        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT);
//        intent.setType("vnd.android.cursor.item/event");
//        startActivity(intent);

        addCalendarEvent();
    }


    private void addCalendarEvent() {
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
        intent.putExtra("title", "Watch Movie: "+getIntent().getStringExtra("movie"));
        intent.putExtra("description", "This is a sample description");
        startActivity(intent);
    }

}
