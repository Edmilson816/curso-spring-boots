package com.mscompras.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mscompras.model.Pedido;
import com.mscompras.repository.PedidoRepository;
import com.mscompras.service.PedidoService;
import com.mscompras.service.rabbitmq.Producer;

@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
	
	@InjectMocks
	private PedidoService pedidoService;
	
	@Mock
	private PedidoRepository pedidoRepository;
	
	@Mock
	private Producer producer;
	
	private DadosMok Mock = new DadosMok();
	
	@Test
	@DisplayName("Salvar pedido com sucesso")
	private void devSalvarUmPedidoComSucesso() {
		/*NÃO ESTÁ EXECUTANDO E PORTANTO O TESTE FOI ALOCADO PARA A CLASSE CompraApplicatioTests.java*/
		Pedido pedidoMok  = Mock.getPedido();
		
		Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMok);
		
		/*Senpre que eu for testar um metodo do tipo void utilizo o doNothing()*/ 
		Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));
		
		Pedido pedidoSalvo = pedidoService.salvarPedido(pedidoMok);
		
		assertEquals(pedidoMok.getCep(), pedidoSalvo.getCep());
		assertNotNull(pedidoSalvo.getCep() != null);
		
	}
	

}
