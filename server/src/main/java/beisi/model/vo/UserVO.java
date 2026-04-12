package beisi.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserVO {
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户Token
     */
    private String token;
}
