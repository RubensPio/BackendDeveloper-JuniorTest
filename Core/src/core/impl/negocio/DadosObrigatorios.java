package core.impl.negocio;

import ClassesDominio.Aluno;
import ClassesDominio.EntidadeDominio;
import core.IStrategy;

public class DadosObrigatorios implements IStrategy{

	@Override
	public String processar(EntidadeDominio entidade) {
		Aluno aluno = new Aluno();
		aluno = (Aluno)entidade;
		
		if(aluno.getNome() == null || aluno.getNome().trim().equals("")) {
			return "O nome do aluno é obrigatório!";
		}else if(aluno.getNome().length() < 4) {
			return "O nome precisa ter mais que 3 caracteres!";
		}
		
		if(aluno.getSobrenome() == null || aluno.getSobrenome().trim().equals("")) {
			return "O Sobrenome do aluno é obrigatório!";
		}else if(aluno.getSobrenome().length() < 4) {
			return "O Sobrenome precisa ter mais que 3 caracteres!";
		}
		
		if(aluno.getMatricula() == null || aluno.getMatricula().trim().equals("")) {
			return "A matrícula do aluno é obrigatória!";
		}else if(aluno.getMatricula().length() < 4) {
			return "A matrícula precisa ter mais que 3 caracteres!";
		}
		
		if(aluno.getTelefone() == null || aluno.getTelefone().size() == 1 && aluno.getTelefone().get(0).trim().equals("")) {
			return "É preciso de ao menos um telefone vinculado ao aluno!";
		}else {
			for(String telefone:aluno.getTelefone()) {
				if(telefone.length() < 4) {
					return "O Telefone precisa ter mais que 3 caracteres!";
				}
			}
		}
		
		return null;
	}

}
