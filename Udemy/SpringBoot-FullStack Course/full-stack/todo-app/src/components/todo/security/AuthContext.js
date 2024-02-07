import { useState } from 'react'
import { createContext, useContext } from "react";

// 1. Create a context
export const AuthContext = createContext()

// 2. Create a hook so anyone can use this context
export const useAuth = () => useContext(AuthContext)

// 2. Share the created context with other components 

export default function AuthProvider({children}){

    const [isAuthenticated, setAuthenticated] = useState(false)

    const valuesToBeShared = {isAuthenticated, login, logout}

    function login(username, password) {
        if(username==="root" && password==="1234"){
            setAuthenticated(true)
            return true
        } else {
            setAuthenticated(false)
            return false
        }
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