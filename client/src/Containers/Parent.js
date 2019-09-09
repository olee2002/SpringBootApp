import React, { Component } from 'react'

import Child from '../Components/Child'

export default class Parent extends Component {
    render() {
        return (
            <div>
                <Child/>
            </div>
        )
    }
}
