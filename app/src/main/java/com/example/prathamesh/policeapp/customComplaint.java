package com.example.prathamesh.policeapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Planet on 4/11/2018.
 */
public class customComplaint extends ArrayAdapter<UserDetails> {
    static List<UserDetails> name,cno,csub;
    static  ArrayList <String>name2;
    static  ArrayList <String>name3 = new ArrayList<>();

    View view;

    public static String name1,email,address,phone,complaint,subject,complaint_no;

    TextView cusername,compno,csubject;
    public customComplaint(Context context, List<UserDetails> menu) {
        super(context,R.layout.customcomplaint, menu);
        name = menu;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        view = inflater.inflate(R.layout.customcomplaint,parent,false);

        name2=new ArrayList();


        cusername = (TextView)view.findViewById(R.id.username);
        compno = (TextView)view.findViewById(R.id.complaintno);
        csubject = (TextView)view.findViewById(R.id.subject);


        UserDetails userDetails=name.get(position);


        cusername.setText("Name : "+userDetails.getName());
        compno.setText("Complaint No : "+userDetails.getComplaint_no());
        csubject.setText("Subject : "+userDetails.getSubject());
        name1=userDetails.getName();
        subject=userDetails.getSubject();
        email=userDetails.getEmail();
        address=userDetails.getAddress();
        phone=userDetails.getPhone();
        complaint=userDetails.getComplaint();
        complaint_no=userDetails.getComplaint_no();

        for(int i=0;i<50;i++){
            try{
                UserDetails userDetails1=name.get(i);
                name2.add(userDetails1.getName());
                name2.add(userDetails1.getEmail());
                name2.add(userDetails1.getPhone());
                name2.add(userDetails1.getAddress());
                name2.add(userDetails1.getSubject());
                name2.add(userDetails1.getComplaint());
                name2.add(userDetails1.getComplaint_no());

            }catch(Exception e){
                Log.d("jadslfkjsadfsakldfjlks","sdfkjasdhf");
            }

        }

        for(int i =0;i<name2.size();i++){
            Log.d("VALUESSSSSSSSSSS ",""+name2.get(i));
        }
        for(int i=0;i<name2.size();i+=7){
            name3.add(name2.get(i)+"%"+name2.get(i+1)+"%"+name2.get(i+2)+"%"+name2.get(i+3)+"%"+name2.get(i+4)+"%"+name2.get(i+5)+"%"+name2.get(i+6));
        }

        Retrievedata.list2 = name3;




        return view;
    }

}
