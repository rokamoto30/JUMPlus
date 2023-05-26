import React from 'react'
import { useState, useEffect } from 'react';
import {Product} from "./product";
import "./shop.css";
export const Shop = () => {
    const [products, setProducts] = useState([]);

    useEffect(() => { // make api call and parse as json
        fetch('http://localhost:3000/furniture')
        .then(response => response.json())
        .then(json => setProducts(json))
        .catch(error => console.error(error));
    }, []);


  return (
    <div className = "shop">
        <div>
            <h1 className = "title"> Shopping </h1>
        </div>
        <div className = "products"> 

            {/* map products to product component */}
            {products.map((product) => <Product data={product}/>)} 

        </div>
    </div>
  )
}
