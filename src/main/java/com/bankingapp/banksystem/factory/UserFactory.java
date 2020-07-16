package com.bankingapp.banksystem.factory;

import com.bankingapp.banksystem.dao.RoleDao;
import com.bankingapp.banksystem.model.PrimaryAccount;
import com.bankingapp.banksystem.model.SavingsAccount;
import com.bankingapp.banksystem.model.User;
import com.bankingapp.banksystem.model.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class UserFactory {

    private BCryptPasswordEncoder encoder;

    private RoleDao roleDao;

    @Autowired
    public UserFactory(BCryptPasswordEncoder encoder, RoleDao roleDao) {
        this.encoder = encoder;
        this.roleDao = roleDao;
    }

    @Transactional
    public User constructUser(User user, Set<UserRole> roles) {
        String encryptedPassword = encoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        for(UserRole role : roles) {
            roleDao.save(role.getRole());
        }

        PrimaryAccount primaryAccount = new PrimaryAccount();
        SavingsAccount savingsAccount = new SavingsAccount();

        primaryAccount.setAccountBalance(new BigDecimal(0.0));
        savingsAccount.setAccountBalance(new BigDecimal(0.0));

        user.setPrimaryAccount(primaryAccount);
        user.setSavingsAccount(savingsAccount);

        user.getRoles().addAll(roles);

        return user;
    }

}
