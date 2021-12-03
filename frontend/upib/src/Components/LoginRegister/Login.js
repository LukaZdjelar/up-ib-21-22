import axios from "axios";
import React, { useState } from "react";
import Card from "../UI/Card";
import styles from "./Login.module.css";

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [invalid, setInvalid] = useState(false);

  const formSubmitionHandler = (event) => {
    event.preventDefault();

    const user = {
      email: email,
      password: password,
    };

    axios.post("http://localhost:8080/user/login", user).then((response) => {
      if (response.status !== 200) {
        setInvalid(true);
      }
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
    <section className={styles.login}>
      <Card>
        <form onSubmit={formSubmitionHandler} className={styles.form}>
          <div className={styles.control}>
            <label>E-mail</label>
            <input
              type="text"
              name="email"
              onChange={emailInputChangeHandler}
            ></input>
          </div>
          <div className={styles.control}>
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
          {invalid && <p>Invalid input!</p>}
        </form>
      </Card>
    </section>
  );
};

export default Login;
