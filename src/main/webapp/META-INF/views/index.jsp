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
    <link rel="icon" href="todo.png"/>
    <link rel="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/site.css" />
    <link rel="stylesheet" href="css/table.css" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
        integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>

<body>
    <header>
        <nav class="navbar navbar-dark bg-light shadow-sm rounded">
            <div class="container-fluid">
                <div class="d-flex align-items-start justify-items-between">
                   <img width="42" height="42" src="todo.png"/>
                   <h6 class="navbar-brand text-primary ml-2">Todo App</h6>
                </div>

                <form action="/logout" class="d-flex mt-2">
                    <button class="btn btn-primary" type="submit">Logout</button>
                </form>
            </div>
        </nav>
    </header>
    <main>
        <section>
          <div class="modal fade" id="todoModal" tabindex="-1" aria-labelledby="todoModalLabel" aria-hidden="true">
            <div class="modal-dialog  modal-dialog-centered">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="todoModalLabel">Add Todo</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <input type="hidden" id="todoId" value="">
                  <div class="mb-3">
                    <label for="todoText" class="form-label">Task</label>
                    <input type="text" class="form-control" id="todoText">
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" id="saveTodo">Save</button>
                </div>
              </div>
            </div>
          </div>
        </section>
        <section class="ftco-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h5 class="text-center mb-4">Hello ${userFullName}!</h5>
                        <button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#todoModal"><i class="fas fa-plus mr-2"></i>Add Todo</button>
                        <div class="table-wrap">
                            <form method="post">
                                <table class="table">
                                    <colgroup>
                                        <col width="5%" />
                                        <col width="30%" />
                                        <col width="10%" />
                                        <col width="20%" />
                                        <col width="20%" />
                                        <col width="15%" />
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
                                                   <td>
                                                      <core:if test="${todo.status eq 'Completed'}">
                                                        <span class="badge badge-pill badge-success badge-lg">Done</span>
                                                      </core:if>
                                                      <core:if test="${todo.status eq 'Pending'}">
                                                        <span class="badge badge-pill badge-secondary badge-lg">${todo.status}</span>
                                                      </core:if>

                                                   </td>
                                                   <td><fmt:formatDate value="${todo.createdOn}" pattern="dd/mm/yyyy H:mm" /></td>
                                                   <td>
                                                       <core:if test="${todo.completedOn != null}">
                                                           <fmt:formatDate value="${todo.completedOn}" pattern="dd/mm/yyyy H:mm" />
                                                       </core:if>
                                                       ${todo.completedOn != null ? "" : "N/A"}
                                                   </td>
                                                   <td class="d-flex align-items-start justify-content-between">
                                                      <div class="dropdown">
                                                         <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"  aria-haspopup="true" id="dropdownMenuButton" aria-expanded="true"><i class="mx-2 fas fa-pen"></i>Edit</button>
                                                         <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                                            <core:if test="${todo.status != 'Completed'}">
                                                                 <button title="Mark this todo as complete" class="dropdown-item btn btn-success" onclick="complete(${todo.id})"><i class="mr-2 fas fa-clipboard-check"></i> Done</button>
                                                            </core:if>
                                                           <button class="dropdown-item dropdown-item btn btn-warning" type="button">Edit</button>
                                                         </div>
                                                      </div>
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
    <script src="jquery/dist/jquery.min.js"></script>
    <script src="jquery/dist/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/index.js"></script>
</body>

</html>