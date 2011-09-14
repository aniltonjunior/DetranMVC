package br.pucpr.trabalho5;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class TransferenciaDAO {
	private VeiculoDAO _veiculos;
	
	public void TransferenciasDAO() {
		this._veiculos = new VeiculoDAO();
	}
	public void inserirTransferencia(Transferencia _transferencia) {
		
		String sql = "INSERT INTO TRANSFERENCIA (vendedorTransf, compradorTransf, veiculoTransf, valorTransf) VALUES " +
				     "('" + _transferencia.getVendedorTransf() + "', '" + _transferencia.getCompradorTransf() + "', " +
				     "'" + _transferencia.getVeiculoTransf() + "','" + _transferencia.getValorTransf() + "')";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		int _idVeiculo = _transferencia.getVeiculoTransf();
		Vector<Veiculo> _retorno = _veiculos.buscaVeiculo(3, String.valueOf(_idVeiculo));
		Veiculo _novo = _retorno.firstElement();
		Veiculo _atualizado = new Veiculo (_novo.getPlacaVeiculo(),_novo.getMarcaVeiculo(), _novo.getModeloVeiculo(), _novo.getAnoVeiculo(), 0);
		_veiculos.atualizarVeiculo(_atualizado, _idVeiculo);
		
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

	public void removerTransferencia(int _id) {
		
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
	
	public Vector<String[]> buscaVendedores() {
		Vector<String[]> _resultado = new Vector<String[]>();
		
		String sql = "SELECT a.id, a.preNome, a.posNome FROM PESSOA a, VEICULO b WHERE a.id = b.propVeiculo";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			ResultSet resultadoSQL = comando.executeQuery(sql);
			
			while (resultadoSQL.next()) {
				String _idPessoa = resultadoSQL.getString(1);
				String _preNome = resultadoSQL.getString(2);
				String _posNome = resultadoSQL.getString(3);
				String[] nova = new String[2];
				nova[0] = _idPessoa;
				nova[1] = _preNome + " " + _posNome;
				_resultado.add(nova);
			}
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			System.out.println(_resultado);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na busca.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
		
		return _resultado;
	}
	
	public String buscaVeiculo(int id) {
		String _resultado = null;
		
		String sql = "SELECT b.marcaVeiculo, b.modeloVeiculo, b.anoVeiculo , b.id FROM PESSOA a, VEICULO b WHERE a.id = b.propVeiculo AND a.id =" + id;
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			ResultSet resultadoSQL = comando.executeQuery(sql);
			
			while (resultadoSQL.next()) {
				String _marca = resultadoSQL.getString(1);
				String _modelo = resultadoSQL.getString(2);
				String _ano = resultadoSQL.getString(3);
				String _id = resultadoSQL.getString(4);
				_resultado = _id + ":" + _marca + " - " + _modelo + "(" + _ano + ")";
			}
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			System.out.println(_resultado);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na busca.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
		
		return _resultado;
	}
	
	public Vector<String[]> buscaCompradores() {
		Vector<String[]> _resultado = new Vector<String[]>();
		
		String sql = "SELECT a.id, a.preNome, a.posNome FROM PESSOA a, VEICULO b WHERE a.id != b.propVeiculo";
		
		Connection conexao = ConexaoMySql.getConexaoMySql();
		
		try {
			Statement comando = conexao.createStatement();
			
			ResultSet resultadoSQL = comando.executeQuery(sql);
			
			while (resultadoSQL.next()) {
				String _idPessoa = resultadoSQL.getString(1);
				String _preNome = resultadoSQL.getString(2);
				String _posNome = resultadoSQL.getString(3);
				String[] nova = new String[2];
				nova[0] = _idPessoa;
				nova[1] = _preNome + " " + _posNome;
				_resultado.add(nova);
			}
			
			comando.close();
			
			conexao.close();
			
			System.out.println("SQL: " + sql);
			System.out.println(_resultado);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um erro na busca.");
			System.out.println("SQL: " + sql);
			System.out.println(e.toString());
		}
		
		return _resultado;
	}

}