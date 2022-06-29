import jwtDecode from "jwt-decode";

const getToken = () => {
    return localStorage.getItem("token");
}

const setToken = (value) => {
    localStorage.setItem("token", value);
}

const removeToken = () => {
    localStorage.removeItem("token");
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
    return jwtDecode(TokenService.getToken()).clinicId
}

const getUserId = () => {
    return jwtDecode(TokenService.getToken()).id
}

const getUserType = () => {
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