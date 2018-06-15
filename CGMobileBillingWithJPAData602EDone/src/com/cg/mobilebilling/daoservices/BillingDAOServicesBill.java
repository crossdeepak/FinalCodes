package com.cg.mobilebilling.daoservices;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.mobilebilling.beans.Bill;

public interface BillingDAOServicesBill extends JpaRepository<Bill, Integer>{

}
