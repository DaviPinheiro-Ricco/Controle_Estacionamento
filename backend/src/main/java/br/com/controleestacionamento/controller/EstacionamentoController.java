package br.com.controleestacionamento.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.controleestacionamento.dto.EntradaVeiculoDTO;
import br.com.controleestacionamento.model.Vaga;
import br.com.controleestacionamento.model.Veiculo;
import br.com.controleestacionamento.service.EstacionamentoService;

@RestController
@RequestMapping("/estacionamento")
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    public EstacionamentoController(
            EstacionamentoService estacionamentoService) {

        this.estacionamentoService = estacionamentoService;
    }

    // Cadastrar veículo
    @PostMapping("/veiculos")
    public ResponseEntity<Veiculo> cadastrarVeiculo(
            @RequestBody Veiculo veiculo) {

        estacionamentoService.cadastrarVeiculo(veiculo);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(veiculo);
    }

    // Registrar entrada
    @PostMapping("/entrada")
    public ResponseEntity<String> registrarEntrada(
            @RequestBody EntradaVeiculoDTO entrada) {

        estacionamentoService.registrarEntrada(
            entrada.getPlaca(),
            entrada.getNumeroVaga()
        );

        return ResponseEntity.ok(
            "Entrada registrada com sucesso."
        );
    }

    // Registrar saída
    @PostMapping("/saida/{placa}")
    public ResponseEntity<String> registrarSaida(
            @PathVariable String placa) {

        estacionamentoService.registrarSaida(placa);

        return ResponseEntity.ok(
            "Saída registrada com sucesso."
        );
    }

    // Listar vagas
    @GetMapping("/vagas")
    public ResponseEntity<List<Vaga>> listarVagas() {

        return ResponseEntity.ok(
            estacionamentoService.getEstacionamento().getVagas()
        );
    }
}