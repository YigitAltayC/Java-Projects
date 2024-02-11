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

/*export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`)
*/

/*
 * Retrieve with authorization
 */
export const retrieveHelloWorldPathVariable
    = (username) => apiClient.get(`/hello-world/path-variable/${username}`, {
        headers: {
            // Authorization's value equals to the 
            Authorization: 'Basic cm9vdDpkdW1teQ=='
        }
    })

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basic-auth`, {
        headers: {
            // Authorization's value equals to the 
            Authorization: token
        }
    })