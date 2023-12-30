<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login y Registro</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="/src/main/resources/static/style.css" />
</head>
<body>

	<div class="container">
		<h1 class="text-dark text-start fw-bold display-2"> IDEAS GENIALES</h1>

		<div class="row">
			<!-- Sección de Registro -->
			<div class="col-md-6">
				<h4 class="fw-semibold display-6">Registrarte</h4>
				<form:form action="/register" method="post"
					modelAttribute="registro">
					<div class="mb-3 row">
						<form:label path="userName" for="exampleInputEmail1"
							class="col-sm-3 col-form-label text-left">Nombre:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="userName" />
							<form:input path="userName" type="text" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="email" for="exampleInputEmail1"
							class="col-sm-3 col-form-label text-left">Email:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="email" />
							<form:input path="email" type="email" class="form-control"
								id="exampleInputEmail1" aria-describedby="emailHelp" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="password" for="exampleInputPassword"
							class="col-sm-3 col-form-label text-left">Contraseña:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="password" />
							<form:input path="password" type="password" class="form-control"
								id="exampleInputPassword" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="passwordConfirmation"
							for="exampleInputConfirmation"
							class="col-sm-3 col-form-label text-left">Confirmación:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="passwordConfirmation" />
							<form:input path="passwordConfirmation" type="password"
								class="form-control" id="exampleInputConfirmation" />
						</div>
					</div>
					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-secondary">Registrarte</button>
					</div>
				</form:form>
			</div>

			<!-- Sección de Inicio de Sesión -->
			<div class="col-md-6">
				<h2 class="fw-semibold display-6 d-flex justify-content-end">Iniciar
					Sesión</h2>
				<form:form action="/login" method="post" modelAttribute="login">
					<div class="mb-3 row">
						<form:label path="email" for="exampleInputEmail2"
							class="col-sm-3 col-form-label text-left">Email:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="email" />
							<form:input path="email" type="email" class="form-control"
								id="exampleInputEmail2" aria-describedby="emailHelp" />
						</div>
					</div>
					<div class="mb-3 row">
						<form:label path="password" for="exampleInputPassword2"
							class="col-sm-3 col-form-label text-left">Contraseña:</form:label>
						<div class="col-sm-9">
							<form:errors class="text-danger" path="password" />
							<form:input path="password" type="password" class="form-control"
								id="exampleInputPassword2" />
						</div>
					</div>
					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-secondary">Iniciar
							Sesión</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>