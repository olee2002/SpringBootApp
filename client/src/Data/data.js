import Axios from 'axios'

const host = '/http://5000/api/v1/users'

export const api = {
    getUsers(){
        return Axios.get(host)
    },
    postAUser(user){
        return Axios.post(host, user)
    },
    updateAUser(user){
        return Axios.put(host,user)
    },
    deleteAUser(userId){
        return Axios.delete(host, userId)
    }
}
