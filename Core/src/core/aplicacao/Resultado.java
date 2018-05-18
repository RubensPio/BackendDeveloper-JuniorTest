package core.aplicacao;

import java.util.List;

import ClassesDominio.EntidadeDominio;

public class Resultado extends EntidadeAplicacao{
	private String msg; // Mensagem mandada para a proxima camada, pode ser tanto de sucesso quanto de falha
	private List<EntidadeDominio> entidades; //Entidades dominio resultado de alguma operaçao  
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
}
