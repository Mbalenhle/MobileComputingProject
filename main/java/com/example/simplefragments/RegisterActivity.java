package com.example.simplefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.HttpUrl;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Added by Kgotso */
        setTheme(R.style.Theme_SimpleFragments);

        setContentView(R.layout.activity_register);

        Button btnSignUp = findViewById(R.id.buttonSignUp);

        String localhost = getString(R.string.registerUrl);
        EditText email = findViewById(R.id.editEmail);
        EditText password = findViewById(R.id.editPassword);
        EditText first_name = findViewById(R.id.editPlayerName);
        EditText last_name = findViewById(R.id.editPlayerLastName);
        EditText confirm_password = findViewById(R.id.editConfirmPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean valid = true;

                //error handling
                if(password.getText().toString().trim().length()< 5){
                    password.setError("password must be at least 4 characters");
                    valid = false;

                }

                if(first_name.getText().toString().trim().length() < 2){
                    first_name.setError("Field must contain at least 2 character");
                    valid = false;

                }
                if(last_name.getText().toString().trim().length() < 2){
                    last_name.setError("Field must contain at least 2 character");
                    valid = false;
                }
                if(!password.getText().toString().trim().equals(confirm_password.getText().toString().trim())){
                    confirm_password.setError("passwords do not match");
                    valid = false;
                }
                Pattern pattern = Pattern.compile(".+@.+\\.com$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(email.getText().toString());
                boolean matchFound = matcher.find();
                if(!matchFound){
                    email.setError("Please enter valid email");
                    valid = false;
                }

                if(valid){
                    AsyncNetwork getTeams = new AsyncNetwork();
                    HttpUrl.Builder urlBuilder = HttpUrl.parse(localhost).newBuilder();
                    urlBuilder.addQueryParameter("email", email.getText().toString().trim());
                    urlBuilder.addQueryParameter("password", password.getText().toString().trim());
                    urlBuilder.addQueryParameter("first_name", first_name.getText().toString().trim());
                    urlBuilder.addQueryParameter("last_name", last_name.getText().toString().trim());

                    String url = urlBuilder.build().toString();
                    getTeams.execute(url);

                    while( getTeams.Result.equals("Waiting")){
                        System.out.println("loading");
                    }
                    try{
                        JSONObject jsonObject =  new JSONObject(getTeams.Result);

                        if(jsonObject.getString("success").equals("1")){
                            Intent i = new Intent(getApplicationContext() , LoginActivity.class );
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            //startActivity(i);
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), jsonObject.getString("message").toString(), Toast.LENGTH_LONG).show();
                        }

                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}