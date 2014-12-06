package com.flisol.views;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class desktopPane_principal extends JDesktopPane{


	private static final long serialVersionUID = 1L;
	private static desktopPane_principal objDesktopPane;
	private  Image IMG;
	
	private desktopPane_principal()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		IMG=new ImageIcon(getClass().getResource("/com/flisol/image/fondo.png")).getImage();
	}
	
	public static desktopPane_principal getInstance()
	{
		if(objDesktopPane == null)
			objDesktopPane = new desktopPane_principal();
		
		return objDesktopPane;
	}
	
	 public void paintChildren(Graphics g){
	        g.drawImage(IMG, 0, 0, getWidth(), getHeight(), this);
	        super.paintChildren(g);
	 }
}
