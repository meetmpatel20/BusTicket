package com.project.BusReservationSystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.project.brs.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BusLayout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DBHelper dbHelper;
    ArrayList<Integer> bSeats;
    String date;
    int busID;
    Button[] seats = new Button[15];
    int seatNo;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buslayout);
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
        busID = extras.getInt("id");
        date = extras.getString("date");
        int[] idList = {R.id.b1, R.id.b2, R.id.b3,R.id.b4,R.id.b5,R.id.b6,R.id.b7,R.id.b8,R.id.b9,R.id.b10,R.id.b11,R.id.b12,R.id.b13,R.id.b14,R.id.b15};
        for(int i = 0;i<15;i++){

            seats[i] = (Button)findViewById(idList[i]);
        }


        dbHelper = new DBHelper(this);

        bSeats = dbHelper.bTickets(busID,date);

        for(int i = 0;i<15;i++){
            for(int j = 0;j<bSeats.size();j++){
                if(bSeats.get(j)==i+1)
                    seats[i].setEnabled(false);

            }
        }

    }

    public void bookClick(View view){

        btn = (Button) view;
        seatNo = Integer.parseInt(btn.getText().toString());

        int ticketId = dbHelper.bookTicket(busID,date,seatNo);
        Toast.makeText(this,"Ticket Booked Sucessfully",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getApplicationContext(),TicketActivity.class);
        intent.putExtra("tickID",ticketId);
        startActivity(intent);



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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(BusLayout.this, MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_book) {
            Intent intent = new Intent(BusLayout.this, BookTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_cancel) {
            Intent intent = new Intent(BusLayout.this, CancelTicketActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_retrieve) {
            Intent intent = new Intent(BusLayout.this, RetrieveTicketActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
