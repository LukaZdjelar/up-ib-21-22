import jwtDecode from "jwt-decode";

const getToken = () => {
    return localStorage.getItem("token");
}

const setToken = (value) => {
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

export const TokenService = {
    getToken,
    setToken,
    removeToken,
    decodeToken,
    didTokenExpire,
    getClinicId,
    getUserId,
    getUserType,
};