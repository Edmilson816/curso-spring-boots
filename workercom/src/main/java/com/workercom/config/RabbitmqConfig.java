package com.workercom.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {
	
	@Value("${queue.compra-pendente}") /*Quando o Spring subir Pega o nome que está na aplication-test.properties e passa na funcao abaixo*/
	private String queueName;
	
	@Bean
	public Queue queue() {
		return new Queue(queueName, true); /*o primeiro parametro se refere ao nome na fila: COMPRA_EFETUADA e o segundo que mantem a fila ate que alguem leia */
	}

}
