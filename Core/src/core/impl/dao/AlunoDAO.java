package core.impl.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import ClassesDominio.Aluno;
import ClassesDominio.EntidadeDominio;

public class AlunoDAO extends AbstractJdbcDAO{

	public AlunoDAO() {
		super("tb_aluno", "idTable");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void salvar(EntidadeDominio entidade) throws SQLException {
		openConnection();
		PreparedStatement pst = null;
		Aluno objAluno = (Aluno) entidade;
		StringBuilder sql;
		
		if(objAluno != null) {
			try {
				connection.setAutoCommit(false);
				
				//fazendo a query de insert de aluno
				sql = new StringBuilder();
				sql.append("INSERT INTO tb_aluno(ALU_NOME, ALU_SOBRNOME, ALU_MATRICULA)");
				sql.append(" VALUES(?,?,?)");
				
				//prepara o insert e insere p return generated keys para pegar o ID do aluno ao inseri-lo no banco
    			pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
    			
    			pst.setString(1,objAluno.getNome());
    			pst.setString(2, objAluno.getSobrenome());
    			pst.setString(3, objAluno.getMatricula());
				
    			pst.executeUpdate();
				
    			ResultSet rs = pst.getGeneratedKeys();
                int id=0;
                if(rs.next())
    			{
    				id = rs.getInt(1);
    				objAluno.setId(id);
    			}
    			
    			connection.commit();
    			
    			//laço para inserir os telefones do aluno
    			for(String telefone:objAluno.getTelefone()) {
    				connection.setAutoCommit(false);
    				
    				sql = new StringBuilder();
    				
    				sql.append("INSERT INTO tb_telefones(TEL_ALU_ID, TEL_TELEFONE) ");
    				sql.append("VALUES(?,?)");
    				
    				pst = connection.prepareStatement(sql.toString());
    				
    				pst.setInt(1, objAluno.getId());
    				pst.setString(2, telefone);
    				
    				pst.executeUpdate();
    				
    				connection.commit();
    			}
    			
			}catch (SQLException e) {
                try {
                    connection.rollback(); // tenta dar rollback no banco
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                e.printStackTrace();
            } finally {
                try {
                    pst.close(); // fecha o prepareStatment
                    connection.close(); // fecha a conecção
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
            }
		}
	}

}
