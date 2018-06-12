<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<nav id="navigation" class="navbar margem" role="navigation" aria-label="Navegação principal">
    <div class="navbar-brand">
        <a class="navbar-item" href="/">
            <img src="images/logo4everalone.png" alt="4ever alone" style="max-height: 65px;">
        </a>
        <a role="button" class="navbar-burger" data-target="navMenu" aria-label="menu" aria-expanded="false">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
        </a>
    </div>
    <c:if test="${user.adm == true}">
        <div id="navMenu" class="navbar-menu navbar-end">
            <a class="navbar-item is-active" href="HomeServlet">
                Home
            </a>
            <a class="navbar-item" href="ClienteServlet?action=view">
                Cadastrar cliente
            </a>
            <a class="navbar-item" href="ClienteServlet?action=view">
                Promover festa
            </a>
            <a class="navbar-item" href="ClienteServlet?action=view">
                Relatório
            </a>
            <a class="navbar-item" href="LogoutServlet">
                Logout
            </a>
        </div>
    </c:if>
    <c:if test="${user.adm == false}">
        <div id="navMenu" class="navbar-menu navbar-end">
            <a class="navbar-item is-active" href="HomeServlet">
                Home
            </a>
            <a class="navbar-item" href="EncontroServlet?action=solicitar-encontro-view">
                Solicitar Encontro
            </a>
            <a class="navbar-item" href="EncontroServlet?action=meus-encontros">
                Meus Encontros
            </a>
            <a class="navbar-item" href="FestaServlet?action=view">
                Festas
            </a>
            <a class="navbar-item" href="CasamentoServlet?action=visualizar">
                Casamento
            </a>
            <div id="active-toggle" class="navbar-item has-dropdown">
                <a class="navbar-link">
                    <img class="round-image" src="ImageServlet?action=view&img=${user.clienteId}"/>
                </a>
                <div class="navbar-dropdown">
                    <a class="navbar-item" href="ClienteServlet?action=view">
                        Perfil
                    </a>

                    <a class="navbar-item" href="LogoutServlet">
                        Logout
                    </a>
                </div>
            </div>
        </div>
    </c:if>

</nav>

<script>
    $(function () {
        $("#active-toggle").click(function () {
            $(this).toggleClass("is-active");
        });
    });
    document.addEventListener('DOMContentLoaded', function () {
        // Get all "navbar-burger" elements
        var $navbarBurgers = Array.prototype.slice.call(document.querySelectorAll('.navbar-burger'), 0);

        // Check if there are any navbar burgers
        if ($navbarBurgers.length > 0) {

            // Add a click event on each of them
            $navbarBurgers.forEach(function ($el) {
                $el.addEventListener('click', function () {

                    // Get the target from the "data-target" attribute
                    var target = $el.dataset.target;
                    var $target = document.getElementById(target);

                    // Toggle the class on both the "navbar-burger" and the "navbar-menu"
                    $el.classList.toggle('is-active');
                    $target.classList.toggle('is-active');

                });
            });
        }
    });
</script>