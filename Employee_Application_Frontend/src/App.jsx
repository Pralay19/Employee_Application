/* eslint-disable no-unused-vars */
import React from 'react'
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Home from './components/Home';
import Login from './components/Login';
import Register from './components/Register';
import Profile from './components/Profile';
import UpdateProfile from './components/UpdateProfile';


const App = () => {
  return (
    <Router>
    <Routes>
      <Route path="/iiitb/Employee" element={<Home />} />
      <Route path="/iiitb/Employee/Login" element={<Login />} />
      <Route path="/iiitb/Employee/Register" element={<Register />} />
      <Route path="/iiitb/Employee/Profile/:email" element={<Profile />} />
      <Route path="/iiitb/Employee/:email/updateinfo" element={<UpdateProfile />} />
    </Routes>
  </Router>
  );
}

export default App