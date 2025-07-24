import React, { useState, useRef } from 'react';
import axios from 'axios';

export default function UserList({ users, setUsers }) {
    const [editingUserId, setEditingUserId] = useState(null);
    const [editData, setEditData] = useState({
        firstname: '',
        lastname: '',
        email: '',
        role: ''
    });
    const listRef = useRef(null);

    const handleDelete = async (id) => {
        try {
            await axios.delete(`http://localhost:8080/api/demo1/users/delete/${id}`);
            setUsers(users.filter((u) => u.id !== id));
        } catch (err) {
            alert('Error during deletion');
        }
    };

    const handleEditClick = (user) => {
        setEditingUserId(user.id);
        setEditData({
            firstname: user.firstname || '',
            lastname: user.lastname || '',
            email: user.email || '',
            role: user.role || ''
        });
    };

    const handleEditChange = (e) => {
        setEditData({ ...editData, [e.target.name]: e.target.value });
    };

    const handleEditSubmit = async (id) => {
        if (!editingUserId) return;
        try {
            const response = await axios.put(
                `http://localhost:8080/api/demo1/users/update1/${editingUserId}`,
                editData,
                {
                    headers: { 'Content-Type': 'application/json' }
                }
            );
            const updatedUser = response.data;
            setEditingUserId(null);
            const updatedUsers = users.map((user) =>
                user.id === id ? updatedUser : user
            );
            setUsers(updatedUsers);
            if (listRef.current) listRef.current.scrollIntoView({ behavior: 'smooth' });
        } catch (err) {
            alert('Error occurred while saving');
        }
    };

    const handleCancelEdit = () => {
        setEditingUserId(null);
        if (listRef.current) listRef.current.scrollIntoView({ behavior: 'smooth' });
    };

    return (
        <div>
            <h2 className="text-cyan-600 font-bold">List of Users</h2>
            <ul ref={listRef}>
                {users.map((user) => (
                    <li key={user.id} className="mb-2 border-2 border-b-cyan-950 p-2">
                        {editingUserId === user.id ? (
                            <div className="space-y-1">
                                <input
                                    name="firstname"
                                    value={editData.firstname}
                                    onChange={handleEditChange}
                                    placeholder="Firstname"
                                />
                                <input
                                    name="lastname"
                                    value={editData.lastname}
                                    onChange={handleEditChange}
                                    placeholder="Lastname"
                                />
                                <input
                                    name="email"
                                    value={editData.email}
                                    onChange={handleEditChange}
                                    placeholder="Email"
                                />
                                <select name="role" value={editData.role} onChange={handleEditChange}>
                                    <option value="EMPLOYER">EMPLOYER</option>
                                    <option value="CHEF_DIVISION">CHEF_DIVISION</option>
                                    <option value="DIRECTEUR_GENERAL">DIRECTEUR GENERAL</option>
                                </select>
                                <button onClick={() => handleEditSubmit(user.id)} className="bg-green-500 text-white px-2 rounded">
                                    Enregistrer
                                </button>
                                <button onClick={handleCancelEdit} className="bg-red-500 text-white px-2 ml-2 rounded">
                                    Annuler
                                </button>
                            </div>
                        ) : (
                            <div>
                                <strong>{user.firstname} {user.lastname}</strong> - {user.email} - {user.role}
                                <br />
                                <button onClick={() => handleEditClick(user)} className="bg-yellow-500 text-white px-2 rounded">Modifier</button>
                                <button onClick={() => handleDelete(user.id)} className="bg-red-500 text-white px-2 ml-2 rounded">Supprimer</button>
                            </div>
                        )}
                    </li>
                ))}
            </ul>
        </div>
    );
}
