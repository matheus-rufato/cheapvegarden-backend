package com.cheapvegarden.repository.dto;

import javax.validation.constraints.NotNull;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SetupDto {

    private Long id;

    @NotNull
    private Boolean status;

    private Boolean tipoControle;
}
