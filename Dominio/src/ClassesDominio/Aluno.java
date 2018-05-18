package ClassesDominio;

import java.util.ArrayList;

public class Aluno extends EntidadeDominio{
	//Classe de dominio para o Aluno
	private String Nome;
	private String Sobrenome;
	private String Matricula;
	private ArrayList<String> Telefone = new ArrayList<String>();
	
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getSobrenome() {
		return Sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		Sobrenome = sobrenome;
	}
	public String getMatricula() {
		return Matricula;
	}
	public void setMatricula(String matricula) {
		Matricula = matricula;
	}
	public ArrayList<String> getTelefone() {
		return Telefone;
	}
	public void setTelefone(ArrayList<String> telefone) {
		Telefone = telefone;
	}
	
	
}
