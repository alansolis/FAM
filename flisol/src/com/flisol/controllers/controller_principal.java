package com.flisol.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.flisol.views.view_principal;

public class controller_principal implements ActionListener{

	private static controller_principal objControllerPrincipal;
	private view_principal objViewPrincipal;
	private  controller_expositores objControllerExpositores;
	private controller_aula objControllerAula;
	private controller_acercade objControlleracAcercade;
	private controller_asistentes objControllerAsistentes;
	private controller_actividad objControllerActividad;
	private controller_registro objControllerRegistro;
	
	private controller_principal()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		objViewPrincipal = view_principal.getInstance();
		objViewPrincipal.setVisible(true);
		objViewPrincipal.getMenuExpositores().addActionListener(this);
		objViewPrincipal.getMenuAula().addActionListener(this);
		objViewPrincipal.getMenuAcercade().addActionListener(this);
		objViewPrincipal.getMenuAsistentes().addActionListener(this);
		objViewPrincipal.getMenuActividad().addActionListener(this);
		objViewPrincipal.getMenuRegistro().addActionListener(this);
		
	}
	
	
	public static controller_principal getInstance()
	{
		if(objControllerPrincipal == null)
			objControllerPrincipal = new controller_principal();
		
		return objControllerPrincipal;
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() == objViewPrincipal.getMenuExpositores())
		{
			objControllerExpositores = controller_expositores.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControllerExpositores.getViewExpositores()))
			{
				objViewPrincipal.getDesktopPane().add(objControllerExpositores.getViewExpositores());
				objControllerExpositores.getViewExpositores().setVisible(true);
			}
			else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControllerExpositores.getViewExpositores());
			}
				
		}else if(arg0.getSource() == objViewPrincipal.getMenuAula())
		{
			objControllerAula = controller_aula.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControllerAula.getViewAula()))
			{
				objViewPrincipal.getDesktopPane().add(objControllerAula.getViewAula());
				objControllerAula.getViewAula().setVisible(true);
			}
			else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControllerAula.getViewAula());
			}
				
		}else if(arg0.getSource() == objViewPrincipal.getMenuAcercade())
		{
			objControlleracAcercade = controller_acercade.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControlleracAcercade.getViewAcercaDe()))
			{
				objViewPrincipal.getDesktopPane().add(objControlleracAcercade.getViewAcercaDe());
				objControlleracAcercade.getViewAcercaDe().setVisible(true);
			}
			else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControlleracAcercade.getViewAcercaDe());
			}
		}else if(arg0.getSource() == objViewPrincipal.getMenuAsistentes())
		{
			objControllerAsistentes = controller_asistentes.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControllerAsistentes.getViewAsistentes()))
			{
				objViewPrincipal.getDesktopPane().add(objControllerAsistentes.getViewAsistentes());
				objControllerAsistentes.getViewAsistentes().setVisible(true);
			}else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControllerAsistentes.getViewAsistentes());
			}
			
		}else if(arg0.getSource() == objViewPrincipal.getMenuActividad())
		{
			objControllerActividad = controller_actividad.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControllerActividad.getViewActividad()))
			{
				objViewPrincipal.getDesktopPane().add(objControllerActividad.getViewActividad());
				objControllerActividad.getViewActividad().setVisible(true);
			}else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControllerActividad.getViewActividad());
			}
		}else if(arg0.getSource() == objViewPrincipal.getMenuRegistro())
		{
			objControllerRegistro = controller_registro.getInstance();
			
			if(!objViewPrincipal.getDesktopPane().isAncestorOf(objControllerRegistro.getViewRegistro()))
			{
				objViewPrincipal.getDesktopPane().add(objControllerRegistro.getViewRegistro());
				objControllerRegistro.getViewRegistro().setVisible(true);
			}else
			{
				objViewPrincipal.getDesktopPane().setSelectedFrame(objControllerRegistro.getViewRegistro());
			}
		}
		
	}
}
