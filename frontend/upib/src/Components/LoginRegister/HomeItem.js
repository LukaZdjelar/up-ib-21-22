import React, { useContext } from "react";
import UserContext from "../../store/user-context";
import styles from "./HomeItem.module.css";

const HomeItem = (props) => {
  const userContext = useContext(UserContext);
  if (props.userType === userContext.user.userType) {
    return <button className={styles.button}>{props.text}</button>;
  } else {
    return <div></div>;
  }
};

export default HomeItem;
