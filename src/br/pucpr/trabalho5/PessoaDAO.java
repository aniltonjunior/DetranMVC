package br.pucpr.trabalho5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class PessoaDAO {
	
	public void inserirPessoa(Pessoa _pessoa) {
		
		String sql = "INSERT INTO PESSOA (preNome, posNome, nCPF, enderecoCompleto) VALUES " +
				     "('" + _pessoa.getPreNome() + "', '" + _pessoa.getPosNome() + "', " +
				     "'" + _pessoa.getnCPF() + "','" + _pessoa.getEnderecoCompleto() + "')";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			comando.executeUpdate(sql);
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar inserir.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
	}
	
	public void atualizarPessoa(Pessoa _pessoa, int _id) {
		
		String sql = "UPDATE PESSOA SET preNome '" + _pessoa.getPreNome() + "', posNome = '" + _pessoa.getPosNome() + "', " +
				     "nCPF = '" + _pessoa.getnCPF() + "',  enderecoCompleto = '" + _pessoa.getEnderecoCompleto() + "' WHERE id = '" + _id + "'";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			comando.executeUpdate(sql);
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar atualizar.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
	}

	public void removerPessoa(int _id) {
		
		String sql = "DELETE FROM PESSOA WHERE id = '" + _id + "'";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			comando.executeUpdate(sql);
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar remover.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
	}
	
	public Vector<Pessoa> buscaPessoa(int _tipo, String _busca[]) {
		Vector<Pessoa> resultado = new Vector<Pessoa>();
		String sql;
		switch (_tipo) {
		case 0:
			sql = "SELECT * FROM PESSOA";
			break;
		case 1:
			sql = "SELECT * FROM PESSOA WHERE preNome = '" + _busca[0] + "'";
			if (_busca[1] != null) {
				sql += " AND posNome = '" + _busca[1] + "'";
			}
			break;
		case 2:
			sql = "SELECT * FROM PESSOA WHERE nCPF = '" + _busca[0] + "'";
			break;
		case 3:
			sql = "SELECT * FROM PESSOA WHERE id = '" + _busca[0] + "'";
		default:
			System.out.println("Opção não existente.");
			return null;
		}
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			ResultSet resultadoSQL = comando.executeQuery(sql);
			
			while (resultadoSQL.next()) {
				String _preNome = resultadoSQL.getString("preNome");
				String _posNome = resultadoSQL.getString("posNome");
				String _nCPF = resultadoSQL.getString("nCPF");
				String _endereco = resultadoSQL.getString("enderecoCompleto");
				if (_endereco == null) {
					resultado.add(new Pessoa(_preNome, _posNome, _nCPF));
				} else {
					resultado.add(new Pessoa(_preNome, _posNome, _nCPF, _endereco));
				}
			}
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na busca.");
			System.out.println("SQL: " + sql);
			System.out.println("Tipo: "+ _tipo + " e parametro: " + _busca);
			System.out.println(e.toString());
		}
		
		return resultado;
	}

}
