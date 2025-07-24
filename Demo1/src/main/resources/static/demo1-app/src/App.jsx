import './index.css';
import UserSearch from "./components/UserSearch";
import axios from 'axios';
import {useEffect, useState} from "react";
import UserForm from "./components/UserForm";
import UserList from "./components/UserList";

function App() {
    const [users, setUsers] = useState([]);

    // Fetch all users on mount
    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/demo1/users/getall');
                setUsers(response.data);
            } catch (err) {
                alert('Error while loading users');
            }
        };
        fetchUsers();
    }, []);

    return (
        <div className="App p-6">
            <h1 className="text-2xl font-bold mb-4 text-blue-600">TickFlow - Manage Users</h1>
            <div className="flex justify-between items-start mb-4">
                <UserForm onUserAdded={(newUser) => setUsers(prev => [...prev, newUser])} />
                <div className="ml-4 w-1/3 flex-shrink-0">
                    <UserSearch setUsers={setUsers} />
                </div>
            </div>
            <hr className="my-4" />
            <UserList users={users} setUsers={setUsers} />
        </div>
    );
}

export default App;


