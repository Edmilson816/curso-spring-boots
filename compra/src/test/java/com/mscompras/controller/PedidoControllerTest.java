package com.mscompras.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscompras.CompasApplication;
import com.mscompras.model.Pedido;
import com.mscompras.service.DadosMok;
import com.mscompras.service.PedidoService;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CompasApplication.class)/*Aqui colocamos o nome do projeto principal*/
@AutoConfigureWebMvc /*Utilizamos esta anotação para poder realizar o teste dos Request's*/
@ActiveProfiles("test") /*Utilizamos esta anotação para informar de qual aplication-properties iremos pegar a configuração que nada mais é que o application-test.properties*/
public class PedidoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	private PedidoService pedidoService;
	
	@Autowired
	private ObjectMapper mapper;
	
	private DadosMok dadosMok = new DadosMok();
	
	private static final String ROTA_PEDIDO = "/pedido";
	
	@Test
	@DisplayName("POST - Deve Cadastrar um Novo Pedido com Sucesso no banco de dados")
	private void deveCadastrarPedidoComSucesso() throws Exception{
		
		Pedido pedidoBody = dadosMok.getPedido();
		
		long id = 1L; /*precisa existir um pedido com este codigo no banco*/
		
		mockMvc.perform(post(ROTA_PEDIDO)
				.content(mapper.writeValueAsString(pedidoBody))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)) /*irá escrever o pedido em formato json*/
		        .andDo(print())
		        .andExpect(status().isOk());/*Informa qual o valor experado*/
		
		Pedido pedidoSalvo = pedidoService.buscarPedidoOuFalharPorID(id);
		
		assertEquals(pedidoSalvo.getId(), id);
		assertNotNull(pedidoSalvo);
		
	} 
	
}
