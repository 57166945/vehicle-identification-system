package com.example.visapp.service;

import com.example.visapp.dao.CustomerQueryDAO;
import com.example.visapp.model.customer_query;
import java.util.List;

public class CustomerQueryService {
    private CustomerQueryDAO queryDAO = new CustomerQueryDAO();

    public List<customer_query> getAll() throws Exception {
        return queryDAO.findAll();
    }

    public void addQuery(customer_query cq) throws Exception {
        queryDAO.insert(cq);
    }

    public void updateQuery(customer_query cq) throws Exception {
        queryDAO.update(cq);
    }

    public void deleteQuery(int id) throws Exception {
        queryDAO.delete(id);
    }

    public int countQueries() throws Exception {
        return queryDAO.count();
    }
}
