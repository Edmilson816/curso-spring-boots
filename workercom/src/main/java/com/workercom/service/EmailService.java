package com.workercom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.workercom.model.Pedido;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private PedidoProducer pedidoProducer; 
	
	public void notificarCliente(Pedido pedido) {
		var msg = new SimpleMailMessage();
		msg.setTo(pedido.getEmail());
		msg.setSubject("Compra recebida!");
		msg.setText("Este é um e-mail de confirmação de compra recebida. " +
                "Agora vamos aprovar sua compra e brevemente você receberá um novo e-mail de confirmação." +
                "Obrigado por comprar com a gente!!");
		javaMailSender.send(msg); 
		log.info("Cliente notificado com sucesso!");
		/*Link para configurar o acesso ao gmail*/
		/*https://atendimento.tecnospeed.com.br/hc/pt-br/articles/4418115119127-Como-criar-senha-de-aplicativo-para-email*/		
        log.info("Preparando pedido consumer!");
		pedidoProducer.enviarPedido(pedido);		
		
	}
	

}
