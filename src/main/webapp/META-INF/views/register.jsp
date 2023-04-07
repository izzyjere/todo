<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo</title>
    <link rel="icon" href="todo.png"/>
    <link rel="stylesheet" href="bootstrap/bootstrap.min.css"/>
    <link rel="stylesheet" href="css/site.css">
    <link rel="stylesheet" href="css/table.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css"
        integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
</head>

<body>
    <div class="container center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="text-primary">Create a new account</h4>
                </div>
                <div class="card-body">
                    <form id="signupForm">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text"  class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="firstname">First Name:</label>
                            <input type="text"  class="form-control" id="firstname" name="firstname">
                        </div>
                        <div class="form-group">
                            <label for="lastname">Last Name:</label>
                            <input type="text"  class="form-control" id="lastname" name="lastname">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password"  class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="confirmPassword">Confirm Password:</label>
                            <input type="password"  class="form-control" id="confirmPassword"
                                name="confirmPassword">
                        </div>
                        <button title="Create your account." type="submit" class="btn btn-primary">Sign Up</button>
                        <a title="Already have an account? Login." href="/login" class="btn btn-success">Login</a>
                    </form>

                </div>
            </div>
            <div id="e-message" class="mt-4 d-none pa-2">
                <div class="alert alert-danger">
                    <div class="d-flex align-items-center">
                        <div>
                            <i id="e-icon" class="fas fa-exclamation-triangle"></i>
                        </div>
                        <div class="ml-4 mt-3">
                            <p id="e-message-content"></p>
                        </div>
                    </div>
                </div>
                <div>
                </div>
            </div>
        </div>
    </div>
    <script src="jquery/dist/jquery.min.js"></script>
    <script src="jquery/dist/jquery.validate.min.js"></script>
    <script src="bootstrap/bootstrap.bundle.min.js"></script>
    <script src="js/register.js"></script>
</body>
</html>