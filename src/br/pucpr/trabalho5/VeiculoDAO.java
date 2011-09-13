package br.pucpr.trabalho5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class VeiculoDAO {
	
	public void inserirVeiculo(Veiculo _veiculo) {
		
		String sql = "INSERT INTO VEICULO (placaVeiculo, marcaVeiculo, modeloVeiculo, anoVeiculo, propVeiculo) VALUES " +
				     "('" + _veiculo.getPlacaVeiculo() + "', '" + _veiculo.getMarcaVeiculo() + "', " +
				     "'" + _veiculo.getModeloVeiculo() + "','" + _veiculo.getAnoVeiculo() + "', " +
				     _veiculo.getPropriVeiculo() +"')";
		
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
	
	public void atualizarVeiculo(Veiculo _veiculo, int _id) {
		
		String sql = "UPDATE VEICULO SET placaVeiculo '" + _veiculo.getPlacaVeiculo() + "', marcaVeiculo = '" + _veiculo.getMarcaVeiculo() + "', " +
				     "modeloVeiculo = '" + _veiculo.getModeloVeiculo() + "',  anoVeiculo = '" + _veiculo.getAnoVeiculo() + "', " +
				     "', propVeiculo = '" + _veiculo.getPropriVeiculo() + "' WHERE id = '" + _id + "'";
		
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
		
		String sql = "DELETE FROM VEICULO WHERE id = '" + _id + "'";
		
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
	
	public Vector<Veiculo> buscaVeiculo(int _tipo, String _busca) {
		Vector<Veiculo> resultado = new Vector<Veiculo>();
		String sql;
		switch (_tipo) {
		case 0:
			sql = "SELECT * FROM VEICULO";
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