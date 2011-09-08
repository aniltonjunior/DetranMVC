package br.pucpr.trabalho5;

import java.util.Vector;

public class PessoaBO {
	
	private PessoaDAO dao;
	
	PessoaBO () {
		dao = new PessoaDAO();
	}
	
	public void inserirPessoa(Pessoa _pessoa) {
		boolean _existe = buscaMesmaPessoa(_pessoa);
		
		if (_existe) {
			System.out.println("J‡ eiste pessoa com este nome.");
			return;
		} else {
			dao.inserirPessoa(_pessoa);
		}
	}
	
	public void atualizarPessoa(Pessoa _pessoa, int _id) {
		dao.atualizarPessoa(_pessoa, _id);
	}
	
	public void removerPessoa(int _id) {
		dao.removerPessoa(_id);
	}
	
	public Vector<Pessoa> buscaPessoa(String _busca, int _tipo) {
		return dao.buscaPessoa(_tipo, _busca);
	}
	
	private boolean buscaMesmaPessoa(Pessoa _busca) {
		String _preNome = _busca.getPreNome();
		boolean resultado;
		
		Vector<Pessoa> _resultado = dao.buscaPessoa(1, _preNome);
		
		if (_resultado.isEmpty()) {
			resultado = false;
		} else {
			resultado = true;
		}
		
		return resultado;
	}
}
