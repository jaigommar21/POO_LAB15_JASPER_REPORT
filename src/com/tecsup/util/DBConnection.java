package com.tecsup.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	
	
	public Connection con() {

		// declarando un objeto conexion;
		Connection link = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/TECSUP?useSSL=false&serverTimezone=UTC";
			String usuario = "root"; //
			String clave = "123456"; //
			
			link =  DriverManager.getConnection(url, usuario, clave);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return link;
	}



}
