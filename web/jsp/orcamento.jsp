<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedido Casamento</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>
        
        <script>
            $(function() {
                $('#vt1').mask("000000000.00", {reverse: true});
                $('#vt2').mask("000000000.00", {reverse: true});
            });
        </script>
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>
        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">Detalhes para o orçamento</h1>
                    <h2 class="subtitle">Casamento de ${pedido[0].nomeConjuge} & ${pedido[0].nomeCliente}</h2>
                </div>
            </div>
        </section>
        <div class="container margem">
            <table width="100%" class="table">
                <thead>
                    <tr>
                        <th>Data Casamento</th>
                        <th>Convidados</th>
                        <th>Padre</th>
                        <th>Padrinho1</th>
                        <th>Padrinho2</th>
                        <th>Madrinha1</th>
                        <th>Madrinha2</th>
                        <th>Igreja</th>
                        <th>Local Lua de Mel</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm" value="${pedido[0].dataCasamento}"></fmt:formatDate></td>
                        <td><c:out value="${pedido[0].nConvidados}"/></td>
                        <td><c:out value="${pedido[0].padre}"/></td>
                        <td><c:out value="${pedido[0].padrinho1}"/></td>
                        <td><c:out value="${pedido[0].padrinho2}"/></td>
                        <td><c:out value="${pedido[0].madrinha1}"/></td>
                        <td><c:out value="${pedido[0].madrinha2}"/></td>
                        <td><c:out value="${pedido[0].igreja}"/></td>
                        <td><c:out value="${pedido[0].localLua}"/></td>
                    </tr>
                </tbody>
            </table>
            <form 
                action="PedidoCasamentoServlet?action=update"
                method="POST">
                <input style="visibility: hidden" type="text" name="idOrcamento1" value="${pedido[0].idOrcamento}"/>
                <input style="visibility: hidden" type="text" name="idOrcamento2" value="${pedido[1].idOrcamento}"/>
                <div class="field">
                    <label class="label">Orçamento Standard:</label>
                    <textarea style="white-space: pre-wrap;" required rows="4" <c:if test="${form == 'view'}">disabled="true"</c:if> class="textarea" type="text" name="orcamento1">${pedido[0].itensOrcamento}</textarea>
                </div>
                <div class="field">
                    <label class="label">Valor Total:</label>
                    <input  required id="vt1" type="text" <c:if test="${form == 'view'}">disabled="true"</c:if> class="input" name="valortotal1" 
                        value="<fmt:formatNumber value='${pedido[0].vlrTotal}' minFractionDigits="2"/>"/>
                </div>
                <div class="field">
                    <label class="label">Orçamento Premium:</label>
                    <textarea style="white-space: pre-wrap;" required rows="4" <c:if test="${form == 'view'}">disabled="true"</c:if> class="textarea" type="text" name="orcamento2">${pedido[1].itensOrcamento}</textarea>
                </div>
                <div class="field">
                    <label class="label">Valor Total:</label>
                    <input required id="vt2" type="text" <c:if test="${form == 'view'}">disabled="true"</c:if> class="input" name="valortotal2" 
                       value="<fmt:formatNumber value='${pedido[1].vlrTotal}' minFractionDigits="2"/>"/>
                </div>
                <c:if test="${form != 'view'}">
                    <div class="field is-grouped">
                        <div class="control">
                          <button type="submit" class="button is-link">Salvar</button>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
    </body>
</html>
