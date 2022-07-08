import api.controller.MyTimer;
import api.controller.TelegramImplementations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
@SpringBootApplication
@ComponentScan
public class Main extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            TelegramImplementations telegramImplementations = new TelegramImplementations();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(telegramImplementations);

            MyTimer myTimer = new MyTimer(telegramImplementations);
            myTimer.sendNotify();

        } catch (TelegramApiException e) {
            e.getStackTrace();
        }
        SpringApplication.run(Main.class,args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }
}