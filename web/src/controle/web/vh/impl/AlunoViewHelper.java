package controle.web.vh.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesDominio.Aluno;
import ClassesDominio.EntidadeDominio;
import controle.web.vh.IViewHelper;
import core.aplicacao.Resultado;

public class AlunoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		Aluno aluno = new Aluno();
		
		aluno.setNome(request.getParameter("txtNome"));
		aluno.setSobrenome(request.getParameter("txtSobrenome"));
		aluno.setMatricula(request.getParameter("txtMatricula"));
		
		Map<String,String[]> parmMap = request.getParameterMap();
		for(String telefone:parmMap.get("txtTelefone")) {
			aluno.getTelefone().add(telefone);
		}
		
		return aluno;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null;
		String operacao = request.getParameter("operacao");
		
		if(resultado.getMsg() == null && operacao.equals("SALVAR")) {
			resultado.setMsg("Aluno cadastrado com sucesso!");
			request.getSession().setAttribute("resultado", resultado);
			
			d = request.getRequestDispatcher("Cadastro-Aluno.jsp");
		}
		
		if(!resultado.getMsg().equals("Aluno cadastrado com sucesso!") || resultado.getMsg() != null && operacao.equals("SALVAR")) {
			request.getSession().setAttribute("resultado", resultado);
			
			d = request.getRequestDispatcher("Cadastro-Aluno.jsp");
		}
		
		d.forward(request, response);
	}

}
