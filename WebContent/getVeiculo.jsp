<%@ page import="br.pucpr.trabalho5.TransferenciaBO" %>

<%

	String id_vendedor = request.getParameter("id");
	TransferenciaBO _transf = new TransferenciaBO();
	out.print(_transf.retornaVeiculo(id_vendedor));

%>