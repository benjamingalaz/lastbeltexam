<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Idea</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/src/main/resources/static/style.css" />
    <style>
        .container {
            margin-top: 40px;
            width: 700px;
        }

        h1 {
            margin-bottom: 50px;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        label {
            margin-bottom: 5px;
            text-align: left;
        }

        .form-buttons {
            display: flex;
            justify-content: flex-end;
            align-items: center;
            margin-top: 40px;
        }

        .btn-secondary {
            margin-right: 20px;
        }

        .btn-danger {
            margin-top: 20px;
            margin-left: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1>${idea.nombreIdea}</h1>

    <form:form modelAttribute="idea" method="post" action="/guardarEdicionIdea">
        <form:hidden path="id" />
        <form:hidden path="usuarioAgregador" />

        <div class="form-group">
            <label for="nombreIdea">Contenido:</label>
            <form:input path="nombreIdea" id="nombreIdea" class="form-control" required="true" />
        </div>

        <div class="form-buttons">
            <a href="<c:url value='/ideas/${idea.nombreIdea}'/>" class="btn btn-secondary">Cancelar</a>
            <button type="submit" class="btn btn-secondary">Actualizar</button>
        </div>
    </form:form>

    <form:form action="/eliminarIdea" method="post" modelAttribute="idea">
    <input type="hidden" name="ideaId" value="${idea.id}" />
    <button type="submit" class="btn btn-secondary">Borrar</button>
</form:form>



</div>

</body>
</html>