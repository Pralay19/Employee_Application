/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProfile, updateProfile } from '../httputils/employeeApi.js';
import '../styles/updateprofile.css'


function UpdateProfile() {
  const { email } = useParams();
  const [formData, setFormData] = useState({});
  const [photo, setPhoto] = useState("");
  const [profilePhoto, setProfilePhoto] = useState(""); 
  const navigate = useNavigate();
 

  useEffect(() => {
    const fetchProfile = async () => {
      const token = localStorage.getItem('token');
      try {
        const response = await getProfile(email, token);
        setFormData(response.data);
        const recvdpath = response.data.photoPath;
        setProfilePhoto("http://localhost:8080/employee_images/"+recvdpath.substring(17));
        
      } catch (error) {
        console.error("Error fetching profile data", error);
      }
    };

    fetchProfile();
  }, [email]);

  const handleUpdate = async (e) => {
    e.preventDefault();
    const data = new FormData();
    if(photo) data.append('photo', photo);
    data.append('data', new Blob([JSON.stringify(formData)], { type: 'application/json' }));

    try {
      const token = localStorage.getItem('token');
      await updateProfile(email, data, token);
      navigate(`/iiitb/Employee/Profile/${email}`);
    } catch (error) {
      alert('Update failed');
      navigate(`/iiitb/Employee`);
    }
  };

  return (    
    <div className="container">
  <h1 className="main-heading"> Employee Update Form</h1>
  <div className="form-layout">
    <form className="update-form">
      <div className="form-group">
        <label htmlFor='firstName'>First Name</label>
        <input
          id="firstName"
          type="text"
          value={formData.firstName || ''}
          onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
          placeholder="First Name"
        />
      </div>
      <div className="form-group">
        <label htmlFor='lastName'>Last Name</label>
        <input
          id="lastName"
          type="text"
          value={formData.lastName || ''}
          onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
          placeholder="Last Name"
        />
      </div>
      <div className="form-group">
        <label htmlFor='email'>Email</label>
        <input id="email" type="email" value={formData.email} readOnly placeholder="Email" />
      </div>
      <div className="form-group">
        <label htmlFor='password'>Password</label>
        <input
          id="password"
          type="password"
          value={formData.password || ''}
          onChange={(e) => setFormData({ ...formData, password: e.target.value })}
          placeholder="Password"
        />
      </div>
      <div className="form-group">
        <label htmlFor='employeeId'>Employee ID</label>
        <input
          id="employeeId"
          type="text"
          value={formData.employeeId || ''}
          onChange={(e) => setFormData({ ...formData, employeeId: e.target.value })}
          placeholder="Employee ID"
        />
      </div>
      <div className="form-group">
        <label htmlFor='title'>Title</label>
        <input
          id="title"
          type="text"
          value={formData.title || ''}
          onChange={(e) => setFormData({ ...formData, title: e.target.value })}
          placeholder="Title"
        />
      </div>
      <div className="form-group">
        <label htmlFor='department'>Department</label>
        <select
          id="department"
          value={formData.department}
          onChange={(e) => setFormData({ ...formData, department: e.target.value })}
        >
          <option value="Engineering">Engineering</option>
          <option value="Administration">Administration</option>
          <option value="Finance">Finance</option>
          <option value="Marketing">Marketing</option>
        </select>
      </div>
      <button className="submit-btn" onClick={handleUpdate}>Update</button>
    </form>

    <div className="profile-section">
      <h3 className="profile-heading">Profile Picture</h3>
      {profilePhoto && (
        <img
          src={profilePhoto}
          alt="Profile"
          className="profile-photo-preview"
        />
      )}
      <div className="photo-upload">
        <label htmlFor='profilePhoto'>Update Profile Photo</label>
        <input id="profilePhoto" type="file" onChange={(e) => setPhoto(e.target.files[0])} />
      </div>
    </div>
  </div>
</div>


  );
}

export default UpdateProfile;
