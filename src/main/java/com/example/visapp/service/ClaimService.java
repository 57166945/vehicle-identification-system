package com.example.visapp.service;

import com.example.visapp.dao.ClaimDAO;
import com.example.visapp.model.claim;
import java.util.List;

public class ClaimService {
    private ClaimDAO claimDAO = new ClaimDAO();

    public List<claim> getAllClaims() throws Exception {
        return claimDAO.findAll();
    }

    public void addClaim(claim c) throws Exception {
        claimDAO.insert(c);
    }

    public void updateClaim(claim c) throws Exception {
        claimDAO.update(c);
    }

    public void deleteClaim(int id) throws Exception {
        claimDAO.delete(id);
    }

    public int countClaims() throws Exception {
        return claimDAO.count();
    }
}
