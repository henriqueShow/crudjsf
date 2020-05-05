package br.com.dominio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.dominio.model.Pessoa;

public class PessoaDAO {

	public void create(Pessoa pessoa) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("INSERT INTO pessoa (id, nome, endereco, telefone)VALUES(?,?,?,?)");
			stmt.setInt(1, pessoa.getId());
			stmt.setString(2, pessoa.getNome());
			stmt.setString(3, pessoa.getEndereco());
			stmt.setString(4, pessoa.getTelefone());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar: ", e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public void update(Pessoa pessoa) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("UPDATE pessoa SET nome = ?, endereco = ?, telefone = ? where id = ?");
			stmt.setString(1, pessoa.getNome());
			stmt.setString(2, pessoa.getEndereco());
			stmt.setString(3, pessoa.getTelefone());
			stmt.setInt(4, pessoa.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao atualizar: ", e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}

	}
	
	public void delete(Pessoa pessoa) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("DELETE FROM pessoa where id = ?");
			stmt.setInt(1, pessoa.getId());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir: ", e);
		}finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
	public List<Pessoa> findAll(){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Pessoa> pessoas = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM pessoa");
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Pessoa pessoa = new Pessoa();
				
				pessoa.setId(rs.getInt("id"));
				pessoa.setNome(rs.getString("nome"));
				pessoa.setEndereco(rs.getString("endereco"));
				pessoa.setTelefone(rs.getString("telefone"));
				pessoas.add(pessoa);
			}
			
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao salvar: ", e);
		} finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		
		return pessoas;
	}
	
}
