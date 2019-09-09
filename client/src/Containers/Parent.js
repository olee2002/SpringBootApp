import React, { Component } from 'react'

import Child from '../Components/Child'

export default class Parent extends Component {

    state={
        input:'Type something here!'
    }


    handleChange = (event, key) =>{
        this.setState({[key]: event.target.value})
    }


    render() {
        return (
            <div className='flex'>
                <Child handleChange={this.handleChange} input = {this.state.input}/>
            </div>
        )
    }
}
