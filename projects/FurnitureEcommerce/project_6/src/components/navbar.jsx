import React from 'react'
import {Link} from "react-router-dom"
import {House, ShoppingCart, IdentificationBadge, Receipt} from "phosphor-react"
import "./navbar.css";
const  iconSize = 50;

export const NavBar = () => {
  return (
    <div className ="navbar">
        <div className = "links">
            <Link to="/"> <House size={iconSize}/> </Link>

            <Link to="/checkout"> <ShoppingCart  size={iconSize}/> </Link>

            <Link to="/login"> <IdentificationBadge  size={iconSize}/> </Link>

            {/* <Link to="/history"> <Receipt  size={iconSize}/> </Link> */}

        </div>
    </div>
  )
}
