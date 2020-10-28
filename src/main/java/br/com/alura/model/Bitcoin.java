package br.com.alura.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Bitcoin {

    private Long id;
    private Double preco;
    private String tipo;
    private LocalDate data;

}
