<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>TekDays - The Community is the Conference!</title>
</head>

<body>

<script>
    function resp() {
        $.ajax({
            url: "http://localhost:8080/TekDays_Rest/tekUser/ajaxGet?format=json",
            type: "GET",
            contentType: "text/json",
            success: function (result) {
                console.log(result)
            },
            error: function (request, status, error) {
                alert(request.responseText);
            }
        });
    }

    resp()
</script>
</body>
</html>