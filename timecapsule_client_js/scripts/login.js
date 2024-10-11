//const SERVER_URL = 'http://localhost:8080';

document.getElementById('showRegister').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('loginForm').classList.add('hidden'); 
    document.getElementById('registerForm').classList.remove('hidden'); 
});

document.getElementById('showLogin').addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById('registerForm').classList.add('hidden'); 
    document.getElementById('loginForm').classList.remove('hidden'); 
});

document.getElementById('loginForm').classList.remove('hidden');

let jwt = null; 

document.getElementById('loginButton').addEventListener('click', function(event) {
    event.preventDefault();
    login();
});

document.getElementById('dataButton').addEventListener('click', function() {
    fetchProtectedData();
});

//logs in the user by sending their name and password to the server and storing the jwt
function login() {
    const name = document.getElementById('name').value;
    const password = document.getElementById('password').value;

    console.log('Logging in with:', { name, password });


    fetch(`${SERVER_URL}/api/authenticate`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ name: name, password: password }),
    })
    .then(response => response.json())
    .then(data => {
        if (data.token) {
            localStorage.setItem('token', data.token); //sparar JWT i 
            localStorage.setItem('name', name)
            jwt = data.token; 
            console.log('JWT stored in localStorage:', data.token);
            alert('JWT received and stored. Check the console for the token.');
            window.location.href = './home.html';
        } else {
            alert('Login failed: ' + data.error);
        }
    })
    .catch(error => console.error('Error:', error));
}


// function fetchProtectedData() {
//     if (jwt) {
//         fetch(`${SERVER_URL}/protected`, { 
//             headers: {
//                 'Authorization': `Bearer ${jwt}`,
//             }
//         })
//         .then(response => response.json())
//         .then(data => {
//             if (data.data) {
//                 alert('Protected Data: ' + data.data);
//             } else {
//                 alert('Failed to fetch protected data: ' + data.error);
//             }
//         })
//         .catch(error => console.error('Error:', error));
//     } else {
//         alert('You are not authenticated. Please log in to get the JWT.');
//     }
// }
