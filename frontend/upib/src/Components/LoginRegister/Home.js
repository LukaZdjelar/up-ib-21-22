import React from "react";

import Card from "../UI/Card";
import HomeItem from "./HomeItem";
import styles from "./Home.module.css";

const Home = (props) => {
  return (
    <Card>
      <HomeItem userType="PATIENT" text="Clinics"></HomeItem>
      <HomeItem userType="PATIENT" text="Check-up history"></HomeItem>
      <HomeItem userType="PATIENT" text="Medical record"></HomeItem>
      <HomeItem userType="PATIENT" text="My profile"></HomeItem>

      <HomeItem userType="DOCTOR" text="Patients"></HomeItem>
      <HomeItem userType="DOCTOR" text="Check-up"></HomeItem>
      <HomeItem userType="DOCTOR" text="Work calendar"></HomeItem>
      <HomeItem userType="DOCTOR" text="My profile"></HomeItem>

      <HomeItem userType="CLINIC_ADMINISTRATOR" text="Edit clinic data"></HomeItem>
      <HomeItem userType="CLINIC_ADMINISTRATOR" text="Appointments"></HomeItem>
      <HomeItem userType="CLINIC_ADMINISTRATOR" text="Create new appointment"></HomeItem>
      <HomeItem userType="CLINIC_ADMINISTRATOR" text="Clinic report"></HomeItem>

      <button className={styles.button}>Log out</button>
    </Card>
  );
};

export default Home;
