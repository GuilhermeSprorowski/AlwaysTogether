<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Funcionario</title>

        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>
        <script>
            $(function() {
            $('#datepicker').datepicker($.extend({
                showMonthAfterYear: false,
                dateFormat: 'dd/mm/yy'
              }
            ));
            $('#cpf').mask("000.000.000-00");
            <c:if test="${funcionario.adm}">
                $('#adm').click();
            </c:if>
            
        });
        </script>

    </head>
    <body>
        <c:import url="../components/header.jsp" ></c:import>
        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <c:if test = "${form != 'alterar'}">
                        <h1 class="title">Cadastrar funcionário</h1>
                    </c:if>
                    <c:if test = "${form == 'alterar'}">
                        <h1 class="title">Alterar funcionário</h1>
                    </c:if>
                </div>
            </div>
        </section>
        <div class="container margem">
            <form method="POST" 
            <c:if test = "${form == 'alterar'}">action="FuncionarioServlet?action=alterar"</c:if>
            <c:if test = "${form != 'alterar'}">action="FuncionarioServlet?action=salva"</c:if>
               class="column card">               
                <div class="columns">
                    <div class="column">
                        <div class="columns">
                            <c:if test="${form == 'alterar'}">
                                <input type="hidden" name="idFuncionario" value="${funcionario.funcionarioId}"/>
                            </c:if>
                            
                            <div class="field column">
                                <label class="label">Nome completo</label>
                                <div class="control">
                                    <input required name="nome" class="input" type="text"
                                           placeholder="Nome Completo" value="${funcionario.nome}">
                                </div>
                            </div>
                            <div <c:if test="${form == 'alterar'}">style="display: none;"</c:if> class="field column">
                                <label class="label">Email</label>
                                <div class="control">
                                    <input name="email" class="input" type="email"
                                           placeholder="E-mail">
                                </div>
                            </div>
                            <c:if test="${form != 'alterar'}">
                                <div class="field column">
                                    <label class="label">Senha</label>
                                    <div class="control">
                                        <input name="senha" class="input" type="text"
                                               placeholder="Senha">
                                    </div>
                                </div>
                            </c:if>
                            <div class="field column is-3">
                                <label class="label">Data de nascimento:</label>
                                <div class="control has-icons-left">
                                    <input required name="dataNasc" class="input" type="text" id="datepicker"
                                           placeholder="Data nascimento" value='<fmt:formatDate pattern="dd/mm/yyyy" value="${funcionario.dataNasc}"></fmt:formatDate>'>
                                    <span class="icon is-left">
                                        <i class="fas fa-calendar-alt"></i>
                                    </span> 
                                </div>
                            </div>
                        </div>

                        <div class="columns">
                            <div class="column">
                                <label class="label" for="cpf">CPF</label>
                                <input required name="cpf" id="cpf" class="input" type="text"
                                       placeholder="000.000.000-00" value="${funcionario.cpf}">
                            </div>
                            <div class="column">
                                <label class="label" for="codigo">Código</label>
                                <input required name="codigo" class="input" type="text"
                                       value="${funcionario.codigo}">
                            </div>
                        </div>
                        <div class="field">
                            <label class="label" for="adm">Adm?</label>
                            <input id="adm" name="adm" type="checkbox">
                        </div>
                            
                    </div>
                </div>
                <div class="field is-grouped">
                    <div class="control">
                        <button type="submit" class="button is-link">
                            <c:if test="${form != 'alterar'}">Cadastar</c:if>
                            <c:if test="${form == 'alterar'}">Alterar</c:if>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
