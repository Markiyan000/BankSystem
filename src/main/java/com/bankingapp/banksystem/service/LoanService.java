package com.bankingapp.banksystem.service;

import com.bankingapp.banksystem.dao.LoanDao;
import com.bankingapp.banksystem.model.Loan;
import com.bankingapp.banksystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoanService {

    private LoanDao loanDao;

    @Autowired
    public LoanService(LoanDao loanDao) {
        this.loanDao = loanDao;
    }

    @Transactional
    public void addLoan(User user, Loan loan) {
        user.addLoan(loan);
    }

    public Loan findById(Long loanId) {
        return loanDao.findById(loanId).get();
    }

    public void removeById(Long loanId) {
        loanDao.deleteById(loanId);
    }

}
