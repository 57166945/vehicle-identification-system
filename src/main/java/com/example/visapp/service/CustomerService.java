package com.example.visapp.service;

import com.example.visapp.dao.CustomerDAO;
import com.example.visapp.model.customer;
import java.util.List;

public class CustomerService {
    private CustomerDAO customerDAO = new CustomerDAO();

    public List<customer> getAll() throws Exception {
        return customerDAO.getAll();
    }

    public void addCustomer(customer c) throws Exception {
        customerDAO.insert(c);
    }

    public void updateCustomer(customer c) throws Exception {
        customerDAO.update(c);
    }

    public void deleteCustomer(int id) throws Exception {
        customerDAO.delete(id);
    }

    public int countCustomers() throws Exception {
        return customerDAO.count();
    }
}
