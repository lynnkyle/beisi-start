package beisi.model.dto;

import lombok.Data;

@Data
public class UserRegisterRequest {
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 校验密码
     */
    private String checkPassword;
}
