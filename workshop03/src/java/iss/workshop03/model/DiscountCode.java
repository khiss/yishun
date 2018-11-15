/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package iss.workshop03.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author issuser
 */
@Entity
@Table(name="discount_code")
public class DiscountCode {
    
    public enum Code { H, L, M, N }
    
    @Id @Column(name="discount_code")
    @Enumerated(EnumType.STRING)
    private Code discountCode;
    
    private Float rate;
    
    // One "DiscountCode" to Many "Customer".
    // Thus, "DiscountCode" is NOT the relationship owner.
    // Need to add "mappedBy".
    @OneToMany(mappedBy="discountCode")
    private List<Customer> customer;

    public Code getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(Code discountCode) {
        this.discountCode = discountCode;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "DiscountCode{" + "discountCode=" + discountCode + ", rate=" + rate + ", customer=" + customer + '}';
    }
}
