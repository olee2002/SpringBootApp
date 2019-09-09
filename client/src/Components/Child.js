import React, { Component } from 'react'

export default class Child extends Component {

    state = {
        input : this.props.input
    }

    render() {
        const { input } = this.state
        return (
            <div>
                <h4 class="m-3">Enter Input</h4>
                <input 
                type="text" 
                class="form-control m-3" 
                placeholder="Username" 
                style={{width: '30vw'}}
                onChange = {(e)=> this.props.handleChange(e)}
                aria-label="Username" 
                aria-describedby="basic-addon1"
                />
            </div>
        )
    }
}