import { apiClient } from "./ApiClient";


export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basic-auth`, {
        headers: {
            // Authorization's value equals to the 
            Authorization: token
        }
    })

export const executeJwtAuthenticationService
    = (username, password) => 
        apiClient.post(`/authenticate`, {username, password})