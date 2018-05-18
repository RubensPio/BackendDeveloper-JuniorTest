package controle.web.command.impl;

import ClassesDominio.EntidadeDominio;
import core.aplicacao.Resultado;

public class SalvarCommand extends AbstractCommand{
	public Resultado execute(EntidadeDominio entidade) {
		return fachada.salvar(entidade);
	}
}
