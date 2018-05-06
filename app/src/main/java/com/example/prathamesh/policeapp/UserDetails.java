package com.example.prathamesh.policeapp;

/**
 * Created by PRATHAMESH on 4/12/2018.
 */

public class UserDetails {
    public String name,email,address,phone,complaint,subject,complaint_no;
    public UserDetails()
    {

    }
    public UserDetails(String name,String email,String phone,String address,String complaint,String subject,String complaint_no )
    {
        this.name=name;
        this.email=email;
        this.address=address;
        this.phone=phone;
        this.complaint=complaint;
        this.subject=subject;
        this.complaint_no=complaint_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getComplaint_no() {
        return complaint_no;
    }

    public void setComplaint_no(String complaint_no) {
        this.complaint_no = complaint_no;
    }
}
