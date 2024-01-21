package es.deusto.ingenieria.sd.auctions.client.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import es.deusto.ingenieria.sd.auctions.client.MainProgram;
import es.deusto.ingenieria.sd.auctions.server.data.dto.ChallengeDTO;

@SuppressWarnings("serial")
public class StartAChallengeGUI extends JFrame{
	
	private JTable tabla;
	@SuppressWarnings("unused")
	private int prueba;
	private List<ChallengeDTO> listaChallenges;
	private ChallengeDTO challenge;
	
	@SuppressWarnings("unused")
	public StartAChallengeGUI() {
		
		setSize(1200,600);
		setResizable(false);
		setLocationRelativeTo(null);
		// Establecer el diseño a AbsoluteLayout
        setLayout(new FlowLayout());
        
        //Boton
        JButton botonAceptar = new JButton("Acept Challenge");
        botonAceptar.setBackground(Color.WHITE);
        
        //Creacion del modelo de la tabla
        DefaultTableModel m = new DefaultTableModel();
        m.addColumn("Challenges");
        
        //Tabla y ScrollPane
        tabla = new JTable(m) {
        	private static final long serialVersionUID = 1L;
        	public boolean isCellEditable(int row, int col) {
        		return false;
        	} 
        };
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(0, 128, 1187, 304);
        
        //Cargar Challenges en la tabla
        listaChallenges = MainProgram.mainWindow.getChallenges();
        
        //Tamaños de los elemenetos
        scrollPane.setPreferredSize(new Dimension(1187, 304));
        botonAceptar.setPreferredSize(new Dimension(89, 23));
        
        
        //Challenge selection
        ListSelectionModel model = tabla.getSelectionModel();
        tabla.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				int filaSeleccionada = tabla.getSelectedRow();
				prueba = filaSeleccionada;
					if(filaSeleccionada != 1) {
					challenge = listaChallenges.get(filaSeleccionada);
					}
				}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
				
			});
        
        //Listeners
        ListSelectionListener l = new ListSelectionListener() {
        	
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(!e.getValueIsAdjusting()) {
					int filaSelec = tabla.getSelectedRow();
					if (filaSelec >= 0) {
						for (int i = 0; i < tabla.getColumnCount(); i++) {
							tabla.setValueAt(tabla.getValueAt(filaSelec, i), filaSelec, i);
						}
						tabla.setSelectionBackground(Color.GREEN);
					}
				}				
			}
        };
        
        tabla.getSelectionModel().addListSelectionListener(l);
        
        botonAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(challenge != null) {
					//MainProgram.mainWindow.acceptChallenge( /*token*/ , challenge);
				}
			}
		});
        
        //Add
        add(scrollPane);
        add(botonAceptar);
        
        setVisible(true);
        
	}
}
