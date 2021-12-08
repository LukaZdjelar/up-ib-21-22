import axios from "axios";
import ClinicCard from "./ClinicCard";
import Card from "../UI/Card";
import { useEffect, useState } from "react";
import styles from "./Clinics.module.css";
import BigCard from "../UI/BigCard";

const Clinics = () => {
  const [clinics, setClinics] = useState([]);

  useEffect(() => {
    sendGetRequest();
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get("http://localhost:8080/clinic");
    setClinics(response.data);
  };

  const clinicList = clinics.map((clinic) => (
    <ClinicCard
      key={clinic.id}
      id={clinic.id}
      name={clinic.name}
      address={clinic.address}
      description={clinic.description}
    />
  ));

  return (
    <section className={styles.clinics}>
      <BigCard>
        <ul>{clinicList}</ul>
      </BigCard>
    </section>
  );
};

export default Clinics;
