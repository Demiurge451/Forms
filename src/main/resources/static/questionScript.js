var questionSubmitted = false;

function submitQuestion() {
    if (!questionSubmitted) {
        sendPostRequest();
        questionSubmitted = true;
    } else {
        sendPutRequest();
    }
}
function addAnswer() {
    const txt = document.getElementById('answerTxt').value;

    const answerData = {
        txt: txt
    };

    fetch('http://localhost:8081/api/answers/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(answerData),
    })
    .then(response => {
        if (response.ok) {
            alert('Answer created successfully!');
        } else {
            alert('Error creating answer.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

function sendPostRequest() {
    const form_id = document.getElementById('form_id').value;
    const txt = document.getElementById('txt').value;
    const multiplySelection = document.getElementById('multiplySelection').value;

    const questionData = {
        txt: txt,
        multiplySelection: multiplySelection,
        form_id: form_id
    };
    fetch('http://localhost:8081/api/questions/', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(questionData),
    })
    .then(response => {
        response.json()
    })
    .then(data => {
        console.log(data);

        updateQuestionWithData(data);
        alert('Question created successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error creating question.');
    });
}

function sendPutRequest() {
    const id = document.getElementById('id').value;
    const txt = document.getElementById('txt').value;
    const form_id = document.getElementById('form_id').value;
    const multiplySelection = document.getElementById('multiplySelection').value;

    const questionData = {
        id: id,
        txt: txt,
        multiplySelection: multiplySelection,
        form_id: form_id
    };
    const api = 'http://localhost:8081/api/questions/' + id;
    fetch(api, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(questionData),
    })
    .then(response => response.json())
    .then(data => {
        updateQuestionWithData(data);
        alert('Question update successfully!');
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error creating question.');
    });
}

function updateQuestionWithData(data) {
    document.getElementById('id').value = data.id;
    document.getElementById('txt').value = data.txt;
    document.getElementById('multiplySelection').value = data.multiplySelection;
    document.getElementById('form_id').value = form_id;
}

function setQueryParam(name) {
     const urlParams = new URLSearchParams(window.location.search);
     document.getElementById('form_id').value = urlParams.get(name);
}
