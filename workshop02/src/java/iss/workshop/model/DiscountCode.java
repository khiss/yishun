/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop.model;

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
    
    @Id @Column(name="dicount_code")
    @Enumerated(EnumType.STRING)
    private Code discountCode;
    
    private Float rate;
    
    @OneToMany(mappedBy="dicountCode")
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
}
