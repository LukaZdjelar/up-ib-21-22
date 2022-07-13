import Login from "../Components/Home/Login";
import Home from "../Components/Home/Home";
import Clinics from "../Components/Clinic/Clinics";
import Clinic from "../Components/Clinic/Clinic";
import Appointments from "../Components/Appointment/Appointments";
import Appointment from "../Components/Appointment/Appointment";
import ScheduleAppointment from "../Components/Appointment/ScheduleAppointment";
import AppointmentHistory from "../Components/Appointment/AppointmentHistory";
import User from "../Components/User/User";
import EditUser from "../Components/User/EditUser";
import CreateAppointment from "../Components/Appointment/CreateAppointment";
import WorkCalendar from "../Components/Doctor/WorkCalendar";
import {TokenService} from "./TokenService";

const unauthorizedRoutes = [
    {path: "/", element: <Login/>},
]
const patientRoutes = [
    {path: "/home", element: <Home/>},
    {path: "/clinics", element: <Clinics/>},
    {path: "/clinics/:clinicId", element: <Clinic/>},
    {path: "/clinics/:clinicId/:doctorId", element: <Appointments/>},
    {path: "/clinics/:clinicId/:doctorId/:appointmentId", element: <Appointment/>},
    {path: "/clinics/:clinicId/:doctorId/:appointmentId/schedule", element: <ScheduleAppointment/>},
    {path: "/history", element: <AppointmentHistory/>},
    {path: "/user", element: <User/>},
    {path: "/user/edit", element: <EditUser/>},
]
const doctorRoutes = [
    {path: "/home", element: <Home/>},
    {path: "/calendar", element: <WorkCalendar/>},
]
const adminRoutes = [
    {path: "/home", render: <Home/>},
    {path: "/createAppointment", render: <CreateAppointment/>},
]

const getAllowedRoutes = () => {
    let role = TokenService.getUserType()
    if (role === null) {
        return unauthorizedRoutes;
    }
    return getRoutesFromRole(role);
}

const getRoutesFromRole = (role) => {
    if (role === "PATIENT") {
        return patientRoutes
    } else if (role === "DOCTOR") {
        return doctorRoutes
    } else if (role === "ADMINISTRATOR") {
        return adminRoutes
    }
}

const redirect = () => {
    if (TokenService.getUserType() == null) {
        return "/"
    } else {
        return "/home"
    }
}
export const RouteService = {
    getAllowedRoutes,
    getRoutesFromRole,
    redirect,
}