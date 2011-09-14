package br.pucpr.trabalho5;

import java.util.Vector;

public class TransferenciaBO {
	
	private TransferenciaDAO dao;
	
	public TransferenciaBO () {
		dao = new TransferenciaDAO();
	}
	
	public void inserirTransferencia(Transferencia _transferencia) {
		this.dao.inserirTransferencia(_transferencia);
	}
	
	public void atualizarPessoa(Transferencia _transferencia, int _id) {
		this.dao.atualizarTransferencia(_transferencia, _id);
	}
	
	public void removerTransferencia(int _id) {
		if (_id <= 0) {
			System.out.println("Dever‡ ser inserido um id de registro v‡lido.");
			return;
		}
		
		this.dao.removerTransferencia(_id);
	}
	
	public Vector<String[]> retornaVendedores() {
		Vector<String[]> _resultado = null;
		
		_resultado = this.dao.buscaVendedores();
		
		return _resultado;
	}
	
	public String retornaVeiculo(String id) {
		String _resultado = null;
		
		_resultado = this.dao.buscaVeiculo(Integer.parseInt(id));
		
		return _resultado;
	}
	
	public Vector<String[]> retornaCompradores() {
		Vector<String[]> _resultado = null;
		
		_resultado = this.dao.buscaCompradores();
		
		return _resultado;
	}
}