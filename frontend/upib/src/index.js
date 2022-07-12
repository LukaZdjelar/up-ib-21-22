import React from "react";
import ReactDOM from "react-dom";
import "./index.css";
import App from "./App";
import reportWebVitals from "./reportWebVitals";
import {BrowserRouter} from "react-router-dom";
import axios from "axios";
import {TokenService} from "./Service/TokenService";

axios.interceptors.request.use(request => {
    request.headers.Authorization = "Bearer " + TokenService.getToken();
    return request;
});

axios.interceptors.response.use(response => {
    return response;
}, error => {
    const originalRequest = error.config;
    console.log(error.response.status)

    if (originalRequest.url === '/auth/refresh') {
        localStorage.clear();
        window.location.replace("/");
        alert("Refresh token expired")
        return Promise.reject(error);
    }

    if (error.response.status === 403) {
        localStorage.clear();
        window.location.replace("/");
        alert("Access token expired")
        return Promise.reject(error);
    }
    return Promise.reject(error);
});

ReactDOM.render(
    <BrowserRouter>
        <App/>
    </BrowserRouter>,
    document.getElementById("root")
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
