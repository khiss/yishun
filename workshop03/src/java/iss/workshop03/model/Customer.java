/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop03.model;

import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author issuser
 */
@Entity
public class Customer {
    
    @Id @Column(name = "customer_id")
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
    
    @Column(name = "credit_limit")
    private Integer creditLimt;
    
    // Many "Customer" to One "DiscountCode".
    // The one is many is the relationship owner.
    // Thus, need to define the JoinColumn
    @ManyToOne
    @JoinColumn(name = "discount_code", referencedColumnName="discount_code")
    private DiscountCode discountCode;

    // One "Customer" to Many "PurchaseOrder".
    // Thus, "Customer" is NOT the relationship owner.
    // Need to add "mappedBy".
    @OneToMany(mappedBy = "customer")
    private List<PurchaseOrder> purchaseOrders;

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

    public DiscountCode getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(DiscountCode discountCode) {
        this.discountCode = discountCode;
    }

    public List<PurchaseOrder> getPurchaseOrders() {
        return purchaseOrders;
    }

    public void setPurchaseOrders(List<PurchaseOrder> purchaseOrders) {
        this.purchaseOrders = purchaseOrders;
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
                .add("discountCode", discountCode.getDiscountCode().toString())
                .add("email", email);
                
        return builder.build();
    }
}
