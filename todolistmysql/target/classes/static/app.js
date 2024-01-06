// Fetch and render todos on page load
fetchAndRenderTodos();

function fetchAndRenderTodos() {
    fetch('http://localhost:8080/allTodo')
        .then(response => response.json())
        .then(todos => {
            const todoList = document.getElementById("todoList");
            todoList.innerHTML = ""; // Clear existing todos

            todos.forEach(todo => {
                const tr = document.createElement("tr");

                const tdId = document.createElement("td");
                tdId.textContent = todo.id;

                const tdTitle = document.createElement("td");
                tdTitle.textContent = todo.title;

                const tdDescription = document.createElement("td");
                tdDescription.textContent = todo.description;

                const tdCompleted = document.createElement("td");
                tdCompleted.textContent = todo.completed;

                const tdAction = document.createElement("td");
                const deleteButton = document.createElement("button");
                deleteButton.innerHTML = '<i class="fas fa-trash"></i> Delete';
                deleteButton.onclick = () => deleteTodo(todo.id);
                tdAction.appendChild(deleteButton);

                tr.appendChild(tdId);
                tr.appendChild(tdTitle);
                tr.appendChild(tdDescription);
                tr.appendChild(tdCompleted);
                tr.appendChild(tdAction);

                todoList.appendChild(tr);
            });
        })
        .catch(error => console.error('Error fetching todos:', error));
}

function addTodo() {
    const title = document.getElementById("newTodoTitle").value.trim();
    const description = document.getElementById("newTodoDescription").value.trim();

    // Get the selected value from the dropdown
    const completed = document.getElementById("newTodoCompleted").value === "true";

    if (title === "") return;

    fetch('http://localhost:8080/addTodo', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            title: title,
            description: description,
            completed: completed,
        }),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            console.log('Reloading...');
            location.reload();

        })
        .catch(error => console.error('Error:', error));

    document.getElementById("newTodoTitle").value = "";
    document.getElementById("newTodoDescription").value = "";
}

function deleteTodo(todoId) {
    fetch(`http://localhost:8080/delete/${todoId}`, {
        method: 'DELETE',
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
            fetchAndRenderTodos(); // Fetch and render updated todos
        })
        .catch(error => console.error('Error:', error));
}
