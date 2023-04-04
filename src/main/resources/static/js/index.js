$(document).ready(function(){
 $(".dropdown-toggle").dropdown()
})
//DELETE TODO
function deleteTodo(todoId){

  const confirmed = confirm("Are you sure you want to delete this todo?")
    if (!confirmed) {
      return
    }
    const postData = { todoId : parseInt(todoId) }
    
    $.ajax({
      url: `/todo/delete?id=${todoId}`,
      type: "DELETE",
      success: function(data){
       window.location.href="/"
      },
      error: function (error) {
        alert(
          "An error has occurred while trying to delete."
        )
        console.error(error)
      },
    })
}

//COMPLETE TODO
function complete(todoId){
    const confirmed = confirm("Are you sure you want to mark this todo as complete?")
    if (!confirmed) {
      return
    }
    $.ajax({
      url: `/todo/complete?id=${todoId}`,
      type: "PUT",
      success: function(data){
        window.location.href="/"
      },
      error: function (error) {
        alert(
          "An error has occurred. Try again"
        )
        console.error(error)
      },
    })

}


//ADD EDIT TODO
$(document).ready(function() {
  $('#todoModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)// Button that triggered the modal
    var todoId = button.data('todo-id') // Extract info from data-* attributes
    var todoText = button.data('todo-text')
    var modal = $(this)
    if (todoId) {
      modal.find('.modal-title').text('Edit Todo')
      modal.find('#todoId').val(todoId)
      modal.find('#todoText').val(todoText)
    } else {
      modal.find('.modal-title').text('Add Todo')
      modal.find('#todoId').val(0)
      modal.find('#todoText').val('')
    }
  });

  $('#saveTodo').click(function() {
    var todoId = $('#todoId').val()
    var todoText = $('#todoText').val()
    // AJAX
    let postData = JSON.stringify({ details : todoText,  id : parseInt(todoId) })

        //POST to API
        $.ajax({
          url: "/todo",
          type: "POST",
          contentType: "application/json",
          data: postData,
          dataType: "json",
          error: function (error) {
            alert("An error has occurred while trying to save the todo.")
            console.error(JSON.stringify(error))
          }
    })
    $('#todoModal').modal('hide');
  });
});
