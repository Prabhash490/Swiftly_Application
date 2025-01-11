import React, { useEffect, useState } from 'react';
import ExampleComponent from './components/ExampleComponent';
import './App.css';

function App() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        try {
            const response = await fetch('/api/examples');
            const result = await response.json();
            setData(result);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    };

    return (
        <div className="App">
            <h1>Full-Stack Application</h1>
            <ExampleComponent data={data} />
        </div>
    );
}

export default App;