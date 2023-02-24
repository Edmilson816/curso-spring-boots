package com.mscompras.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonNaming
@ToString
@Entity(name="tb_pedido")
public class Pedido implements Serializable {
	
    @Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
    @NotBlank /*Valida se é nulo e vazio*/
	private String nome;

    @NotNull /*Valida se é nulo e vazio*/
    @Min(1)/*Indica que não podemos ter um produto com valor negativo*/
	private Long produto;
	
    @NotNull /*Valida se é nulo e vazio*/
    @Min(1)
	private BigDecimal valor;
	
    @NotNull /*Valida se é nulo e vazio*/
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date dataCompra;
	
    @NotBlank /*Valida se é nulo e vazio*/
	private String cpfCliente;
	
    @NotBlank /*Valida se é nulo e vazio*/
	private String cep;
    
    @NotBlank
    private String email;
	
	
	
}
