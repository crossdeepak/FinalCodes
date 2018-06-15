package com.cg.payroll.daoservices;

import java.sql.SQLException;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.payroll.beans.Associate;

public interface PayrollDAOServices extends JpaRepository<Associate, Integer>{

	/*int insertAssociate(Associate associate) throws SQLException;

	boolean updateAssociate(Associate associate) throws SQLException;

	boolean deleteAssociate(int associateID);

	Associate getAssociate(int associateID);

	List<Associate> getAssociates();*/

}