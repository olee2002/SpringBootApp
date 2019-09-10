import React, { Component } from 'react'

import Child from '../Components/Child'
import { api } from '../Data/data'

console.log('api', api)

export default class Parent extends Component {

    state={
        input:'Type something here!'
    }


    handleChange = (event, key) =>this.setState({[key]: event.target.value})


    render() {
        return (
            <div className='app flex'>
                <Child handleChange={this.handleChange} input = {this.state.input}/>
            </div>
        )
    }
}
