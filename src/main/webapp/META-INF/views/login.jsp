<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <st </head>

<body>
    <div class="container center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="text-primary">Login</h4>
                </div>
                <div class="card-body">
                    <form action="/signin" id="loginForm" method="POST">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" required class="form-control" id="username" name="username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" required class="form-control" id="password" name="password">
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                        <a href="register" title="Don't have an account yet? Register."
                            class="btn btn-success mx-4">Register</a>
                    </form>
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
            <script src="lib/jquery/dist/jquery.min.js"></script>
            <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
            <script src="js/site.js" asp-append-version="true"></script>
            <script>
                $(document).ready(function () {
                    const urlParams = new URLSearchParams(window.location.search);
                    const error = urlParams.get('error');
                    if (error != null) {
                        showMessage('Error : ' + error);
                    }
                });
                function showMessage(message) {
                    $('#e-message').removeClass("d-none");
                    $('#e-message-content').text(message);
                }
            </script>
</body>

</html>