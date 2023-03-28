<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>TekDays - The Community is the Conference!</title>
    <asset:stylesheet src="application.css"/>
    <asset:javascript src="application.js"/>
    <g:javascript>
        function resp(params) {
            $.ajax({
                url: "http://localhost:8080/TekDays_Rest/tekUser/getById?id=${params.id}&format=json",
                type: "GET",
                contentType: "text/json",
                data: {
                    fullName: params.fullName
                },
                success: function (data) {
                    console.log(data)
                },
                error: function (request, status, error) {
                    alert(request.responseText);
                }
            });
        }
        resp()
    </g:javascript>
</head>

<body>
</body>
</html>