import React from "react";

import Card from "../UI/Card";
import HomeItem from "./HomeItem";
import styles from "./Home.module.css";
import { useNavigate } from "react-router-dom";

const Home = (props) => {
  const navigate = useNavigate();

  const logoutButtonHandler = () => {
    localStorage.removeItem("loggedUser");
    navigate("/");
  };

  return (
    <Card>
      <HomeItem
        userType="PATIENT"
        text="Clinics"
        navigate="/clinics"
      ></HomeItem>
      <HomeItem
        userType="PATIENT"
        text="Check-up history"
        navigate="/history"
      ></HomeItem>
      <HomeItem
        userType="PATIENT"
        text="Medical record"
        navigate="/"
      ></HomeItem>
      <HomeItem
        userType="PATIENT"
        text="My profile"
        navigate={`/user/${JSON.parse(localStorage.getItem("loggedUser")).id}`}
      ></HomeItem>

      <HomeItem userType="DOCTOR" text="Patients" navigate="/"></HomeItem>
      <HomeItem userType="DOCTOR" text="Check-up" navigate="/"></HomeItem>
      <HomeItem userType="DOCTOR" text="Work calendar" navigate="/"></HomeItem>
      <HomeItem userType="DOCTOR" text="My profile" navigate="/"></HomeItem>

      <HomeItem
        userType="CLINIC_ADMINISTRATOR"
        text="Edit clinic data"
        navigate="/"
      ></HomeItem>
      <HomeItem
        userType="CLINIC_ADMINISTRATOR"
        text="Appointments"
        navigate="/"
      ></HomeItem>
      <HomeItem
        userType="CLINIC_ADMINISTRATOR"
        text="Create new appointment"
        navigate="/createAppointment"
      ></HomeItem>
      <HomeItem
        userType="CLINIC_ADMINISTRATOR"
        text="Clinic report"
        navigate="/"
      ></HomeItem>

      <button className={styles.button} onClick={logoutButtonHandler}>
        Log out
      </button>
    </Card>
  );
};

export default Home;
