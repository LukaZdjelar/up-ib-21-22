import { Route, Routes } from "react-router-dom";
import "./App.css";
import Clinic from "./Components/Clinic/Clinic";
import Clinics from "./Components/Clinic/Clinics";
import Home from "./Components/Home/Home";
import Login from "./Components/Home/Login";
import User from "./Components/User/User";
import EditUser from "./Components/User/EditUser";
import Appointments from "./Components/Appointment/Appointments";
import Appointment from "./Components/Appointment/Appointment";
import CreateAppointment from "./Components/Appointment/CreateAppointment";
import ScheduleAppointment from "./Components/Appointment/ScheduleAppointment";
import AppointmentHistory from "./Components/Appointment/AppointmentHistory";
import WorkCalendar from "./Components/Doctor/WorkCalendar";

function App() {
  return (
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
      <Route path="/history" element={<AppointmentHistory />} />
      <Route path="/user/:userId" element={<User />} />
      <Route path="/user/:userId/edit" element={<EditUser />} />
      <Route path="/createAppointment" element={<CreateAppointment />} />
      <Route path="/calendar" element={<WorkCalendar />} />
    </Routes>
  );
}

export default App;
