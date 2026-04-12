// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** 此处后端没有提供注释 GET /get/login */
export async function getLoginUser(options?: { [key: string]: any }) {
    return request<API.BaseResponseUserVO>("/get/login", {
        method: "GET",
        headers: {
            Authorization: localStorage.getItem('token') || '',
        },
        ...(options || {}),
    });
}

/** 此处后端没有提供注释 POST /login */
export async function userLogin(
    body: API.UserLoginRequest,
    options?: { [key: string]: any }
) {
    return request<API.BaseResponseUserVO>("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        data: body,
        ...(options || {}),
    });
}

/** 此处后端没有提供注释 POST /register */
export async function userRegister(
    body: API.UserRegisterRequest,
    options?: { [key: string]: any }
) {
    return request<API.BaseResponseBoolean>("/register", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        data: body,
        ...(options || {}),
    });
}
