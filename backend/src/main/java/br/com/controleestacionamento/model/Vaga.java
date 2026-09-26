package br.com.controleestacionamento.model;

import org.springframework.stereotype.Component;

import br.com.controleestacionamento.model.enums.TipoVaga;

public class Vaga {

    private int numero;
    private TipoVaga tipo;
    private Veiculo veiculo;

    public Vaga(int numero, TipoVaga tipo) {
        this.numero = numero;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public TipoVaga getTipo() {
        return tipo;
    }

    public void setTipo(TipoVaga tipo) {
        this.tipo = tipo;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}