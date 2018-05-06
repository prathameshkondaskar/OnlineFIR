package com.example.prathamesh.policeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by PRATHAMESH on 4/13/2018.
 */

public class Retrievedata extends Activity {

   static ArrayList<String> name;
    static int posi;

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;

    ArrayList<UserDetails> list;
    static ArrayList<String> list2;
    ArrayAdapter<UserDetails> adapter;
    UserDetails userDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewcomplaints);


        name=new ArrayList<>();
        userDetails=new UserDetails();
        listView=(ListView)findViewById(R.id.listView);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("User");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<UserDetails>(this,R.layout.customcomplaint,R.id.username,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                UserDetails ud = new UserDetails();
                list.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    UserDetails userDetails =ds.getValue(UserDetails.class);
                    list.add(userDetails);


                }
                customComplaint customComplaint= new customComplaint(Retrievedata.this,list);

                listView.setAdapter(customComplaint);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              InfoView.pos=i;
                InfoView.viewdetails = list2.get(i);
              String x = customComplaint.complaint_no;
              Log.d("Complaint No ",x);
              name.add(String.valueOf(customComplaint.name2));
              startActivity(new Intent(Retrievedata.this,InfoView.class));
            }
        });
    }
}
