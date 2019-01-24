package site.qipeng.scheduled;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TestTask {

    public static final Logger logger = LoggerFactory.getLogger(TestTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        logger.info("The current time is: " + dateFormat.format(new Date()));
    }
}
