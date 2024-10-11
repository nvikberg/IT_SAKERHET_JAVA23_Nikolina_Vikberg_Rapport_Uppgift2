const SERVER_URL = 'http://localhost:8080';


//collects form data and sends post request to register a new user
document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); 
    const formData = new FormData(this);
    const data = {
        name: formData.get('name'),
        password: formData.get('password'),
        message: formData.get('message'),
    };

    if (!data.name || !data.password) {
        alert('Please fill in all required fields.');
        return;
    }

    fetch(`${SERVER_URL}/api/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
    .then(response => {
        if (response.ok) {
            alert('User added!');
            this.reset(); 
        } else {
            return response.json().then(err => {
                alert(`Failed to add ${err.message || 'error'}`);
            });
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('An error occurred while adding the user.');
    });
});
