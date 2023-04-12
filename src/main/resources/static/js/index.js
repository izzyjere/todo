//DELETE TODO
function deleteTodo(todoId) {
swal({
    title: "Delete todo",
    text: "Are you sure you want to delete this todo?",
    icon: "warning",
    buttons:{
    cancel: {
        text: "Cancel",
        value: null,
        visible: true,
        className: "",
        closeModal: true,
      },
      confirm: {
        text: "Yes",
        value: true,
        visible: true,
        className: "",
        closeModal: true
      }},
    dangerMode:true
  })
  .then((willDelete) => {
    if (willDelete) {
     $.ajax({
         url: `/todo/delete?id=${todoId}`,
         type: "DELETE",
         success: async function (data) {
           if (data.succeeded) {
             await swal("Deleted!", "Todo has been deleted!", "success");
             window.location.href = "/index"
           }
           else {
            await swal("Oops!", data.message, "error");
           }
         },
         error: async function (error) {
           await swal("Oops!", "Seems like we couldn't delete that task.", "error");
           console.error(error)
         },
       })
    }
  });

}

//COMPLETE TODO
function complete(todoId) {
  swal({
    title: "Complete todo",
    text: "Are you sure you want to mark this todo as complete?",
    icon: "info",
      buttons:{
    cancel: {
        text: "Cancel",
        value: null,
        visible: true,
        className: "",
        closeModal: true,
      },
      confirm: {
        text: "Complete",
        value: true,
        visible: true,
        className: "",
        closeModal: true
      }}
  })
  .then((mark) => {
    if (mark) {
     $.ajax({
         url: `/todo/complete?id=${todoId}`,
         type: "PUT",
         success: async function (data) {
           if (data.succeeded) {
             await swal("Completed!", "Todo has been marked as complete!", "success");
             window.location.href = "/index"
           }
           else {
             await swal("Oops!", data.message, "error");
           }
         },
         error: async function (error) {
           await swal("Oops!", "Seems like we couldn't complete that task.", "error");
           console.error(error)
         },
       })
    }
  });
}


//ADD EDIT TODO
$(document).ready(function () {
  $('#todoModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget)// Button that triggered the modal
    var todoId = button.data('todo-id') // Extract info from data-* attributes
    var todoText = button.data('todo-text')
    var todoStatus = button.data('todo-status')
    var modal = $(this)
    if (todoId) {
      modal.find('.modal-title').text('Edit Todo')
      modal.find('#todoId').val(todoId)
      modal.find('#todoText').val(todoText)
      modal.find('#todoStatus').val(todoStatus)
      modal.find('#todoStatus').prop('checked', todoStatus == "Completed")
    } else {
      modal.find('.modal-title').text('Add Todo')
      modal.find('#todoId').val(0)
      modal.find('#todoStatus').val('Pending')
    }
  });
  $('#todoStatus').on('change',function(){
     var box = $(this)
      if(box.is(':checked')){
        box.val("Completed")
      }
      else{
       box.val("Pending")
      }
  })
  $('#saveTodo').click(function () {
    var todoId = $('#todoId').val()
    var todoText = $('#todoText').val()
    var todoStatus = $('#todoStatus').val()
    // AJAX
    let postData = JSON.stringify({ details: todoText, id: parseInt(todoId), status : todoStatus })

    //POST to API
    $.ajax({
      url: "/todo",
      type: "POST",
      contentType: "application/json",
      data: postData,
      dataType: "json",
      success: async function (data) {
        if (data.succeeded) {
          await swal("Saved!", "Todo has been saved!", "success");
          window.location.href = "/index"
        }
        else {
           await swal("Oops!", data.message, "error");
        }
      },
      error: async function (error) {
        await swal("Oops!", "Seems like we couldn't save that task.", "error");
        console.error(JSON.stringify(error))
      }
    })
    $('#todoModal').modal('hide');
  });
});
