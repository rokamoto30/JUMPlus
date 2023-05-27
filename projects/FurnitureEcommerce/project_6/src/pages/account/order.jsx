import React from 'react'

export const Order = (props) => {
  const {Time, Total} = props.data;

  return (
    <div className = "orderItem">
            <text>{Time.date}</text>
            <text>{Time.time}</text>
            <text>${Total}</text>
        </div>
  )
}
