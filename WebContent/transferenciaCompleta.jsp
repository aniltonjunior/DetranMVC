<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="br.pucpr.trabalho5.TransferenciaBO"  %>
<%@ page import="br.pucpr.trabalho5.Transferencia"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insercao Realizada</title>
</head>
<body>
	<%
		int _idComprador = request.getParameter("comprador");
		int _idVendedor = request.getParameter("vendedor");
		int _idVeiculo = request.getParameter("idveiculo");
		double _valor = request.getParameter("valor");
		
		TransferenciaBO bo = new TransferenciaBO();
		bo.inserirTransferencia(new Transferencia(_idVendedor, _idComprador, _idVeiculo, _valor));
	%>
</body>
</html>

<jsp:forward page="transferencia.jsp"></jsp:forward>