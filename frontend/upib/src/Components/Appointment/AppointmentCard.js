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
        <p>{props.doctorFirstname} {props.doctorLastname}</p>
        <p>{props.dateAndTime}</p>
      </div>
    </li>
  );
};

export default AppointmentCard;
