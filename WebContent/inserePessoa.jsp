<%@page import="br.pucpr.trabalho5.ConexaoMySql"%>
<%@page import="br.pucpr.trabalho5.Pessoa"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="pessoaBO" scope="request" class="br.pucpr.trabalho5.PessoaBO"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserindo Pessoa</title>
</head>
<body>
<% 

String preNome = request.getParameter("preNome");
String posNome = request.getParameter("posNome");
String nCPF = request.getParameter("nCPF");
String endereco = request.getParameter("endereco");

out.println("Estado conexao: " + ConexaoMySql.getStatus()); 

pessoaBO.inserirPessoa(new Pessoa(preNome, posNome, nCPF, endereco));

out.println("Estado conexao: " + ConexaoMySql.getStatus());
%>
</body>
</html>

<jsp:forward page="inserePessoa.html"></jsp:forward>