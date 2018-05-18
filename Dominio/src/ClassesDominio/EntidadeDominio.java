package ClassesDominio;
import java.util.Date;

//classe feita para n�o haver erro ao manipular os dados nas
//diferentes camadas, pois todos os objetos ser�o EntidadeDominio
public class EntidadeDominio implements IEntidade{
	private Integer id;
	private Date dtCadastro;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDtCadastro() {
		return dtCadastro;
	}
	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}
}
