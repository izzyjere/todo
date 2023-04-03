$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search)
    const error = urlParams.get('error')
    if (error != undefined) {
        var message = error.length > 0 ? error : "Incorrect credentials."
        showErrorMessage('Error : ' + message)
    }
    $("#loginForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: "Please enter your username"
            },
            password: {
                required: "Please enter a password"
            }
        }
    })
})
function showErrorMessage(message) {
    $('#e-message').removeClass("d-none")
    $('#e-message-content').text(message)
}