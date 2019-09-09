import React, { Component } from 'react'

export default class Child extends Component {

    state = {
        input : this.props.input
    }

    render() {
        const { input } = this.state
        return (
            <>
            <div>
            <h4 class="m-3">Please Log In to proceed!</h4>
            <div>
                <h6 class="m-3">User Name</h6>
                <input 
                type="text" 
                class="form-control m-3" 
                placeholder="Username" 
                style={{width: '30vw'}}
                onChange = {e => this.props.handleChange(e, 'username')}
                aria-label="Username" 
                aria-describedby="basic-addon1"
                />
            </div>
            <div>
                <h6 class="m-3">Password</h6>
                <input 
                type="password" 
                class="form-control m-3" 
                placeholder="Password" 
                style={{width: '30vw'}}
                onChange = {e => this.props.handleChange(e, 'password')}
                aria-label="Password" 
                aria-describedby="basic-addon1"
                />
            </div>
            </div>
            </>
        )
    }
}