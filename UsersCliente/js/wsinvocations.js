function getHello(){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/",
        success: function(data){
            $("#resUser").html(data);      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}

function postUser(formulario) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/users/",
        contentType: "application/json",
        dataType: "text",
        async: false,
        data: JSON.stringify({
            "firstname": formulario.nombre.value,
            "lastname": formulario.apellidos.value,
            "birthdate": formulario.fecha.value,
            "DNI": formulario.dni.value,
            "telephone": formulario.telefono.value,
            "email": formulario.email.value,
            "inscription type": formulario.inscripciones.value,
            "start date": formulario.fecha_ini.value,
            "finish date": formulario.fecha_fin.value,

            
        }),
        success: function(data) {
            $("#resUser").html();
        },
        error: function(res) {
            alert("ERROR " + res.statusText);
        }
    });
}

function getUsers()
{
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/users/",
        dataType: "JSON",
        async: false,        
        success: function(data){
            $("#resUser").html(formatJSON(data));      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}

function getUser(key, put = false){
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/users/" + key,
        success: function(data, choose){
        if (!put)    
            $("#resUser").html(formatJSON(data));
        else $("#putUserDiv").html(html_putUser(data)) ;      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}

function putUser(formulario, dni){
    $.ajax({
        type: "PUT",        
        url: "http://localhost:8080/users/" + dni,
        async: false,
        data: {
            "firstname": formulario.nombre.value,
            "lastname": formulario.apellidos.value,
            "birthdate": formulario.fecha.value,
            "DNI": formulario.dni.value,
            "telephone": formulario.telefono.value,
            "email": formulario.email.value,
            "inscription type": formulario.inscripciones.value,
            "start date": formulario.fecha_ini.value,
            "finish date": formulario.fecha_fin.value,
        },
        success: function(data){
            $("#resUser").html(JSON.stringify(data));      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}

function deleteUsers()
{
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/users/",
        dataType: "text",
        success: function(data){
            $("#resUser").html(data);      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}

function deleteUser(id)
{
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/users/" + id,
        dataType: "text",
        success: function(data){
            $("#resUser").html(data);      },
        error:function(res){
            alert("ERROR: "+ res.statusText);  }
    });
}