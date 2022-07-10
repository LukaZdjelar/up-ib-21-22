import React from "react";
import styles from "./HomeItem.module.css";
import {useNavigate} from "react-router-dom";
import {TokenService} from "../../Service/TokenService";

const HomeItem = (props) => {
    const navigate = useNavigate();

    const buttonClickHandler = () => {
        if (props.navigate !== "") {
            navigate(props.navigate);
        }
    };

    if (
        props.userType === TokenService.getUserType()
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
