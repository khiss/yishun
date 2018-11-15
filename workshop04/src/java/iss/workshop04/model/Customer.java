/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop04.model;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author issuser
 */
public class Customer {
    
    private Integer  customerId;
    private String zip;
    private String name;
    private String addressline1;
    private String addressline2;
    private String city;
    private String state;
    private String phone;
    private String fax;
    private String email;
    private Integer creditLimt;
    private String discountCode;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressline1() {
        return addressline1;
    }

    public void setAddressline1(String addressline1) {
        this.addressline1 = addressline1;
    }

    public String getAddressline2() {
        return addressline2;
    }

    public void setAddressline2(String addressline2) {
        this.addressline2 = addressline2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreditLimt() {
        return creditLimt;
    }

    public void setCreditLimt(Integer creditLimt) {
        this.creditLimt = creditLimt;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", zip=" + zip + ", name=" + name + ", addressline1=" + addressline1 + ", addressline2=" + addressline2 + ", city=" + city + ", state=" + state + ", phone=" + phone + ", fax=" + fax + ", email=" + email + ", creditLimt=" + creditLimt + ", discountCode=" + discountCode + '}';
    }
    
    public JsonObject toJson()
    {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        
        builder.add("customerId", customerId)
                .add("zip", zip)
                .add("name", name)
                .add("addressline1", addressline1)
                .add("addressline2", addressline2)
                .add("city", city)
                .add("state", state)
                .add("phone", phone)
                .add("fax", fax)
                .add("creditLimt", creditLimt)
                .add("discountCode", discountCode)
                .add("email", email);
                
        return builder.build();
    }
}
