//FORM VALIDATION
$(document).ready(function () {

    $.validator.addMethod("passwordMatch", function (value, element) {
        return value == $('#password').val()
    }, "Passwords do not match.")
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            firstname: {
                required: true
            },
            lastname: {
                required: true
            },
            password: {
                required: true
            },
            confirmPassword: {
                required: true,
                passwordMatch: true
            }
        },
        messages: {
            username: {
                required: "Please enter your username"
            },
            firstname: {
                required: "Please enter your first name"
            },
            lastname: {
                required: "Please enter your last name"
            },
            password: {
                required: "Please enter your password"
            },
            confirmPassword: {
                required: "Please confirm password",
                passwordMatch: "Passwords do not match"
            }
        }
    })
})
//REGISTER FORM SUBMISSION
$(document).ready(function () {
    const signUpForm = document.getElementById("signupForm")
    const formData = new FormData(signUpForm);
    signUpForm.submit(function (event) {
        event.preventDefault()

        // Convert the form data to a JSON object
        const postData = {}
        formData.forEach((value, key) => {
            postData[key] = value;
        });
        debugger
        $.ajax({
            url: "/signup",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            dataType: "json",
            success: function (data) {
                // handle the JSON response here
                if (data.succeeded) {
                    window.location.href = "/"
                    signUpForm.trigger("reset")
                } else {
                    showErrorMessage(data.message)
                }
            },
            error: function (error) {
                showErrorMessage(
                    "An error has occurred while trying to create your account."
                )
                console.error(error)
            },
        })
    })
})
function showErrorMessage(message) {
    $('#e-message').removeClass("d-none")
    $('#e-message-content').text(message)
}