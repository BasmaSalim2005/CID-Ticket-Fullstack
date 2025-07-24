import React, { useState } from 'react';
import axios from 'axios';
export default function UserSearch({ setUsers }) {
    const [input, setInput] = useState('');
    const [type, setType] = useState('id');

    const handleSearch = async () => {
        try {
            let url = '';
            if (type === 'id') {
                url = `http://localhost:8080/api/demo1/users/getbyid/${input}`;
                const response = await axios.get(url);
                setUsers(response.data ? [response.data] : []);
            } else {
                url = `http://localhost:8080/api/demo1/users/getbfname/${input}`;
                const response = await axios.get(url);
                setUsers(Array.isArray(response.data) ? response.data : [response.data]);
            }
        } catch (err) {
            setUsers([]);
            alert('No user found or error occurred');
        }
    };
    return (
        <div className="flex items-center space-x-2 justify-end">
            <select value={type} onChange={e => setType(e.target.value)} className="border rounded p-1">
                <option value="id">By id</option>
                <option value="firstname">By firstname</option>
            </select>
            <input value={input} onChange={e => setInput(e.target.value)} className="border rounded p-1" placeholder="Search..." />
            <button onClick={handleSearch} className="bg-green-800 font-semibold text-blue-50 border-b-emerald-500 px-2 py-1 rounded">Search</button>
        </div>
    );
}