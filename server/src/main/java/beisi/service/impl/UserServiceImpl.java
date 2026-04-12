package beisi.service.impl;

import beisi.common.ThrowUtils;
import beisi.exception.ErrorCode;
import beisi.mapper.UserMapper;
import beisi.model.dto.UserLoginRequest;
import beisi.model.dto.UserRegisterRequest;
import beisi.model.entity.User;
import beisi.model.vo.UserVO;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * @author kyle
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2026-04-12 16:26:36
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    // MD5盐值
    private static final String MD5_SALT = "beisi";

    // 密钥 16 位（必须 16/24/32 位）
    private static final String KEY = "beisibeisibeisi_";

    // AES 加密器
    private static final AES aes = SecureUtil.aes(KEY.getBytes());

    @Override
    public UserVO doLogin(UserLoginRequest userLoginRequest) {
        String userName = userLoginRequest.getUserName();
        String userPassword = userLoginRequest.getUserPassword();
        ThrowUtils.throwIf(StrUtil.isAllBlank(userName, userPassword), ErrorCode.ERROR, "账号或密码为空");
        String encodePassword = passwordEncoder(userPassword);
        User user = new User();
        BeanUtil.copyProperties(userLoginRequest, user);
        user.setUserPassword(encodePassword);
        User userFromDb = this.lambdaQuery().eq(User::getUserName, userName).eq(User::getUserPassword, encodePassword).one();
        ThrowUtils.throwIf(userFromDb == null, ErrorCode.ERROR, "用户或密码错误");
        String token = mockToken(userFromDb.getId());
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(userFromDb, userVO);
        userVO.setToken(token);
        return userVO;
    }

    @Override
    public Boolean doRegister(UserRegisterRequest userRegisterRequest) {
        String userName = userRegisterRequest.getUserName();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        ThrowUtils.throwIf(StrUtil.isAllBlank(userName, userPassword, checkPassword), ErrorCode.ERROR, "账号或密码为空");
        ThrowUtils.throwIf(!Objects.equals(userPassword, checkPassword), ErrorCode.ERROR, "账号或密码为空");
        String encodePassword = passwordEncoder(userPassword);
        boolean exists = this.lambdaQuery().eq(User::getUserName, userName).exists();
        ThrowUtils.throwIf(exists, ErrorCode.ERROR, "用户名已存在");
        User user = new User();
        BeanUtil.copyProperties(userRegisterRequest, user);
        user.setUserPassword(encodePassword);
        boolean res = this.save(user);
        ThrowUtils.throwIf(!res, ErrorCode.ERROR, "数据库操作异常");
        return true;
    }

    @Override
    public UserVO getLoginUser(String token) {
        Long userId = mockTokenDecoder(token);
        User user = this.getById(userId);
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    public String passwordEncoder(String userPassword) {
        return DigestUtils.md5DigestAsHex((MD5_SALT + userPassword).getBytes());
    }

    public String mockToken(Long userId) {
        String content = userId.toString();
        return aes.encryptHex(content);
    }

    public Long mockTokenDecoder(String token) {
        try {
            String content = aes.decryptStr(token, CharsetUtil.CHARSET_UTF_8);
            return Long.valueOf(content);
        } catch (Exception e) {
            return null; // token 非法
        }
    }
}




