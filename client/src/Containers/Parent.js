import React, { Component } from 'react'

import SignInPage from '../Components/SignInPage'
import { api } from '../Data/data'


export default class Parent extends Component {

    state={
        username:'',
        password:'',
        loggedIn: false,
        users: {},
        created: false
    }

    async componentDidMount(){
        const response = await api.getUsers()
        this.setState({users: response && response.data}, ()=>{console.log('users', this.state.users)})
    }


    handleChange = (event, key) => this.setState({[key]: event.target.value})

    handleSubmit = async () => {
        const {username, password} = this.state
        const response = await api.postAUser({username, password})
        if(response && response.status===200){
            this.setState({created: true})
        }
    }

    handleLogIn = async () => {
        const {username, password} = this.state
        const response = await api.getUserByUsername({username, password})
        console.log('response', response)
        if(response && response.status===200){
            this.setState({loggedIn: true})
        }
    }


    render() {
        return (
            <div className='app flex'>
                <SignInPage
                handleChange={this.handleChange} 
                handleSubmit={this.handleSubmit}
                handleLogIn={this.handleLogIn}
                username = {this.state.username} 
                password = {this.state.password}
                loggedIn={this.state.loggedIn}
                created={this.state.created}
                />
            </div>
        )
    }
}
