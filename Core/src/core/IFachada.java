package core;

import ClassesDominio.EntidadeDominio;
import core.aplicacao.Resultado;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	//Metodos que a fachada executar, podem ser inseridos outros metodos
	//como excluir, alterar
}
