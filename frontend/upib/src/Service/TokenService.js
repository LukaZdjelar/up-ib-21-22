import jwtDecode from "jwt-decode";
import axios from "axios";

const getToken = () => {
    return localStorage.getItem("token");
}

const getRefreshToken = () => {
    return localStorage.getItem("refreshToken");
}

const setTokens = (response) => {
    localStorage.setItem("token", response.token);
    localStorage.setItem("refreshToken", response.refreshToken);
}

const setAccessToken = (value) => {
    localStorage.setItem("token", value);
}

const removeToken = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("clinicId");
}

const decodeToken = (token) => {
    try {
        return jwtDecode(token);
    } catch (error) {
        return null;
    }
}

const didTokenExpire = () => {
    const token = getToken();
    const decodedToken = token ? decodeToken(token) : null;
    return decodedToken ? decodedToken.exp_date < Date.now() : null;
}

const getClinicId = () => {
    if (TokenService.getToken() === null) {
        return null
    }
    return jwtDecode(TokenService.getToken()).clinicId
}

const getUserId = () => {
    if (TokenService.getToken() === null) {
        return null
    }
    return jwtDecode(TokenService.getToken()).id
}

const getUserType = () => {
    if (TokenService.getToken() === null) {
        return null
    }
    const role = jwtDecode(TokenService.getToken()).role.authority;
    return role.substring(5);
}

const refreshToken = () => {
    axios.post("http://localhost:8080/user/refresh", getRefreshToken())
        .then((response) => {
            if (response.status === 200) {
                console.log(response.data)
                TokenService.setAccessToken(response.data)
                console.log(TokenService.getToken())
            }
        })
}

export const TokenService = {
    getToken,
    getRefreshToken,
    setTokens,
    setAccessToken,
    removeToken,
    decodeToken,
    didTokenExpire,
    getClinicId,
    getUserId,
    getUserType,
    refreshToken,
};