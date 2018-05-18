package core.impl.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ClassesDominio.Aluno;
import ClassesDominio.EntidadeDominio;
import core.IDAO;
import core.IFachada;
import core.IStrategy;
import core.aplicacao.Resultado;
import core.impl.dao.AlunoDAO;
import core.impl.negocio.DadosObrigatorios;

public class Fachada implements IFachada{
	private Map<String, IDAO> daos; // Mapa para os DAOs
	private Map<String, Map<String, List<IStrategy>>> rns; // Mapa para regras de Negocio
	private Resultado resultado; //Resultado, que vai conter a mensagem(Erro ou Sucesso)
	
	public Fachada() {
		daos = new HashMap<String, IDAO>();
		rns = new HashMap<String, Map<String, List<IStrategy>>>();
		//inicializando os hash maps
		
		AlunoDAO alunoDAO = new AlunoDAO();
		
		daos.put(Aluno.class.getName(), alunoDAO); // mapeando a DAO de livro no map
		
		DadosObrigatorios vDadosObrigatorios = new DadosObrigatorios();
		//lista de regras de negocio para salvar um aluno
		List<IStrategy> rnSalvarAluno = new ArrayList<IStrategy>();
		rnSalvarAluno.add(vDadosObrigatorios);
		
		//mapas de regras de negocio
		Map<String, List<IStrategy>> rnAluno = new HashMap<String, List<IStrategy>>();
		//mapeando as regras de negocio
		rnAluno.put("SALVAR", rnSalvarAluno);
		
		rns.put(Aluno.class.getName(), rnAluno);
	}
	
	//metodo para chamar o dao para salvar
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();
		String msg = executarRegras(entidade, "SALVAR");
		
		if(msg == null) {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (SQLException e) {
				e.printStackTrace();
				resultado.setMsg("Não foi possível realizar o registro!");
			}
		} else {
			resultado.setMsg(msg);
		}
		return resultado;
	}
	
	// metodo que executa as regras de negocio
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String nmClasse = entidade.getClass().getName();
		StringBuilder msg = new StringBuilder();
		
		Map<String, List<IStrategy>> regrasOperacao = rns.get(nmClasse);
		
		if(regrasOperacao != null) {
			List<IStrategy> regras = regrasOperacao.get(operacao);
			
			if(regras != null) {
				for(IStrategy s:regras) {
					String m = s.processar(entidade);
					
					if(m != null) {
						msg.append(m);
						msg.append("\n");
					}
				}
			}
		}
		
		if(msg.length()>0)
			return msg.toString();
		else
			return null;
	}
}
