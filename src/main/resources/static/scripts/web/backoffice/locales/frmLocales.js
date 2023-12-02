$(document).on("click", "#btnagregar", function() {
    $("#txtnombrelocal").val("");
    $("#txtdireccionlocal").val("");
    $("#txtciudadlocal").val("");
    $("#txtcodigopostal").val("");
    $("#txtnumerotelefono").val("");
    $("#hddcodlocal").val("0");
    $("#cboempleado").empty();
    listarCboEmpleados(0);
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function() {
    $("#txtnombrelocal").val($(this).attr("data-localnombre"));
    $("#txtdireccionlocal").val($(this).attr("data-localdireccion"));
    $("#txtciudadlocal").val($(this).attr("data-localciudad"));
    $("#txtcodigopostal").val($(this).attr("data-localcodigopostal"));
    $("#txtnumerotelefono").val($(this).attr("data-localtelefonolocal"));
    $("#hddcodlocal").val($(this).attr("data-localid"));
    $("#cboempleado").empty();
    listarCboEmpleados($(this).attr("data-localempleadoid"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function() {
    if (validarCamposLocales()) {
        $.ajax({
            type: "POST",
            url: "/locales/guardar",
            contentType: "application/json",
            data: JSON.stringify({
                idlocal: $("#hddcodlocal").val(),
                nombrelocal: $("#txtnombrelocal").val(),
                direccionlocal: $("#txtdireccionlocal").val(),
                ciudadlocal: $("#txtciudadlocal").val(),
                codigopostal: $("#txtcodigopostal").val(),
                telefonolocal: $("#txtnumerotelefono").val(),
                idempleado: $("#cboempleado").val()
            }),
            success: function(resultado) {
                if (resultado.respuesta) {
                    listarLocales();
                    location.reload();
                }
                alert(resultado.mensaje);
            }
        });
        $("#modalNuevo").modal("hide");
    }
});

function validarCamposLocales() {
    $(".error-message").remove();
    var isValid = true;

    if ($("#txtnombrelocal").val() === "") {
        $("#txtnombrelocal").after('<div class="error-message text-warning">El Nombre del Local es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtdireccionlocal").val() === "") {
        $("#txtdireccionlocal").after('<div class="error-message text-warning">La Dirección del Local es obligatoria</div>');
        isValid = false;
    }

    if ($("#txtciudadlocal").val() === "") {
        $("#txtciudadlocal").after('<div class="error-message text-warning">La Ciudad del Local es obligatoria</div>');
        isValid = false;
    }

    if ($("#txtcodigopostal").val() === "") {
        $("#txtcodigopostal").after('<div class="error-message text-warning">El Código Postal es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtnumerotelefono").val() === "") {
        $("#txtnumerotelefono").after('<div class="error-message text-warning">El Teléfono del Local es obligatorio</div>');
        isValid = false;
    }

    return isValid;
}


function listarCboEmpleados(idempleado) {
    $.ajax({
        type: "GET",
        url: "/empleado/listar",
        dataType: "json",
        success: function(resultado) {
            $.each(resultado, function(index, value) {
                $("#cboempleado").append(
                    `<option value="${value.idempleado}">${value.nombre}</option>`
                );
            });
            if (idempleado > 0) {
                $("#cboempleado").val(idempleado);
            }
        }
    });
}

function listarLocales() {
    $.ajax({
        type: "GET",
        url: "/local/listar",
        dataType: "json",
        success: function(resultado) {
            $("#tbllocales > tbody").html("");
            $.each(resultado, function(index, value) {
                $("#tbllocales > tbody").append("<tr>" +
                    "<td>" + value.idlocal + "</td>" +
                    "<td>" + value.nombrelocal + "</td>" +
                    "<td>" + value.direccionlocal + "</td>" +
                    "<td>" + value.ciudadlocal + "</td>" +
                    "<td>" + value.codigopostal + "</td>" +
                    "<td>" + value.telefonolocal + "</td>" +
                    "<td>" + value.empleado.idempleado + "</td>" +
                    "<td>" +
                    "<button type='button' class='btn btn-dark btnactualizar'" +
                    "data-localid='" + value.idlocal + "'" +
                    "data-localnombre='" + value.nombrelocal + "'" +
                    "data-localdireccion='" + value.direccionlocal + "'" +
                    "data-localciudad='" + value.ciudadlocal + "'" +
                    "data-localcodigopostal='" + value.codigopostal + "'" +
                    "data-localtelefonolocal='" + value.telefonolocal + "'" +
                    "data-localempleadoid='"+value.empleado.idempleado + "'" +
                    "><i class='fas fa-edit'></i></button></td>"+
                    "<td>"+
                    "<button type='button' class='btn btn-danger btneliminarlocales'"+
                    "data-localid='"+value.idlocal+"'"+
                    "data-localnombre='"+value.nombrelocal+"'"+
                    "><i class='fas fa-trash-alt'></i></button>"+
                    "</td></tr>");
            });
        }
    });
}


$(document).on("click", "#btneliminarlocales", function() {
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/locales/eliminarLocal",
        data: JSON.stringify({
            idlocal: $("#hddideliminarlocales").val()
        }),
        success: function(resultado) {
            alert(resultado.mensaje);
            listarLocales();
            location.reload();
        },
        error: function(xhr, status, error) {
            console.error("Error al eliminar local:", error);
        }
    });
    $("#modalEliminarLocales").modal("hide");
});


$(document).on("click", ".btneliminarlocales", function(){
   $("#hddideliminarlocales").val($(this).attr("data-localid"));
   $("#mensajeeliminar").text("¿Seguro de eliminar el " + $(this).attr("data-localid") + "?");
   $("#modalEliminarLocales").modal("show");
});
