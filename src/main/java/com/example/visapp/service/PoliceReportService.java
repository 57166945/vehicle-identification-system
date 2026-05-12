package com.example.visapp.service;

import com.example.visapp.dao.PoliceReportDAO;
import com.example.visapp.model.police_report;
import java.util.List;

public class PoliceReportService {
    private PoliceReportDAO reportDAO = new PoliceReportDAO();

    public List<police_report> getAllReports() throws Exception {
        return reportDAO.getAll();
    }

    public void addReport(police_report pr) throws Exception {
        reportDAO.insert(pr);
    }

    public void updateReport(police_report pr) throws Exception {
        reportDAO.update(pr);
    }

    public void deleteReport(int id) throws Exception {
        reportDAO.delete(id);
    }

    public int countReports() throws Exception {
        return reportDAO.count();
    }
}
