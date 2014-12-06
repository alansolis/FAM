package com.flisol.views;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class view_aula extends JInternalFrame{

	
	private static final long serialVersionUID = 1L;
	private static view_aula objViewAula;
	private JPanel objPanel;
	private JScrollPane objScrollPane;
	private JLabel objLabelNombre, objLabelNum, objLabelTipo;
	private JTextField objTextFieldNombre, objTextFieldNum;
	private JComboBox<String> objComboBox;
	private JButton objBotonAgregar,objBotonModificar,objBotonEliminar, objBotonCancelar;
	private JTable objTabla;
	private DefaultTableModel objModelo;
	private Vector<String> headers;
	
	private view_aula(Vector<Vector<String>> modelo)
	{
		super("Aula",true,true,false,true);
		initComponents(modelo);
	}
	
	private void initComponents(Vector<Vector<String>> modelo)
	{
		this.setSize(600,600);
		this.resizable = false;
		this.setLayout(new FlowLayout());
		
		objPanel = new JPanel();
		objPanel.setLayout(null);
		objPanel.setPreferredSize(new Dimension(600,300));
		
		objLabelNombre = new JLabel("Nombre");
		objLabelNombre.setBounds(10, 10, 100, 25);
		
		objLabelNum = new JLabel("Num. lugares");
		objLabelNum.setBounds(10, 110, 100, 25);
		
		objLabelTipo = new JLabel("Tipo");
		objLabelTipo.setBounds(10, 210, 100, 25);
		
		objTextFieldNombre = new JTextField();
		objTextFieldNombre.setBounds(10, 60, 400, 25);
		
		objTextFieldNum = new JTextField();
		objTextFieldNum.setBounds(10, 160, 400, 25);
		
		objComboBox = new JComboBox<String>();
		objComboBox.addItem("Selecciona una opción . . . . .");
		objComboBox.addItem("salon");
		objComboBox.addItem("laboratorio");
		objComboBox.setBounds(10, 260, 400, 25);
		
		objBotonAgregar = new JButton("Agregar");
		objBotonAgregar.setBounds(430,10,150,50);
		
		objBotonModificar = new JButton("Modificar");
		objBotonModificar.setBounds(430,80,150,50);
		
		objBotonEliminar = new JButton("Eliminar");
		objBotonEliminar.setBounds(430,150,150,50);
		
		objBotonCancelar = new JButton("Cancelar");
		objBotonCancelar.setBounds(430, 220, 150, 50);
		
		objPanel.add(objLabelNombre);
		objPanel.add(objLabelNum);
		objPanel.add(objLabelTipo);
		objPanel.add(objTextFieldNombre);
		objPanel.add(objTextFieldNum);
		objPanel.add(objComboBox);
		objPanel.add(objBotonAgregar);
		objPanel.add(objBotonModificar);
		objPanel.add(objBotonEliminar);
		objPanel.add(objBotonCancelar);
		this.add(objPanel);
		
		headers = new Vector<String>();
		
		headers.add("ID");
		headers.add("Nombre");
		headers.add("Lugares");
		headers.add("Tipo");
		
		objModelo = new DefaultTableModel(modelo,headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		
		objTabla = new JTable(objModelo);
		objTabla.setBounds(10, 10, 520, 200);
		
		objScrollPane = new JScrollPane(objTabla);
		objScrollPane.setPreferredSize(new Dimension(580,200));
		
		this.add(objScrollPane);
	}
	
	public JButton getBotonAgregar()
	{
		return this.objBotonAgregar;
	}
	
	public JButton getBotonModificar()
	{
		return this.objBotonModificar;
	}
	
	public JButton getBotonEliminar()
	{
		return this.objBotonEliminar;
	}
	
	public JButton getBotonCancelar()
	{
		return this.objBotonCancelar;
	}
	
	public void setNombre(String nombre)
	{
		this.objTextFieldNombre.setText(nombre);
	}
	
	public JTable getTabla()
	{
		return this.objTabla;
	}
	
	public String getNombre()
	{
		return this.objTextFieldNombre.getText();
	}
	
	public void setLugares(String lugares)
	{
		this.objTextFieldNum.setText(lugares);
	}
	
	public String getLugares()
	{
		return this.objTextFieldNum.getText();
	}
	
	public void setTipo(String tipo)
	{
		this.objComboBox.setSelectedItem(tipo);
	}
	
	public Object getTipo()
	{
		return this.objComboBox.getSelectedItem();
	}
	
	public void setTipoIndex(int index)
	{
		this.objComboBox.setSelectedIndex(index);
	}
	
	public int getTipoIndex()
	{
		return this.objComboBox.getSelectedIndex();
	}
	
	public void setModel(Vector<Vector<String>> datos)
	{
		DefaultTableModel newModel = new DefaultTableModel(datos, headers)
		{	
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
	        //all cells false
	        return false;
			}
		};
		
		this.objTabla.setModel(newModel);
	}
	
	public static view_aula getInstance(Vector<Vector<String>> modelo)
	{
		if(objViewAula == null)
			objViewAula  = new view_aula(modelo);
		
		return objViewAula;
	}
} 
