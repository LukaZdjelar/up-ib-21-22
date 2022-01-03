import styles from "./DoctorCard.module.css";
import { useNavigate } from "react-router-dom";

const ClinicCard = (props) => {
  const navigate = useNavigate();

  const doctorListClickHandler = () =>{
    navigate(String(props.id));
  }

  return (
    <li className={styles.doctor} onClick={doctorListClickHandler}>
      <div>
        <p>{props.firstname} {props.lastname}</p>
      </div>
    </li>
  );
};

export default ClinicCard;
