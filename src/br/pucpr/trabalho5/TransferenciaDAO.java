package br.pucpr.trabalho5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TransferenciaDAO {
	
	public void inserirTransferencia(Transferencia _transferencia) {
		
		String sql = "INSERT INTO VEICULO (vendedorTransf, compradorTransf, veiculoTransf, valorTransf) VALUES " +
				     "('" + _transferencia.getVendedorTransf() + "', '" + _transferencia.getCompradorTransf() + "', " +
				     "'" + _transferencia.getVeiculoTransf() + "','" + _transferencia.getValorTransf() + "')";
		
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
	
	public void atualizarTransferencia(Transferencia _transferencia, int _id) {
		
		String sql = "UPDATE TRANSFERENCIA SET vendedorTransf '" + _transferencia.getVendedorTransf() + "', " +
				     "compradorTransf = '" + _transferencia.getCompradorTransf() + "', " +
				     "veiculoTransf = '" + _transferencia.getVeiculoTransf() + "', " +
				     "valorTransf = '" + _transferencia.getValorTransf() + "'";
		
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

	public void removerVeiculo(int _id) {
		
		String sql = "DELETE FROM TRANSFERENCIA WHERE id = '" + _id + "'";
		
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
	
	public Vector<Transferencia> buscaTransferencia(int _tipo, String _busca) {
		Vector<Transferencia> resultado = new Vector<Transferencia>();
		String sql;
		switch (_tipo) {
		case 0:
			sql = "SELECT * FROM TRANSFERENCIA";
			break;
		case 1:
			sql = "SELECT * FROM VEICULO WHERE modeloVeiculo like '" + _busca + "%'";
			break;
		case 2:
			sql = "SELECT * FROM VEICULO WHERE placaVeiculo = '" + _busca + "'";
			break;
		default:
			System.out.println("Opção não existente.");
			return null;
		}
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			ResultSet resultadoSQL = comando.executeQuery(sql);
			
			while (resultadoSQL.next()) {
				String _placaVeiculo = resultadoSQL.getString("placaVeiculo");
				String _marcaVeiculo = resultadoSQL.getString("marcaVeiculo");
				String _modeloVeiculo = resultadoSQL.getString("modeloVeiculo");
				String _anoVeiculo = resultadoSQL.getString("anoVeiculo");
				String _tipoVeiculo = resultadoSQL.getString("tipoVeiculo");
				resultado.add(new Veiculo(_placaVeiculo, _marcaVeiculo, _modeloVeiculo, _anoVeiculo, Integer.parseInt(_tipoVeiculo)));
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