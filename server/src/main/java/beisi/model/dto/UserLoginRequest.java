package beisi.model.dto;

import lombok.Data;

@Data
public class UserLoginRequest {
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;
}
