package com.example.visapp.service;

import com.example.visapp.dao.InsurancePolicyDAO;
import com.example.visapp.model.insurance_policy;
import java.util.List;

public class InsurancePolicyService {
    private InsurancePolicyDAO policyDAO = new InsurancePolicyDAO();

    public List<insurance_policy> getAll() throws Exception {
        return policyDAO.findAll();
    }

    public void addPolicy(insurance_policy ip) throws Exception {
        policyDAO.insert(ip);
    }

    public void updatePolicy(insurance_policy ip) throws Exception {
        policyDAO.update(ip);
    }

    public void deletePolicy(int id) throws Exception {
        policyDAO.delete(id);
    }

    public int countPolicies() throws Exception {
        return policyDAO.count();
    }
}
