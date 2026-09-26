package br.com.controleestacionamento.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.controleestacionamento.model.Veiculo;

@Repository
public class VeiculoRepository {

    private List<Veiculo> veiculos = new ArrayList<>();

    public void salvar(Veiculo veiculo) {
        veiculos.add(veiculo);
    }

    public Veiculo buscarPorPlaca(String placa) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getPlaca().equalsIgnoreCase(placa)) {
                return veiculo;
            }
        }

        return null;
    }

    public List<Veiculo> listarTodos() {
        return veiculos;
    }
}