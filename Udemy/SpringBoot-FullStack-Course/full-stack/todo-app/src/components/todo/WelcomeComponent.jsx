import { useState } from 'react'
import {useParams, Link} from 'react-router-dom'
import { retrieveHelloWorldBean, retrieveHelloWorldPathVariable } from './api/HelloWorldApiService';
import axios from 'axios';
import { useAuth } from './security/AuthContext';


function WelcomeComponent(){

    // useParams works as a hook for URL parameters.
    // This actually indicates that, welcome component should have an username parameter
    const { username } = useParams();
    const [message, setMessage] = useState(null)
    const authContext = useAuth();

    function callHelloWorldRestApi() {
        console.log("called")

        retrieveHelloWorldPathVariable(username, authContext.token)
             .then( (response) => successfulResponse(response))
             .catch( (error) => console.log(error))
             .finally( () => console.log("cleanup"))
        
    }

    function successfulResponse(response){
        console.log(response)
        setMessage(response.data.message)
    }

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username} !</h1>
            <div>
                Manage your todo list - <Link to="/todos">Go here!</Link>
            </div>
            <div>
                <button className='btn btn-success m-5' onClick={callHelloWorldRestApi}>
                    Call Hello World Rest API
                </button>
            </div>
            <div className='text-info'>{message}</div>
        </div>
    )
}

export default WelcomeComponent