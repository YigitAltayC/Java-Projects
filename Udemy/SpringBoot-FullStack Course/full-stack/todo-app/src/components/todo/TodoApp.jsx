import { useState } from 'react'
import { BrowserRouter, Routes, Route, useNavigate, useParams} from 'react-router-dom'

export default function TodoApp()
{
    return (
        <div className="todoApp">
            <BrowserRouter>
                <Routes>
                    { /*These tags on below are for Routing to different parts of the application.*/}
                    <Route path='/' element={ <LoginComponent /> }></Route>
                    <Route path='/login' element={ <LoginComponent /> }></Route>

                    { /* Routing to another component with a parameter */}
                    <Route path='/welcome/:username' element={ <WelcomeComponent /> }></Route>

                    { /* Invalid routing, routing to error page. */}
                    <Route path='*' element={ <ErrorMessageComponent /> }></Route>
                </Routes>
            
            </BrowserRouter>
            
        </div>
    )
}

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
    const [showSuccessMessage, setShowSuccessMessage] = useState(false)
    const [showErrorMessage, setShowErrorMessage] = useState(false)

    // Navigate function delegate.
    const navigate = useNavigate();


    function handleUsernameChange(event) {
        setUsername(event.target.value)
    }

    function handlePasswordChange(event) {
        setPassword(event.target.value)
    }

    function handleSubmit() {
        if(username==="root" && password==="1234"){
            setShowSuccessMessage(true);
            setShowErrorMessage(false);
            console.log("success")

            // Using navigate function with dynamic $ parameter.
            navigate(`/welcome/${username}`)
        } else {
            setShowSuccessMessage(false);
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
            {showSuccessMessage && <div className="successMessage">Authenticated Successfully</div>}
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


function WelcomeComponent(){

    // useParams works as a hook for URL parameters.
    // This actually indicates that, welcome component should have an username parameter
    const { username } = useParams();

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username} !</h1>
            <div>
                Welcome to our website, enjoy your time !
            </div>
        </div>
    )
}

function ErrorMessageComponent(){
    return (
        <div>
            <h2>Oops! I think the page you're looking does not exist.</h2>
            <div>
                Apologies for 404. Reach out to our team at 0ABC-DEF-GH-IJ
            </div>
        </div>
    )
}