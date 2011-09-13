package br.pucpr.trabalho5;

public class Veiculo {
	private String placaVeiculo;
	private String marcaVeiculo;
	private String modeloVeiculo;
	private String anoVeiculo;
	private int propriVeiculo;
	
	public Veiculo(String placa, String marca, String modelo, String ano, int propVeiculo) {
		this.placaVeiculo = placa;
		this.marcaVeiculo = marca;
		this.modeloVeiculo = modelo;
		this.anoVeiculo = ano;
		this.propriVeiculo = propVeiculo;
	}
	
	public void setPlacaVeiculo(String placaVeiculo) {
		this.placaVeiculo = placaVeiculo;
	}

	public String getPlacaVeiculo() {
		return placaVeiculo;
	}

	public void setMarcaVeiculo(String marcaVeiculo) {
		this.marcaVeiculo = marcaVeiculo;
	}

	public String getMarcaVeiculo() {
		return marcaVeiculo;
	}

	public void setModeloVeiculo(String modeloVeiculo) {
		this.modeloVeiculo = modeloVeiculo;
	}

	public String getModeloVeiculo() {
		return modeloVeiculo;
	}

	public void setAnoVeiculo(String anoVeiculo) {
		this.anoVeiculo = anoVeiculo;
	}

	public String getAnoVeiculo() {
		return anoVeiculo;
	}

	public int getPropriVeiculo() {
		return propriVeiculo;
	}

	public void setPropriVeiculo(int propriVeiculo) {
		this.propriVeiculo = propriVeiculo;
	}
}
