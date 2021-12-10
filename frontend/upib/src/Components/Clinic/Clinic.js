import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import BigCard from "../UI/BigCard";

const Clinic = (props) => {
  const [clinic, setClinic] = useState([]);
  const params = useParams();

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/clinic/" + String(params.clinicId)
    );
    setClinic(response.data);
  };

  return (
    <BigCard>
      <div>
        <p>{clinic.name}</p>
        <p>{clinic.description}</p>
        <p>{clinic.address}</p>
      </div>
    </BigCard>
  );
};
export default Clinic;
