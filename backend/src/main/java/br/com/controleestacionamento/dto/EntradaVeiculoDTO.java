package br.com.controleestacionamento.dto;

public class EntradaVeiculoDTO {

    private String placa;
    private int numeroVaga;

    public EntradaVeiculoDTO() {
    }
    
    public EntradaVeiculoDTO(String placa, int numeroVaga) {
        this.placa = placa;
        this.numeroVaga = numeroVaga;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public void setNumeroVaga(int numeroVaga) {
        this.numeroVaga = numeroVaga;
    }
}