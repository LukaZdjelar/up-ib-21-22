import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import BigCard from "../UI/BigCard";
import styles from "./Clinic.module.css";

const Clinic = (props) => {
  const [clinic, setClinic] = useState([]);
  const params = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/clinic/" + String(params.clinicId)
    );
    setClinic(response.data);
  };

  const appointmentsClickHandler = () => {
    navigate("appointments");
  };

  return (
    <BigCard>
      <div>
        <p>{clinic.name}</p>
        <p>{clinic.description}</p>
        <p>{clinic.address}</p>
      </div>
      <button className={styles.button} onClick={appointmentsClickHandler}>
        Appointments
      </button>
    </BigCard>
  );
};
export default Clinic;
