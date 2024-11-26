/* eslint-disable no-unused-vars */
import React, { useEffect,useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { loginEmployee } from '../httputils/employeeApi';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();
  const location = useLocation();

  // useEffect(()=>{
  //   if(location.state && location.state.email){
  //     setEmail(location.state.email);
  //   }
  // },[location.state]);
  //Because of this piece of "code" i was unable to login even when the password was correct.

  const handleLogin = async () => {
    try {
      const response = await loginEmployee({ email, password });
      const token = response.data;  
      localStorage.setItem('token', token.trim());
      console.log(token)
      navigate(`/iiitb/Employee/Profile/${email}`);
    } catch (error) {
      alert('Login failed. Please check credentials');
      console.log(location.state.email);
      console.error(error);
      setPassword('');
      setEmail('');
      navigate('/iiitb/Employee/Login', { replace: true });
    }
  };

  return (
    <div className='container'>
      <h2>Login</h2>

      <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
      <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} />
      <button onClick={handleLogin}>Login</button>
    </div>
  );
}

export default Login;
