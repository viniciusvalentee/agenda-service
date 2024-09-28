package com.valente.agenda.service;

import com.valente.agenda.domain.entity.Agenda;
import com.valente.agenda.domain.entity.Paciente;
import com.valente.agenda.domain.entity.repository.AgendaRepository;
import com.valente.agenda.exception.BusinessException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @InjectMocks
    AgendaService service;

    @Mock
    PacienteService pacienteService;

    @Mock
    AgendaRepository repository;

    @Captor
    ArgumentCaptor<Agenda> agendaCaptor;

    @Test
    @DisplayName("Should save agendamento with sucess")
    void saveWithSuccess() {

        //Arrange
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição de agendamento de testes");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("PatientTest");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.searchById(agenda.getPaciente().getId())).thenReturn(Optional.of(paciente));
        Mockito.when(repository.findByHorario(now)).thenReturn(Optional.empty());

        //Act
        service.save(agenda);

        //Assert
        Mockito.verify(pacienteService).searchById(agenda.getPaciente().getId());
        Mockito.verify(repository).findByHorario(now);

        Mockito.verify(repository).save(agendaCaptor.capture());
        Agenda savedAgenda = agendaCaptor.getValue();

        Assertions.assertThat(savedAgenda.getPaciente()).isNotNull();
        Assertions.assertThat(savedAgenda.getDataCriacao()).isNotNull();
    }

    @Test
    @DisplayName("Shouldn't save agendamento without paciente")
    void saveErrorPacienteNotFound() {

        //Arrange
        LocalDateTime now = LocalDateTime.now();
        Agenda agenda = new Agenda();
        agenda.setDescricao("Descrição de agendamento de testes");
        agenda.setHorario(now);

        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("PatientTest");

        agenda.setPaciente(paciente);

        Mockito.when(pacienteService.searchById(ArgumentMatchers.any())).thenReturn(Optional.empty());

        //Act
        BusinessException businessException = assertThrows(BusinessException.class, () -> {
            service.save(agenda);
        });

        //Assert
        Assertions.assertThat(businessException.getMessage()).isEqualTo("Patient not found");


    }
}