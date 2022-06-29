import axios from "axios";
import React, {useState} from "react";
import Card from "../UI/Card";
import styles from "./Login.module.css";
import {useNavigate} from "react-router-dom";
import {TokenService} from "../../Service/TokenService";

const Login = (props) => {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [invalid, setInvalid] = useState(false);
    const [errorMessage, setErrorMessage] = useState("");
    const navigate = useNavigate();

    const formSubmitionHandler = (event) => {
        event.preventDefault();

        const user = {
            email: email,
            password: password,
        };

        axios
            .post("http://localhost:8080/user/login", user)
            .then((response) => {
                if (response.status !== 200) {
                    setInvalid(true);
                    setErrorMessage("Invalid input!");
                } else {
                    TokenService.setToken(response.data)
                    if (response.data.userType !== "PATIENT") {
                        localStorage.setItem("clinicId", TokenService.getClinicId());
                    }
                    navigate("home");
                }
            })
            .catch((e) => {
                setInvalid(true);
                setErrorMessage("Network error");
            });
    };

    const emailInputChangeHandler = (event) => {
        setEmail(event.target.value);
        setInvalid(false);
    };

    const passwordInputChangeHandler = (event) => {
        setPassword(event.target.value);
        setInvalid(false);
    };

    return (
        <Card>
            <form onSubmit={formSubmitionHandler} className={styles.form}>
                <div className={styles.block}>
                    <label>E-mail</label>
                    <input
                        type="text"
                        name="email"
                        onChange={emailInputChangeHandler}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Password</label>
                    <input
                        type="password"
                        name="password"
                        onChange={passwordInputChangeHandler}
                    ></input>
                </div>
                <div className={styles.button}>
                    <button type="submit">Log in</button>
                </div>
                {invalid && <p>{errorMessage}</p>}
            </form>
        </Card>
    );
};

export default Login;
