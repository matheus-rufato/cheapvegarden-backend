package com.cheapvegarden.repository.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturaLeituraDto {

    private Long id;

    private String nome;

    private Long controleId;

    private Long setupId;
}
