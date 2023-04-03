
//DELETE TODO
$(document).ready(function () {
  const deleteButton = document.getElementById("deleteTodo")
  const formData = new FormData(deleteButton);
  deleteButton.submit(function (event) {
    event.preventDefault()
    const confirmed = confirm("Are you sure you want to delete this todo?")
    if (!confirmed) {
      return
    }
    const postData = {}
    formData.forEach((value, key) => {
      postData[key] = value;
    });
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
$(document).ready(function () {
  const completeButton = document.getElementById("completeTodo")
  const formData = new FormData(completeButton);
  completeButton.submit(function (event) {
    event.preventDefault()
    const confirmed = confirm("Are you sure you want to mark this todo as complete?")
    if (!confirmed) {
      return
    }
    const postData = {}
    formData.forEach((value, key) => {
      postData[key] = value;
    });
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
