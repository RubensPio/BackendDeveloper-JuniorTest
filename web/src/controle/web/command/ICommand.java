package controle.web.command;

import ClassesDominio.EntidadeDominio;
import core.aplicacao.Resultado;

public interface ICommand {
	public Resultado execute(EntidadeDominio entidade);
}
