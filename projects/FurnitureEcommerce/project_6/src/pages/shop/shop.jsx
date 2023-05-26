import React from 'react'
import {useContext } from 'react';
import {UserContext, InventoryContext} from "../../context/context"
import {Product} from "./product";
import "./shop.css";
export const Shop = (props) => {
    // const{products, setProducts, curUser, setUser} = props.data;
    const{curUser, setUser} = useContext(UserContext);
    const{products} = useContext(InventoryContext);
    console.log(curUser)

  return (
    <div className = "shop">
        <div>
            <h1 className = "title"> Mykea </h1>
        </div>
        <div className = "products"> 

            {/* map products to product component */}
            {products.map((product) => <Product data={product}/>)} 

        </div>
    </div>
  )
}
