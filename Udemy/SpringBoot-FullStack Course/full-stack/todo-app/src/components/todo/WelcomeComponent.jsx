import { useState } from 'react'
import {useParams, Link} from 'react-router-dom'

import axios from 'axios';

function WelcomeComponent(){

    // useParams works as a hook for URL parameters.
    // This actually indicates that, welcome component should have an username parameter
    const { username } = useParams();
    const [message, setMessage] = useState(null)


    function callHelloWorldRestApi() {
        console.log("called")

        axios.get('http://localhost:8080/hello-world')
            .then( (response) => successfulResponse(response) )
            .catch( (error) => console.log(error) )
            .finally( () => console.log("cleanup") )
    }

    function successfulResponse(response){
        console.log(response)
        setMessage(response.data)
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