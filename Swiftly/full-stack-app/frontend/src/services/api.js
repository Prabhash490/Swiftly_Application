export const API_URL = 'http://localhost:8080/api'; // Update with your backend URL

export const fetchExamples = async () => {
    const response = await fetch(`${API_URL}/examples`);
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
};

export const createExample = async (exampleData) => {
    const response = await fetch(`${API_URL}/examples`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(exampleData),
    });
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
};

export const updateExample = async (id, exampleData) => {
    const response = await fetch(`${API_URL}/examples/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(exampleData),
    });
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
};

export const deleteExample = async (id) => {
    const response = await fetch(`${API_URL}/examples/${id}`, {
        method: 'DELETE',
    });
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
};