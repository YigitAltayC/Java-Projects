import {useParams, Link} from 'react-router-dom'


function WelcomeComponent(){

    // useParams works as a hook for URL parameters.
    // This actually indicates that, welcome component should have an username parameter
    const { username } = useParams();

    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username} !</h1>
            <div>
                Manage your todo list - <Link to="/todos">Go here!</Link>
            </div>
        </div>
    )
}

export default WelcomeComponent