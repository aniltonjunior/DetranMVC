package br.pucpr.trabalho5;

import java.util.Vector;

public class PessoaBO {
	
	private PessoaDAO dao;
	
	public PessoaBO () {
		dao = new PessoaDAO();
	}
	
	public void inserirPessoa(Pessoa _pessoa) {
		if (_pessoa == null || _pessoa.getPreNome() == "") {
			System.out.println("Dever‡ ser inserido um objeto Pessoa n‹o nulo.");
			return;
		}
		
		boolean _existe = buscaMesmaPessoa(_pessoa);
		
		if (_existe) {
			System.out.println("J‡ existe pessoa com este nome.");
			return;
		} else {
			dao.inserirPessoa(_pessoa);
		}
	}
	
	public void atualizarPessoa(Pessoa _pessoa, int _id) {
		if (_pessoa == null || _pessoa.getPreNome() == "") {
			System.out.println("Dever‡ ser inserido um objeto Pessoa n‹o nulo.");
			return;
		}
		
		dao.atualizarPessoa(_pessoa, _id);
	}
	
	public void removerPessoa(int _id) {
		if (_id <= 0) {
			System.out.println("Dever‡ ser inserido um id de registro v‡lido.");
			return;
		}
		
		dao.removerPessoa(_id);
	}
	
	public Vector<Pessoa> buscaPessoa(String _busca, int _tipo) {
		String _nbusca[] = new String[2];
		_nbusca[0] = _busca;
		return dao.buscaPessoa(_tipo, _nbusca);
	}
	
	public Vector<Pessoa> buscaPessoa(String _busca[], int _tipo) {
		return dao.buscaPessoa(_tipo, _busca);
	}
	
	private boolean buscaMesmaPessoa(Pessoa _busca) {
		String _nome[] = new String[2];
		_nome[0] = _busca.getPreNome();
		_nome[1] = _busca.getPosNome();
		
		boolean resultado;
		
		Vector<Pessoa> _resultado = dao.buscaPessoa(1, _nome);
		
		if (_resultado.isEmpty()) {
			resultado = false;
		} else {
			resultado = true;
		}
		
		return resultado;
	}
}
