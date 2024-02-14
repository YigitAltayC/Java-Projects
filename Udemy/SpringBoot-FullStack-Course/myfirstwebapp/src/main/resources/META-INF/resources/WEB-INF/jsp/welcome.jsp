<%@ include file="common/header.jspf"%>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
        }

        .welcome-message {
            width: 300px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>

<%@ include file="common/headToBodyTag.jspf"%>

    <%@ include file="common/navigation.jspf"%>

    <div class="welcome-message">
        <h2>Welcome to our page ${username} <span id="namePlaceholder"></span></h2>
        <div>Click this to: <a href="list-todos">View Todo-List</a> </div>
    </div>

<%@ include file="common/footer.jspf"%>