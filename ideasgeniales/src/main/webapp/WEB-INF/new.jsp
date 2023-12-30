<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nueva Idea</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
    <link rel="stylesheet" href="/src/main/resources/static/style.css" />
    <style>
        .container {
            margin-top: 20px;
            width: 700px;
        }
        h2 {
            margin-bottom: 100px;
        }
    </style>
</head>
<body>

<div class="container">
    <h1 class="mt-4">Crear nueva idea</h1>

    <form:form action="/agregarIdea" method="post" modelAttribute="nuevaIdea">
    <div class="mb-3 row">
        <form:label path="nombreIdea" class="col-sm-3 col-form-label text-left">Content:</form:label>
        <div class="col-sm-9">
            <form:input path="nombreIdea" type="text" class="form-control" />
            <form:errors path="nombreIdea" class="text-danger" />
        </div>
    </div>

    <div class="d-flex justify-content-end mt-3">
        <a href="/ideas" class="btn btn-secondary text-white" style="margin-right: 20px;">Cancelar</a>
        <button type="submit" class="btn btn-secondary">Crear</button>
    </div>
</form:form>
</div>

</body>
</html>
