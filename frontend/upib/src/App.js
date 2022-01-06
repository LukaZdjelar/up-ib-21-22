import { Route, Routes } from "react-router-dom";
import "./App.css";
import Clinic from "./Components/Clinic/Clinic";
import Clinics from "./Components/Clinic/Clinics";
import Home from "./Components/LoginRegister/Home";
import Login from "./Components/LoginRegister/Login";
import User from "./Components/User/User";
import UserProvider from "./store/UserProvider";
import EditUser from "./Components/User/EditUser";
import Appointments from "./Components/Appointment/Appointments";
import Appointment from "./Components/Appointment/Appointment";
import CreateAppointment from "./Components/Appointment/CreateAppointment";
import ScheduleAppointment from "./Components/Appointment/ScheduleAppointment";

function App() {
  return (
    <UserProvider>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/home" element={<Home />} />
        <Route path="/clinics" element={<Clinics />} />
        <Route path="/clinics/:clinicId" element={<Clinic />} />
        <Route path="/clinics/:clinicId/:doctorId" element={<Appointments />} />
        <Route
          path="/clinics/:clinicId/:doctorId/:appointmentId"
          element={<Appointment />}
        />
        <Route
          path="/clinics/:clinicId/:doctorId/:appointmentId/schedule"
          element={<ScheduleAppointment />}
        />
        <Route path="/user/:userId" element={<User />} />
        <Route path="/user/:userId/edit" element={<EditUser />} />
        <Route path="/createAppointment" element={<CreateAppointment />} />
      </Routes>
    </UserProvider>
  );
}

export default App;
