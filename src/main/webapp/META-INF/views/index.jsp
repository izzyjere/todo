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
    <link rel="stylesheet" href="bootstrap/bootstrap.min.css"/>
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
                  <div class="mb-3">
                   <div class="form-check form-switch ml-4 mt-4">
                     <input class="form-check-input" type="checkbox" id="todoStatus">
                     <label class="form-check-label" for="todoStatus">Completed</label>
                   </div>
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
                                <table class="table table-striped">
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
                                            <th>#</th>
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
                                                   <td><fmt:formatDate value="${todo.createdOn}" pattern="dd/MM/yyyy H:mm" /></td>
                                                   <td>
                                                       <core:if test="${todo.completedOn != null}">
                                                           <fmt:formatDate value="${todo.completedOn}" pattern="dd/MM/yyyy H:mm" />
                                                       </core:if>
                                                       ${todo.completedOn != null ? "" : "N/A"}
                                                   </td>
                                                   <td class="d-flex align-items-start justify-content-start">
                                                        <button title="Edit this todo" class="btn btn-outline-warning mx-2" data-bs-toggle="modal" data-bs-target="#todoModal" data-todo-id="${todo.id}" data-todo-status="${todo.status}" data-todo-text="${todo.details}" type="button"><i class="mx-2 fas fa-pen"></i></button>
                                                        <button title="Delete this todo" onclick="deleteTodo(${todo.id})" class="btn btn-outline-danger mx-2"><i class="mx-2 fas fa-trash"></i></button>
                                                         <core:if test="${todo.status != 'Completed'}">
                                                              <button title="Mark this todo as complete" class="btn btn-outline-success mx-2" onclick="complete(${todo.id})"><i class="mx-2 fas fa-clipboard-check"></i></button>
                                                         </core:if>
                                                   </td>
                                               </tr>
                                           </core:forEach>
                                    </tbody>
                                </table>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <script src="jquery/dist/jquery.min.js"></script>
    <script src="jquery/dist/jquery.validate.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    <script src="js/index.js"></script>
</body>

</html>