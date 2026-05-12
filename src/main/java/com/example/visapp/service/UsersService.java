package com.example.visapp.service;

import com.example.visapp.dao.UsersDAO;
import com.example.visapp.model.users;
import java.util.List;

public class UsersService {
    private UsersDAO usersDAO = new UsersDAO();

    public List<users> getAllUsers() throws Exception {
        return usersDAO.findAll();
    }

    public void addAdmin(users users) throws Exception {
        usersDAO.insert(users);
    }

    public void updateAdmin(users users) throws Exception {
        usersDAO.update(users);
    }

    public void deleteAdmin(int id) throws Exception {
        usersDAO.delete(id);
    }

    public int countAdmins() throws Exception {
        return usersDAO.count();
    }
}

