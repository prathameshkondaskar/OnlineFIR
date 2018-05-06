package com.example.prathamesh.policeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    EditText editTextuname,editTextPhone,editTextEmail,editTextPassword,editTextRepassword;
    Button buttonSignup,buttonCancel;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth= FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);

        editTextuname=(EditText)findViewById(R.id.uname);
        editTextPhone=(EditText)findViewById(R.id.phone);
        editTextEmail=(EditText)findViewById(R.id.email);
        editTextPassword=(EditText)findViewById(R.id.password);
        editTextRepassword=(EditText)findViewById(R.id.rePassword);
        buttonSignup=(Button)findViewById(R.id.signup);
        buttonCancel=(Button)findViewById(R.id.cancel);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
            }
        });



    }

    private void registerUser() {

        String email = editTextEmail.getText().toString().trim();
        String passsword = editTextPassword.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String repassword = editTextRepassword.getText().toString().trim();
        String username = editTextuname.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "Please Enter Username", Toast.LENGTH_SHORT).show();

            return;
        }
        else if(username.contains("'")){
            System.out.println(""+username);
            username.indexOf("'");
            String s1 = username.substring(0, username.indexOf("'"));
            String s2 = username.substring(username.indexOf("'"), username.length());
            System.out.println(s1+"'"+s2);
        }

        else if(!username.matches("[A-Za-z]{6,12}")){
            Toast.makeText(this, "Username must be greater than 6 Character \n" +
                    "Do not use Special Character", Toast.LENGTH_SHORT).show();
        }


        //////////////////Mobile Validation

        else if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please Enter Phone no.", Toast.LENGTH_SHORT).show();

            return;
        }
        else if(!phone.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$")){
            Toast.makeText(this, "Invalid pnone no.", Toast.LENGTH_SHORT).show();
        }
        else if (phone.length() != 10 || phone.matches(".*[a-z].*")) {
            Toast.makeText(this, "invalid Phone ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();

            return;
        } else if (TextUtils.isEmpty(passsword)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            return;
        }  else if (TextUtils.isEmpty(repassword)) {
            Toast.makeText(this, "Please Enter Password", Toast.LENGTH_SHORT).show();

            return;
        }  else if (!passsword.matches(repassword)) {
            Toast.makeText(this, "Password Not Matches ", Toast.LENGTH_SHORT).show();
        } else {

            // Register.emailid=email;

            progressDialog.setMessage("Registering User");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, passsword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                finish();
                                startActivity(new Intent(Signup.this, Login.class));
                                // Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(Signup.this, "Could not Registered" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}
