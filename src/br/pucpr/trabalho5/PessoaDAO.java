package br.pucpr.trabalho5;

import java.sql.Connection;
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
			System.out.println(e.toString());
		}
	}

	public void removerPessoa(int _id) {
		
		String sql = "REMOVE FROM PESSOA WHERE id = '" + _id + "'";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			comando.executeUpdate(sql);
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro ao tentar remover.");
			System.out.println(e.toString());
		}
	}
	
	public Vector<Pessoa> buscaPessoa(int _tipo, String _busca) {
		Vector<Pessoa> resultado = new Vector<Pessoa>();
		String sql;
		switch (_tipo) {
		case 0:
			sql = "SELECT * FROM PESSOA";
			break;
		case 1:
			sql = "SELECT * FROM PESSOA WHERE preNome like %" + _busca;
			break;
		case 2:
			sql = "SELECT * FROM PESSOA WHERE nCPF = '" + _busca + "'";
			break;
		default:
			System.out.println("Opção não existente.");
			return null;
		}
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			comando.executeUpdate(sql);
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na busca.");
			System.out.println("Tipo: "+ _tipo + " e parametro: " + _busca);
			System.out.println(e.toString());
		}
		
		return resultado;
	}

}
