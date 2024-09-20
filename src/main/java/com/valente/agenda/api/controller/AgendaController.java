package com.valente.agenda.api.controller;

import com.valente.agenda.api.mapper.AgendaMapper;
import com.valente.agenda.api.request.AgendaRequest;
import com.valente.agenda.api.response.AgendaResponse;
import com.valente.agenda.domain.entity.Agenda;
import com.valente.agenda.service.AgendaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda") //localhost:8080/paciente
public class AgendaController {

    private final AgendaService service;
    private final AgendaMapper mapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> searchAll() {
        List<Agenda> agendas = service.listAll();
        List<AgendaResponse> agendaResponses = mapper.toAgendaResponseList(agendas);

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> searchById(@PathVariable Long id) {
        Optional<Agenda> optAgenda = service.searchById(id);

        if (optAgenda.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        AgendaResponse agendaResponse = mapper.toAgendaResponse(optAgenda.get());

        return ResponseEntity.status(HttpStatus.OK).body(agendaResponse);
    }

    @PostMapping
    public ResponseEntity<AgendaResponse> save(@Valid @RequestBody AgendaRequest request) {
        Agenda agenda = mapper.toAgenda(request);
        Agenda savedAgenda = service.save(agenda);
        AgendaResponse agendaResponse = mapper.toAgendaResponse(savedAgenda);

        return ResponseEntity.status(HttpStatus.CREATED).body(agendaResponse);
    }
}
