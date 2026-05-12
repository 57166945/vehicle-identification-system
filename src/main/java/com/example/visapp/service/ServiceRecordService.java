package com.example.visapp.service;

import com.example.visapp.dao.ServiceRecordDAO;
import com.example.visapp.model.service_record;
import java.util.List;

public class ServiceRecordService {
    private ServiceRecordDAO serviceDAO = new ServiceRecordDAO();

    public List<service_record> getAllRecords() throws Exception {
        return serviceDAO.findAll();
    }

    public void addRecord(service_record sr) throws Exception {
        serviceDAO.insert(sr);
    }

    public void updateRecord(service_record sr) throws Exception {
        serviceDAO.update(sr);
    }

    public void deleteRecord(int id) throws Exception {
        serviceDAO.delete(id);
    }

    public int countRecords() throws Exception {
        return serviceDAO.count();
    }
}
