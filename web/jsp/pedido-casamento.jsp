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
    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>
        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">Pedidos de casamento</h1>
                </div>
            </div>
        </section>
        <div class="container margem">
            <div class="card">
                <table width="100%" class="table">
                    <thead>
                        <tr>
                            <th>Solicitação</th>
                            <th>Casamento</th>
                            <th>Aceito</th>
                            <th>Orçamento</th>
                            <th>Visualizar</th>
                            <th>Deletar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pedido" items="${pedidos}">
                            <tr>
                                <td><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${pedido.dataSolicitacao}"/></td>
                                <td><c:out value="${pedido.nomeCliente}"/> & <c:out value="${pedido.nomeConjuge}"/></td>
                                <td>
                                    <c:if test="${pedido.aceito}">Sim</c:if>
                                    <c:if test="${not pedido.aceito}">Não</c:if>
                                    <c:if test="${pedido.aceito and pedido.premium}">- Premium</c:if>
                                    <c:if test="${pedido.aceito and not pedido.premium}">- Standard</c:if>
                                </td>
                                <td>
                                    <c:if test="${pedido.itensOrcamento != null}">
                                        Já realizado
                                    </c:if>
                                    <c:if test="${pedido.itensOrcamento == null}">
                                        <a href="PedidoCasamentoServlet?action=new&id=<c:out value="${pedido.idPedido}"/>" class="button is-link">Orçar</a>
                                    </c:if>
                                </td>
                                <td><a href="PedidoCasamentoServlet?action=view&id=<c:out value="${pedido.idPedido}"/>" class="button is-link">Visualizar</a></td>
                                <td><a href="PedidoCasamentoServlet?action=delete&id=<c:out value="${pedido.idPedido}"/>" class="button is-danger">Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
