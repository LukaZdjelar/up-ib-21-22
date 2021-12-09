import axios from "axios";
import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import BigCard from "../UI/BigCard";

const Clinic = (props) => {
  const [clinic, setClinic] = useState([]);

  useEffect(() => {
    sendGetRequest();
  }, []);

  const params = useParams();

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/clinic/" + String(params.clinicId)
    );
    setClinic(response.data);
  };

  return <BigCard>
      <div>
          <p>{clinic.name}</p>
          <p>{clinic.description}</p>
          <p>{clinic.address}</p>
      </div>
  </BigCard>;
};
export default Clinic;
