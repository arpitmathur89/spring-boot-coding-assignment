function addRecord(){
    event.preventDefault();
    $("#table_details").hide();
    var methodURL = "/create";
    var id = document.getElementById("id").value;
    var name = document.getElementById('name').value;
    var username = document.getElementById('username').value;
    if(id.length == 0 || name.length == 0 || username.length == 0){
        window.alert("Please pass a value");
        return;
    }
    var jsonObj = {"id" :id, "name" : name, "username" : username};
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : methodURL,
        data : JSON.stringify(jsonObj),
        contentType: "application/json; charset=utf-8",
        success : function(data1) {
            if(data1 == "inserted"){
                window.alert("Added " + name);
            }else{
                window.alert("Unable to add " + name);
            }
        },
        error : function(e) {
            console.log("Some Error occured");
        }
    });
    $("#id").val("");
    $("#name").val("");
    $("#username").val("");
    $("#table_details").hide();


}

function getRecords(){

    var methodURL = "/read";
    $("#topviewedtable").children().remove();
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : methodURL,
        success : function(data) {
            var tabdata = JSON.parse(JSON.stringify(data));
            $("#table_details").show();
            var tableId = document.getElementById("topviewedtable");
            $(function() {
                $.each(tabdata, function(i, item) {
                    var $tr = $('<tr>').append(
                        $('<td>').text(item.id),
                        $('<td>').text(item.name),
                        $('<td>').text(item.username)
                    ).appendTo(tableId);

                });
            });
        },
        error : function(e) {
            console.log(e);
        }
    });
}

function deleteRecord(){
    $("#table_details").hide();
    var methodURL = "/delete";
    var temp = $("#del_emp_id").val();
    if(temp.length == 0){
        window.alert("Please pass a valid value");
        return;
    }
    $.ajax({
        type : "GET",
        contentType : "application/json",
        url : methodURL,
        data : "id=" + $("#del_emp_id").val(),
        success : function(data1) {
            if(data1 == "deleted"){
                window.alert("Record Deleted");
            }else{
                window.alert("Unable to delete!! Id doesn't exist");
            }
        },
        error : function(e) {
            console.log(e);
        }
    });
    $("#del_emp_id").val("");
    $("#table_details").hide();
}