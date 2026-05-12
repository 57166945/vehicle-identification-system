package com.example.visapp.service;

import com.example.visapp.dao.ViolationDAO;
import com.example.visapp.model.violation;
import java.util.List;

public class ViolationService {
    private ViolationDAO violationDAO = new ViolationDAO();

    public List<violation> getAllViolations() throws Exception {
        return violationDAO.getAll();
    }

    public void addViolation(violation v) throws Exception {
        violationDAO.insert(v);
    }

    public void updateViolation(violation v) throws Exception {
        violationDAO.update(v);
    }

    public void deleteViolation(int id) throws Exception {
        violationDAO.delete(id);
    }

    public int countViolations() throws Exception {
        return violationDAO.count();
    }
}
