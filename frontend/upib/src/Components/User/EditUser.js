const EditUser = () =>{
    return <p>Edit user {JSON.parse(localStorage.getItem("loggedUser")).id}</p>
}

export default EditUser;