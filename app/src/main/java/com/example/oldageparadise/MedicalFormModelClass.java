package com.example.oldageparadise;

public class MedicalFormModelClass {



    private String name, email, contact, address, age, weight, startDate, endDate;

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getContact() {
        return contact;
    }
    public String getAddress() {
        return address;
    }
    public String getAge() {
        return age;
    }
   /* public String getWeight() {
        return weight;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }*/
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public void setWeight(String weight) {
        this.weight = weight;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public MedicalFormModelClass(String name, String email, String contact, String address , String age
    ,String weight, String startDate, String endDate) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = address;
        this.age = age;
        this.weight = weight;
        this.startDate = startDate;
        this.endDate= endDate;
    }

}
