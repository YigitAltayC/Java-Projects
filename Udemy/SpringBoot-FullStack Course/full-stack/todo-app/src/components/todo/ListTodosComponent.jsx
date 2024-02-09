import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUsername } from "./api/TodoApiService"
import { useAuth } from "./security/AuthContext"

function ListTodosComponent(){

    const today = new Date()
    const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDay())


    const authContext = useAuth()

    const username = authContext.username


    const [todos, setTodos] = useState([])
    const [message, setMessage] = useState(null)

    useEffect( () => refreshTodos(), [] )

    function refreshTodos() {

        retrieveAllTodosForUsername(username)
        .then(response =>{
            console.log(response.data)
            setTodos(response.data)
        })
        .catch(error => console.log(error))
    }

    function deleteTodo(id) {
        deleteTodoApi(username, id)
        .then(
            () => { 
                setMessage(`Delete of todo with ${id} successfully happened.`)
                refreshTodos() 
            }
        )
        .catch(error => console.log(error))

    }

    return (
        // container className belongs to boostrap
        <div className='container'>
            <h1>Tasks you want to accomplish!</h1>
            {message && <div className="alert alert-warning">{message}</div> }
            <div>
                
                { /* container className belongs to boostrap */ }
                <table className="table">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Description</th>
                            <th>Is Done?</th>
                            <th>Deadline Date</th>
                            <th></th>
                        </tr>

                    </thead>
                    <tbody>
                    {
                        todos.map(
                            todo => (
                                <tr key={todo.id}>
                                    <td>{todo.id}</td>
                                    <td>{todo.description}</td>
                                    <td>{todo.done.toString()}</td>
                                    <td>{todo.targetDate.toString()}</td>
                                    <td> <button className="btn btn-warning" onClick={() => deleteTodo(todo.id)}>Delete</button></td>
                                </tr>
                            )
                        )
                    }
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default ListTodosComponent