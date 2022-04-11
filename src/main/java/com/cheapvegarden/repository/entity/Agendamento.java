package com.cheapvegarden.repository.entity;

import java.time.LocalTime;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Agendamento")
public class Agendamento {
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "native", strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_cultura")
    @NotNull
    private Cultura cultura;

    @Column(name = "Hora_inicio")
    @NotNull
    private LocalTime horaInicio;

    @Column(name = "Hora_fim")
    @NotNull
    private LocalTime horaFim;

}
