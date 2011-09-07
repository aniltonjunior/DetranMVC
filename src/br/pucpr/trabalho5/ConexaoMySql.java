package br.pucpr.trabalho5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {
	public static String status = "N‹o foi possivel conectar ao banco de dados.";
	
	public ConexaoMySql() {
		
	}
	
	public static Connection getConexaoMySql() {
		Connection conexao = null;
		
		try {
			String drivername = "com.mysql.jdbc.Driver";
			Class.forName(drivername);
			
			String nomeServidor = "127.0.0.1";
			String bancoDeDados = "SistemaDetran";
			String url = "jdbc:mysql://" + nomeServidor + "/" + bancoDeDados;
			String usuario = "root";
			String senha = "senha";
			conexao = DriverManager.getConnection(url, usuario, senha);
			
			if (conexao != null) {
				status = ("STATUS --> Conectado com sucesso.");
			} else {
				status = ("STATUS --> Nao foi possivel estabelecer conexao.");
			}
			
			return conexao;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver especificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
			System.out.println("N‹o foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}
	
	public static String getStatus() {
		return status;
	}
	
	public static Boolean fecharConexao() {
		try {
			ConexaoMySql.getConexaoMySql().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static Connection reiniciarConexao() {
		fecharConexao();
		return ConexaoMySql.getConexaoMySql();
	}
}