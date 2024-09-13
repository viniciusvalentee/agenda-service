package com.valente.agenda.service;


import com.valente.agenda.domain.entity.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;

    public Paciente save(Paciente paciente) {

        // TODO: validate if CPF exists
        
        return repository.save(paciente);
    }

    public List<Paciente> listAll() {
        return repository.findAll();
    }

    public Optional<Paciente> searchById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
