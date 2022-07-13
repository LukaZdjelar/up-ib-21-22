import {Navigate, Route, Routes} from "react-router-dom";
import "./App.css";
import {RouteService} from "./Service/RouteService";

function App() {
    return (
        <>
            <Routes>
                {RouteService.getAllowedRoutes().map(route => {
                    return <Route path={route.path} element={route.element}/>
                })}
                <Route path='*' element={<Navigate to={RouteService.redirect()} replace/>}/>
            </Routes>
        </>
        // <Routes>
        //     <Route path="/" element={<Login/>}/>
        //     <Route path="/home" element={<Home/>}/>
        //     <Route path="/clinics" element={<Clinics/>}/>
        //     <Route path="/clinics/:clinicId" element={<Clinic/>}/>
        //     <Route path="/clinics/:clinicId/:doctorId" element={<Appointments/>}/>
        //     <Route
        //         path="/clinics/:clinicId/:doctorId/:appointmentId"
        //         element={<Appointment/>}
        //     />
        //     <Route
        //         path="/clinics/:clinicId/:doctorId/:appointmentId/schedule"
        //         element={<ScheduleAppointment/>}
        //     />
        //     <Route path="/history" element={<AppointmentHistory/>}/>
        //     <Route path="/user/:userId" element={<User/>}/>
        //     <Route path="/user/:userId/edit" element={<EditUser/>}/>
        //     <Route path="/createAppointment" element={<CreateAppointment/>}/>
        //     <Route path="/calendar" element={<WorkCalendar/>}/>
        // </Routes>
    );
}

export default App;
