package br.com.exemplo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import br.com.exemplo.model.Leitor;
import br.com.exemplo.util.ConnectionFactory;

public class LeitorDAO {
	
	private Leitor leitor;
	private Connection conn;
	private PreparedStatement ps; //executa query
	private ResultSet rs;  //cria uma tabela
	
	public LeitorDAO() throws Exception{
		try {
			conn = ConnectionFactory.getConnection();
		
		
		}catch (Exception e) {
			throw new Exception("erro"+e.getMessage());
	}
	}
	
	public void Salvar(Leitor leitor) throws Exception{
		
		try {
			String sql="INSERT INTO tbLeitor(codLeitor,nomeLeitor,tipoLeitor)"
					+ " values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,leitor.getCodLeitor());
			ps.setString(2,leitor.getNomeLeitor());
			ps.setString(3,leitor.getTipoLeitor());
			ps.executeUpdate();
		
		}catch (Exception e) {
			throw new Exception("erro ao salvar"+e.getMessage());
		}
	}

	public ArrayList<Leitor> listarTodos() throws Exception{
		ArrayList<Leitor> lista = new ArrayList<Leitor>();
		String sql = "SELECT * FROM tbLeitor";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				int codLeitor = rs.getInt("codLeitor");
				String nomeLeitor = rs.getString("nomeLeitor");
				String tipoLeitor = rs.getString("tipoLeitor");
				lista.add(new Leitor(codLeitor,nomeLeitor,tipoLeitor));
				
			}
			return lista;
			
		}catch (Exception e) {
			throw new Exception("erro ao listar"+e.getMessage());
		}
	}
	
	public void Alterar(Leitor leitor) throws Exception{
		
		try {
			String sql="UPDATE tbLeitor SET nomeLeitor=? , tipoLeitor=?"
					+ " WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1,leitor.getNomeLeitor());
			ps.setString(2,leitor.getTipoLeitor());
			ps.setInt(3,leitor.getCodLeitor());
			ps.executeUpdate();
		
		}catch (Exception e) {
			throw new Exception("erro ao alterar"+e.getMessage());
		}
	}
	
	public void Excluir(int codLeitor) throws Exception{
		
		try {
			String sql="DELETE FROM tbLeitor "
					+ " WHERE codLeitor=?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,codLeitor);
			ps.executeUpdate();
		
		}catch (Exception e) {
			throw new Exception("erro ao excluir"+e.getMessage());
		}
	}
	
	public Leitor consultar(int codLeitor) throws Exception{
		
		try {
			ps = conn.prepareStatement("SELECT * FROM tbLeitor WHERE codLeitor=?");
			ps.setInt(1,codLeitor);
			rs = ps.executeQuery();
			if(rs.next()) {
				String nomeLeitor = rs.getString("nomeLeitor");
				String tipoLeitor = rs.getString("tipoLeitor");
				leitor = new Leitor(codLeitor,nomeLeitor,tipoLeitor);
			}
			return leitor;
			
		}catch (Exception e) {
			throw new Exception("erro ao listar"+e.getMessage());
		}
	}
	

}