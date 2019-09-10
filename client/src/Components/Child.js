import React, { Component } from 'react'

export default class Child extends Component {

    state = {
        input : this.props.input
    }

    render() {
        const { input } = this.state
        return (
            <>
            <div className='card' style={{boxShadow:'3px 3px 2px #8d8d8d'}}>
            <h5 class="m-3 flex-col">Please Log In to proceed!</h5>
            <div>
                <small class="m-3">User Name</small>
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
                <small class="m-3">Password</small>
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
            <button className='btn btn-primary m-3'>Submit</button>
            </div>
            </>
        )
    }
}