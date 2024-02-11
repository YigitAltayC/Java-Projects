import { useState } from 'react'
import { createContext, useContext } from "react";
import { executeBasicAuthenticationService } from '../api/HelloWorldApiService';

// 1. Create a context
export const AuthContext = createContext()

// 2. Create a hook so anyone can use this context
export const useAuth = () => useContext(AuthContext)

// 3. Share the created context with other components 

export default function AuthProvider({children}){

    const [isAuthenticated, setAuthenticated] = useState(false)
    
    const [username, setUsername] = useState(null)

    const valuesToBeShared = {isAuthenticated, login, logout, username}


    
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
    function login(username, password) {

        const baToken = 'Basic ' + window.btoa(username + ":" + password)

        executeBasicAuthenticationService(baToken)
        .then(response => console.log(response))
        .catch(error => console.log(error))

        setAuthenticated(false)

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
    }

    return (
        <AuthContext.Provider value = {valuesToBeShared}>
            {children}
        </AuthContext.Provider>
    )
}