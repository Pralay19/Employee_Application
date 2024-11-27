/* eslint-disable no-unused-vars */
import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getProfile } from '../httputils/employeeApi';
import '../styles/profile.css';

function Profile() {
  const { email } = useParams();
  const navigate = useNavigate();
  const defaultprofile="../assets/defaultpicture.jpg";
  const [profile, setProfile] = useState({
    'photo':defaultprofile
  });
  const [imgsrc, setImgsrc]=useState("");

  
  useEffect(() => {
    const fetchProfile = async () => {
      try {
        const token = localStorage.getItem('token');
        // console.log()
        const response = await getProfile(email, token);
        setProfile(response.data);
        console.log(response.data);
        const recvdpath = response.data.photoPath;
        setImgsrc("http://localhost:8080/employee_images/"+recvdpath.substring(17));
        
      } catch (error) {
        alert('Failed to load profile');
      }
    };
    
    fetchProfile();
  }, [email]);
  
  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate(`/iiitb/Employee`) 
  };
  
  // const changePhotopath = () => {
  //   const recvdpath = profile.photoPath;
  //   const usepath = "http://localhost:8080/employee_images/"+recvdpath.substring(18);
  //   console.log(usepath);
  //   return usepath;
  // }

  return (
<div className="profile-container">
    <div className="profile-image">
      <img src={imgsrc} alt="Employee" />
    </div>
    <div className="profile-info">
      <h2 className="profile-heading">{profile.firstName} {profile.lastName}</h2>
      <p>{profile.email}</p>
      <p>{profile.department}</p>
      <p>{profile.title}</p>
      <hr />
      <button onClick={() => navigate(`/iiitb/Employee/${email}/updateinfo`)}>Update</button>
      <button onClick={handleLogout}>Logout</button>
    </div>
</div>

  );
}

export default Profile;
