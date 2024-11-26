/* eslint-disable no-unused-vars */
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerEmployee } from '../httputils/employeeApi';

function Register() {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    title: '',
    department: 'Engineering',
    password: ''
  });
  const [photo, setPhoto] = useState(null);
  const navigate = useNavigate();

  const handleRegister = async () => {
    const data = new FormData();
    data.append('photo', photo);
    data.append('data', new Blob([JSON.stringify(formData)], { type: 'application/json' })); 

    try {
      const response = await registerEmployee(data);
      const generatedEmail = response.data.email;
      alert('Registration complete\nYour emai:'+ generatedEmail);
      console.log('Navigating to Login with state:', generatedEmail);
      const token=response.data.token;
      localStorage.setItem('token', token.trim());
      navigate(`/iiitb/Employee/Profile/${generatedEmail}`,{state:{email:formData.email}});
    } catch (error) {
      alert('Registration failed');
      console.error(error);
    }
  };

  return (
    <div className='container'>
      <h2>Register</h2>
      <input type="text" placeholder="First Name" onChange={(e) => setFormData({ ...formData, firstName: e.target.value })} />
      <input type="text" placeholder="Last Name" onChange={(e) => setFormData({ ...formData, lastName: e.target.value })} />
      <input type="text" placeholder="Title" onChange={(e) => setFormData({ ...formData, title: e.target.value })} />
      <select onChange={(e) => setFormData({ ...formData, department: e.target.value })}>
        <option value="Engineering">Engineering</option>
        <option value="Administration">Administration</option>
        <option value="Finance">Finance</option>
        <option value="Marketing">Marketing</option>
        <option value="MOSIP">MOSIP</option>
      </select>
      <input type="password" placeholder="Password" onChange={(e) => setFormData({ ...formData, password: e.target.value })} />
      <input type="file" onChange={(e) => setPhoto(e.target.files[0])} />
      <button onClick={handleRegister}>Register</button>
    </div>
  );
}

export default Register;
