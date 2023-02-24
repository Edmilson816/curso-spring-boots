package com.mscompras.controller.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

/*Classe criada para exibir as informações da validação dos atributos*/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {
	
	private Integer status;
	private OffsetDateTime timeStamp; /*hora da msg erro*/
	private String type;
	private String title; /*titulo da menssagem */
	private String detail; /*detalhe da menssagem */
	private List<Object> object; /*Retorna os atributos da classe levantados na excessão*/
	
    public static class Object{
    	private String name;
    	private String userMessage;
    }

}
