package core;

import java.sql.SQLException;

import ClassesDominio.EntidadeDominio;

//interface contendo os métodos para a DAO
public interface IDAO {
	public void salvar(EntidadeDominio entidade) throws SQLException;
	//aqui estariam os demais métodos, como o excluir, alterar
}
