import { BrowserRouter, Routes, Route, useNavigate} from 'react-router-dom'
import "./TodoApp.css"

import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ListTodosComponent from './ListTodosComponent'
import ErrorMessageComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'

export default function TodoApp()
{
    return (
        <div className="todoApp">

            <BrowserRouter>
                <HeaderComponent />
                <Routes>
                    { /*These tags on below are for Routing to different parts of the application.*/}
                    <Route path='/' element={ <LoginComponent /> }></Route>
                    <Route path='/login' element={ <LoginComponent /> }></Route>

                    { /* Routing to another component with a parameter */}
                    <Route path='/welcome/:username' element={ <WelcomeComponent /> }></Route>

                    <Route path='/todos' element={ <ListTodosComponent /> }></Route>

                    <Route path='/logout' element={ <LogoutComponent /> }></Route>
                    { /* Invalid routing, routing to error page. */}
                    <Route path='*' element={ <ErrorMessageComponent /> }></Route>
                </Routes>

            </BrowserRouter>

            
        </div>
    )
}


