let deletionForm = document.forms["deletionForm"]
deleteUser()

async function deletionModal(id) {
    const modalDelete = new bootstrap.Modal(document.querySelector('#deleteModal'));
    await fill_modal(deletionForm, modalDelete, id);
}

function deleteUser() {
    deletionForm.addEventListener("submit", event => {
        event.preventDefault();
        fetch("http://localhost:8080/api/user/" + deletionForm.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteClose').click();
            showAllUsers();
        });
    });
}
