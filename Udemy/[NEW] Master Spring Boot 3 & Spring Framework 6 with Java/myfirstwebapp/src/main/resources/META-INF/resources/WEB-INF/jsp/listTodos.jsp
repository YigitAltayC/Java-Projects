<%@ include file="common/header.jspf"%>

    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f4;
        }

        h2 {
            color: #333;
        }

        table {
            width: 80%;
            margin: 20px 0px;
            border-collapse: collapse;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4caf50;
            color: white;
        }

        tr:hover {
            background-color: #f5f5f5;
        }
    </style>

<%@ include file="common/headToBodyTag.jspf"%>


    <%@ include file="common/navigation.jspf"%>

    <h2>${username} - Todo List</h2>

    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Done</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <!-- Assuming 'todos' is a List<Todo> attribute in your servlet or controller -->
            <c:forEach var="todo" items="${todos}">
                <tr>
                    <td>${todo.id}</td>
                    <td>${todo.username}</td>
                    <td>${todo.description}</td>
                    <td>${todo.targetDate}</td>
                    <td>${todo.done}</td>
                    <td> <a href ="delete-todo?id=${todo.id}" class="btn btn-warning">Remove</a> </td>
                    <td> <a href ="update-todo?id=${todo.id}" class="btn btn-success">Edit</a> </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a href ="add-todo" class="btn btn-success">Add New Task</a>

<%@ include file="common/footer.jspf"%>

