package com.tecsup.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.tecsup.servicio.ServicioAlumnos;
import com.tecsup.util.DBConnection;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class Main {

	private JFrame frame;
	
	private JComboBox cmbEdades; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//cargarDatos();
			}
		});
		frame.setBounds(100, 100, 385, 226);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton btnNewButton = new JButton("Generar reporte");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				generarReporte();
			}
		});
		btnNewButton.setBounds(102, 100, 158, 25);
		frame.getContentPane().add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Edad : ");
		lblNewLabel.setBounds(75, 33, 61, 16);
		frame.getContentPane().add(lblNewLabel);

		
		ServicioAlumnos se = new ServicioAlumnos();
		ArrayList edades = se.getEdades();
		
		this.cmbEdades = new JComboBox(edades.toArray());
		//cmbEdades.addItem(getEdades());
		cmbEdades.setBounds(162, 29, 127, 25);
		frame.getContentPane().add(cmbEdades);
		
	}

	protected void generarReporte()  {	
		// TODO Auto-generated method stub
		try {
			DBConnection db = new DBConnection();
			Connection con = db.con();
			
		      Map parameters = new HashMap();
		      //parameters.put("SF_EDAD", 20);
		      String edad = this.cmbEdades.getSelectedItem().toString();
		      parameters.put("SF_EDAD", new Integer(edad));
		      
		     // parameters.put("FECHA", new java.util.Date());
		      JasperReport report = JasperCompileManager.compileReport(
		          "rpt_alumnos.jrxml");
		      JasperPrint print = JasperFillManager.fillReport(report, parameters, con);
		      JRViewer jv = new JRViewer(print);
		      final JFrame jf = new JFrame();
		      jf.getContentPane().add(jv);
		      jf.validate();
		      jf.setVisible(true);
		      jf.setExtendedState(jf.getExtendedState() | JFrame.MAXIMIZED_BOTH );
		      jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		      WindowListener exitListener = new WindowAdapter() {			
		    	  @Override
		    	  public void windowClosing(WindowEvent e) {
		    		  //super.windowClosing(e);
		    		  jf.dispose();
		    	  }		    	 	  
		      };
		      jf.addWindowListener(exitListener);
		      
		      con.close();
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
		
	}

	
}
