package core;

import java.sql.SQLException;

import ClassesDominio.EntidadeDominio;

//interface contendo os m�todos para a DAO
public interface IDAO {
	public void salvar(EntidadeDominio entidade) throws SQLException;
	//aqui estariam os demais m�todos, como o excluir, alterar
}
