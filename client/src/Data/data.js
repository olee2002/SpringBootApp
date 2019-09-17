import Axios from 'axios'

const host = 'http://localhost:8080/api/v1/users'

export const api = {
    getUsers(){
        return Axios.get(host)
    },
    getUserByUsername(user){
        console.log('usr', user)
        return Axios.post(`${host}/login`, user)
    },
    postAUser(user){
        return Axios.post(host, user)
    },
    updateAUser(user){
        return Axios.put(host, user)
    },
    deleteAUser(userId){
        return Axios.delete(host, userId)
    }
}
