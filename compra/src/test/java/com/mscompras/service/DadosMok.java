package com.mscompras.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.mscompras.model.Pedido;

@Service
public class DadosMok {
	
	public Pedido getPedido() {
		return Pedido.builder()
				.nome("Edmilson Carvalho")
				.produto(1L)
				.valor(BigDecimal.TEN) /*10.00*//*TEN equivale a 10.00*/
				.dataCompra(new Date())
				.cpfCliente("111.222.333-44")
				.cep("14020720")
				.email("edmilsoncarvalho816@gmail.com")
				.build(); 
	}
	
	public Pedido getPedidoSalvo() {
		return Pedido.builder()
				.id(1L)
				.nome("Edmilson Carvalho")
				.produto(1L)
				.valor(BigDecimal.TEN) /*10.00*//*TEN equivale a 10.00*/
				.dataCompra(new Date())
				.cpfCliente("111.222.333-44")
				.cep("14020720")
				.email("edmilsoncarvalho816@gmail.com")
				.build(); 
	}
	

}
