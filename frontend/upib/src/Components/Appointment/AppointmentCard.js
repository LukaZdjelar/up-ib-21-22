import styles from "./AppointmentCard.module.css";
import { useNavigate } from "react-router-dom";

const AppointmentCard = (props) => {
  const navigate = useNavigate();

  const appointmentListClickHandler = () =>{
    navigate(String(props.id));
  }

  return (
    <li className={styles.appointment} onClick={appointmentListClickHandler}>
      <div>
        <p>{props.doctorFirstname}</p>
        <p>{props.doctorLastname}</p>
        <p>{props.dateAndTime}</p>
        <p>{props.price}</p>
        <p>{props.duration}</p>
        <p>{props.clinicName}</p>
      </div>
    </li>
  );
};

export default AppointmentCard;
