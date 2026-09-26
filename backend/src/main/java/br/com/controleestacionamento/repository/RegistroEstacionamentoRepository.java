package br.com.controleestacionamento.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.controleestacionamento.model.RegistroEstacionamento;

@Repository
public class RegistroEstacionamentoRepository {

    private List<RegistroEstacionamento> registros = new ArrayList<>();
	
    public void salvar(RegistroEstacionamento registro) {
        registros.add(registro);
    }

    public List<RegistroEstacionamento> listarTodos() {
        return registros;
    }

    public RegistroEstacionamento buscarRegistroAbertoPorPlaca(String placa) {

        for (RegistroEstacionamento registro : registros) {

            if (registro.getVeiculo()
                    .getPlaca()
                    .equalsIgnoreCase(placa)
                    && registro.getHoraSaida() == null) {

                return registro;
            }
        }

        return null;
    }
}