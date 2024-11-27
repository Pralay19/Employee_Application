/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProfile } from '../httputils/employeeApi';
import './App.css';

function Profile() {
  const { email } = useParams();
  const [profile, setProfile] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        // console.log()
        const response = await getProfile(email, token);
        setProfile(response.data);
        console.log(response.data);
      } catch (error) {
        alert('Failed to load profile');
      }
    };

    fetchProfile();
  }, [email]);

  if (!profile) return <div>Loading...</div>;

  return (
    <div className='profile-container'>
      <h2 className='profile-heading'>Profile</h2>
      <div className="profile-info">
        <p>First Name: {profile.firstName}</p>
        <p>Last Name: {profile.lastName}</p>
        <p>Email: {profile.email}</p>
        <p>Department: {profile.department}</p>
        <p>Title: {profile.title}</p>
        <img src={profile.photo} alt="Employee" />
      </div>
      
      <hr />
      <button onClick={() => navigate(`/iiitb/Employee/${email}/updateinfo`)}>Update</button>
    </div>
  );
}

export default Profile;
