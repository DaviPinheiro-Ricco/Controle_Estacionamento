package br.com.controleestacionamento.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

public class RegistroEstacionamento {

    private Veiculo veiculo;
    private Vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;

    public RegistroEstacionamento(
            Veiculo veiculo,
            Vaga vaga,
            LocalDateTime horaEntrada) {

        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = horaEntrada;
        this.horaSaida = null;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
    }
}