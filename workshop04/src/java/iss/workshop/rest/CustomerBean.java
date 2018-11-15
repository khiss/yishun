/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop.rest;

import iss.workshop04.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 *
 * @author issuser
 */
@Stateless
public class CustomerBean {
    @Resource(lookup = "jdbc/sample")
    private DataSource sampleDS;
    
    public Optional<Customer> findByCustomerId(Integer custId)
            throws SQLException
    {
        try (Connection conn = sampleDS.getConnection())
        {
            PreparedStatement ps = conn.prepareStatement("select * from customer where customer_id = ?");
            
            ps.setInt(1, custId);
            
            ResultSet rs = ps.executeQuery();
            if (!rs.next())
            {
                return (Optional.empty());
            }
            
            Customer customer = new Customer();
            
            customer.setCustomerId(rs.getInt("customer_id"));
            customer.setName(rs.getString("name"));
            customer.setAddressline1(rs.getString("addressline1"));
            customer.setAddressline2(rs.getString("addressline2"));
            customer.setCity(rs.getString("city"));
            customer.setState(rs.getString("state"));
            customer.setZip(rs.getString("zip"));
            customer.setPhone(rs.getString("phone"));
            customer.setFax(rs.getString("fax"));
            customer.setEmail(rs.getString("email"));
            customer.setCreditLimt(rs.getInt("credit_limit"));
            customer.setDiscountCode(rs.getString("discount_code"));
            
            return (Optional.of(customer));
        }
    }
    
}
