package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editUserName;
    EditText editPassword;
    Button btnLogIn;
    TextView txtErrorLogIn;
    TextView txtForgotPassword;

    public class LogInListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            if(editUserName.getText().toString().contains("admin") && editPassword.getText().toString().contains("admin")){
                txtErrorLogIn.setVisibility(View.INVISIBLE);
                editUserName.setBackgroundResource(R.drawable.ednormal);
                editPassword.setBackgroundResource(R.drawable.ednormal);
                Intent homeAct = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(homeAct);
                finish();
            }else{
                txtErrorLogIn.setVisibility(View.VISIBLE);
                editUserName.setBackgroundResource(R.drawable.edterr);
                editPassword.setBackgroundResource(R.drawable.edterr);
            }
        }
    }

    public class ForgotPassListener implements View.OnClickListener{
        @Override
        public void onClick(View view){

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUserName = findViewById(R.id.editTextUserName);
        editPassword = findViewById(R.id.editTextPassword);
        btnLogIn = findViewById(R.id.buttonLogIn);
        txtErrorLogIn = findViewById(R.id.textViewErrorLogin);
        txtForgotPassword = findViewById(R.id.textViewForgotPassword);

        btnLogIn.setOnClickListener(new LogInListener());
        txtForgotPassword.setOnClickListener(new ForgotPassListener());
    }
}