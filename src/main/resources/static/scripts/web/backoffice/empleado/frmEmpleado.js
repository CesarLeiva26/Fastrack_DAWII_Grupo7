$(document).on("click", "#btnagregar", function(){
    $("#txtnombre").val("");
    $("#cbotipoempleado").empty();
    listarCboTipoEmpleados(0);
    $("#txtnumerotelefono").val("");
    $("#txtcorreoelectronico").val("");
    $("#hddcodempleado").val("0");
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnombre").val($(this).attr("data-empleadonombre"));
    $("#txtnumerotelefono").val($(this).attr("data-empleadotelefono"));
    $("#txtcorreoelectronico").val($(this).attr("data-empleadocorreo"));
    $("#hddcodempleado").val($(this).attr("data-empleadoid"));
    $("#cbotipoempleado").empty();
    listarCboTipoEmpleados($(this).attr("data-empleadotipo"));
    $("#modalNuevo").modal("show");
});

$(document).on("click", "#btnguardar", function(){
    if(validarCampos()){
        $.ajax({
            type: "POST",
            url: "/empleado/guardar",
            contentType: "application/json",
            data: JSON.stringify({
                idempleado: $("#hddcodempleado").val(),
                nombre: $("#txtnombre").val(),
                numerotelefono: $("#txtnumerotelefono").val(),
                correoelectronico: $("#txtcorreoelectronico").val(),
                idtipoempleado: $("#cbotipoempleado").val()
            }),
            success: function(resultado){
                if(resultado.respuesta){
                    listarEmpleados();
                }
                alert(resultado.mensaje);
            }
        });
        $("#modalNuevo").modal("hide");
    }
});

function validarCampos() {
    $(".error-message").remove();
    var isValid = true;

    if ($("#txtnombre").val() === "") {
        $("#txtnombre").after('<div class="error-message text-warning">El Nombre y Apellido del Empleado es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtnumerotelefono").val() === "") {
        $("#txtnumerotelefono").after('<div class="error-message text-warning">El Número es obligatorio</div>');
        isValid = false;
    }

    if ($("#txtcorreoelectronico").val() === "") {
        $("#txtcorreoelectronico").after('<div class="error-message text-warning">El Correoelectronico es obligatorio</div>');
        isValid = false;
    }

    return isValid;
}

function listarCboTipoEmpleados(idtipoempleado){
    $.ajax({
        type: "GET",
        url: "/tipoempleado/listar",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cbotipoempleado").append(
                    `<option value="${value.idtipoempleado}">${value.nombretipoempleado}</option>`
                );
            });
            if(idtipoempleado > 0){
                $("#cbotipoempleado").val(idtipoempleado);
            }
        }
    });
}

function listarEmpleados(){
    $.ajax({
        type: "GET",
        url: "/empleado/listar",
        dataType: "json",
        success: function(resultado){
            $("#tblempleado > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblempleado > tbody").append("<tr>"+
                    "<td>"+value.idempleado+"</td>"+
                    "<td>"+value.nombre+"</td>"+
                    "<td>"+value.tiposempleados.nombretipoempleado+"</td>"+
                    "<td>"+value.numerotelefono+"</td>"+
                    "<td>"+value.correoelectronico+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-dark btnactualizar'"+
                        "data-empleadoid='"+value.idempleado+"'"+
                        "data-empleadonombre='"+value.nombre+"'"+
                        "data-empleadotipo='"+value.tiposempleados.idtipoempleado+"'"+
                        "data-empleadotelefono='"+value.numerotelefono+"'"+
                        "data-empleadocorreo='"+value.correoelectronico+"'"+
                        "><i class='fas fa-edit'></i></button></td>"+
                        "<td>"+
                        "<button type='button' class='btn btn-danger btneliminarempleado'"+
                        "data-empleadoid='"+value.idempleado+"'"+
                        "data-empleadonombre='"+value.nombre+"'"+
                        "><i class='fas fa-trash-alt'></i></button>"+
                        "</td></tr>");
            });
        }
    });
}

$(document).on("click", "#btneliminarempleado", function(){
    $.ajax({
        type: "DELETE",
        contentType: 'application/json',
        url: "/empleado/eliminarEmpleado",
        data: JSON.stringify({
            idempleado: $("#hddideliminarempleado").val()
        }),
        success: function(resultado){
            alert(resultado.mensaje);
            listarEmpleados();
        },
        error: function(xhr, status, error) {
            console.error("Error al eliminar empleado:", error);
        }
    });
    $("#modalEliminarEmpleado").modal("hide");
});

$(document).on("click", ".btneliminarempleado", function(){
    $("#hddideliminarempleado").val($(this).attr("data-empleadoid"));
    $("#mensajeeliminar").text("¿Seguro de eliminar el " + $(this).attr("data-empleadoid") + "?");
    $("#modalEliminarEmpleado").modal("show");
});