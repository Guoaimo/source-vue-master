package cn.source;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 *
 * @author ruoyi
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class RuoYiApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(RuoYiApplication.class, args);
        System.out.println("   _____                   _                 \n" +
                "  / ____|                 (_)                \n" +
                " | |  __ _   _  ___   __ _ _ _ __ ___   ___  \n" +
                " | | |_ | | | |/ _ \\ / _` | | '_ ` _ \\ / _ \\ \n" +
                " | |__| | |_| | (_) | (_| | | | | | | | (_) |\n" +
                "  \\_____|\\__,_|\\___/ \\__,_|_|_| |_| |_|\\___/ \n" +
                "                                             \n" +
                "                                             ");
    }
}
