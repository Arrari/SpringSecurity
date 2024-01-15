const navbarNamed = document.getElementById('navbarNamed');
const tableForShow = document.getElementById('tableForShow')


function showUserInfo() {
    fetch('http://localhost:8080/api/showMyAccount')
        .then(response => response.json())
        .then(user => {
            let data = '';
            data = '';

            data += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.lastname}</td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${rolesToString(user.roles)}</td>
            </tr>`;
            tableForShow.innerHTML = data;
            navbarNamed.innerHTML = `<b><span>${user.username}</span></b>
                             <span>with roles:</span>
                             <span>${rolesToString(user.roles)}</span>`;
        });
}

showUserInfo();

async function getUserById(id) {
    let response = await fetch("http://localhost:8080/api/user/" + id);
    return await response.json();
}