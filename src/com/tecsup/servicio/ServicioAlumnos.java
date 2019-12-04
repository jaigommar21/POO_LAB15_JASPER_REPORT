package com.tecsup.servicio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tecsup.util.DBConnection;

public class ServicioAlumnos {

	public ArrayList getEdades() {
		// TODO Auto-generated method stub

		ArrayList list = new ArrayList();
		
		DBConnection db = new DBConnection();
		Connection con = db.con();

		try {
			String query = "SELECT DISTINCT  EDAD FROM ALUMNOS GROUP BY EDAD ORDER BY EDAD ASC ";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int i = 1;
			while (rs.next()) {
				int edad= rs.getInt("EDAD");
				list.add(edad);
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
