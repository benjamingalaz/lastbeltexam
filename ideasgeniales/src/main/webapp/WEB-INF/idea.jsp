<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ideas</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/src/main/resources/static/style.css" />
    <style>
        .container {
            margin-top: 20px;
            width: auto; 
        }

        .table-container {
            margin-top: 20px;
        }

        .table tbody tr:nth-child(odd) {
            background-color: #f8f9fa; 
        }

        .table tbody tr:nth-child(even) {
            background-color: #ffffff; 
        }

        .table thead tr {
            background-color: #adadad;
            color: #ffffff; 
        }

        .agregar-idea-btn {
            margin-top: 10px; 
            margin-bottom: 20px;
        }
    </style>
</head>
<body>

    <div class="container">
        <div class="row gx-2 align-items-center">
            <h1 class="col-4">
                Bienvenido,
                <c:out value="${usuario.userName}!"></c:out>
            </h1>
            <div class="col-8 display-8">
                <a href="/logout">Cerrar Sesion</a>
            </div>
        </div>

        <div class="row gx-2 align-items-center">
            <div class="col-12 text-end">
                <a href="/ideas?orderBy=desc">Menos Me Gusta</a>
                <a href="/ideas?orderBy=asc">Más Me Gustas</a>
            </div>
        </div>

        <h2 class="mt-4">Ideas</h2>

        <div class="table-container">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Idea</th>
                        <th>Creado por</th>
                        <th>Me Gustas</th>
                        <th>Acción</th>
                    </tr>
                </thead>
                <tbody>
    <c:forEach var="idea" items="${ideas}">
    <tr>
        <td>
            <c:url var="ideaUrl" value="/ideas/${fn:replace(idea.nombreIdea, ' ', '%20')}" />
            <a href="${ideaUrl}" class="btn btn-link">${idea.nombreIdea}</a>
        </td>
        <td>${idea.usuarioAgregador.userName}</td>
        <td>${idea.contadorLikes}</td>
        <td>
    <form:form action="/agregarLike" method="post">
        <input type="hidden" name="ideaId" value="${idea.id}" />
        <button type="submit" class="btn btn-secondary">
            <c:choose>
                <c:when test="${idea.usuariosQueDieronLike.contains(usuario)}">
                    No Me Gusta
                </c:when>
                <c:otherwise>
                    Me Gusta
                </c:otherwise>
            </c:choose>
        </button>
    </form:form>
</td>
    </tr>
</c:forEach>
</tbody>
            </table>
        </div>

        <form:form action="/agregarIdea" method="post" modelAttribute="newIdea" class="agregar-idea-btn">
    <div class="d-flex justify-content-start">
        <button type="submit" class="btn btn-secondary">Crear Una Idea</button>
    </div>
</form:form>

    </div>
</body>
</html>