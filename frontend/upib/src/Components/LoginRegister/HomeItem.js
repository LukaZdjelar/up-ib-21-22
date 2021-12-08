import React, { useContext } from "react";
import UserContext from "../../store/user-context";
import styles from "./HomeItem.module.css";
import { useNavigate } from "react-router-dom";

const HomeItem = (props) => {
  const navigate = useNavigate();
  const userContext = useContext(UserContext);

  const buttonClickHandler = () => {
    navigate(props.navigate);
  };

  if (props.userType === userContext.user.userType) {
    return (
      <button className={styles.button} onClick={buttonClickHandler}>
        {props.text}
      </button>
    );
  } else {
    return <div></div>;
  }
};

export default HomeItem;
