<%@page import="core.aplicacao.Resultado"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de Aluno</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
	    $("#addTel").click(function(){
	        $("#tocpy").clone().appendTo("#tel");
	    });
	});
</script>
</head>
<%
	String mensagem = null;
	try{
		Resultado resultado = (Resultado) session.getAttribute("resultado");
		mensagem = resultado.getMsg();
	}catch(Exception e){
		
	}
	StringBuilder sb = new StringBuilder();
%>
<body class="bg-light">
	<div class="container">
		<div class="py-5 text-center">
			<h2>Cadastrar Aluno <i clas="fa fa-user"></i></h2>
			<p>Obs: Os campos com * são obrigatórios</p>
		</div>
		<form action="SalvarAluno" method="post">
			<div class="row justify-content-md-center">
				<div class="col-md-6">
					<div class="row">
						<div class="col-md-6">
							<label>Nome*</label>
							<input class="form-control" type="text" name="txtNome">
						</div>
						<div class="col-md-6">
							<label>Sobrenome*</label>
							<input class="form-control" type="text" name="txtSobrenome">
						</div>
					</div><br>
					<div class="row">
						<div class="col-md-12">
							<label>Matricula*</label>
							<input class="form-control" type="text" name="txtMatricula">
						</div>
					</div><br>
					<div class="row">
						<div class="col-md-6" id="tocpy">
							<label>Telefone*</label>
							<input class="form-control" type="text" name="txtTelefone">
						</div>
						<div class="col-md-3">
							<label>Adicionar</label><br>
							<a class="btn btn-success" id="addTel"><i class="fa fa-plus" style="color:white;"></i></a>
						</div>
					</div><br>
					<div class="row" id="tel">
					</div><br>
					<%
						sb = new StringBuilder();
						if(mensagem != null){
							sb.append("<div class='alert alert-warning'>");
							sb.append("<strong>Msg: </strong>" + mensagem);
							sb.append("</div>");
							mensagem = null;
							out.print(sb.toString());
						}
					%>
					<div class="row">
						<div class="col-12">
							<div class="row justify-content-end">
								<div class="col-md-6">
									<button type="submit" class="btn btn-success btn-block" name="operacao" value="SALVAR">Salvar</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
</html>