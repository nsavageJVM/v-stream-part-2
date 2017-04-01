package fish.beans.config;


import javafx.fxml.FXMLLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

/**
 * Created by ubu on 8/28/16.
 */
@Component
public class IoCFXMLLoader implements ApplicationContextAware {

    // == constants ==
    private static final String FXML_DIR = "fxml/";

    private ApplicationContext applicationContext;
    /**
     * Creates a Spring IoC  enabled FXMLLoader
     */
    public FXMLLoader loader(String path) {
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(this::createControllerAsBean);
        String fullPath = FXML_DIR + path;
        Resource res = new ClassPathResource(fullPath);
        URL url = null;
        try {
            url = res.getURL();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loader.setLocation(url);
        return loader;
    }


    private Object createControllerAsBean(Class<?> type) {
        return this.applicationContext.getBean(type);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        if (this.applicationContext != null) {
            return;
        }
        this.applicationContext = applicationContext;
    }

}
