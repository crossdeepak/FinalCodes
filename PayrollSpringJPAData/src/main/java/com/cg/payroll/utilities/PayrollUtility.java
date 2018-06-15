package com.cg.payroll.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.cg.payroll.exception.PayrollServicesDownException;

public class PayrollUtility {
	private static Connection con=null;
	public static Connection getDBConnection()throws PayrollServicesDownException{
		//we have to create connection only once
		try {
			if(con==null){
				Properties properties=new Properties();
				properties.load(new FileInputStream(new File(".//resource//payrolldb.properties")));
				Class.forName(properties.getProperty("driver"));
				con=DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("user"),"");
			}
			return con;
			//We have to hide these exceptions from user
		} catch (ClassNotFoundException|IOException|SQLException e) {
			e.printStackTrace();
			throw new PayrollServicesDownException("Payroll services are down...try again later", e);
		}
	}
}