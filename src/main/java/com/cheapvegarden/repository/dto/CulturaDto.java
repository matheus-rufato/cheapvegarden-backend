package com.cheapvegarden.repository.dto;

import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "controleId" }, allowGetters = true, allowSetters = false)
public class CulturaDto {

    private long id;

    @Size(max = 50)
    @NotNull
    private String nome;

    @NotNull
    private long controleId;

    @NotNull
    private SetupDto setupDto;
}