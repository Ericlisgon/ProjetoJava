package br.com.exemplo.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import br.com.exemplo.dao.LeitorDAO;
import br.com.exemplo.model.Leitor;
import javax.swing.SwingConstants;
import java.awt.Canvas;
import javax.swing.JMenuBar;

public class TelaPrincipal extends JFrame {
	
	protected static final JLabel txtcdleitor = null;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblmensagem;
	private JTextField txtcodeleitor;
	private JComboBox cmbtipoleitor;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnConsultar;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;
	private TextArea textArea;
	private Leitor leitor;
	private LeitorDAO dao;
	private JLabel lblNome;
	private JTextField txtnomeleitor;
	private JLabel lblTipo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break ;
					
				}
			}
		} catch (ClassNotFoundException ex){
			System.err.println(ex);
		} catch (InstantiationException ex) {
			System.err.println(ex);
		} catch (IllegalAccessException ex) {
			System.err.println(ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 716, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblNewLabel = new JLabel("C\u00F3digo:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 11, 77, 25);
		contentPane.add(lblNewLabel);
		
		lblmensagem = new JLabel("");
		lblmensagem.setBackground(Color.BLACK);
		lblmensagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblmensagem.setForeground(new Color(255, 0, 0));
		lblmensagem.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblmensagem.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, new Color(0, 0, 0), Color.BLACK, Color.DARK_GRAY));
		lblmensagem.setBounds(211, 86, 331, 39);
		contentPane.add(lblmensagem);
		
		txtcodeleitor = new JTextField();
		txtcodeleitor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtcodeleitor.setBounds(64, 10, 51, 30);
		contentPane.add(txtcodeleitor);
		txtcodeleitor.setColumns(10);
		
		cmbtipoleitor = new JComboBox();
		cmbtipoleitor.setFont(new Font("Tahoma", Font.BOLD, 14));
		cmbtipoleitor.setModel(new DefaultComboBoxModel(new String[] {"Selecione uma opção", "Aluno", "Professor", "Administrativo"}));
		cmbtipoleitor.setBounds(54, 44, 224, 31);
		contentPane.add(cmbtipoleitor);
		
		//============================================================
		
		btnNewButton = new JButton("Novo");
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
			//=================================
			txtcodeleitor.setText(null);
			txtnomeleitor.setText(null);
			textArea.setText("");
			cmbtipoleitor.setSelectedIndex(0);
			lblmensagem.setText(null);
			//================================
			}
		});
			
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(10, 86, 87, 39);
		contentPane.add(btnNewButton);
		
		//============================================================
		
		btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//=================================
				try {
						//criado objeto leitor para pegar os dados da tela
						leitor = new Leitor();
						leitor.setCodLeitor(Integer.parseInt(txtcodeleitor.getText()));
						leitor.setNomeLeitor(txtnomeleitor.getText());
						leitor.setTipoLeitor((String) cmbtipoleitor.getSelectedItem());
						// abrir conexão
						dao = new LeitorDAO();
						// salvar
						dao.Salvar(leitor);
						lblmensagem.setForeground(Color.BLUE);
						lblmensagem.setText("Salvo com Sucesso!!");
					}catch(Exception e) {
						lblmensagem.setForeground(Color.RED);
						lblmensagem.setText("Erro ao Salvar");
					}
				//================================
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.setBounds(288, 45, 135, 33);
		contentPane.add(btnNewButton_1);
		
		//============================================================
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//==========================================================	
			try {
				
				dao = new LeitorDAO();
				int codigo = Integer.parseInt(txtcodeleitor.getText());
				leitor = dao.consultar(codigo);
				txtnomeleitor.setText(leitor.getNomeLeitor());
				String tipo = leitor.getTipoLeitor();
				if(tipo.equals("Aluno")){
					cmbtipoleitor.setSelectedIndex(1);
				}
				else if(tipo.equals("Professor")){
					cmbtipoleitor.setSelectedIndex(2);
				}
				else {
					cmbtipoleitor.setSelectedIndex(3);
				}
				}catch(Exception e1) {
					lblmensagem.setForeground(Color.RED);
				lblmensagem.setText("Erro ao Consultar");
				}	
			//==========================================================
			}});
		btnConsultar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnConsultar.setBounds(428, 45, 135, 33);
		contentPane.add(btnConsultar);
		
		//============================================================
		
		btnNewButton_3 = new JButton("Alterar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//=============================================================
				try {
					//criado objeto leitor para pegar os dados da tela
					leitor = new Leitor();
					leitor.setCodLeitor(Integer.parseInt(txtcodeleitor.getText()));
					leitor.setNomeLeitor(txtnomeleitor.getText());
					leitor.setTipoLeitor((String) cmbtipoleitor.getSelectedItem());
					// abrir conexão
					dao = new LeitorDAO();
					//Alterar
					dao.Alterar(leitor);
					lblmensagem.setForeground(Color.BLUE);
					lblmensagem.setText("Alterado com Sucesso!!");
					}catch(Exception e1) {
						lblmensagem.setForeground(Color.RED);
						lblmensagem.setText("Erro ao Alterar"+e1.getMessage());
					}
				//==============================================================
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_3.setBounds(107, 86, 92, 39);
		contentPane.add(btnNewButton_3);
		
				//============================================================
		
		btnNewButton_4 = new JButton("Excluir");
		btnNewButton_4.setBackground(new Color(255, 69, 0));
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//======================================================
				try {
					//criado objeto leitor para pegar os dados da tela
					// abrir conexão
					dao = new LeitorDAO();
					// salvar
					int codigo = Integer.parseInt(txtcodeleitor.getText());
					dao.Excluir(codigo);
					lblmensagem.setForeground(Color.BLUE);
					lblmensagem.setText("Excluido com Sucesso!!");
					}catch(Exception e1) {
					lblmensagem.setForeground(Color.RED);
					lblmensagem.setText("Erro ao Excluir");		
					}
				//==========================================================
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_4.setBounds(552, 86, 135, 39);
		contentPane.add(btnNewButton_4);
		
		//============================================================
		
		btnNewButton_5 = new JButton("Listar");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			//==========================================================	
				try {
				List<Leitor> lista = new ArrayList<Leitor>();
				dao = new LeitorDAO();
				lista = dao.listarTodos();
				for(Leitor leitor : lista) {
				textArea.append("Codigo do Leitor..." +leitor.getCodLeitor()+"\n");
				textArea.append("Nome do Leitor..." +leitor.getNomeLeitor()+"\n");
				textArea.append("Tipo de Leitor..." +leitor.getTipoLeitor()+"\n\n");
				}
				}catch(Exception e1) {
					lblmensagem.setForeground(Color.RED);
					lblmensagem.setText("Erro ao Listar");
			//==========================================================
			}
		}});
		btnNewButton_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_5.setBounds(573, 45, 114, 33);
		contentPane.add(btnNewButton_5);
		
		//============================================================
		
		textArea = new TextArea();
		textArea.setFont(new Font("Century", Font.BOLD, 15));
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setBounds(10, 141, 678, 354);
		contentPane.add(textArea);
		
		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNome.setBounds(125, 11, 44, 25);
		contentPane.add(lblNome);
		
		txtnomeleitor = new JTextField();
		txtnomeleitor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtnomeleitor.setColumns(10);
		txtnomeleitor.setBounds(177, 10, 510, 30);
		contentPane.add(txtnomeleitor);
		
		lblTipo = new JLabel("Tipo:");
		lblTipo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTipo.setBounds(10, 47, 57, 25);
		contentPane.add(lblTipo);
	}	
}