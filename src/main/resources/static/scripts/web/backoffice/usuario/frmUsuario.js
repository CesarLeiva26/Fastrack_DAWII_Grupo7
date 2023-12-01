<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:replace="~{layout::head}">
    <meta charset="UTF-8">
    <title>Fastrack - Mantenimiento de Usuarios</title>
    <link rel="stylesheet" type="text/css" th:href="@{/styles/css/usuario.css}">
</head>
<body>
<script th:src="@{/scripts/web/backoffice/usuario/frmUsuario.js}" type="text/javascript"></script>
<nav th:replace="~{layout::menu}"></nav>
<div class="card">
    <div class="card-body">
        <button id="btnagregar" type="button" class="btn btn-danger fs-"><i class="fas fa-plus"></i> Agregar Usuario</button>
        <hr />
        <div id="mensaje"></div>
        <table id="tblusuario" class="table table-striped table-bordered text-center">
            <thead>
            <tr>
                <th scope="col">ID Usuario</th>
                <th scope="col">Nombre de Usuario</th>
                <th scope="col">Contraseña</th>
                <th scope="col">Empleado</th>
                <th scope="col">Actualizar</th>
            </tr>
            </thead>
            <tbody>
            <tr th:each="usuario : ${listaUsuarios}">
                <td th:text="${usuario.idusuario}"></td>
                <td th:text="${usuario.nombreusuario}"></td>
                <td th:text="${usuario.contra}"></td>
                <td th:text="${usuario.empleado.nombre}"></td>
                <td>
                    <button type="button" class="btn btn-info btnactualizar"
                            th:data-usuarioid="${usuario.idusuario}"
                            th:data-username="${usuario.nombreusuario}"
                            th:data-contrausuario="${usuario.contra}"
                            th:data-idempleado="${usuario.empleado.nombre}">
                        <i class="fas fa-edit"></i>
                    </button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="modalNuevo" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content bg-dark text-white">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Usuario - Fastrack</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div class="mb-3">
                    <label for="txtnombreusuario" class="form-label">Nombre de Usuario</label>
                    <input type="text" class="form-control" id="txtnombreusuario">
                </div>
                <div class="mb-3">
                    <label for="txtcontra" class="form-label">Contraseña</label>
                    <input type="email" class="form-control" id="txtcontra">
                </div>
                <div class="mb-3">
                    <label for="cboempleado" class="form-label">Empleado</label>
                    <select id="cboempleado" class="form-control"></select>
                </div>
                <input type="hidden" id="hddcodusuario" value="0">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"><i class="fas fa-times"></i> Cancelar</button>
                <button type="button" id="btnguardar" class="btn btn-primary"><i class="fas fa-save"></i> Guardar</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>