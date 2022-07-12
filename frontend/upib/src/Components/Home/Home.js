import React from "react";

import Card from "../UI/Card";
import HomeItem from "./HomeItem";
import styles from "./Home.module.css";
import {useNavigate} from "react-router-dom";
import {TokenService} from "../../Service/TokenService";

const Home = (props) => {
    const navigate = useNavigate();

    const logoutButtonHandler = () => {
        TokenService.removeToken()
        navigate("/");
        window.location.reload()
    };

    const refreshButtonHandler = () => {
        TokenService.refreshToken()
        alert("Token refreshed")
    }

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
                text="My profile"
                navigate={`/user/${TokenService.getUserId()}`}
            ></HomeItem>

            {/*<HomeItem userType="DOCTOR" text="Patients" navigate=""></HomeItem>*/}
            {/*<HomeItem userType="DOCTOR" text="Check-up" navigate=""></HomeItem>*/}
            <HomeItem
                userType="DOCTOR"
                text="Work calendar"
                navigate="/calendar"
            ></HomeItem>
            {/*<HomeItem userType="DOCTOR" text="My profile" navigate=""></HomeItem>*/}

            {/*<HomeItem*/}
            {/*    userType="CLINIC_ADMINISTRATOR"*/}
            {/*    text="Edit clinic data"*/}
            {/*    navigate=""*/}
            {/*></HomeItem>*/}
            {/*<HomeItem*/}
            {/*    userType="CLINIC_ADMINISTRATOR"*/}
            {/*    text="Appointments"*/}
            {/*    navigate=""*/}
            {/*></HomeItem>*/}
            <HomeItem
                userType="CLINIC_ADMINISTRATOR"
                text="Create new appointment"
                navigate="/createAppointment"
            ></HomeItem>
            {/*<HomeItem*/}
            {/*    userType="CLINIC_ADMINISTRATOR"*/}
            {/*    text="Clinic report"*/}
            {/*    navigate=""*/}
            {/*></HomeItem>*/}
            <button className={styles.button} onClick={refreshButtonHandler}>
                Refresh token
            </button>

            <button className={styles.button} onClick={logoutButtonHandler}>
                Log out
            </button>
        </Card>
    );
};

export default Home;
