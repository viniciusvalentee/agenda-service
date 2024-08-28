package com.valente.agenda.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter // Lombok gera Getter e Setters por baixo dos panos
@Entity
@Table(name = "paciente")
public class Paciente { //Tabela paciente do postgres

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
    private String cpf;

}
