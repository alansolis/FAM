package com.flisol.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class view_acercade extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static view_acercade objAcercade;
	private JPanel objPanel;
	private JLabel objLabelNombre, objLabelLicencia, objLabelJava, objLabelImagen;

	
	private view_acercade()
	{
		super("Acerca de",true,true,false,true);
		initComponents();
	}
	
	private void initComponents()
	{
		this.setSize(600, 400);
		this.resizable = false;
		this.setLayout(new FlowLayout());
		
		objPanel = new JPanel();
		objPanel.setPreferredSize(new Dimension(600,400));
		objPanel.setLayout(null);
		
		objLabelImagen = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/com/flisol/image/flisol_logo.png")).getImage()));
		objLabelImagen.setBounds(0, 0, 600, 300);
		
		objLabelNombre = new JLabel("Flisol 1.0.1");
		objLabelNombre.setFont(new Font("Arial Bold",Font.BOLD,25 ));
		objLabelNombre.setBounds(230, 170, 200, 200);
		
		objLabelLicencia = new JLabel("License GNU/GPL");
		objLabelLicencia.setFont(new Font("Arial Bold",Font.BOLD,16 ));
		objLabelLicencia.setBounds(20, 240, 200, 200);
		
		objLabelJava = new JLabel("Powered by JAVA");
		objLabelJava.setFont(new Font("Arial Bold",Font.BOLD,16 ));
		objLabelJava.setBounds(430, 240, 200, 200);
		
		objPanel.add(objLabelNombre);
		objPanel.add(objLabelLicencia);
		objPanel.add(objLabelJava);
		objPanel.add(objLabelImagen);
		this.add(objPanel);
	}
	
	public static view_acercade getInstance()
	{
		if(objAcercade == null)
			objAcercade = new view_acercade();
		
		return objAcercade;
	}
}
