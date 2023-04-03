<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="zm.org.zra.todo.dtos.TodoDTO" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Todo</title>
    <link rel="stylesheet" href="lib/bootstrap/dist/css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/site.css" />
    <link rel="stylesheet" href="css/table.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
        integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>

<body>
    <header>
        <nav class="navbar navbar-dark bg-primary">
            <div class="container-fluid">
                <a class="navbar-brand text-white">Todo App</a>
                <form action="/logout" class="d-flex mt-2">
                    <button class="btn btn-outline-primary" type="submit">Logout</button>
                </form>
            </div>
        </nav>
    </header>
    <main>
        <section>
            <div class="container pt-4">
                <form action="/todo/new" method="post">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="details">What to do next?</label>
                            <input required type="text" class="form-control mb-3" id="details" name="details">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
            </div>
        </section>
        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h4 class="text-center mb-4">Hello ${userFullName}! Your Todo List.</h4>
                        <div class="table-wrap">
                            <form method="post">
                                <table class="table">
                                    <colgroup>
                                        <col width="5%" />
                                        <col width="30%" />
                                        <col width="10%" />
                                        <col width="17%" />
                                        <col width="17%" />
                                        <col width="25%" />
                                    </colgroup>
                                    <thead class="thead-primary">
                                        <tr>
                                            <th>ID</th>
                                            <th>Details</th>
                                            <th>Status</th>
                                            <th>Created On</th>
                                            <th>Completed</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                            <core:forEach var="todo" items="${todoList}">
                                                <tr>
                                                    <td>${todo.count}</td>
                                                    <td>${todo.details}</td>
                                                    <td>${todo.status}</td>
                                                    <td><fmt:formatDate value="${todo.createdOn}" pattern="dd MMM yyyy H:mm" /></td>
                                                    <td>
                                                     <core:if test="${todo.completedOn != null}">
                                                       <fmt:formatDate value="${todo.completedOn}" pattern="dd MMM yyyy H:mm" />
                                                       <core:else>
                                                       <p>Not yet.</p>
                                                     </core:if>
                                                    ${todo.completedOn != null ?
                                                        todo.completedOn : "N/A"}</td>
                                                    <td class="d-flex align-items-start">
                                                    <form  class="mr-2" id="deleteTodo">
                                                          <input type="hidden" name="todoId" value="${todo.id}">
                                                          <button title="Delete this todo."  class="btn btn-danger" type="submit">Delete</button>
                                                    </form>
                                                    <core:if test="${todo.status != 'Completed'}">
                                                       <form id="completeTodo">
                                                          <input type="hidden" name="todoId" value="${todo.id}">
                                                          <button title="Mark this todo as complete" class="btn btn-success" type="submit">Complete</button>
                                                       </form>
                                                     </core:if>
                                                    </td>
                                                </tr>

                                            </core:forEach>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <script src="lib/jquery/dist/jquery.min.js"></script>
    <script src="lib/jquery/dist/jquery.validate.min.js"></script>
    <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/index.js"></script>
</body>

</html>