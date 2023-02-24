package com.mscompras.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mscompras.model.Pedido;
import com.mscompras.service.PedidoService;


import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/pedido")
public class PedidoController {
	
	private final PedidoService pedidoService;	
	
	@PostMapping
    public ResponseEntity<Pedido>salvar(@RequestBody @Valid Pedido pedido){
		return ResponseEntity.ok(pedidoService.salvarPedido(pedido));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> getPedidoPorId(@PathVariable Long id){
		return ResponseEntity.ok(pedidoService.buscarPedidoOuFalharPorID(id));	
		
	}
	
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        pedidoService.excluir(id);
    }	
}
