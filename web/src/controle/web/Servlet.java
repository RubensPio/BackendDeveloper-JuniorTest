package controle.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ClassesDominio.EntidadeDominio;
import controle.web.command.ICommand;
import controle.web.command.impl.SalvarCommand;
import controle.web.vh.IViewHelper;
import controle.web.vh.impl.AlunoViewHelper;
import core.aplicacao.Resultado;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static Map<String, ICommand> commands;
	private static Map<String, IViewHelper> vhs;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        commands = new HashMap<String, ICommand>();
        //mapeando os commands que ser�o usados
        commands.put("SALVAR", new SalvarCommand());
        
        vhs = new HashMap<String, IViewHelper>();
        //mapeando as URLS que ser�o usadas
        vhs.put("/web/SalvarAluno", new AlunoViewHelper());
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessRequest(request, response);
	}
	
	protected void doProcessRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//Obt�m a uri que invocou esta servlet (O que foi definido no methdo do form html)
		String uri = request.getRequestURI();
		
		//Obt�m a opera��o executada
		String operacao = request.getParameter("operacao");
		
		//Obt�m um viewhelper indexado pela uri que invocou esta servlet
		IViewHelper vh = vhs.get(uri);
		
		//O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
		EntidadeDominio entidade =  vh.getEntidade(request);
		
		//Obt�m o command para executar a respectiva opera��o
		ICommand command = commands.get(operacao);
		/*Executa o command que chamar� a fachada para executar a opera��o requisitada
		 * o retorno � uma inst�ncia da classe resultado que pode conter mensagens derro 
		 * ou entidades de retorno
		 */
		Resultado resultado = command.execute(entidade);
		/*
		 * Executa o m�todo setView do view helper espec�fico para definir como dever� ser apresentado 
		 * o resultado para o usu�rio
		 */
		vh.setView(resultado, request, response);
	}
}
