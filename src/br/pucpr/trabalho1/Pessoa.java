package br.pucpr.trabalho1;

public class Pessoa {
	private String preNome;
	private String posNome;
	private String nCPF;
	private String enderecoCompleto;
	
	public Pessoa(String preNome, String posNome, String nCPF) {
		this.preNome = preNome;
		this.posNome = posNome;
		this.nCPF = nCPF;
		this.enderecoCompleto = null;
	}
	
	public Pessoa(String preNome, String posNome, String nCPF, String endereco) {
		this.preNome = preNome;
		this.posNome = posNome;
		this.nCPF = nCPF;
		this.enderecoCompleto = endereco;
	}
	
	public void setPreNome(String preNome) {
		this.preNome = preNome;
	}
	public String getPreNome() {
		return preNome;
	}
	public void setPosNome(String posNome) {
		this.posNome = posNome;
	}
	public String getPosNome() {
		return posNome;
	}
	public void setnCPF(String nCPF) {
		this.nCPF = nCPF;
	}
	public String getnCPF() {
		return nCPF;
	}
	public void setEnderecoCompleto(String enderecoCompleto) {
		this.enderecoCompleto = enderecoCompleto;
	}
	public String getEnderecoCompleto() {
		return enderecoCompleto;
	}
}
