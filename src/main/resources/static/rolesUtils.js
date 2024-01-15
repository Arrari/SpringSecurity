function rolesToString(roles) {
    let rolesString = '';
    for (const element of roles) {
        rolesString += (element.rolename.toString().replace('ROLE_', '') + ', ');
    }
    rolesString = rolesString.substring(0, rolesString.length - 2);
    return rolesString;
}


