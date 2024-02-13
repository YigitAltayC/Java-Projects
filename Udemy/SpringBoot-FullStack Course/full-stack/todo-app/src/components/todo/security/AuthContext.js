import { useState } from 'react'
import { createContext, useContext } from "react";
import { executeBasicAuthenticationService, executeJwtAuthenticationService } from '../api/AuthenticationApiService';
import { apiClient } from '../api/ApiClient';

// 1. Create a context
export const AuthContext = createContext()

// 2. Create a hook so anyone can use this context
export const useAuth = () => useContext(AuthContext)

// 3. Share the created context with other components 

export default function AuthProvider({children}){

    const [isAuthenticated, setAuthenticated] = useState(false)
    
    const [username, setUsername] = useState(null)
    const [token, setToken] = useState(null)

    const valuesToBeShared = {isAuthenticated, login, logout, username, token}


    
    /**
     * Without auth
     */
    /*function login(username, password) {
        if(username==="root" && password==="1234"){
            setAuthenticated(true)
            setUsername(username)
            return true
        } else {
            setAuthenticated(false)
            setUsername(null)
            return false
        }
    } */

    // With auth
    /*
    async function login(username, password) {

        const baToken = 'Basic ' + window.btoa(username + ":" + password)

        /* Wait for successful response. 
         * Don't execute this method again 
         * until this method is completely executed.
         */
        /*try {
            const response = await executeBasicAuthenticationService(baToken)
            

            if(response.status==200){
                setAuthenticated(true)
                setToken(baToken)
                setUsername(username)

                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = baToken
                        return config
                    }
                )


                return true
            } else {
                logout()
                return false
            }
        } catch(error) {
            logout()
            return false
        }
        /** 
         * Will be completed after setting full auth.
        if(executeBasicAuthenticationService){
            setAuthenticated(true)
            setUsername(username)
            return true
        } else {
            setAuthenticated(false)
            setUsername(null)
            return false
        }
        */
    //}

    async function login(username, password) {



        /* Wait for successful response. 
         * Don't execute this method again 
         * until this method is completely executed.
         */
        try {
            const response = await executeJwtAuthenticationService(username, password)
            

            if(response.status==200){
                const jwtToken = 'Bearer ' + response.data.token
                
                setAuthenticated(true)
                setUsername(username)
                setToken(jwtToken)


                apiClient.interceptors.request.use(
                    (config) => {
                        console.log('intercepting and adding a token')
                        config.headers.Authorization = jwtToken
                        return config
                    }
                )


                return true
            } else {
                logout()
                return false
            }
        } catch(error) {
            logout()
            return false
        }
        /** 
         * Will be completed after setting full auth.
        if(executeBasicAuthenticationService){
            setAuthenticated(true)
            setUsername(username)
            return true
        } else {
            setAuthenticated(false)
            setUsername(null)
            return false
        }
        */
    }

    function logout(){
        setAuthenticated(false)
        setToken(null)
        setUsername(null)
    }

    return (
        <AuthContext.Provider value = {valuesToBeShared}>
            {children}
        </AuthContext.Provider>
    )
}