package com.flisol.views;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.WindowConstants;
import com.flisol.views.desktopPane_principal;

public class view_principal extends JFrame{


	private static final long serialVersionUID = 1L;
	private static view_principal objViewPrincipal;
	desktopPane_principal objDesktopPane;
	private JMenuBar objMenuBar;
	private JMenu objFlisol,objGestionar, objAyuda;
	private JMenuItem objMenuExpositor, objMenuAula, objMenuAsistentes, objMenuAcercaDe, objMenuActividad, objRegistro;
	private float width;
	private float height;
	
	
	private view_principal()
	{
		super("Flisol");
		initComponents();
	}
	
	private void initComponents()
	{
		pantalla();
		
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setSize((int) width, (int) height);
		
		objDesktopPane = desktopPane_principal.getInstance();
		this.add(objDesktopPane);
		
		objMenuBar = new JMenuBar();
		
		this.setJMenuBar(objMenuBar);
		
		objFlisol = new JMenu("Flisol");
		objGestionar = new JMenu("Gestionar");
		objMenuActividad = new JMenuItem("Actividades");
		objRegistro = new JMenuItem("Registro");
		objAyuda = new JMenu("Ayuda");
		
		objMenuExpositor = new JMenuItem("Expositores");
		objMenuAula = new JMenuItem("Aula");
		objMenuAsistentes = new JMenuItem("Asistentes");
		objMenuAcercaDe = new JMenuItem("Acerca de");
		objGestionar.add(objMenuExpositor);
		objGestionar.add(objMenuAula);
		objGestionar.add(objMenuAsistentes);
		objGestionar.add(objMenuActividad);
		objGestionar.add(objRegistro);
		
		objAyuda.add(objMenuAcercaDe);
		
		objMenuBar.add(objFlisol);
		objMenuBar.add(objGestionar);
		objMenuBar.add(objAyuda);
		
	}
	
	public JDesktopPane getDesktopPane()
	{
		return objDesktopPane;
	}
	
	public static view_principal getInstance()
	{
		if(objViewPrincipal == null)
			objViewPrincipal = new view_principal();
		
		return objViewPrincipal;
	}
	
	//obtenemos el tamaño de la pantalla
	private void pantalla()
	{
			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension tamanio = tk.getScreenSize();
			width = (float) tamanio.getWidth(); 
		    height = (float) tamanio.getHeight();
	}
	
	public JMenuItem getMenuExpositores()
	{
		return objMenuExpositor;
	}
	
	public JMenuItem getMenuAula()
	{
		return objMenuAula;
	}
	
	public JMenuItem getMenuAcercade()
	{
		return objMenuAcercaDe;
	}
	
	public JMenuItem getMenuAsistentes()
	{
		return objMenuAsistentes;
	}
	
	public JMenuItem getMenuActividad()
	{
		return objMenuActividad;
	}
	
	public JMenuItem getMenuRegistro()
	{
		return objRegistro;
	}
}
