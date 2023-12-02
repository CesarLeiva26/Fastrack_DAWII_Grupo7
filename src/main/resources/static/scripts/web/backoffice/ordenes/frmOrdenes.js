function listarCboVehiculos(idVehiculo) {
    $.ajax({
        type: "GET",
        url: "/vehiculo/listarVehiculos",
        dataType: "json",
        success: function (resultado) {
            $("#cboVehiculo").empty();
            $.each(resultado, function (index, value) {
                $("#cboVehiculo").append(
                    `<option value="${value.idvehiculo}">${value.placa}</option>`
                );
            });
            if (idVehiculo > 0) {
                $("#cboVehiculo").val(idVehiculo);
            }
        }
    });
}

function listarCboEstadosOrden(idEstadoOrden) {
    $.ajax({
        type: "GET",
        url: "/estadoorden/listaEstado",
        dataType: "json",
        success: function (resultado) {
            $("#cboEstadoOrden").empty();
            $.each(resultado, function (index, value) {
                $("#cboEstadoOrden").append(
                    `<option value="${value.idestadoorden}">${value.nombreestado}</option>`
                );
            });
            if (idEstadoOrden > 0) {
                $("#cboEstadoOrden").val(idEstadoOrden);
            }
        }
    });
}

function listarCboClientes(idCliente) {
    $.ajax({
        type: "GET",
        url: "/cliente/listarcliente",
        dataType: "json",
        success: function (resultado) {
            $("#cboCliente").empty();
            $.each(resultado, function (index, value) {
                $("#cboCliente").append(
                    `<option value="${value.idcliente}">${value.nombre}</option>`
                );
            });
            if (idCliente > 0) {
                $("#cboCliente").val(idCliente);
            }
        }
    });
}
$(document).on("click", "#btnagregarorden", function () {
    $("#cboCliente").empty();
    listarCboClientes(0);
    $("#txtfechacreacion").val("");
    $("#cboVehiculo").empty();
    listarCboVehiculos(0);
    $("#txtquienrecepciona").val("");
    $("#txttrack").val("");
    $("#cboEstadoOrden").empty();
    listarCboEstadosOrden(0);
    $("#hddidorden").val("0");
    $("#modalNuevoOrden").modal("show");
});

$(document).on("click", "#btnguardarorden", function () {
    if (validarCamposOrden()) {
        $.ajax({
            type: "POST",
            url: "/orden/guardarorden",
            contentType: "application/json",
            data: JSON.stringify({
                idCliente: $("#cboCliente").val(),
                fechacreacion: $("#txtfechacreacion").val(),
                idTipoEstadoOrden: $("#cboEstadoOrden").val(),
                quienRecepciona: $("#txtquienrecepciona").val(),
                track: $("#txttrack").val(),
                idVehiculo: $("#cboVehiculo").val(),
            }),
            success: function (resultado) {
                alert(resultado.mensaje);
            },
            error: function (error) {
                alert("Error al guardar la orden: " + error.responseJSON.mensaje);
            }
        });

        $("#modalNuevoOrden").modal("hide");

        setTimeout(function () {
            location.reload();
        }, 2000);
    }
});

function validarCamposOrden() {
    $(".error-message").remove();
    var isValid = true;


    if ($("#txtfechacreacion").val() === "") {
        $("#txtfechacreacion").after('<div class="error-message text-danger">La Fecha de Creación es obligatoria</div>');
        isValid = false;
    }

    if ($("#txtquienrecepciona").val() === "") {
        $("#txtquienrecepciona").after('<div class="error-message text-danger">El campo Quién Recepciona es obligatorio</div>');
        isValid = false;
    }

    if ($("#txttrack").val() === "") {
        $("#txttrack").after('<div class="error-message text-danger">El campo Track es obligatorio</div>');
        isValid = false;
    }
    return isValid;
}