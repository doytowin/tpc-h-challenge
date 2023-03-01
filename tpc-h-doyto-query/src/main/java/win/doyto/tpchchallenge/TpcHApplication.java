package win.doyto.tpchchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import win.doyto.query.web.WebMvcConfigurerAdapter;

/**
 * TpcHApplication
 *
 * @author f0rb on 2023/2/23
 */
@SpringBootApplication
public class TpcHApplication extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(TpcHApplication.class);
    }
}
