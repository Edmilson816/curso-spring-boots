package com.mscompras;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.annotation.Testable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mscompras.model.Pedido;
import com.mscompras.repository.PedidoRepository;
import com.mscompras.service.DadosMok;
import com.mscompras.service.PedidoService;
import com.mscompras.service.exception.NegocioException;
import com.mscompras.service.rabbitmq.Producer;

@ExtendWith(MockitoExtension.class)
class CompraApplicationTests {

	@InjectMocks
	private PedidoService pedidoService;
	
	@Mock
	private PedidoRepository pedidoRepository;
	
	@Mock
	private Producer producer;
	
	private DadosMok Mock = new DadosMok();
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	private DadosMok dadosMok = new DadosMok();
	
	private static final String ROTA_PEDIDO = "/pedido";
	

	@Test
	void contextLoads() {		
		
		
	}
	
	
	@Test
	@DisplayName("Salvar pedido com sucesso")
	void devSalvarUmPedidoComSucesso() {		

		
        Pedido pedidoMok  = Mock.getPedido();
			
		Mockito.when(pedidoRepository.save(Mockito.any(Pedido.class))).thenReturn(pedidoMok);
			
		/*Senpre que eu for testar um metodo do tipo void utilizo o doNothing()*/ 
		Mockito.doNothing().when(producer).enviarPedido(Mockito.any(Pedido.class));
			
		Pedido pedidoSalvo = pedidoService.salvarPedido(pedidoMok);
			
		assertEquals(pedidoMok.getCep(), pedidoSalvo.getCep());
		assertNotNull(pedidoSalvo.getCep() != null);
		
	}
	
	@Test
	@DisplayName("Deve falhar na busca de pedido nao existente")
	void devFalharNaBuscaDePedidoNaoExistente(){
      
		var id = 1L;
		/*O tipo: Throwable retorna uma exceção generica*/
		Throwable exception = assertThrows(NegocioException.class, () ->{
			pedidoService.buscarPedidoOuFalharPorID(1L);
		});
		
		assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());
      
	}
	
	@Test
	@DisplayName("Deve Buscar pedido com sucesso")
	void deveBuscarPedidoComSucesso() {
	  
		Pedido pedidoMok = Mock.getPedidoSalvo();
		
		long id = 1L;
		
		Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMok));		
		Pedido pedidoSalvo = pedidoService.buscarPedidoOuFalharPorID(id);
		
		assertEquals(pedidoMok.getId(), pedidoSalvo.getId());
		assertNotNull(pedidoSalvo);
		/*Por ser um metodo que não retorna nada precisamos adicionar o verify 
		 * para garantir que o teste passará pelo metodo*/
		Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).findById(id);
	  
	}
	
	@Test
	@DisplayName("Deve excluir um pedido com sucesso")
	void deveExcluirPedidoComSucesso() {
		Pedido pedidoMok = Mock.getPedidoSalvo();
        
		long id = 1L;

		/*Aqui é retornado o objeto com as informações no pedidoMok*/
		Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.of(pedidoMok));
		
		/*Adicionamos este metodo para evitar que de problema no banco ao executar o repository. Ou seja que não faça nada*/
		Mockito.doNothing().when(pedidoRepository).deleteById(id);

		pedidoService.excluir(id);
		/*Por ser um metodo que não retorna nada precisamos adicionar o verify 
		 * para garantir que o teste passará pelo metodo*/
		Mockito.verify(pedidoRepository, Mockito.atLeastOnce()).deleteById(id);
	}
	
	@Test
	@DisplayName("Deve falhar ao excluir o pedido")
	public void deveFalharAoExcluirPedidoInexistente(){
		
		long id = 1L;
		
		/*Aqui é retornado o objeto com as informações vazias*/
		Mockito.when(pedidoRepository.findById(id)).thenReturn(Optional.empty());
		
		Throwable exception = assertThrows(NegocioException.class, () ->{
			pedidoService.excluir(id);
		});
		
		assertEquals("O pedido de id: " + id + " nao existe na base de dados!", exception.getMessage());

	}
	

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


