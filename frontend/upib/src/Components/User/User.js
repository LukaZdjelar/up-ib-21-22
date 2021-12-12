import BigCard from "../UI/BigCard";
import { useNavigate } from "react-router-dom";
import styles from "./User.module.css";
import { useEffect, useState} from "react";
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
      <p>{user.firstname}</p>
      <p>{user.lastname}</p>
      <p>{user.email}</p>
      <p>{user.password}</p>
      <p>{user.phoneNumber}</p>
      <p>{user.address}</p>
      <button className={styles.button} onClick={editClickHandler}>
        Edit profile info
      </button>
    </BigCard>
  );
};

export default User;
