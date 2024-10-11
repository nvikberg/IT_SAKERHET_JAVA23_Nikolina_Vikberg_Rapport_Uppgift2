const SERVER_URL = 'http://localhost:8080';

//welcome text after user logs in
const welcometext = document.getElementById("wtext");
const name = localStorage.getItem('name');
welcometext.innerText = `Welcome ${name}`; 
let jwt = localStorage.getItem('token'); // hämta JWT från localStorage

//logs out eventlistener
document.getElementById('logoutButton').addEventListener('click', function() {
    jwt = null;
    localStorage.removeItem('token'); 
    localStorage.removeItem('name');
    alert('You have logged out.');
    window.location.href = './index.html';
});

//fetches messages eventlistener
document.getElementById('fetchDataButton').addEventListener('click', function() {
    console.log('Fetch data button klickad');
    fetchMessage();
});

//sends messages to database
document.getElementById('sendMessageButton').addEventListener('click', function() {
    const message = document.getElementById('messageInput').value;
    const name = localStorage.getItem('name');

    if (message) {
        const messageData = {
            message: message,
            name: name 
        };

        fetch(`${SERVER_URL}/api/message`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwt}`
            },
            body: JSON.stringify(messageData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('failed to send ' + response.statusText);
            }
            console.log('Response status:', response.status);

            return response.json(); 
        })
        .then(data => {
            alert('Message sent ' + data.message);
            document.getElementById('messageInput').value = ''; 
            //fetchMessage();
        })
        .catch(error => console.error('error', error));
    } else {
        alert('Please enter a message');
    }
});


//fetching messages from databse
function fetchMessage() {
    const name = localStorage.getItem('name');
    const messageText = document.getElementById("messageText");

    console.log('Current JWT', jwt);


    if (jwt && name) {
        console.log(`Fetching messages for ${name} with jwt ${jwt}`);

        fetch(`${SERVER_URL}/api/message/${name}`, {
            headers: {
                'Authorization': `Bearer ${jwt}`,
            }
        })
        .then(response => response.json())
        .then(data => {
           //messageText.innerHTML = '';
            data.forEach(msg => {
                const msgElement = document.createElement("p");
                msgElement.innerText = `${msg.name}: ${msg.message}`;
                messageText.appendChild(msgElement);
            });
        })
        .catch(error => {
            alert('Error fetching message ' + error.message);
            console.error('Error fetching messages', error);
        });
    } else {
        alert('You are not authenticated. Please log in to get the JWT.');
    }
}
