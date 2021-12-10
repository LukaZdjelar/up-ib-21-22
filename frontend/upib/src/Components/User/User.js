import BigCard from "../UI/BigCard";
import { useNavigate } from "react-router-dom";
import styles from "./User.module.css";

const User = () => {
  const user = JSON.parse(localStorage.getItem("loggedUser"));
  const navigate = useNavigate();

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
