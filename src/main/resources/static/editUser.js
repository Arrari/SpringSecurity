let editionForm = document.forms["editionForm"];
editUser();

async function editModal(id) {
    const modalEdit = new bootstrap.Modal(document.querySelector('#editModal'));
    await fill_modal(editionForm, modalEdit, id);
    loadRolesEdit();
}

function editUser() {
    editionForm.addEventListener("submit", event => {
        event.preventDefault();

        let rolesForEdit = [];

        for (let i = 0; i < editionForm.roles.options.length; i++) {
            if (editionForm.roles.options[i].selected)
                rolesForEdit.push({
                    id: editionForm.roles.options[i].value,
                    rolename: "ROLE_" + editionForm.roles.options[i].text
                });
        }

        fetch("http://localhost:8080/api/user/" + editionForm.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editionForm.id.value,
                firstname: editionForm.firstname.value,
                lastname: editionForm.lastname.value,
                username: editionForm.username.value,
                email: editionForm.email.value,
                password: editionForm.password.value,
                roles: rolesForEdit
            })
        }).then(() => {
            $('#editClose').click();
            showAllUsers();
        });
    });
}

function loadRolesEdit() {
    let selection = document.getElementById("edit-roles");
    selection.innerHTML = "";

    fetch("http://localhost:8080/api/getAllRoles")
        .then(res => res.json())
        .then(data => {
            data.forEach(role => {
                let option = document.createElement("option");
                option.value = role.id;
                option.text = role.rolename.toString().replace('ROLE_', '');
                selection.appendChild(option);
            });
        })
        .catch(error => console.error(error));
}

window.addEventListener("load", loadRolesEdit);
