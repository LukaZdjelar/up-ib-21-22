import Card from "../UI/Card";
import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import styles from "./ScheduleAppointment.module.css";

const ScheduleAppointment = () => {
  const [appointment, setAppointment] = useState([]);
  const params = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/appointment/" + String(params.appointmentId)
    );
    setAppointment(response.data);
  };

  const scheduleClickHandler = () => {
    const checkup = {
      appointmentId: appointment.id,
      patientId: JSON.parse(localStorage.getItem("loggedUser")).id,
    };
    axios.post("http://localhost:8080/appointment/schedule", checkup);
    navigate("/home");
  };

  return (
    <Card>
      <p>Confirm appointment scheduling</p>
      <p>
        Dr. {appointment.doctorFirstname} {appointment.doctorLastname}
      </p>
      <p>Date: {appointment.dateAndTime}</p>
      <p>Duration: {appointment.duration} minutes</p>
      <p>Price: {appointment.price} RSD</p>
      <button className={styles.button} onClick={scheduleClickHandler}>
        Schedule
      </button>
    </Card>
  );
};

export default ScheduleAppointment;
