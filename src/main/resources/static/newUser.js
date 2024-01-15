let newUserForm = document.forms["newUser-form"];

creatNewUser();

function creatNewUser() {
    newUserForm.addEventListener("submit", event => {
        event.preventDefault();
        let rolesForNewUser = [];

        for (let i = 0; i < newUserForm.roles.options.length; i++) {
            if (newUserForm.roles.options[i].selected)
                rolesForNewUser.push({
                    id: newUserForm.roles.options[i].value,
                    role: "ROLE_" + newUserForm.roles.options[i].text
                });
        }

        fetch("http://localhost:8080/api/create", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstname: newUserForm.firstname.value,
                lastname: newUserForm.lastname.value,
                username: newUserForm.username.value,
                email: newUserForm.email.value,
                password: newUserForm.password.value,
                roles: rolesForNewUser
            })
        }).then(() => {
            newUserForm.reset();
            showAllUsers();
            $('#nav-home-tab').click();
        });
    });
}

function loadRolesForNewUser() {
    let selection = document.getElementById("newUser-role");
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

window.addEventListener("load", loadRolesForNewUser);