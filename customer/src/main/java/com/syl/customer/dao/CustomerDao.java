package com.syl.customer.dao;

import com.syl.model.customer.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CustomerDao {

    int insert(Customer customer);

    List<Customer> get(Customer customer);

    int update(Customer customer);
}