package com.worker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.worker.model.Pedido;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void notificarClienteCompra(Pedido pedido, String tituloEmail, String menssagemEmail) {
		var msg = new SimpleMailMessage();
		msg.setTo(pedido.getEmail());
		msg.setSubject(tituloEmail);
		msg.setText("Olá "+pedido.getNome()+" "+menssagemEmail);
		javaMailSender.send(msg); 
		log.info("Compra aprovada com sucesso!");
		/*Link para configurar o acesso ao gmail*/
		/*https://atendimento.tecnospeed.com.br/hc/pt-br/articles/4418115119127-Como-criar-senha-de-aplicativo-para-email*/		
	}
	
}
