package br.com.controleestacionamento.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.controleestacionamento.model.enums.TipoVaga;

@Component
public class Estacionamento {

    private List<Vaga> vagas;
    private List<Veiculo> veiculos;
    private List<RegistroEstacionamento> registros;

    public Estacionamento() {
        this.vagas = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.registros = new ArrayList<>();

        // 16 vagas comuns
        for (int i = 1; i <= 16; i++) {
            vagas.add(new Vaga(i, TipoVaga.COMUM));
        }

        // 2 vagas PCD
        for (int i = 17; i <= 18; i++) {
            vagas.add(new Vaga(i, TipoVaga.PCD));
        }

        // 2 vagas extras
        for (int i = 19; i <= 20; i++) {
            vagas.add(new Vaga(i, TipoVaga.EXTRA));
        }
    }

    public List<Vaga> getVagas() {
        return vagas;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public List<RegistroEstacionamento> getRegistros() {
        return registros;
    }
}