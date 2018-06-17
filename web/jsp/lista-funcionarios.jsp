<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Funcionarios</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>

    </head>
    <body>
        <c:import url="../components/header.jsp" >

        </c:import>

        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">
                        Lista de Funcionários
                    </h1>
                    <button class="button"><a href="FuncionarioServlet?action=form-new">Cadastrar Funcionário</a></button>
                </div>
            </div>
        </section>
        <div class="container margem">
            <div class="card">
                <table width="100%" class="table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Código</th>
                            <th>Editar</th>
                            <th>Deletar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="funcionario" items="${funcionarios}">
                            <tr>
                                <td><c:out value="${funcionario.nome}"/></td>
                                <td><c:out value="${funcionario.codigo}"/></td>
                                <td><a href="FuncionarioServlet?action=edit&idFuncionario=<c:out value="${funcionario.funcionarioId}"/>" class="button is-link">Editar</a></td>
                                <td><a href="FuncionarioServlet?action=delete&idFuncionario=<c:out value="${funcionario.funcionarioId}"/>" class="button is-danger">Excluir</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
