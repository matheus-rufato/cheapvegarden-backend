package com.cheapvegarden.repository.dto;

import javax.validation.constraints.Size;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CulturaDto {

    private Long id;
    
    @Size(max = 20)
    private String nome;

    private ControleDto controleDto;

    private SetupDto setupDto;
}
