package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturaDto {

    private long id;

    @Size(max = 20)
    @NotNull
    private String nome;

    @NotNull
    private long controleId;

    @NotNull
    private SetupDto setupDto;
}
