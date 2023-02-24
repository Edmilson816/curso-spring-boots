package com.mscompras.service.exception;

public class PessoaNotFoundException extends EntidadeNaoEncontradaException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PessoaNotFoundException(String mensagem) {
        super(mensagem);
    }

    public PessoaNotFoundException(Long id) {
        this(String.format("Não existe uma pessoa com o id: %s na base de dados!", id));
    }

}
