import React from "react";
import styles from "./HomeItem.module.css";
import { useNavigate } from "react-router-dom";

const HomeItem = (props) => {
  const navigate = useNavigate();

  const buttonClickHandler = () => {
    navigate(props.navigate);
  };

  if (
    props.userType === JSON.parse(localStorage.getItem("loggedUser")).userType
  ) {
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
