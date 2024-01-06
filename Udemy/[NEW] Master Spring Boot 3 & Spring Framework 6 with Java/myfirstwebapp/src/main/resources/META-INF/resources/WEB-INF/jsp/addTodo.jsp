<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet" >

    <title>Add New Task</title>

    <style>
        body {
            background-color: #f4f4f4;
            text-align: center;
            margin: 50px;
        }

        h2 {
            color: #333;
        }

        form {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            background-color: #4caf50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <h2>Todo Details</h2>

    <form:form method="post" modelAttribute = "todo">

        <label for="id" type="hidden" value="ID:">
           <form:input type="hidden" path="id" required="required"/>
        </label>

        <label for="description">Description:
            <form:input type="text" path="description" required="required"/>
        </label>


        <label for="targetDate">Target Date:
            <form:input type="date" path="targetDate" required="required"/>
        </label>

        <label for="done" type="hidden" value="Is Done?">
             <form:input type="hidden" path="done" required="required"/>
        </label>

        <input type="submit" class="btn btn-success" value="Create Todo"></input>
    </form:form>

     <script src="webjars/bootstrap/5.1.3/js/bootstrap.min.js"></script>
     <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
</body>
</html>