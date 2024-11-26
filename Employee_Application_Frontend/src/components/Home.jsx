/* eslint-disable no-unused-vars */
import React from 'react';
import { useNavigate } from 'react-router-dom';

function Home() {
  const navigate = useNavigate();

  return (
    <div>
      <h1>Employee Management</h1>
      <button onClick={() => navigate('/iiitb/Employee/Register')}>Register</button>
      <button onClick={() => navigate('/iiitb/Employee/Login')}>Login</button>
    </div>
  );
}

export default Home;
