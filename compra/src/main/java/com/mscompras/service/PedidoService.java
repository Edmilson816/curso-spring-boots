package com.mscompras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mscompras.model.Pedido;
import com.mscompras.repository.PedidoRepository;
import com.mscompras.service.exception.EntidadeNaoEncontradaException;
import com.mscompras.service.exception.NegocioException;
import com.mscompras.service.rabbitmq.Producer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class PedidoService {

	private final PedidoRepository pedidoRepository;
	private final Producer producer;
	
	public Pedido salvarPedido(Pedido pedido) {
		
		Pedido pedidoSalvo =  pedidoRepository.save(pedido); /*Salva no banco*/
		producer.enviarPedido(pedidoSalvo); /*Envia para o rabbit servico de menssageria*/
		
		return pedido;
		
	}
	
	public Pedido buscarPedidoOuFalharPorID(Long id){
        
		return pedidoRepository.findById(id)
                .orElseThrow(()-> new EntidadeNaoEncontradaException("O pedido de id: " + id + " nao existe na base de dados!"));		
	}
	
	public void excluir(long id) {
		Pedido pedido = buscarPedidoOuFalharPorID(id);
		pedidoRepository.deleteById(pedido.getId());
	}
	
	
	
	
}
