package com.valente.agenda.api.mapper;

import com.valente.agenda.api.request.PacienteRequest;
import com.valente.agenda.api.response.PacienteResponse;
import com.valente.agenda.domain.entity.Paciente;

public class PacienteMapper {

    public static Paciente toPaciente(PacienteRequest request) {

        Paciente paciente = new Paciente();

        paciente.setNome(request.getNome());
        paciente.setSobrenome(request.getSobrenome());
        paciente.setEmail(request.getEmail());
        paciente.setCpf(request.getCpf());

        return paciente;
    }

    public static PacienteResponse toPacienteResponse(Paciente paciente) {

        PacienteResponse response = new PacienteResponse();

        response.setId(paciente.getId());
        response.setNome(paciente.getNome());
        response.setSobrenome(paciente.getSobrenome());
        response.setEmail(paciente.getEmail());
        response.setCpf(paciente.getCpf());

        return response;
    }
}
