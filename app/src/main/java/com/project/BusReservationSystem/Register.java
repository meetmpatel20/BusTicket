package com.project.BusReservationSystem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.project.brs.R;

public class Register extends AppCompatActivity {
    SharedPreferences sp;
    SharedPreferences.Editor edit;
    //DBHelper dbHelper;
    EditText nPWD,cPWD;
    String nPass, cPass;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sp=getSharedPreferences("BusUser", Context.MODE_PRIVATE);
        if(!sp.getString("Password","").isEmpty()){//Checking if password is already present in shared preferences and start Sigin activity
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }

        nPWD = (EditText)findViewById(R.id.nPwd);
        cPWD = (EditText)findViewById(R.id.cPwd);
        dbHelper.DBName = "BusReservation";
        dbHelper = new DBHelper(getApplicationContext());
        dbHelper.insertBusData();




    }

    public void submitClick(View view){
        nPass = nPWD.getText().toString();
        cPass = cPWD.getText().toString();
        //Password and confirm password validations
        if(nPass.isEmpty())
            nPWD.setError("Enter Password");
        else if(cPass.isEmpty())
            cPWD.setError("Enter Password");
        else if(!nPass.equals(cPass))
            Toast.makeText(this,"Both Passwords do not match",Toast.LENGTH_LONG).show();

        else
        {
            sp=getSharedPreferences("BusUser", Context.MODE_PRIVATE);
            edit = sp.edit();
            edit.clear();
            edit.putString("Password",nPass);//Storing password in shared preferencess
            edit.apply();
            Intent i = new Intent(getApplicationContext(),Login.class);
            startActivity(i);//Starting signin activity
        }


    }
    @Override
    public void onBackPressed()
    {
        finish();
    }


}
