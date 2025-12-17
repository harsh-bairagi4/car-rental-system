/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author harsh
 */

public class Customer {

    private int customerId;
    private String name;
    private int age;
    private String licenseNo;
    private String phone;
    private String address;

    public Customer(){
    }
    public Customer(int customerId, String name, int age, String licenseNo, String phone, String address){
        this.customerId = customerId;
        this.name = name;
        this.age = age;
        this.licenseNo = licenseNo;
        this.phone = phone;
        this.address = address;
    }
    public Customer(String name, int age, String licenseNo, String phone, String address){
        this.name = name;
        this.age = age;
        this.licenseNo = licenseNo;
        this.phone = phone;
        this.address = address;
    }
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}

