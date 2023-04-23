package com.project.BusReservationSystem;

import android.content.Intent;
import android.os.Bundle;

import com.project.brs.R;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.EditText;

public class TicketActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    int ticketID;
    EditText ticketIdTxt, busIdTxt,fromTxt,toTxt,dateTxt,deptTxt,arrTxt,seatTxt,priceTxt;
    TicketDetailsObj ticketDetailsObj;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Bundle extra = getIntent().getExtras();
        ticketID = extra.getInt("tickID");
        dbHelper = new DBHelper(this);
        ticketDetailsObj = dbHelper.retriveTicketDetails(ticketID);

        ticketIdTxt = (EditText)findViewById(R.id.tickText);
        busIdTxt = (EditText)findViewById(R.id.busText);
        fromTxt = (EditText)findViewById(R.id.fromText);
        toTxt = (EditText)findViewById(R.id.toText);
        dateTxt= (EditText)findViewById(R.id.dateText);
        deptTxt = (EditText)findViewById(R.id.deptTimeTxt);
        arrTxt = (EditText)findViewById(R.id.arrTimeTxt);
        seatTxt = (EditText)findViewById(R.id.seatTxt);
        priceTxt = (EditText)findViewById(R.id.priceTxt);

        ticketIdTxt.setText(String.valueOf(ticketDetailsObj.ticketID));
        busIdTxt.setText(String.valueOf(ticketDetailsObj.busId));
        fromTxt.setText(ticketDetailsObj.source);
        toTxt.setText(ticketDetailsObj.dest);
        dateTxt.setText(ticketDetailsObj.date);
        deptTxt.setText(ticketDetailsObj.depttime);
        arrTxt.setText(ticketDetailsObj.arrtime);
        seatTxt.setText(String.valueOf(ticketDetailsObj.seatno));
        priceTxt.setText(String.valueOf(ticketDetailsObj.price));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(TicketActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(TicketActivity.this, BookTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cancel) {
            Intent intent = new Intent(TicketActivity.this, CancelTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_retrieve) {
            Intent intent = new Intent(TicketActivity.this, RetrieveTicketActivity.class);
            startActivity(intent);

        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
