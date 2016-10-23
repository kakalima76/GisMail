package com.example.models;

public class Mensagem {	private String mensagem;
	private String origem;
	
	
	public Mensagem(String mensagem, String origem){

		this.mensagem = mensagem;
		this.origem = origem;
	}
	
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public String getOrigem() {
		return origem;
	}
	public void setOrigem(String origem) {
		this.origem = origem;
	}
	
}
