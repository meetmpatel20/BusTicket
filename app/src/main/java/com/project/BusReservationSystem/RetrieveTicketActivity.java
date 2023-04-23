package com.project.BusReservationSystem;

import android.content.Intent;
import android.os.Bundle;

import com.project.brs.R;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RetrieveTicketActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView ticketList;
    DBHelper dbHelper;
    ArrayList<TicketDetailsObj> ticketArray;
    ArrayList<String> listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        dbHelper = new DBHelper(this);
        ticketArray = dbHelper.retriveAllTickets();
        listItem = new ArrayList<>();

        for(int i = 0;i<ticketArray.size();i++){

            listItem.add("Ticket ID: " + ticketArray.get(i).ticketID+
                    "\nBus ID: "+ ticketArray.get(i).busId+
                    "\nFrom: "+ticketArray.get(i).source+
                    "\nTo: "+ticketArray.get(i).dest+
                    "\nDate: "+ticketArray.get(i).date);

        }

        ticketList = (ListView)findViewById(R.id.ticketList);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem);
        ticketList.setAdapter(adapter);

        ticketList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = (String)adapterView.getItemAtPosition(i);
                int id = Integer.parseInt(value.substring(value.indexOf(":")+2,value.indexOf("\n")));

                Intent intent = new Intent(getApplicationContext(),TicketActivity.class);
                intent.putExtra("tickID",id);
                startActivity(intent);

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
            Intent intent = new Intent(RetrieveTicketActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(RetrieveTicketActivity.this, BookTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cancel) {
            Intent intent = new Intent(RetrieveTicketActivity.this, CancelTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_retrieve) {
            Intent intent = new Intent(RetrieveTicketActivity.this, RetrieveTicketActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
