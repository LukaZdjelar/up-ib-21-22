import BigCard from "../UI/BigCard";
import { useNavigate } from "react-router-dom";
import styles from "./User.module.css";
import { useEffect, useState } from "react";
import axios from "axios";

const User = () => {
  const userId = JSON.parse(localStorage.getItem("loggedUser")).id;
  const [user, setUser] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    sendGetRequest(); // eslint-disable-next-line
  }, []);

  const sendGetRequest = async () => {
    const response = await axios.get(
      "http://localhost:8080/user/" + String(userId)
    );
    setUser(response.data);
  };

  const editClickHandler = () => {
    navigate("edit");
  };
  return (
    <BigCard>
      <p>First name: {user.firstname}</p>
      <p>Last name: {user.lastname}</p>
      <p>E-mail: {user.email}</p>
      <p>Phone number: {user.phoneNumber}</p>
      <p>Address: {user.address}</p>
      <button className={styles.button} onClick={editClickHandler}>
        Edit profile info
      </button>
    </BigCard>
  );
};

export default User;
