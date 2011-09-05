package br.pucpr.trabalho1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Veiculo {
	private String placaVeiculo;
	private String marcaVeiculo;
	private String modeloVeiculo;
	private String anoVeiculo;
	private int tipoVeiculo;
	
	public Veiculo(String placa, String marca, String modelo, String ano) {
		this.placaVeiculo = placa;
		this.marcaVeiculo = marca;
		this.modeloVeiculo = modelo;
		this.anoVeiculo = ano;
		this.tipoVeiculo = verificaPlaca(placa);
	}
	
	public Veiculo(String placa, String marca, String modelo, String ano, int tipo) {
		this.placaVeiculo = placa;
		this.marcaVeiculo = marca;
		this.modeloVeiculo = modelo;
		this.anoVeiculo = ano;
		this.tipoVeiculo = tipo;
	}
	
	public int verificaPlaca (String placaCompleta) {
		int fim=0;
		
		Pattern pattern = Pattern.compile("[a-zA-Z]{3}-?[0-9]{4}");
		Matcher matcher = pattern.matcher(placaCompleta);
		
		if(matcher.find()){
			fim = matcher.end();
		}
		if(fim==7){
			return 1;
		}else if(fim==8){
			return 2;			
		}else{
			return 0;			
		}
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

	public void setTipoVeiculo(int tipoVeiculo) {
		this.tipoVeiculo = tipoVeiculo;
	}

	public int getTipoVeiculo() {
		return tipoVeiculo;
	}
}
