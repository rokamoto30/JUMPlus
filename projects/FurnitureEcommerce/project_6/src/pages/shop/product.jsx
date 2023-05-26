import React, {useContext, useEffect} from 'react'
import {ShopContext} from "../../context/shopContext"


export const Product = (props) => {
  
  const {id, name, price, image, source} = props.data;
  const{addToCart, cartItems} = useContext(ShopContext);
  const itemCount = cartItems[id];
  return (
    <div className = "product">
        <img src={image}/>
        <div className ="description">
            <p className ="name">{name}</p>
            <p className ="price"> ${price}</p>
        </div>
        <button className = "buy" onClick={() => addToCart(id)}> Buy {itemCount} </button>

    </div>
  )
}
