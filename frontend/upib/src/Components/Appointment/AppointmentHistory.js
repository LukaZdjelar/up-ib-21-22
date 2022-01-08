import { useEffect, useState } from "react";
import BigCard from "../UI/BigCard";
import AppointmentCard from "../Appointment/AppointmentCard";
import axios from "axios";

const AppointmentHistory = () => {
  const [appointments, setAppointments] = useState([]);

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/appointment/history/" +
        String(JSON.parse(localStorage.getItem("loggedUser")).id)
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

export default AppointmentHistory;
