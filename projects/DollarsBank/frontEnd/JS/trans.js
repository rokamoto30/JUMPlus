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

function transaction() {
    const accountName = document.getElementById("accountName").value;
    if (!accountName) {
        alert("Please enter an account name");
        return;
    }

    const amount = document.getElementById("amount").value;
    if (!amount) {
        alert("Please enter an amount");
        return;
    }

    const description = document.getElementById("description").value;
    if (!description) {
        alert("Please enter an description");
        return;
    }

    fetch('http://localhost:8080/api/trans/create' + param(amount) + param(description) + '/' + readCookie("username") + param(accountName), {method:'POST'})
    .then(response => {
        if (response.ok) {
            window.location.replace("index.html");
        } else {
            response.text().then(error => alert(error))
        }
    });
}

function back() {
    window.location.replace("index.html");
}

function transfer() {
    const accountName = document.getElementById("sourceAccountName").value;
    if (!accountName) {
        alert("Please enter an source account name");
        return;
    }

    const targetUsername = document.getElementById("targetUsername").value;
    if (!targetUsername) {
        alert("Please enter a target username name");
        return;
    }

    const targetAccountName = document.getElementById("targetAcountName").value;
    if (!targetAccountName) {
        alert("Please enter a target account name");
        return;
    }

    const amount = document.getElementById("amount").value;
    if (!amount) {
        alert("Please enter an amount");
        return;
    }

    const description = document.getElementById("description").value;
    if (!description) {
        alert("Please enter an description");
        return;
    }

    fetch('http://localhost:8080/api/trans/transfer' + param(amount) + param(description) + '/' + readCookie("username") + param(accountName)+ param(targetUsername)+ param(targetAccountName), {method:'POST'})
    .then(response => {
        if (response.ok) {
            window.location.replace("index.html");
        } else {
            response.text().then(error => alert(error))
        }
    });
}