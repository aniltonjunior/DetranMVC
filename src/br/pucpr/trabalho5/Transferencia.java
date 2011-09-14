package br.pucpr.trabalho5;

public class Transferencia {
	private int vendedorTransf;
	private int compradorTransf;
	private int veiculoTransf;
	private double valorTransf;
	
	public Transferencia(int vendedor, int comprador, int produto, Double valor) {
		this.vendedorTransf = vendedor;
		this.compradorTransf = comprador;
		this.veiculoTransf = produto;
		this.valorTransf = valor;
	}
	
	public void setVendedorTransf(int vendedorTransf) {
		this.vendedorTransf = vendedorTransf;
	}
	public int getVendedorTransf() {
		return vendedorTransf;
	}
	public void setCompradorTransf(int compradorTransf) {
		this.compradorTransf = compradorTransf;
	}
	public int getCompradorTransf() {
		return compradorTransf;
	}
	public void setVeiculoTransf(int veiculoTransf) {
		this.veiculoTransf = veiculoTransf;
	}
	public int getVeiculoTransf() {
		return veiculoTransf;
	}
	public void setValorTransf(double valorTransf) {
		this.valorTransf = valorTransf;
	}
	public double getValorTransf() {
		return valorTransf;
	}
}
