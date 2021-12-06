import { Route, Routes } from "react-router-dom";
import "./App.css";
import Home from "./Components/LoginRegister/Home";
import Login from "./Components/LoginRegister/Login";
import UserProvider from "./store/UserProvider";

function App() {
  return (
    <UserProvider>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
      </Routes>
    </UserProvider>
  );
}

export default App;
