package com.example.prathamesh.policeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class Register extends AppCompatActivity {

    EditText editTextRname,editTextRphone,editTextRaddr,editTextRcomplaint;
    Button buttonCancel1,buttonSubmit;
    Spinner category;
    static String emailid;

    static ArrayList<String> s = new ArrayList<>();
    static ArrayList<Integer> i = new ArrayList<>();

    private static String ALPHA_NUMERIC_STRING="ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    ArrayAdapter<String> ad;

    static ArrayList<String> categories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();

        FirebaseUser user=firebaseAuth.getCurrentUser();


        category = (Spinner)findViewById(R.id.rspinner);
        editTextRname=(EditText)findViewById(R.id.rname);
        editTextRphone=(EditText)findViewById(R.id.rphone);
        editTextRaddr=(EditText)findViewById(R.id.raddress);
        editTextRcomplaint=(EditText)findViewById(R.id.rcomplaint);
        buttonSubmit=(Button)findViewById(R.id.submit);
        buttonCancel1=(Button)findViewById(R.id.cancel1);

        buttonCancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,MainActivity.class));
            }
        });
        categories.add("Others");
        categories.add("Rape");
        categories.add("Murder");
        categories.add("Hit and Run");
        categories.add("Half Murder");
        categories.add("Fraud");
        categories.add("Abduction");
        categories.add("Black Marketing");
        categories.add("Terrorism");
        categories.add("Money Laundering");
        categories.add("Kidnapping");
        categories.add("Extoration");
        categories.add("Child Labour");
        categories.add("Robbery");

        ad = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,categories);
        category.setAdapter(ad);


        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUserInfo();

            }
        });

    }
    private void saveUserInfo()
    {

        String complaint_no = randomnumber();
        ViewComplaint.cno=complaint_no;

        String name=editTextRname.getText().toString().trim();
        String address=editTextRaddr.getText().toString().trim();
        String phone=editTextRphone.getText().toString().trim();
        String complaint=editTextRcomplaint.getText().toString().trim();
        String subject = category.getSelectedItem().toString();

        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(this,"Please Enter name",Toast.LENGTH_SHORT).show();

            return;
        }
        else if(!name.matches("[A-Za-z]{6,12}")){
            Toast.makeText(this, "Username must be greater than 6 Character \n" +
                    "Do not use Special Character", Toast.LENGTH_SHORT).show();
        }

        else if(TextUtils.isEmpty(address))
        {
            Toast.makeText(this,"Please Enter Address ",Toast.LENGTH_SHORT).show();

            return;
        }

       else if(TextUtils.isEmpty(phone))
        {
            Toast.makeText(this,"Please Enter Phone ",Toast.LENGTH_SHORT).show();

            return;
        }
        else if(!phone.matches("^(?:(?:\\+|0{0,2})91(\\s*[\\ -]\\s*)?|[0]?)?[789]\\d{9}|(\\d[ -]?){10}\\d$")){
            Toast.makeText(this, "Invalid pnone no.", Toast.LENGTH_SHORT).show();
        }
        else if(phone.length()!=10||phone.matches(".*[a-z].*"))
        {
            Toast.makeText(this,"Invalid Phone ",Toast.LENGTH_SHORT).show();

        }
        else if(TextUtils.isEmpty(complaint))
        {
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();

            return;
        }

        else if(TextUtils.isEmpty(subject))
        {
            Toast.makeText(this,"Please Enter Subject",Toast.LENGTH_SHORT).show();

            return;
        }
        else {


            UserDetails userDetails = new UserDetails(name, emailid, phone, address, complaint, subject, complaint_no);

            FirebaseUser user = firebaseAuth.getCurrentUser();
            databaseReference.child("User").child(user.getUid()).setValue(userDetails);
            Toast.makeText(Register.this, "Information saved.....", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Register.this,ViewComplaint.class));
        }
    }
    public static String randomnumber(){
        i.add(1);
        i.add(2);
        i.add(3);
        i.add(4);
        i.add(5);
        i.add(6);
        i.add(7);
        i.add(8);
        i.add(9);
        i.add(0);
        String val="";
        String[] var = ALPHA_NUMERIC_STRING.split("");
        for(String x : var){
            s.add(x);
        }
        for(int h=0;h<20;h++){
            val="";
            Collections.shuffle(s);
            Collections.shuffle(i);

            for(int j=0;j<6;j++){
                val+=s.get(j);
            }
            val+="-";
            for(int j=0;j<6;j++){
                val+=i.get(j);
            }
            Collections.shuffle(s);
            Collections.shuffle(i);
            val+="-";
            for(int j=0;j<6;j++){
                val+=s.get(j);
            }
            val+="-";
            for(int j=0;j<6;j++){
                val+=i.get(j);
            }

            break;
        }
        return val;
    }

}
