import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import BigCard from "../UI/BigCard";
import AppointmentCard from "../Appointment/AppointmentCard";
import axios from "axios";

const Appointments = () => {
    const [appointments, setAppointments] = useState([]);
    const params = useParams();

    useEffect(() => {
        sendGetRequest(); // eslint-disable-next-line
    }, []);

    const sendGetRequest = async () => {
        const response = await axios.get(
            "http://localhost:8080/clinic/" +
            String(params.clinicId) +
            "/" +
            String(params.doctorId)
        );
        response.data.sort((a, b) =>
            a.dateAndTime > b.dateAndTime ? 1 : b.dateAndTime > a.dateAndTime ? -1 : 0
        );
        setAppointments(response.data);
    };

    const appointmentList = appointments.map((appointment) => (
        <AppointmentCard
            key={appointment.id}
            id={appointment.id}
            doctorFirstname={appointment.doctorFirstname}
            doctorLastname={appointment.doctorLastname}
            dateAndTime={appointment.dateAndTime}
            price={appointment.price}
            duration={appointment.duration}
            clinicId={appointment.clinicId}
            clinicName={appointment.clinicName}
            free={appointment.free}
        />
    ));

    return (
        <BigCard>
            <ul>{appointmentList}</ul>
        </BigCard>
    );
};

export default Appointments;
