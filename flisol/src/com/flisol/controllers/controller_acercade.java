package com.flisol.controllers;

import javax.swing.JInternalFrame;

import com.flisol.views.view_acercade;

public class controller_acercade {
	
	private static controller_acercade objAcercaDe;
	private view_acercade objViewAcercaDe;
	
	private controller_acercade()
	{
		objViewAcercaDe = view_acercade.getInstance();
	}
	
	public static controller_acercade getInstance()
	{
		if(objAcercaDe == null)
			objAcercaDe = new controller_acercade();
		
		return objAcercaDe;
	}
	
	public JInternalFrame getViewAcercaDe()
	{
		return this.objViewAcercaDe;
	}
}
