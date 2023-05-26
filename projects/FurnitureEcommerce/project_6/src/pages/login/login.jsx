import React, {useContext, useState} from 'react'
import {UserContext} from "../../context/context"
import {Link} from "react-router-dom"
import "./login.css";
import {User, LockSimple} from "phosphor-react"
const  iconSize = 25;





export const Login = (props) => {
  const{curUser, setUser} = useContext(UserContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const errorHandler = (error) => {
    console.log(error);
  }

  const setCurUserForAll = (username) => {
    setUser(username);
    props.data(username);
  }

  // const toggleRegister = () => {
  //   props.setLoginState(false);
  // }

  const login = (e) => {
    e.preventDefault();

      fetch(`http://localhost:3000/users?username=${username}&password=${password}`)
      .then(response => response.json())
      .then(json => {
        setCurUserForAll(json[0].username)
      })
      .catch(error => {
        setUser()
        errorHandler(error)
      });
  }

  

  return (
    <div className="login">
      <form onSubmit={login}>
      <div className="title">login</div>

        {/* <label htmlFor="username">username</label> */}
        <input type="text" placeholder="username" id="username" value={username} onChange={(e) => setUsername(e.target.value)}></input><br/>
        <User className="icon" size={iconSize}/>

        {/* <label htmlFor="password">password</label> */}
        <input type="password" placeholder="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)}></input><br/>
        <LockSimple className="icon" size={iconSize}/>

        <button type="submit" className="submit" id="submitButton">Login</button>
        <Link to="/register" className="link"> Or Register Here! </Link>


      </form>
      {/* <button type="submit" id="submitButton">Or Register Here!</button> */}
    </div>
  
  )
}
