import Card from "../UI/Card";
import styles from "./EditUser.module.css";
import {useEffect, useState} from "react";
import axios from "axios";
import {useNavigate} from "react-router-dom";
import {TokenService} from "../../Service/TokenService";

const EditUser = () => {
    const navigate = useNavigate();
    const userId = TokenService.getUserId();

    const [firstname, setFirstname] = useState("");
    const [lastname, setLastname] = useState("");
    const [address, setAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [password, setPassword] = useState("");

    useEffect(() => {
        sendGetRequest(); // eslint-disable-next-line
    }, []);

    const sendGetRequest = async () => {
        const response = await axios.get(
            "http://localhost:8080/user/" + String(userId)
        );
        setFirstname(response.data.firstname);
        setLastname(response.data.lastname);
        setAddress(response.data.address);
        setPhoneNumber(response.data.phoneNumber);
        setPassword(response.data.password);
    };

    const editedUser = {
        firstname: firstname,
        lastname: lastname,
        address: address,
        phoneNumber: phoneNumber,
        password: password,
    };

    const firstnameInputChangeHandler = (event) => {
        setFirstname(event.target.value);
    };

    const lastnameInputChangeHandler = (event) => {
        setLastname(event.target.value);
    };

    const addressInputChangeHandler = (event) => {
        setAddress(event.target.value);
    };

    const phoneNumberInputChangeHandler = (event) => {
        setPhoneNumber(event.target.value);
    };

    const passwordInputChangeHandler = (event) => {
        setPassword(event.target.value);
    };

    const formSubmitHandler = (event) => {
        event.preventDefault();
        axios
            .put("http://localhost:8080/user/" + String(userId), editedUser)
            .then(() => {
                navigate("/user/" + String(userId));
            });
    };

    return (
        <Card>
            <form onSubmit={formSubmitHandler}>
                <div className={styles.block}>
                    <label>First name</label>
                    <input
                        type="text"
                        name="firstname"
                        onChange={firstnameInputChangeHandler}
                        value={firstname}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Last name</label>
                    <input
                        type="text"
                        name="lastname"
                        onChange={lastnameInputChangeHandler}
                        value={lastname}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Address</label>
                    <input
                        type="text"
                        name="address"
                        onChange={addressInputChangeHandler}
                        value={address}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Phone number</label>
                    <input
                        type="text"
                        name="phoneNumber"
                        onChange={phoneNumberInputChangeHandler}
                        value={phoneNumber}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Password</label>
                    <input
                        type="password"
                        name="password"
                        onChange={passwordInputChangeHandler}
                        value={password}
                    ></input>
                </div>
                <div className={styles.button}>
                    <button type="submit">Confirm</button>
                </div>
            </form>
        </Card>
    );
};

export default EditUser;
