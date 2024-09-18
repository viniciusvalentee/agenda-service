package com.valente.agenda.service;


import com.valente.agenda.domain.entity.Paciente;
import com.valente.agenda.domain.entity.repository.PacienteRepository;
import com.valente.agenda.exception.BusinessException;
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

        boolean CpfExists = false;
        Optional<Paciente> optPaciente = repository.findByCpf(paciente.getCpf());

        if(optPaciente.isPresent()) {
            if(!optPaciente.get().getId().equals(paciente.getId())) {
                CpfExists = true;
            }
        }
        if(CpfExists) {
            throw new BusinessException("CPF já cadastrado");
        }
        
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

    public Paciente change(Long id, Paciente paciente) {
        Optional<Paciente> optPaciente = this.searchById(id);


        if (optPaciente.isEmpty()) {
            throw new BusinessException("Paciente não cadastrado!");
        }

        paciente.setId(id);

        return save(paciente);
    }
}
