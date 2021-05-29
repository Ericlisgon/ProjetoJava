package br.com.exemplo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConnectionFactory {
	public static Connection getConnection() throws Exception{
		try {
			//indica o db é mysql apontando para Driver
			Class.forName("com.mysql.jdbc.Driver");
			// conexao com DB
			String login = "root";
			String senha = "root";
			String url = "jdbc:mysql://localhost:3306/dbleitor";
			return DriverManager.getConnection(url,login,senha);
		}

		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		try {
			
			Connection conn = ConnectionFactory.getConnection();
			JOptionPane.showMessageDialog(null, "Conectado");
		}
		catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
}
