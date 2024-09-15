package com.valente.agenda.api.controller;


import com.valente.agenda.domain.entity.Paciente;
import com.valente.agenda.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente") //localhost:8080/paciente
public class PacienteController {

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> save(@RequestBody Paciente paciente) {

        Paciente savedPaciente = service.save(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPaciente);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.listAll());
    }
}
