import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import "./TodoApp.css"

import LogoutComponent from './LogoutComponent'
import HeaderComponent from './HeaderComponent'
import ListTodosComponent from './ListTodosComponent'
import ErrorMessageComponent from './ErrorComponent'
import WelcomeComponent from './WelcomeComponent'
import LoginComponent from './LoginComponent'
import TodoComponent from './TodoComponent'
import AuthProvider, { useAuth } from './security/AuthContext'

{ /** 
    A function component with parameter children, should contain other tags as parameter 
    Like this:
    <AuthenticatedRoute>
        <other tag>
        <another tag>
        ...
    <AuthenticatedRoute/>
*/}

function AuthenticatedRoute({children}) {
    const authContext = useAuth()

    if(authContext.isAuthenticated)
        return children

    return <Navigate to="/" />
}


export default function TodoApp()
{
    { /*These tags on below are for Routing to different parts of the application.*/}

    return (
        <div className="todoApp">

            <AuthProvider>
                <BrowserRouter>
                    <HeaderComponent />
                    <Routes>
                        { /*These tags on below are for Routing to different parts of the application.*/}
                        <Route path='/' element={ <LoginComponent /> }></Route>
                        <Route path='/login' element={ <LoginComponent /> }></Route>

                        { /* Routing to another component with a parameter */}
                        <Route path='/welcome/:username' element={
                            <AuthenticatedRoute>
                                <WelcomeComponent />
                            </AuthenticatedRoute>
                              
                        }/>

                        <Route path='/todos' element={
                             <AuthenticatedRoute>
                                <ListTodosComponent />
                             </AuthenticatedRoute> 
                              
                        }/>

                        <Route path='/todo/:id' element={
                             <AuthenticatedRoute>
                                <TodoComponent />
                             </AuthenticatedRoute> 
                              
                        }/>

                        <Route path='/logout' element={ 
                            <AuthenticatedRoute>
                                <LogoutComponent />
                             </AuthenticatedRoute> 
                        }/>
                        { /* Invalid routing, routing to error page. */}
                        <Route path='*' element={ <ErrorMessageComponent /> }></Route>
                    </Routes>

                </BrowserRouter>
            </AuthProvider>

            
        </div>
    )
}


