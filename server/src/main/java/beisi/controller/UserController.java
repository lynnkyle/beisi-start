package beisi.controller;

import beisi.common.BaseResponse;
import beisi.common.ResultUtils;
import beisi.common.ThrowUtils;
import beisi.exception.ErrorCode;
import beisi.model.dto.UserLoginRequest;
import beisi.model.dto.UserRegisterRequest;
import beisi.model.vo.UserVO;
import generator.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<UserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        ThrowUtils.throwIf(userLoginRequest == null, ErrorCode.ERROR, "请求参数为空");
        UserVO userVO = userService.doLogin(userLoginRequest);
        return ResultUtils.success(userVO, "Login successful");
    }

    @PostMapping("/register")
    public BaseResponse<Boolean> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        ThrowUtils.throwIf(userRegisterRequest == null, ErrorCode.ERROR, "请求参数为空");
        Boolean res = userService.doRegister(userRegisterRequest);
        return ResultUtils.success(res, "Register successful");
    }

    @GetMapping("/get/login")
    public BaseResponse<UserVO> getLoginUser(@RequestHeader("Authorization") String token){
        ThrowUtils.throwIf(token==null,ErrorCode.ERROR, "用户未登录");
        UserVO loginUser = userService.getLoginUser(token);
        return ResultUtils.success(loginUser, "成功获取用户登录信息");
    }
}
