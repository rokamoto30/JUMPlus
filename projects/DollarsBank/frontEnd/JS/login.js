const baseURL = 'http://localhost:8080/api/user'

function param(paramater) {
    return '/' + paramater;
}

function login() {
    const username = document.getElementById("username").value;
    if (!username) {
        alert("Please enter a username name");
        return;
    }

    const password = document.getElementById("password").value;
    if (!password) {
        alert("Please enter a password");
        return;
    }

    fetch(baseURL + '/login' + param(username) + param(password))
    .then(response => {
        if (response.ok) {
            response.json().then(
                res => document.cookie = "username=" + res.username);
                window.location.replace("index.html");
            // console.log(response.json().username)
            // window.location.replace("index.html");
            // return response.json();
        } else {
            response.text().then(error => alert(error))
        }
    });

    return;
}

function createUser() {
    const username = document.getElementById("username").value;
    if (!username) {
        alert("Please enter a username name");
        return;
    }

    const password = document.getElementById("password").value;
    if (!password) {
        alert("Please enter a password");
        return;
    }

    fetch(baseURL + '/create' + param(username) + param(password), {method:'POST'})
    .then(response => {
        if (response.ok) {
            response.json().then(
                res => document.cookie = "username=" + res.username);
                window.location.replace("index.html");
            // console.log(response.json().username)
            // window.location.replace("index.html");
            // return response.json();
        } else {
            response.text().then(error => alert(error))
        }
    });
}