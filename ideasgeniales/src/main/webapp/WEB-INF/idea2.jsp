<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Detalles de la Idea</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/src/main/resources/static/style.css" />
    <style>
        .container {
            margin-top: 40px;
            width: 1000px;
        }

        .program-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .col-8.display-8 {
            text-align: right;
        }

        .details-container {
            text-align: left;
            margin-bottom: 20px;
        }

        .details-row {
            margin-bottom: 10px;
        }

        .details-label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .details-value {
            width: 100%;
        }

        .table-header {
            background-color: #adadad;
            color: #ffffff;
        }

        .even-row {
            background-color: #ffffff;
        }

        .odd-row {
            background-color: #f8f9fa;
        }

        .wide-column {
            width: 70%;
        }

        .narrow-column {
            width: 30%;
        }

        .editar-button {
            margin-top: 20px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="program-header">
        <h1>${idea.nombreIdea}</h1>
        <div class="col-8 display-8">
            <a href="/ideas">Regresar al Panel</a>
        </div>
    </div>

    <div class="details-container">
        <div class="details-row">
            <span class="details-label">Creado Por:</span>
            <span class="details-value">${idea.usuarioAgregador.userName}</span>
        </div>


    </div>

    <h3>Usuarios quienes han dado "Me Gusta" a tu idea:</h3>

    <table class="table table-bordered">
        <thead class="table-header">
        <tr>
            <th>Nombre</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="usuarioLike" items="${idea.usuariosQueDieronLike}">
            <tr>
                <td>${usuarioLike.userName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<c:if test="${usuario.userName eq idea.usuarioAgregador.userName}">
    <div class="editar-button">
        <a href="/editarIdea?ideaId=${idea.id}" class="btn btn-secondary">Editar</a>
    </div>
</c:if>


</div>

</body>
</html>