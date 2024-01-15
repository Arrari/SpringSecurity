async function fill_modal(form, modal, id) {
    modal.show();
    let user = await getUserById(id);
    form.id.value = user.id;
    form.username.value = user.username;
    form.firstname.value = user.firstname;
    form.lastname.value = user.lastname;
    form.email.value = user.email;
    form.password.value = user.password;
    form.roles.value = user.roles;
}
