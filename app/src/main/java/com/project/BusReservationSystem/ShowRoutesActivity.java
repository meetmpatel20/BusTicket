package com.project.BusReservationSystem;

import android.content.Intent;
import android.database.Cursor;
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
import android.widget.Toast;

import java.util.ArrayList;

public class ShowRoutesActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ListView routesData;
    ArrayList<String> listItem;
    DBHelper dbHelper;
    ArrayAdapter adapter;
    String date,source,dest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showroutes);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Bundle extras = getIntent().getExtras();
        source = extras.getString("src");
        dest = extras.getString("dt");
        date = extras.getString("date");

        routesData = (ListView) findViewById(R.id.listviewdata);

        dbHelper = new DBHelper(this);

        listItem = new ArrayList<>();

        showAvailableRoutes();
    }

    private void showAvailableRoutes() {

            Cursor cursor = dbHelper.getRoutes(source,dest);

            if(cursor.getCount() == 0){
                Toast.makeText(ShowRoutesActivity.this,"No Data to show",Toast.LENGTH_LONG).show();
            } else{
                while(cursor.moveToNext()){
                    listItem.add("ID: "+ cursor.getString(0)
                            + "\n"+"From: "+ cursor.getString(1)
                            + "\n"+"To: " +cursor.getString(2)
                            + "\n"+"Price: " +cursor.getDouble(3)
                            + "\n"+"Departure Time: "+cursor.getString(4)
                            + "\n"+"Arrival TIME: "+cursor.getString(5));




                }

                adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listItem);
                routesData.setAdapter(adapter);

                routesData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String value = (String)adapterView.getItemAtPosition(i);
                        int id = Integer.parseInt(value.substring(value.indexOf(" ")+1,value.indexOf("\n")));

                        Intent intent = new Intent(getApplicationContext(),BusLayout.class);
                        intent.putExtra("id",id);
                        intent.putExtra("date",date);
                        startActivity(intent);


                    }
                });
            }
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
            Intent intent = new Intent(ShowRoutesActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(ShowRoutesActivity.this, BookTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cancel) {
            Intent intent = new Intent(ShowRoutesActivity.this, CancelTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_retrieve) {
            Intent intent = new Intent(ShowRoutesActivity.this, RetrieveTicketActivity.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
