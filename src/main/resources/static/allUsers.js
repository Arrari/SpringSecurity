showAllUsers();

function showAllUsers() {
    fetch('http://localhost:8080/api/allUsers')
        .then(response => response.json())
        .then(users => {
            let userData = '';
            let rolesOfUsers = '';

            const userTable = document.getElementById('usersTable');
            for (let user of users) {
                rolesOfUsers = rolesToString(user.roles);

                userData += `<tr>
                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${rolesOfUsers}</td>
                        
                        <td>
                          <button type="button"
                          class="btn btn-info"
                          data-bs-toogle="modal"
                          data-bs-target="#editModal"
                          onclick="editModal(${user.id})">
                                Edit
                            </button>
                        </td>

                        <td>
                            <button type="button" 
                            class="btn btn-danger" 
                            data-toggle="modal" 
                            data-target="#deleteModal" 
                            onclick="deletionModal(${user.id})">
                                Delete
                            </button>
                        </td>
                    </tr>`;
            }
            userTable.innerHTML = userData;
        });
}

async function getUserById(id) {
    let response = await fetch("http://localhost:8080/api/user/" + id);
    return await response.json();
}

