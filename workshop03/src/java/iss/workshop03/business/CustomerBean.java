/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iss.workshop03.business;

import iss.workshop03.model.Customer;
import iss.workshop03.model.DiscountCode;
import java.util.Optional;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author issuser
 */
@Stateless
public class CustomerBean {
    
    @PersistenceContext private EntityManager em;
    
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Optional<Customer> findByCustomerId(Integer custId)
    {
        Customer c = em.find(Customer.class, custId);
        System.out.println(">> id " + custId);
        System.out.println(">> c = " + c);
        return (Optional.ofNullable(c));
    }
    
    public void addNewCustomer(Customer c) throws CustomerException
    {
        DiscountCode code = em.find(DiscountCode.class, c.getDiscountCode().getDiscountCode());
        
        if (null == code)
            throw new CustomerException("Discount Code Not Found");
            
        c.setDiscountCode(code);
            
        em.persist(c);
    }
}
