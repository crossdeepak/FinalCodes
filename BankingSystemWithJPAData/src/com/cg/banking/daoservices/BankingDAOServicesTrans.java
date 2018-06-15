package com.cg.banking.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.banking.beans.Transaction;

public interface BankingDAOServicesTrans extends JpaRepository<Transaction, Integer>{

}
