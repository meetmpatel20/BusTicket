package com.project.BusReservationSystem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.project.brs.R;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class BookTicketActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Spinner source,dest;
    CalendarView cal;
    Button search, clear;
    DBHelper dbHelper;
    Calendar c;
    String date;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookticket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        source = (Spinner)findViewById(R.id.from);
        dest = (Spinner)findViewById(R.id.to);
        cal = (CalendarView)findViewById(R.id.date);
        search = (Button)findViewById(R.id.srch_bt);
        dbHelper = new DBHelper(this);
        Calendar mindate = Calendar.getInstance();
        mindate.add(Calendar.DAY_OF_MONTH,1);

        cal.setMinDate(mindate.getTime().getTime());
        c = Calendar.getInstance();
        final SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
        date =dtf.format(c.getTime());
        ArrayList<String> allSource = dbHelper.retriveSOURCE();
        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,allSource);
        source.setAdapter(sourceAdapter);
        source.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String src = source.getSelectedItem().toString();
                ArrayList<String> allDest = dbHelper.retriveDest(src);

                ArrayAdapter<String> destAdapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1,allDest);
                dest.setAdapter(destAdapter);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                c = new GregorianCalendar(i,i1,i2);
                date =dtf.format(c.getTime());
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(BookTicketActivity.this,ShowRoutesActivity.class);
                String src = source.getSelectedItem().toString();
                String dt = dest.getSelectedItem().toString();
                intent1.putExtra("src",src);
                intent1.putExtra("dt",dt);
                intent1.putExtra("date",date);
                startActivity(intent1);
            }
        });
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            super.onBackPressed();

        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(BookTicketActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(BookTicketActivity.this, BookTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cancel) {
            Intent intent = new Intent(BookTicketActivity.this, CancelTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_retrieve) {
            Intent intent = new Intent(BookTicketActivity.this, RetrieveTicketActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
