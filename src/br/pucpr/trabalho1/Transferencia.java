package br.pucpr.trabalho1;

public class Transferencia {
	private Pessoa vendedorTransf;
	private Pessoa compradorTransf;
	private Veiculo veiculoTransf;
	private double valorTransf;
	
	public Transferencia(Pessoa vendedor, Pessoa comprador, Veiculo produto, Double valor) {
		this.vendedorTransf = vendedor;
		this.compradorTransf = comprador;
		this.veiculoTransf = produto;
		this.valorTransf = valor;
	}
	
	public void setVendedorTransf(Pessoa vendedorTransf) {
		this.vendedorTransf = vendedorTransf;
	}
	public Pessoa getVendedorTransf() {
		return vendedorTransf;
	}
	public void setCompradorTransf(Pessoa compradorTransf) {
		this.compradorTransf = compradorTransf;
	}
	public Pessoa getCompradorTransf() {
		return compradorTransf;
	}
	public void setVeiculoTransf(Veiculo veiculoTransf) {
		this.veiculoTransf = veiculoTransf;
	}
	public Veiculo getVeiculoTransf() {
		return veiculoTransf;
	}
	public void setValorTransf(double valorTransf) {
		this.valorTransf = valorTransf;
	}
	public double getValorTransf() {
		return valorTransf;
	}
}
