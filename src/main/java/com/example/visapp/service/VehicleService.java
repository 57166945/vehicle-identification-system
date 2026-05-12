package com.example.visapp.service;

import com.example.visapp.dao.VehicleDAO;
import com.example.visapp.model.vehicle;
import java.util.List;

public class VehicleService {
    private VehicleDAO vehicleDAO = new VehicleDAO();

    public List<vehicle> getAll() throws Exception {
        return vehicleDAO.findAll();
    }

    public void addVehicle(vehicle v) throws Exception {
        vehicleDAO.insert(v);
    }

    public void updateVehicle(vehicle v) throws Exception {
        vehicleDAO.update(v);
    }

    public void deleteVehicle(int id) throws Exception {
        vehicleDAO.delete(id);
    }

    public int countVehicles() throws Exception {
        return vehicleDAO.count();
    }
}

