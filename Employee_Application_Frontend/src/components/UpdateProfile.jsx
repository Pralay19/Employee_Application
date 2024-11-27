/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProfile, updateProfile } from '../httputils/employeeApi';
import './App.css';


function UpdateProfile() {
  const { email } = useParams();
  const [formData, setFormData] = useState({});
  const [photo, setPhoto] = useState(null);
  const [profilePhoto, setProfilePhoto] = useState(null); 
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      const token = localStorage.getItem('token');
      try {
        const response = await getProfile(email, token);
        setFormData(response.data);
        setProfilePhoto(response.data.photoPath); 
      } catch (error) {
        console.error("Error fetching profile data", error);
      }
    };

    fetchProfile();
  }, [email]);

  const handleUpdate = async () => {
    const data = new FormData();
    data.append('photo', photo);
    data.append('data', new Blob([JSON.stringify(formData)], { type: 'application/json' }));

    try {
      const token = localStorage.getItem('token');
      await updateProfile(email, data, token);
      alert('Update successful');
      navigate(`/iiitb/Employee/Profile/${email}`);
    } catch (error) {
      alert('Update failed');
    }
  };

  return (
    <div className="container">
      <h2>Update Profile</h2>
      <input
        type="text"
        value={formData.firstName || ''}
        onChange={(e) => setFormData({ ...formData, firstName: e.target.value })}
        placeholder="First Name"
      />
      <input
        type="text"
        value={formData.lastName || ''}
        onChange={(e) => setFormData({ ...formData, lastName: e.target.value })}
        placeholder="Last Name"
      />
      <input
        type="email"
        value={formData.email}
        readOnly
        placeholder="Email"
      />
      <input
        type="password"
        value={formData.password || ''}
        onChange={(e) => setFormData({ ...formData, password: e.target.value })}
        placeholder="Password"
      />
      <input
        type="text"
        value={formData.employeeId || ''}
        onChange={(e) => setFormData({ ...formData, employeeId: e.target.value })}
        placeholder="EmployeeId"
      />
      <input
        type="text"
        value={formData.title || ''}
        onChange={(e) => setFormData({ ...formData, title: e.target.value })}
        placeholder="Title"
      />
      <select value={formData.department} onChange={(e) => setFormData({ ...formData, department: e.target.value })}>
        <option value="Engineering">Engineering</option>
        <option value="Administration">Administration</option>
        <option value="Finance">Finance</option>
        <option value="Marketing">Marketing</option>
        <option value="Marketing">Marketing</option>
      </select>

      
      <div>
        {profilePhoto && (
          <img
            src={profilePhoto}
            alt="Profile"
            style={{ width: '100px', height: '100px', borderRadius: '8px', objectFit: 'cover' }}
          />
        )}
        <input type="file" onChange={(e) => setPhoto(e.target.files[0])} />
      </div>

      <button onClick={handleUpdate}>Update</button>
    </div>
  );
}

export default UpdateProfile;
