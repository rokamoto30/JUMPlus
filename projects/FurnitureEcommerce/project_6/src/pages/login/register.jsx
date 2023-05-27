import React, {useContext, useState} from 'react'
import {UserContext} from "../../context/context"
import {Link} from "react-router-dom"
import {User, LockSimple} from "phosphor-react"
const  iconSize = 25;



export const Register = (props) => {
    const{curUser, setUser} = useContext(UserContext);
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");


    const errorHandler = (error) => {
        setError(error);
        console.log(error);
    }

    const setCurUserForAll = (username) => {
      setUser(username);
      props.data(username);
    }
    

    const register = (e) => {
        e.preventDefault();
        // useEffect(() => { // make api call and parse as json
          fetch(`http://localhost:3000/users?username=${username}&password=${password}`)
          .then(response => response.json())
          .then(json => {
            if(Object.keys(json).length === 0) {
                fetch('http://localhost:3000/users', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({username, password})
                })
                .then(response => response.json())
                .then(json => setCurUserForAll(json.username))
            } else {
                errorHandler("User already exists")
            }
          })
          .catch(error => console.log(error));
        // }, []);
      }

    // const register  = (e) => {
    //     e.preventDefault();
    //     fetch('http://localhost:3000/users?username=${username}?password=${password}')
    //     .then(response => {
    //       if(response.ok) {
    //         errorHandler("user already exists")
    //       } else if (response.status === 404) {
    //         fetch('http://localhost:3000/users', {
    //           method: 'POST',
    //           headers: {
    //             'Content-Type': 'application/json',
    //           },
    //           body: JSON.stringify({
    //             'username': {username},
    //             'password': {password}
    //           })
    //         })
    //         .then(response => response.json())
    //         .then(json => setUser(json.username))
    //         .catch(error => errorHandler(error))
    //       }
    //     })
    //     .catch(error => errorHandler(error))
    //   }

  return (
    <div className="login">
      {/* <div>register</div> */}
      <form onSubmit={register}>
      <div className="title">Register</div>

        {/* <label htmlFor="username">username</label> */}
        <input type="text" placeholder="username" id="username" value={username} onChange={(e) => setUsername(e.target.value)} required></input>
        <User className="icon" size={iconSize}/>

        {/* <label htmlFor="password">password</label> */}
        <input type="password" placeholder="password" id="password" value={password} onChange={(e) => setPassword(e.target.value)} required></input>
        <LockSimple className="icon" size={iconSize}/>

        <text className="errorText">{error}</text>
        <button type="submit" className = "submit" id="submitButton">Register</button>
        <Link to="/login" className="link"> Back To Login </Link>

      </form>
      
    </div>
  )
}
