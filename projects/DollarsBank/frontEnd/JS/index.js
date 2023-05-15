const baseURL = 'http://localhost:8080/api';

function render() {
    document.getElementById("currentUserDisplay").innerHTML = readCookie("username");
    populateAccount();
    pupulateTrans();
}

function param(paramater) {
    return '/' + paramater;
}

function readCookie(name) { //sourced from: https://stackoverflow.com/questions/260749/what-is-the-best-way-to-get-and-set-a-single-cookie-value-using-javascript
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function logout() {
    window.location.replace("login.html");
}

function transaction() {
    window.location.replace("trans.html");
}

function transfer() {
    window.location.replace("transfer.html");
}

function pupulateTrans() {
    fetch(baseURL + '/trans/get/' + readCookie("username"))
    .then(response => {
        if (response.ok) {
            response.json().then(
                res => loopTrans(res));

        } else {
            response.text().then(error => alert(error))
        }
    });
}

function loopTrans(trans) {
    let table = document.getElementById("transBody");
    table.innerHTML = "";
    for (var i in trans) {

        let table = document.getElementById("transBody");
        let row = table.insertRow(-1);
        let c1 = row.insertCell(0);
        let c2 = row.insertCell(1);
        c1.innerText = trans[i].amount;
        c2.innerText = trans[i].desc;
    }
}

function populateAccount() {
    fetch(baseURL + '/account/get/' + readCookie("username"))
    .then(response => {
        if (response.ok) {
            response.json().then(
                res => loopAccounts(res));
            // console.log(response.json().username)
            // window.location.replace("index.html");
            // return response.json();
        } else {
            response.text().then(error => alert(error))
        }
    });
}

function loopAccounts(accounts) {
    let table = document.getElementById("accountBody");
    table.innerHTML = "";
    for (var i in accounts) {
        let table = document.getElementById("accountBody");
        let row = table.insertRow(-1);
        let c1 = row.insertCell(0);
        let c2 = row.insertCell(1);
        c1.innerText = accounts[i].name;
        c2.innerText = accounts[i].balance;
    }
}

function createAccount() {
    const accountName = document.getElementById("newAccountName").value;
    if (!accountName) {
        alert("Please enter an account name");
        return;
    }

    const balance = document.getElementById("newBalance").value;
    if (!balance) {
        alert("Please enter a balance");
        return;
    }

    fetch(baseURL + '/account/create/' + readCookie("username") + param(accountName) + param(balance), {method:'POST'})
    .then(response => {
        if (response.ok) {
            response.json().then(
                render());

        } else {
            response.text().then(error => alert(error))
        }
    });
}



