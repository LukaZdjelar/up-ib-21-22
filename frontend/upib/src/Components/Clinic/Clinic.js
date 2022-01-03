import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import BigCard from "../UI/BigCard";
import styles from "./Clinic.module.css";
import DoctorCard from "../Doctor/DoctorCard";
import Card from "../UI/Card";

const Clinic = (props) => {
  const [clinic, setClinic] = useState([]);
  const [doctors, setDoctors] = useState([]);
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

    const responseDoc = await axios.get(
      "http://localhost:8080/user/doctors/" + String(params.clinicId)
    );
    setDoctors(responseDoc.data);
  };

  const appointmentsClickHandler = () => {
    navigate("appointments");
  };

  const doctorsList = doctors.map((doctor) => (
    <DoctorCard
      key={doctor.id}
      id={doctor.id}
      firstname={doctor.firstname}
      lastname={doctor.lastname}
    />
  ));

  return (
    <BigCard>
      <div>
        <p>{clinic.name}</p>
        <p>{clinic.description}</p>
        <p>{clinic.address}</p>
      </div>

      <BigCard>
        <ul>{doctorsList}</ul>
      </BigCard>

      {/* <button className={styles.button} onClick={appointmentsClickHandler}>
        Appointments
      </button> */}
    </BigCard>
  );
};
export default Clinic;
