package controle.web.vh;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesDominio.EntidadeDominio;
import core.aplicacao.Resultado;

public interface IViewHelper {
	public EntidadeDominio getEntidade(HttpServletRequest request);
	
	public void setView(Resultado resultado,
			HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException;
}
