package com.valente.agenda.service;


import com.valente.agenda.domain.entity.Agenda;
import com.valente.agenda.domain.entity.Paciente;
import com.valente.agenda.domain.entity.repository.AgendaRepository;
import com.valente.agenda.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {

    private final AgendaRepository repository;
    private final PacienteService pacienteService;

    public List<Agenda> listAll() {
        return repository.findAll();
    }

    public Optional<Agenda> searchById(Long id) {
        return repository.findById(id);
    }

    public Agenda save(Agenda agenda) {
        Optional<Paciente> optPaciente = pacienteService.searchById(agenda.getPaciente().getId());

        if (optPaciente.isEmpty()) {
            throw new BusinessException("Patient not found");
        }

        Optional<Agenda> optHorario = repository.findByHorario(agenda.getHorario());

        if (optHorario.isPresent()) {
            throw new BusinessException("Já existe agendamento para este horário");
        }

        agenda.setPaciente(optPaciente.get());
        agenda.setDataCriacao(LocalDateTime.now());
        return repository.save(agenda);
    }
}
