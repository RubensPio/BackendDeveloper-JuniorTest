package core;

import ClassesDominio.EntidadeDominio;

public interface IStrategy {
	public String processar(EntidadeDominio entidade);
	//metodo para executar as regras de negocios
}
