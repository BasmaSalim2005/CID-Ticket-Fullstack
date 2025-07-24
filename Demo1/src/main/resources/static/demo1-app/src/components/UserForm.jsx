import React, { useState } from 'react';



export default function UserForm({ onUserAdded }) {
    const [formData, setFormData] = useState({
        firstname: '',
        lastname: '',
        email: '',
        role: ''
    });

    const handleChange = e => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async e => {
        e.preventDefault();

        try {
            const response = await fetch("http://localhost:8080/api/demo1/users/add", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(formData),
            });

            if (!response.ok) {
                const errorText = await response.text();
                throw new Error(`Erreur du serveur (${response.status}): ${errorText}`);
            }

            const newUser = await response.json();
            alert('✅ User successfully added!');
            onUserAdded(newUser);

            // Reset form after success
            setFormData({
                firstname: '',
                lastname: '',
                email: '',
                role: ''
            });

        } catch (err) {
            alert('❌ Error : ' + err.message);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="space-y-4 p-4 max-w-md mx-auto">
            <input
                name="firstname"
                placeholder="Firstname"
                value={formData.firstname}
                onChange={handleChange}
                required
                className="w-full p-2 border rounded"
            />
            <input
                name="lastname"
                placeholder="Lastname"
                value={formData.lastname}
                onChange={handleChange}
                required
                className="w-full p-2 border rounded"
            />
            <input
                name="email"
                type="email"
                placeholder="Email"
                value={formData.email}
                onChange={handleChange}
                required
                className="w-full p-2 border rounded"
            />
            <select
                name="role"
                value={formData.role}
                onChange={handleChange}
                required
                className="w-full p-2 border rounded"
            >
                <option value="">Select Role</option>
                <option value="EMPLOYER">EMPLOYER</option>
                <option value="CHEF_DIVISION">CHEF_DIVISION</option>
                <option value="DIRECTEUR_GENERAL">DIRECTEUR_GENERAL</option>
            </select>
            <button
                type="submit"
                className="w-full bg-blue-500 hover:bg-blue-600 text-white font-semibold py-2 rounded"
            >
                Add User
            </button>
        </form>
    );
}