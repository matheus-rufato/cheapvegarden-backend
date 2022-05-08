package com.cheapvegarden.repository.entity;

import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "agendamento")
public class Agendamento {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cultura")
    @NotNull
    private Cultura cultura;

    @Column(name = "hora_inicio")
    @NotNull
    private LocalTime horaInicio;

    @Column(name = "hora_fim")
    @NotNull
    private LocalTime horaFim;
}
