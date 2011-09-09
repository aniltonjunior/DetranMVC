package br.pucpr.trabalho5;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VeiculoBO {
	
	private VeiculoDAO dao;
	
	public VeiculoBO () {
		dao = new VeiculoDAO();
	}
	
	public void inserirVeiculo(Veiculo _veiculo) {
		if (_veiculo == null || _veiculo.getModeloVeiculo() == "") {
			System.out.println("Dever‡ ser inserido um objeto Veiculo n‹o nulo.");
			return;
		}
		
		_veiculo.setPlacaVeiculo(processaPlaca(_veiculo.getPlacaVeiculo()));
		
		boolean _existe = buscaMesmaVeiculo(_veiculo);
		
		boolean _placaincorreta = verificaPlaca(_veiculo.getPlacaVeiculo());
		
		if (_existe && _placaincorreta) {
			System.out.println("J‡ existe carro com esta placa ou placa esta incorreta.");
			return;
		} else {
			dao.inserirVeiculo(_veiculo);
		}
	}
	
	public String processaPlaca(String placa) {
		String resultado;
		String _partesPlaca[];
		_partesPlaca = placa.split("-");
		
		resultado = _partesPlaca[0]+_partesPlaca[1];
		
		return resultado;
	}
	
	public void atualizarVeiculo(Veiculo _veiculo, int _id) {
		if (_veiculo == null || _veiculo.getModeloVeiculo() == "") {
			System.out.println("Dever‡ ser inserido um objeto Pessoa n‹o nulo.");
			return;
		}
		
		dao.atualizarVeiculo(_veiculo, _id);
	}
	
	public void removerVeiculo(int _id) {
		if (_id <= 0) {
			System.out.println("Dever‡ ser inserido um id de registro v‡lido.");
			return;
		}
		
		dao.removerVeiculo(_id);
	}
	
	public Vector<Veiculo> buscaPessoa(String _busca, int _tipo) {
		String _nbusca = _busca;
		return dao.buscaVeiculo(_tipo, _nbusca);
	}
	
	private boolean buscaMesmaVeiculo(Veiculo _busca) {
		String _placa = _busca.getPlacaVeiculo();
		
		boolean resultado;
		
		Vector<Veiculo> _resultado = dao.buscaVeiculo(2, _placa);
		
		if (_resultado.isEmpty()) {
			resultado = false;
		} else {
			resultado = true;
		}
		
		return resultado;
	}
	
	public static boolean verificaPlaca (String placaCompleta) {
		
		Pattern pattern = Pattern.compile("[a-zA-Z]{3}-?[0-9]{4}");
		Matcher matcher = pattern.matcher(placaCompleta);
		
		if(matcher.find()){
			return true;
		} else {
			return false;
		}
	}
}