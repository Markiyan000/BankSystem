package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.dao.RoleDao;
import com.bankingapp.banksystem.dao.UserDao;
import com.bankingapp.banksystem.factory.UserFactory;
import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.model.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserDao userDao;

    private UserFactory userFactory;

    @Autowired
    public UserService(UserDao userDao, UserFactory userFactory) {
        this.userDao = userDao;
        this.userFactory = userFactory;
    }

    @Transactional
    public void save(User user, Set<UserRole> roles) {
        userDao.save(userFactory.constructUser(user, roles));
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public boolean checkExistsUserWithUsername(String username) {
        return userDao.existsUserByUsername(username);
    }

}
