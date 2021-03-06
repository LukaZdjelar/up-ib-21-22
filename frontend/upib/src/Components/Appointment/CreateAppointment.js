import Card from "../UI/Card";
import styles from "./CreateAppointment.module.css";
import {useEffect, useState} from "react";
import axios from "axios";
import DateTimePicker from "react-datetime-picker";
import {TokenService} from "../../Service/TokenService";
import {navigate} from "react-big-calendar/lib/utils/constants";
import {useNavigate} from "react-router-dom";

const CreateAppointment = () => {
    const navigate = useNavigate()
    const [doctors, setDoctors] = useState([]);
    const [doctor, setDoctor] = useState(null);
    const [dateAndTime, setDateAndTime] = useState(null); //TODO: Vremenske zone 17:00 -> 15:00
    const [duration, setDuration] = useState("");
    const [price, setPrice] = useState("");

    useEffect(() => {
        sendGetRequest(); // eslint-disable-next-line
    }, []);

    const sendGetRequest = async () => {
        const response = await axios.get(
            "http://localhost:8080/user/doctors/" +
            TokenService.getClinicId()
        )
        setDoctors(response.data);
        setDoctor(response.data[0])
    };

    const formSubmitHandler = (event) => {
        const appointment = {
            doctorId: doctor,
            dateAndTime: dateAndTime,
            duration: duration,
            price: price,
            clinicId: parseInt(
                TokenService.getClinicId()
            ),
        };
        axios.post("http://localhost:8080/appointment", appointment)
        navigate("/home")
    };

    const doctorInputChangeHandler = (event) => {
        setDoctor(event.target.value);
    };

    const durationInputChangeHandler = (event) => {
        setDuration(event.target.value);
    };

    const priceInputChangeHandler = (event) => {
        setPrice(event.target.value);
    };

    return (
        <Card>
            <form onSubmit={formSubmitHandler}>
                <div className={styles.block}>
                    <label>Doctor</label>
                    <select
                        name="doctor"
                        onChange={doctorInputChangeHandler}
                        //value={doctor}
                    >
                        {doctors.map((doctor) => (
                            <option key={doctor.id} value={doctor.id}>
                                [{doctor.id}] {doctor.firstname} {doctor.lastname}
                            </option>
                        ))}
                    </select>
                </div>
                <div className={styles.block}>
                    <label>Date and time</label>
                    <DateTimePicker value={dateAndTime} onChange={setDateAndTime}/>
                </div>
                <div className={styles.block}>
                    <label>Duration (minutes)</label>
                    <input
                        type="number"
                        name="firstname"
                        onChange={durationInputChangeHandler}
                        value={duration}
                    ></input>
                </div>
                <div className={styles.block}>
                    <label>Price (RSD)</label>
                    <input
                        type="number"
                        name="price"
                        onChange={priceInputChangeHandler}
                        value={price}
                    ></input>
                </div>
                <div className={styles.button}>
                    <button type="submit">Create</button>
                </div>
            </form>
        </Card>
    );
};

export default CreateAppointment;
