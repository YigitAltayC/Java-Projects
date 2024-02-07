import { useState } from 'react'
import { useNavigate} from 'react-router-dom'
import { useAuth } from './security/AuthContext'

function LoginComponent(){
    
    /**
     * States for:
     *    Username
     *    Password
     *    Track of success msg.
     *    And track of error msg.
     */
    const [username, setUsername] = useState('root')
    const [password, setPassword] = useState('')
    const [showErrorMessage, setShowErrorMessage] = useState(false) 

    // Navigate function delegate.
    const navigate = useNavigate()
    
    const authContext = useAuth()

    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    function handleSubmit() {
        if(authContext.login(username, password)){
            /** 
            setShowSuccessMessage(true);
            setShowErrorMessage(false);
            */
            console.log("success")

            // Using navigate function with dynamic $ parameter.
            navigate(`/welcome/${username}`)

        } else {
            
            // setShowSuccessMessage(false);
            setShowErrorMessage(true);

            console.log("failed")
           // navigate("/login")
        }
    }

    /**
     * 
     * If we use these functions as a component above, we might get null.
     * In order to prevent null exceptions, we'll be using like down below (line 98)
     */

    /*
    
    function SuccessMessageComponent() {
        if(showSuccessMessage){
            return <div className="successMessage">Authenticated Successfully</div>
        }

        return null
    }

    function ErrorMessageComponent() {
        if(showErrorMessage){
            return <div className="errorMessage">Authentication Failed. Please check your credentials.</div>

        }
        return null
    }*/

    return (

        <div className="Login">

            { /* If first one is true, return second */}
            {showErrorMessage && <div className="errorMessage">Authentication Failed. Please check your credentials.</div>}
            <div className="LoginForm">
                <div>
                    <label>User Name:</label>
                    <input type="text" 
                           name="username" 
                           value = {username}
                           onChange={handleUsernameChange}/>
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" 
                           name="password"
                           value={password}
                           onChange={handlePasswordChange}/>
                </div>
                <div>
                    <button type="button" 
                            name="login"
                            onClick={handleSubmit}>Login</button>
                </div>
            </div>
        </div>
    )

}



export default LoginComponent