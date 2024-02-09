import axios from 'axios';


/**
 * To-create an axios object:
 * After we create an instance of an axios object,
 * We can use to get rid of the localhost URL on every service function.
 */
const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
)

export const retrieveHelloWorldBean 
    = () => apiClient.get('http://localhost:8080/hello-world-bean')

export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`)