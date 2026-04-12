package generator.service;

import beisi.model.dto.UserLoginRequest;
import beisi.model.dto.UserRegisterRequest;
import beisi.model.entity.User;
import beisi.model.vo.UserVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author kyle
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2026-04-12 16:26:36
 */
public interface UserService extends IService<User> {

    UserVO doLogin(UserLoginRequest userLoginRequest);

    Boolean doRegister(UserRegisterRequest userRegisterRequest);

    UserVO getLoginUser(String token);

    String passwordEncoder(String userPassword);

    String mockToken(Long userId);

}
