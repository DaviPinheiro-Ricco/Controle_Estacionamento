package br.com.controleestacionamento.model;

import org.springframework.stereotype.Component;

public class Veiculo {
	private String placa;
	private boolean possuiSeloPcd;
	
	public Veiculo() {
	}
	
	public Veiculo(String placa, boolean possuiSeloPcd) {
		this.placa = placa;
		this.possuiSeloPcd = possuiSeloPcd;
	}
	
	public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isPossuiSeloPcd() {
        return possuiSeloPcd;
    }

    public void setPossuiSeloPcd(boolean possuiSeloPcd) {
        this.possuiSeloPcd = possuiSeloPcd;
    }
}
