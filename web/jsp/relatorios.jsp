<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Relatórios</title>
        <link rel="stylesheet" href="lib/bulma-0.7.1/css/bulma.min.css"/>
        <link rel="stylesheet" href="lib/jquery-ui/jquery-ui.min.css"/>
        <link rel="stylesheet" href="css/style.css"/>
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
        <script src="lib/jquery-ui/jquery-ui.min.js"></script>
        <script src="lib/jquery-mask/jquery.mask.min.js"></script>

    </head>
    <body>
        <c:import url="../components/header.jsp" >
        </c:import>

        <section class="hero is-info">
            <div class="hero-body">
                <div class="container">
                    <h1 class="title">
                        Relatórios Gerenciais
                    </h1>
                    <h2 class="subtitle">
                        Selecione e visualize um relatório
                    </h2>

                </div>
            </div>
        </section>
        <div class="container margem">
            <form action="GeradorRelatorio" method="POST">
                <div class="field">
                    <label class="label" for="acao">Relatório</label>
                    <div class="select">
                        <select name="acao">
                            <option value="1">Relatório Sintético de Orçamentos</option>
                            <option value="2">Relatório Financeiro de Orçamentos</option>
                        </select>
                    </div>
                </div>   
                <div class="field is-grouped">
                    <div class="control">
                        <button name="submit" type="submit" class="button is-link">Visualizar</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
