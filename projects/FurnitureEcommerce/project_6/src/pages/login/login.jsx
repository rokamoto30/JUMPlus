import React, {useContext, useState} from 'react'
import {UserContext} from "../../context/context"
import {Link, useNavigate} from "react-router-dom"




export const Login = () => {
  const{curUser, setUser} = useContext(UserContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const errorHandler = (error) => {
    console.log(error);
  }

  const login = (e) => {
    e.preventDefault();

      fetch(`http://localhost:3000/users?username=${username}&password=${password}`)
      .then(response => response.json())
      .then(json => {
        setUser(json[0].username)
      })
      .catch(error => {
        setUser()
        errorHandler(error)
      });
  }

  

  return (
    <div className="login">
      <div>login</div>
      <form onSubmit={login}>
        <label htmlFor="username">username</label>
        <input type="text" placeholder="username" id="username" value={username} onChange={(e) => setUsername(e.target.value)}></input>
        <label htmlFor="password">password</label>
        <input type="password" placeholder="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)}></input>
        <button type="submit" id="submitButton">Login</button>
        <Link to="/register"> Or Register Here! </Link>


      </form>
    </div>
  
  )
}
