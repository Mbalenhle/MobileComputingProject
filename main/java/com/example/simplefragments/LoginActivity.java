package com.example.simplefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.HttpUrl;

public class LoginActivity extends AppCompatActivity {

    String localhost = "";
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Added by Kgotso */
        setTheme(R.style.Theme_SimpleFragments);
        setContentView(R.layout.activity_login);

    }

    public void doSignIn(View v){

        localhost = getString(R.string.loginUrl);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPassword);

        AsyncNetwork getTeams = new AsyncNetwork();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost).newBuilder();
        urlBuilder.addQueryParameter("email", email.getText().toString());
        urlBuilder.addQueryParameter("password", password.getText().toString());
        String url = urlBuilder.build().toString();
        getTeams.execute(url);

        while( getTeams.Result.equals("Waiting")){
        }
        try{
            JSONObject jsonObject =  new JSONObject(getTeams.Result);

            if(jsonObject.getString("success").equals("1")){

                Intent i = new Intent(getApplicationContext() , MainActivity.class );
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                //finish();

            }else{
                //password.setError("Incorrect credentials");
                Toast.makeText(getApplicationContext(), "invalid credentials", Toast.LENGTH_LONG).show();
            }

        }catch (Exception e ){
            e.printStackTrace();
        }
    }
    public void doSignUp(View v){
        Intent i = new Intent(getApplicationContext() , RegisterActivity.class );
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);

    }
}