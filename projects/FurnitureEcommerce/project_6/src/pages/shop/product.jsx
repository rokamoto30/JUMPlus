import React, {useContext, useEffect} from 'react'
import {ShopContext} from "../../context/shopContext"


export const Product = (props) => {
  
  const {id, name, price, image, source} = props.data;
  const{addToCart, cartItems, removeFromCart} = useContext(ShopContext);
  const itemCount = cartItems[id];

  const display = () => {
    if (itemCount > 0){
      return itemCount;
    } else {
      return "Buy";
    }
  }
  return (
    <div className = "product">
        <img src={image}/>
        <div className ="description">
            <p className ="name">{name}</p>
            <p className ="price"> ${price}</p>
        </div>
        <div className = "buyButton" >
          <button className="subButton" onClick={() => removeFromCart(id)}>-</button>
          <text className = "buyText">{
            display()
          }</text>
          <button className="addButton" onClick={() => addToCart(id)}>+</button>
        </div>
        {/* <button className = "buy" onClick={() => addToCart(id)}> Buy {itemCount} </button> */}

    </div>
  )
}
