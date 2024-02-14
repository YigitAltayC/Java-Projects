import axios from 'axios';
import { apiClient } from './ApiClient';


/**
 * To-create an axios object:
 * After we create an instance of an axios object,
 * We can use to get rid of the localhost URL on every service function.
 */


export const retrieveAllTodosForUsername
    = (username) => apiClient.get(`/users/${username}/todos`)
    // http://localhost:8080/users/root/todos

export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)
    // http://localhost:8080/users/root/todos

export const retrieveTodoApi
    = (username, id) => apiClient.get(`/users/${username}/todos/${id}`)
    // http://localhost:8080/users/root/todos

export const updateTodoApi
    = (username, id, todo) => apiClient.put(`/users/${username}/todos/${id}`, todo)
    // http://localhost:8080/users/root/todos


export const createTodoApi
    = (username, todo) => apiClient.post(`/users/${username}/todos`, todo)
    // http://localhost:8080/users/root/todos