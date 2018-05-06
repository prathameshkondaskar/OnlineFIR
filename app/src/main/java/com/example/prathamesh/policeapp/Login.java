package com.example.prathamesh.policeapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {

    Button buttonLogin;
    EditText editTextEmail,editTextPassword;

    TextView textView;
    private ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressDialog=new ProgressDialog(this);
        firebaseAuth= FirebaseAuth.getInstance();

        editTextEmail=(EditText)findViewById(R.id.email1);
        editTextPassword=(EditText) findViewById(R.id.password1);
        buttonLogin=(Button)findViewById(R.id.buttonLogin);
        textView=(TextView)findViewById(R.id.text1);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Signup.class);
                startActivity(intent);
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userLogin();
            }
        });
    }

    private void userLogin() {

        String email=editTextEmail.getText().toString().trim();
        String passsword=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(passsword))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();

            return;
        }
        if(email.equals("admin") && passsword.equals("admin"))
        {
            Intent i = new Intent(Login.this, Retrievedata.class);
            startActivity(i);
        }
        else {
            Register.emailid = email;

            progressDialog.setMessage("Loging...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email, passsword)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {

                                finish();
                                startActivity(new Intent(Login.this, MainActivity.class));
                            } else {

                                Toast.makeText(Login.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    @Override
    public void onBackPressed() {


        final AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);

        builder.setTitle("Confirm");
        builder.setMessage("Do you want to Exit ?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                finishAffinity();
                System.exit(0);

            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
