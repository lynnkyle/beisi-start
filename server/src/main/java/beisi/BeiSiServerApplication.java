package beisi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("beisi.mapper")
@SpringBootApplication
public class BeiSiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BeiSiServerApplication.class, args);
    }
}
