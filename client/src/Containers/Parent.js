import React, { Component } from 'react'

import Child from '../Components/Child'

export default class Parent extends Component {

    state={
        input:'Type something here!'
    }


    handleChange = event =>{
        this.setState({input: event.target.value}, ()=>{
            console.log('test input', this.state.input)
        })
    }


    render() {
        return (
            <div>
                <Child handleChange={this.handleChange} input = {this.state.input}/>
            </div>
        )
    }
}
