package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.dao.UserDao;
import com.bankingapp.banksystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserService {

    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public boolean checkExistsUserWithUsername(String username) {
        return userDao.existsUserByUsername(username);
    }
}
