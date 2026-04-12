declare namespace API {
  type BaseResponseBoolean = {
    code?: number;
    data?: boolean;
    message?: string;
    description?: string;
  };

  type BaseResponseUserVO = {
    code?: number;
    data?: UserVO;
    message?: string;
    description?: string;
  };

  type UserLoginRequest = {
    userName?: string;
    userPassword?: string;
  };

  type UserRegisterRequest = {
    userName?: string;
    userPassword?: string;
    checkPassword?: string;
  };

  type UserVO = {
    userName?: string;
    token?: string;
  };
}
