var formSubmitted = false;

function submitForm(formId) {
    if (!formSubmitted) {
        sendPostRequest();
        formSubmitted = true;
    } else {
        sendPutRequest(formId);
    }
}

function sendPostRequest() {
    const title = document.getElementById('title').value;
    const foreword = document.getElementById('foreword').value;

    const formData = {
        title: title,
        foreword: foreword
    };

    fetch('http://localhost:8081/api/forms/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        updateFormWithData(data);
        alert('Form created successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error creating form.');
    });
}

function sendPutRequest() {
    const id = document.getElementById('id').value;
    const title = document.getElementById('title').value;
    const foreword = document.getElementById('foreword').value;

    const formData = {
        id: id,
        title: title,
        foreword: foreword
    };
    const api = 'http://localhost:8081/api/forms/' + id;
    fetch(api, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
    })
    .then(response => response.json())
    .then(data => {
        updateFormWithData(data);
        alert('Form created successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error creating form.');
    });
}

function updateFormWithData(data) {
    document.getElementById('id').value = data.id;
    document.getElementById('title').value = data.title;
    document.getElementById('foreword').value = data.foreword;
}

function redirectToQuestionPage() {
    if (!formSubmitted) {
        alert('Please submit the form before adding an answer.');
        return;
    }
    const id = document.getElementById('id').value;
    window.location.href = '/api/questionView/new?=form_id=' + encodeURIComponent(id);
}
