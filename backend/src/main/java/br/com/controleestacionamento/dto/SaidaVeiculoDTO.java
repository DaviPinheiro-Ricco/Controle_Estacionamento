package br.com.controleestacionamento.dto;

public class SaidaVeiculoDTO {

    private String placa;

    public SaidaVeiculoDTO() {
    }
    
    public SaidaVeiculoDTO(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}