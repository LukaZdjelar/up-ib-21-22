import axios from "axios";
import ClinicCard from "./ClinicCard";
import { useEffect, useState } from "react";
import styles from "./Clinics.module.css";
import BigCard from "../UI/BigCard";
import Card from "../UI/Card";
import DatePicker from "react-date-picker";

const Clinics = () => {
  const [clinics, setClinics] = useState([]);
  const [sortKey, setSortKey] = useState("id");
  const [date, setDate] = useState(new Date());
  const [term, setTerm] = useState("");

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, [sortKey, date, term]);

  const sendGetRequest = async () => {
    var stringDate =
      date.getDate().toString() +
      "-" +
      (date.getMonth() + 1).toString() +
      "-" +
      date.getFullYear().toString();

    const filter = {
      date: stringDate,
      term: term,
    };
    const response = await axios.post(
      "http://localhost:8080/clinic/search",
      filter
    );
    setClinics(response.data);
  };

  const defaultSortHandler = () => {
    setSortKey("id");
  };

  const nameSortHandler = () => {
    setSortKey("name");
  };

  const addressSortHandler = () => {
    setSortKey("address");
  };

  switch (sortKey) {
    case "id":
      clinics.sort((a, b) => (a.id > b.id ? 1 : b.id > a.id ? -1 : 0));
      break;
    case "name":
      clinics.sort((a, b) => (a.name > b.name ? 1 : b.name > a.name ? -1 : 0));
      break;

    case "address":
      clinics.sort((a, b) =>
        a.address > b.address ? 1 : b.address > a.address ? -1 : 0
      );
      break;
    default:
      clinics.sort((a, b) => (a.id > b.id ? 1 : b.id > a.id ? -1 : 0));
      break;
  }

  const clinicList = clinics.map((clinic) => (
    <ClinicCard
      key={clinic.id}
      id={clinic.id}
      name={clinic.name}
      address={clinic.address}
      description={clinic.description}
    />
  ));

  const termInputChangeHandler = (event) => {
    setTerm(event.target.value);
  };

  return (
    <section className={styles.clinics}>
      <Card>
        <button className="sortButton" onClick={defaultSortHandler}>
          Default sorting
        </button>
        <button className="sortButton" onClick={nameSortHandler}>
          Sort by name
        </button>
        <button className="sortButton" onClick={addressSortHandler}>
          Sort by address
        </button>
      </Card>
      <Card>
        <DatePicker value={date} onChange={setDate} />
      </Card>
      <Card>
        <input type="text" value={term} onChange={termInputChangeHandler} />
      </Card>
      <BigCard>
        <ul>{clinicList}</ul>
      </BigCard>
    </section>
  );
};

export default Clinics;
