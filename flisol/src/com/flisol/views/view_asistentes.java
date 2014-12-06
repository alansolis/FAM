package com.flisol.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class view_asistentes extends JInternalFrame{

	private static final long serialVersionUID = 1L;
	private static view_asistentes objViewAsistentes;
	private JPanel objPanel;
	private JScrollPane objScrollPane;
	private JTable objTabla;
	private JButton objBotonAgregar, objBotonModificar, objBotonEliminar, objBotonCancelar;
	private JTextField objTextFieldNombre, objTextFieldOcupacion, objTextFieldGrupo, objTextFieldProcedencia;
	private JLabel objLabelNombre, objLabelOcupacion, objLabelGrupo, objLabelProcedencia;
	private Vector<String> headers;
	private DefaultTableModel objModelo;
	
	private view_asistentes(Vector<Vector<String>> datos)
	{
		super("Asistentes",true,true,false,true);
		initComponents(datos);
	}
	
	private void initComponents(Vector<Vector<String>> datos)
	{
		this.setSize(600,600);
		this.resizable = false;
		this.setLayout(new FlowLayout());
		
		objPanel = new JPanel();
		objPanel.setPreferredSize(new Dimension(600,300));
		objPanel.setLayout(null);
		
		objLabelNombre = new JLabel("Nombre");
		objLabelNombre.setBounds(10, 10, 400, 25);
		objTextFieldNombre = new JTextField();
		objTextFieldNombre.setBounds(10, 30, 400, 25);
		
		objLabelGrupo = new JLabel("Grupo");
		objLabelGrupo.setBounds(10, 80, 400, 25);
		objTextFieldGrupo = new JTextField();
		objTextFieldGrupo.setBounds(10, 100, 400, 25);
		
		objLabelOcupacion = new JLabel("Ocupación");
		objLabelOcupacion.setBounds(10, 150, 400, 25);
		objTextFieldOcupacion = new JTextField();
		objTextFieldOcupacion.setBounds(10, 170, 400, 25);
		
		objLabelProcedencia = new JLabel("Procedencia");
		objLabelProcedencia.setBounds(10, 230, 400, 25);
		objTextFieldProcedencia = new JTextField();
		objTextFieldProcedencia.setBounds(10, 250, 400, 25);
		
		objBotonAgregar = new JButton("Agregar");
		objBotonAgregar.setBounds(420, 10, 160, 50);
		
		objBotonEliminar = new JButton("Eliminar");
		objBotonEliminar.setBounds(420, 80, 160, 50);
		
		objBotonModificar = new JButton("Modificar");
		objBotonModificar.setBounds(420, 150, 160, 50);
		
		objBotonCancelar = new JButton("Cancelar");
		objBotonCancelar.setBounds(420, 220, 160, 50);
		
		objPanel.add(objLabelNombre);
		objPanel.add(objTextFieldNombre);
		objPanel.add(objLabelGrupo);
		objPanel.add(objTextFieldGrupo);
		objPanel.add(objLabelOcupacion);
		objPanel.add(objTextFieldOcupacion);
		objPanel.add(objLabelProcedencia);
		objPanel.add(objTextFieldProcedencia);
		objPanel.add(objBotonAgregar);
		objPanel.add(objBotonEliminar);
		objPanel.add(objBotonModificar);
		objPanel.add(objBotonCancelar);
		
		headers = new Vector<String>();
		headers.add("ID");
		headers.add("Nombre");
		headers.add("Grupo");
		headers.add("Ocupacion");
		headers.add("Procedencia");
		
		objModelo = new DefaultTableModel(datos,headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		objTabla = new JTable(objModelo);
		objTabla.setBounds(10, 10, 520, 230);
		
		objScrollPane = new JScrollPane(objTabla);
		objScrollPane.setPreferredSize(new Dimension(580,260));
		
		this.add(objPanel);
		this.add(objScrollPane);
	}
	
	public JButton getBotonAgregar()
	{
		return this.objBotonAgregar;
	}
	
	public JButton getBotonEliminar()
	{
		return this.objBotonEliminar;
	}
	
	public JButton getBotonModificar()
	{
		return this.objBotonModificar;
	}
	
	public JButton getBotonCancelar()
	{
		return this.objBotonCancelar;
	}
	
	public void setNombre(String nombre)
	{
		this.objTextFieldNombre.setText(nombre);
	}
	
	public void setGrupo(String grupo)
	{
		this.objTextFieldGrupo.setText(grupo);
	}
	
	public void setOcupacion(String ocupacion)
	{
		this.objTextFieldOcupacion.setText(ocupacion);
	}
	
	public void setProcedencia(String procedencia)
	{
		this.objTextFieldProcedencia.setText(procedencia);
	}
	
	public String getNombre()
	{
		return this.objTextFieldNombre.getText();
	}
	
	public String getGrupo()
	{
		return this.objTextFieldGrupo.getText();
	}
	
	public String getOcupacion()
	{
		return this.objTextFieldOcupacion.getText();
	}
	
	public String getProcedencia()
	{
		return this.objTextFieldProcedencia.getText();
	}
	
	public JTable getTabla()
	{
		return this.objTabla;
	}
	
	public void setModel(Vector<Vector<String>> datos)
	{
		objModelo = new DefaultTableModel(datos,headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		objTabla.setModel(objModelo);
	}
	
	public static view_asistentes getInstance(Vector<Vector<String>> datos)
	{
		if(objViewAsistentes == null)
			objViewAsistentes = new view_asistentes(datos);
		
		return objViewAsistentes;
	}
	
}
