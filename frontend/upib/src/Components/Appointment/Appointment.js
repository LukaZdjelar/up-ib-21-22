import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import BigCard from "../UI/BigCard";
import styles from "./Appointment.module.css";

const Appointment = (props) => {
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
    navigate("schedule");
  };

  return (
    <BigCard>
      <div>
        <p>
          Dr. {appointment.doctorFirstname} {appointment.doctorLastname}
        </p>
        <p>Date: {appointment.dateAndTime}</p>
        <p>Duration: {appointment.duration} minutes</p>
        <p>Price: {appointment.price} RSD</p>
      </div>
      <button className={styles.button} onClick={scheduleClickHandler}>
        Schedule
      </button>
    </BigCard>
  );
};

export default Appointment;
