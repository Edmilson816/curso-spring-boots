package com.workercom;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.workercom.service.CepService;

@EnableFeignClients
@EnableRabbit
@SpringBootApplication
public class WorkercomApplication {

	@Autowired
	private CepService cepService;
	
	public static void main(String[] args) {
		SpringApplication.run(WorkercomApplication.class, args);
	}
	
	/*Anotação utilizada para subir o metodo automaticamente*/
	@Bean
	public void teste() {
		cepService.buscarCep("14020720");
	}

}
