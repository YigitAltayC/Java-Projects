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

export const retrieveAllTodosForUsername
    = (username) => apiClient.get(`/users/${username}/todos`)
    // http://localhost:8080/users/root/todos

export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)
    // http://localhost:8080/users/root/todos