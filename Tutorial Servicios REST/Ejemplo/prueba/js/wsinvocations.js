function getMobile(mobileId) {
    var myUrl = "http://localhost:8080/mobiles/" + mobileId;
    $.ajax({
    type: "GET",
    dataType: "json",
    url: myUrl,
    success: function(data) {
    alert("Hola");
    alert(data);
    $("#resGetHello").html(JSON.stringify(data));
    },
    error: function(res) {
    alert("ERROR " + res.statusText);

    
}
});
}
    