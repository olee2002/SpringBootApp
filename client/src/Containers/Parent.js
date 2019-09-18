import React, { Component } from 'react'

import SignInPage from '../Components/SignInPage'
import SignUpPage from '../Components/SignUpPage'
import { api } from '../Data/data'


export default class Parent extends Component {

    state={
        username:'',
        password:'',
        loggedIn: false,
        users: {},
        created: false,
        signup: false,
        err:''
    }

    async componentDidMount(){
        const response = await api.getUsers()
        this.setState({users: response && response.data}, ()=>{console.log('users', this.state.users)})
    }


    handleChange = (event, key) => this.setState({[key]: event.target.value, err:''})

    sendToSignUp = () => this.setState( {signup : true})

    handleSubmit = async () => {
        const {username, password} = this.state
        const response = await api.postAUser({username, password})
        if(response && response.status===200){
            this.setState({created: true})
        }
    }

    handleLogIn = async () => {
        const {username, password} = this.state
        console.log('payload', {username, password})
        const response = await api.getUserByUsername({username, password})
        console.log('response', response)
        if(response && response.data===1){
            this.setState({loggedIn: true})
        } else {
            this.setState({err:"Incorrect Credentials!"})
        }
    }

    render() {
        const { signup, err } = this.state
        return (
            <div className='app flex'>
           {!signup ? 
                <SignInPage
                handleChange={this.handleChange} 
                handleSubmit={this.handleSubmit}
                handleLogIn={this.handleLogIn}
                username = {this.state.username} 
                password = {this.state.password}
                loggedIn={this.state.loggedIn}
                created={this.state.created}
                sendToSignUp={this.sendToSignUp}
                err={err}
                /> 
                :
                <SignUpPage
                handleChange={this.handleChange} 
                handleSubmit={this.handleSubmit}
                handleLogIn={this.handleLogIn}
                username = {this.state.username} 
                password = {this.state.password}
                loggedIn={this.state.loggedIn}
                created={this.state.created}
                /> 
           }
            </div>
        )
    }
}
