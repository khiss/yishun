/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop05.app;

import iss.workshop05.model.Customer;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author issuser
 */
@Stateless
public class CustomerBean {
    
    // @Resource(lookup = "jdbc/sample")
    @PersistenceContext private EntityManager em;
    
    public Optional<Customer> findByCustomerId(Integer custId)
    {
        Customer customer = em.find(Customer.class, custId);
        
        return (Optional.ofNullable(customer));
    }
}
