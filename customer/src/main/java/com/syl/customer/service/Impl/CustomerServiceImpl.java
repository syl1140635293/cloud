package com.syl.customer.service.Impl;

import com.syl.customer.dao.CustomerDao;
import com.syl.customer.service.CustomerService;
import com.syl.model.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao mCustomerDao;

    @Override
    public List<Customer> get() {
        List<Customer> customers = mCustomerDao.get(new Customer());
        return customers;
    }
}
