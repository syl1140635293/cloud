package com.syl.customer.controller;

import com.syl.customer.service.CustomerService;
import com.syl.model.customer.Customer;
import com.syl.model.orders.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService mCustomerService;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/get")
    public List<Customer> get(){
        List<Customer> orders = mCustomerService.get();
        return orders;
    }

}
