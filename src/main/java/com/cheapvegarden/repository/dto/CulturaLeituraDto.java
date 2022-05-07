package com.cheapvegarden.repository.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturaLeituraDto {

    private long id;

    private String nome;

    private long controleId;

    private long setupId;
}
