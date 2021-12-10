import { Route, Routes } from "react-router-dom";
import "./App.css";
import Clinic from "./Components/Clinic/Clinic";
import Clinics from "./Components/Clinic/Clinics";
import Home from "./Components/LoginRegister/Home";
import Login from "./Components/LoginRegister/Login";
import User from "./Components/User/User";
import UserProvider from "./store/UserProvider";
import EditUser from "./Components/User/EditUser";

function App() {
  return (
    <UserProvider>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/clinics" element={<Clinics />} />
        <Route path="/clinics/:clinicId" element={<Clinic />} />
        <Route path="/user/:userId" element={<User />} />
        <Route path="/user/:userId/edit" element={<EditUser />} />
      </Routes>
    </UserProvider>
  );
}

export default App;
