/* eslint-disable no-unused-vars */
import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { registerEmployee } from '../httputils/employeeApi';
import '../styles/register.css'

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
      // const token=response.data.token;
      // localStorage.setItem('token', token.trim());
      navigate(`/iiitb/Employee/login`);
      //Here i was first going to the login page after the registration process and was saving the email state,
      //but didn't work out and hence i made it to go directly to the profile page after registration.
      //Again i had to change the navigation because the image was loading instantly and hence i diverted the user to login
      //after registration.
    } catch (error) {
      alert('Registration failed');
      console.error(error);
    }
  };

  return (
    // <div className='container'>
    //   <h2>Register</h2>
    //   <input type="text" placeholder="First Name" onChange={(e) => setFormData({ ...formData, firstName: e.target.value })} />
    //   <input type="text" placeholder="Last Name" onChange={(e) => setFormData({ ...formData, lastName: e.target.value })} />
    //   <input type="text" placeholder="Title" onChange={(e) => setFormData({ ...formData, title: e.target.value })} />
    //   <select onChange={(e) => setFormData({ ...formData, department: e.target.value })}>
    //     <option value="Engineering">Engineering</option>
    //     <option value="Administration">Administration</option>
    //     <option value="Finance">Finance</option>
    //     <option value="Marketing">Marketing</option>
    //     <option value="MOSIP">MOSIP</option>
    //   </select>
    //   <input type="password" placeholder="Password" onChange={(e) => setFormData({ ...formData, password: e.target.value })} />
    //   <input type="file" onChange={(e) => setPhoto(e.target.files[0])} />
    //   <button onClick={handleRegister}>Register</button>
    // </div>

    <div className="container">
  <h2 className="form-heading">Register</h2>
  <form className="register-form">
    <div className="form-group">
      <label htmlFor="firstName">First Name</label>
      <input
        id="firstName"
        type="text"
        placeholder="First Name"
        onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
      />
    </div>
    <div className="form-group">
      <label htmlFor="lastName">Last Name</label>
      <input
        id="lastName"
        type="text"
        placeholder="Last Name"
        onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
      />
    </div>
    <div className="form-group">
      <label htmlFor="title">Title</label>
      <input
        id="title"
        type="text"
        placeholder="Title"
        onChange={(e) => setFormData({ ...formData, title: e.target.value })}
      />
    </div>
    <div className="form-group">
      <label htmlFor="department">Department</label>
      <select
        id="department"
        onChange={(e) => setFormData({ ...formData, department: e.target.value })}
      >
        <option value="Engineering">Engineering</option>
        <option value="Administration">Administration</option>
        <option value="Finance">Finance</option>
        <option value="Marketing">Marketing</option>
        <option value="MOSIP">MOSIP</option>
      </select>
    </div>
    <div className="form-group">
      <label htmlFor="password">Password</label>
      <input
        id="password"
        type="password"
        placeholder="Password"
        onChange={(e) => setFormData({ ...formData, password: e.target.value })}
      />
    </div>
    <div className="form-group">
      <label htmlFor="profilePhoto">Profile Photo</label>
      <input id="profilePhoto" type="file" onChange={(e) => setPhoto(e.target.files[0])} />
    </div>
    <button type="button" className="submit-btn" onClick={handleRegister}>Register</button>
  </form>
</div>

  );
}

export default Register;
