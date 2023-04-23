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

public class Login extends AppCompatActivity {
    SharedPreferences sp;
    EditText password;
    String pass, getPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password = (EditText)findViewById(R.id.nPwd);

    }
    public void signInClick(View view) {
        sp = getSharedPreferences("BusUser", Context.MODE_PRIVATE);
        pass = sp.getString("Password","");//Getting password saved in Shared Preferences
        getPass = password.getText().toString();
        //Password validations
        if(getPass.isEmpty())
            password.setError("Enter Password");
        else if(!pass.equals(getPass))
            Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_LONG).show();
        else{
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
       }


    }
    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }
}