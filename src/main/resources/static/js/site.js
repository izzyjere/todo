//ERROR ALERTS
$(document).ready(function () {
    const urlParams = new URLSearchParams(window.location.search)
    const error = urlParams.get('error')
    if (error != undefined) {
        var message = error.length > 0 ? error : "Incorrect credentials."
        showErrorMessage('Error : ' + message)
    }
})
//FORM VALIDATION
$(document).ready(function () {
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
    const signUpForm = $("#signupForm")
    const formData = signUpForm.serializeArray()  
    signUpForm.submit(function (event) {
      event.preventDefault()
  
      // Convert the form data to a JSON object
      const postData = {}
      $.each(formData, function (key, value) {
        postData[value.name] = value.value
      })
  
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
//DELETE TODO
$(document).ready(function(){
    const deleteButton = $("#deleteTodo")
    const formData = deleteButton.serializeArray()
    deleteButton.submit(function(event){
        event.preventDefault()
        const confirmed = confirm("Are you sure you want to delete this todo?")
        if(!confirmed) {
            return
        }
        const postData = {}
        $.each(formData, function (key, value) {
          formData[value.name] = value.value
        })
        $.ajax({
            url: "/delete-todo",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            dataType: "json",
            error: function (error) {
              alert(
                "An error has occurred while trying to delete."
              )
              console.error(error)
            },
          })

    })
})
//COMPLETE TODO
$(document).ready(function(){
    const completeButton = $("#completeTodo")
    const formData = completeButton.serializeArray()
    completeButton.submit(function(event){
        event.preventDefault()
        const confirmed = confirm("Are you sure you want to mark this todo as complete?")
        if(!confirmed) {
            return
        }
        const postData = {}
        $.each(formData, function (key, value) {
          formData[value.name] = value.value
        })
        $.ajax({
            url: "/complete-todo",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(postData),
            dataType: "json",
            error: function (error) {
              alert(
                "An error has occurred. Try again"
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