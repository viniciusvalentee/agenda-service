package com.valente.agenda.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter // Lombok gera Getter e Setters por baixo dos panos
@Entity
@Table(name = "agenda")
public class Agenda { // Tabela agenda do postgres


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @ManyToOne // Vários registros de agenda para um paciente só
    private Paciente paciente;

}
