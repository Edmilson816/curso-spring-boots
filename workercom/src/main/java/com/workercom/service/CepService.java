package com.workercom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;

import com.workercom.model.Endereco;
import com.workercom.repository.CepRepository;

import lombok.extern.slf4j.Slf4j;

@EnableFeignClients
@Slf4j
@Service
public class CepService {
  
	@Autowired
	private CepRepository cepRepository;
	
	/*url do via cep*/ /*https://viacep.com.br/ws/14020720/json/*/
	public void buscarCep(String cep) {
		Endereco endereco = cepRepository.buscarPorCep(cep);
		log.info("Endereco montado com sucesso: {}", endereco);
		
	}
	
}
