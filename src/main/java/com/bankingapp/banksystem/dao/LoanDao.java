package com.bankingapp.banksystem.dao;

import com.bankingapp.banksystem.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanDao extends JpaRepository<Loan, Long> {
}
