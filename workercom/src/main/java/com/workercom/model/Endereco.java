package com.workercom.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@ToString
public class Endereco {
	
	private String endereco;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	

}
