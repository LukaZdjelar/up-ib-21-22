import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import BigCard from "../UI/BigCard";
import DoctorCard from "../Doctor/DoctorCard";

const Clinic = (props) => {
  const [clinic, setClinic] = useState([]);
  const [doctors, setDoctors] = useState([]);
  const params = useParams();

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
    </BigCard>
  );
};
export default Clinic;
