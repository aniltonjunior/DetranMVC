<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.pucpr.trabalho5.TransferenciaBO" %>
<%@ page import="java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Detran - MVC</title>
	<script type="text/javascript">
	function clickclear(thisfield, defaulttext) {
		if (thisfield.value == defaulttext) {
			thisfield.value = "";
		}
	}

	function clickrecall(thisfield, defaulttext) {
		if (thisfield.value == "") {
			thisfield.value = defaulttext;
		}
	}
	
	function validaForm() {
		var x = document.forms["transferencia"]["vendedor"].value;
		var y = document.forms["transferencia"]["comprador"].value;
		var z = document.forms["transferencia"]["valor"].value;
		if (x.value == -1) {
			alert("Vendedor deve ser escolhido.");
			return false;
		}
		if (y.value == -1) {
			alert("Comprado deve ser escolhido.");
			return false;
		}
		if (z < 0) {
			alert("Valor deve ser positivo.");
			return false;
		}
	}
	</script>
	<script type="text/javascript">
	function mostraVeiculo(id_vendedor)
	{ 
		if(document.getElementsByName("vendedor").value != "-1") {
	 		xmlHttp = GetXmlHttpObject();
			if (xmlHttp==null) {
	 			alert ("Browser does not support HTTP Request");
	 			return
	 		}
			var url="getVeiculo.jsp";
			url=url+"?id="+id_vendedor;
			
			xmlHttp.onreadystatechange = function() {
				if (this.readyState != 4) {
					//alert("Passamos no onReadStateChange != 4");
					return;
				} 
				if (this.readyState == 4 || this.readyState == "complete") {
					//alert("Passamos no onReadStateChange == 4");
					var strar = xmlHttp.responseText;
					var resultado = strar.split(":");
					document.forms["transferencia"]["idveiculo"].value = resultado[0];
					alert(resultado[0]);
			    	document.forms["transferencia"]["veiculo"].value = resultado[1];
			    	alert(resultado[1]);
				}
			}
			xmlHttp.open("GET",url,true);
			//alert("Passamos no Open");
			xmlHttp.send(null);
			//alert("Passamos no Send");
	 	} else {
	        alert("Escolha um comprador.");
	    }
	}

	function GetXmlHttpObject() {
		var xmlHttp=null;
		try {
	 		// Firefox, Opera 8.0+, Safari
	 		xmlHttp= new XMLHttpRequest;
	 	} catch (e) {
	 		//Internet Explorer
	 		try {
	  			xmlHttp= new ActiveXObject("Msxml2.XMLHTTP");
	  		} catch (e) {
	  			xmlHttp= new ActiveXObject("Microsoft.XMLHTTP");
	  		}
	 	}
	 	//alert("Passamos no GetXMLHttpObject");
		return xmlHttp;
	}
	</script>
</head>
<body>
<h1>Realize sua Transferencia</h1>
<form name="transferencia" action="transferenciaCompleta.jsp" method="POST">
	<select name="vendedor" onchange="mostraVeiculo(this.value)"> 
		<option value="-1">Selecione</option> 
		<%
			TransferenciaBO _transf = new TransferenciaBO();
			Vector<String[]> retorno = _transf.retornaVendedores();
			for(String[] item: retorno) {
				%>
				<option value="<%= item[0] %>"> <%= item[1] %> </option>
				<%
			}
		 	%>	
	</select> <input type="text" name="veiculo" value="Marca - Veiculo(Ano)" size="30" disabled="disabled"> <br>
	<select name="comprador"> 
		<option value="-1">Selecione</option> 
		<%
			Vector<String[]> retorno2 = _transf.retornaCompradores();
			for(String[] item: retorno2) {
				%>
				<option value="<%= item[0] %>"> <%= item[1] %> </option>
				<%
			}
		 	%>	
	</select> <br>
	<input type="text" name="valor" value="Insira o valor" size="20" onclick="clickclear(this, 'Insira o valor')" onblur="clickrecall(this,'Insira o valor')"> <br>
	<input type="hidden" name="idveiculo" value="">
	<input type="submit" name="submeter" value="Enviar"> <br>
</form>
</body>
</html>