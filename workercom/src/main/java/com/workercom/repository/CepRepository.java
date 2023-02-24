package com.workercom.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.workercom.model.Endereco;
                                                                        //cep e o formato 
@FeignClient(name="viacep", url = "${viacep}") //https://viacep.com.br/ws/14020720/json/
public interface CepRepository {  /*Lembrando que ${viacep} é uma constante definida no aplication.properties com a url*/
	
	@GetMapping("/{cep}/json/")
	Endereco buscarPorCep(@PathVariable("cep") String cep);

}
