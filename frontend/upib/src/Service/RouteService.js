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

const RouteService = () => {
    let unauthorizedRoutes = [
        {path: "/", component: Login},
    ]
    let patientRoutes = [
        {path: "/home", component: Home},
        {path: "/clinics", component: Clinics},
        {path: "/clinics/:clinicId", component: Clinic},
        {path: "/clinics/:clinicId/:doctorId", component: Appointments},
        {path: "/clinics/:clinicId/:doctorId/:appointmentId", component: Appointment},
        {path: "/clinics/:clinicId/:doctorId/:appointmentId/schedule", component: ScheduleAppointment},
        {path: "/history", component: AppointmentHistory},
        {path: "/user/:userId", component: User},
        {path: "/user/:userId/edit", component: EditUser},
    ]
    let doctorRoutes = [
        {path: "/home", component: Home},
        {path: "/calendar", component: WorkCalendar},
    ]
    let adminRoutes = [
        {path: "/home", component: Home},
        {path: "/createAppointment", component: CreateAppointment},
    ]
}