<%@page import="br.pucpr.trabalho5.Pessoa"%>
<%@page import="java.util.Vector"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="pessoaBO" scope="request" class="br.pucpr.trabalho5.PessoaBO"></jsp:useBean>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Buscando Pessoa</title>
</head>
<body>
<%

String tipo = request.getParameter("tipoBusca");
String buscaNome = request.getParameter("buscaNome");
String buscaCPF = request.getParameter("buscaCPF");
Vector<Pessoa> vetorResultado = new Vector<Pessoa>();

if (tipo.compareTo("0") == 0) {
	vetorResultado = pessoaBO.buscaPessoa("", Integer.parseInt(tipo));
} else if (tipo.compareTo("1") == 0) {
	vetorResultado = pessoaBO.buscaPessoa(buscaNome, Integer.parseInt(tipo));
} else if (tipo.compareTo("2") == 0) {
	vetorResultado = pessoaBO.buscaPessoa(buscaCPF, Integer.parseInt(tipo));
}

for(Pessoa item : vetorResultado) {
	out.println("Nome: " + item.getPreNome() + " " + item.getPosNome());
	out.println("CPF: " + item.getnCPF());
	if (item.getEnderecoCompleto() != " ") {
		out.println("Endereco: " + item.getEnderecoCompleto());
	}
}

%>

</body>
</html>