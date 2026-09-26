package br.com.controleestacionamento.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.controleestacionamento.model.Estacionamento;
import br.com.controleestacionamento.model.RegistroEstacionamento;
import br.com.controleestacionamento.model.Vaga;
import br.com.controleestacionamento.model.Veiculo;
import br.com.controleestacionamento.model.enums.TipoVaga;
import br.com.controleestacionamento.repository.RegistroEstacionamentoRepository;
import br.com.controleestacionamento.repository.VeiculoRepository;

@Service
public class EstacionamentoService {

    private Estacionamento estacionamento;
    private VeiculoRepository veiculoRepository;
    private RegistroEstacionamentoRepository registroRepository;

    public EstacionamentoService(
            Estacionamento estacionamento,
            VeiculoRepository veiculoRepository,
            RegistroEstacionamentoRepository registroRepository) {

        this.estacionamento = estacionamento;
        this.veiculoRepository = veiculoRepository;
        this.registroRepository = registroRepository;
    }

    // Cadastrar veículo
    public void cadastrarVeiculo(Veiculo veiculo) {

        Veiculo veiculoExistente =
                veiculoRepository.buscarPorPlaca(veiculo.getPlaca());

        if (veiculoExistente != null) {
            throw new IllegalArgumentException(
                "Já existe um veículo cadastrado com essa placa."
            );
        }

        veiculoRepository.salvar(veiculo);
    }
    
    //Consultar Veículos
    public List<Veiculo> consultarVeiculos(){
    	if(veiculoRepository.listarTodos().isEmpty()) {
    		return null;
    	}
    	
    	return veiculoRepository.listarTodos();
    }

    // Registrar entrada
    public void registrarEntrada(String placa, int numeroVaga) {

        // Busca o veículo
        Veiculo veiculo = veiculoRepository.buscarPorPlaca(placa);

        if (veiculo == null) {
            throw new IllegalArgumentException(
                "Veículo não cadastrado."
            );
        }

        // Busca a vaga
        Vaga vaga = buscarVaga(numeroVaga);

        if (vaga == null) {
            throw new IllegalArgumentException(
                "Vaga não encontrada."
            );
        }

        // Verifica se o veículo já está estacionado
        RegistroEstacionamento registroAberto =
                registroRepository.buscarRegistroAbertoPorPlaca(placa);

        if (registroAberto != null) {
            throw new IllegalArgumentException(
                "Este veículo já está estacionado."
            );
        }

        // Verifica se a vaga está ocupada
        if (vaga.getVeiculo() != null) {
            throw new IllegalArgumentException(
                "Esta vaga já está ocupada."
            );
        }

        // Regra da vaga PCD
        if (vaga.getTipo() == TipoVaga.PCD
                && !veiculo.isPossuiSeloPcd()) {

            throw new IllegalArgumentException(
                "Veículo sem selo PCD não pode ocupar vaga PCD."
            );
        }

        // Ocupa a vaga
        vaga.setVeiculo(veiculo);

        // Cria o registro
        RegistroEstacionamento registro =
                new RegistroEstacionamento(
                    veiculo,
                    vaga,
                    LocalDateTime.now()
                );

        registroRepository.salvar(registro);
    }

    // Registrar saída
    public void registrarSaida(String placa) {

        RegistroEstacionamento registro =
                registroRepository.buscarRegistroAbertoPorPlaca(placa);

        if (registro == null) {
            throw new IllegalArgumentException(
                "O veículo não está estacionado."
            );
        }

        // Registra o horário de saída
        registro.setHoraSaida(LocalDateTime.now());

        // Libera a vaga
        registro.getVaga().setVeiculo(null);
    }

    // Buscar uma vaga pelo número
    public Vaga buscarVaga(int numeroVaga) {

        for (Vaga vaga : estacionamento.getVagas()) {

            if (vaga.getNumero() == numeroVaga) {
                return vaga;
            }
        }

        return null;
    }

    public Estacionamento getEstacionamento() {
        return estacionamento;
    }
}