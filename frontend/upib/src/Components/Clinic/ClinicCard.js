import styles from "./ClinicCard.module.css";
import { useNavigate } from "react-router-dom";

const ClinicCard = (props) => {
  const navigate = useNavigate();

  const clinicListClickHandler = () =>{
    navigate(String(props.id));
  }

  return (
    <li className={styles.clinic} onClick={clinicListClickHandler}>
      <div>
        <p>{props.name}</p>
        <p>{props.address}</p>
        <p>{props.description}</p>
      </div>
    </li>
  );
};

export default ClinicCard;
