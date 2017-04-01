package fish.beans.config;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.WritableImage;
import javafx.stage.Screen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

/**
 * Created by ubu on 8/28/16.
 */

@Configuration
@Slf4j
@PropertySources({
        @PropertySource("classpath:application.properties"),
        @PropertySource(value = "file:${vs.properties}", ignoreResourceNotFound = true) })
public class AppConfig {

    @Autowired
    private Environment env;

    private Point2D mainScreenBounds;

    private Point2D largeDialogScreenBounds;


    private WritableImage writableImage1, writableImage2, writableImage3;

    public AppConfig() {

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        mainScreenBounds = new Point2D(bounds.getWidth()*0.8, bounds.getHeight()*0.6);
        largeDialogScreenBounds = new Point2D(bounds.getWidth()*0.5, bounds.getHeight()*0.7);
        log.info(mainScreenBounds.toString());

    }


    @Bean(name = "screenBounds")
    public Point2D getMainScreenBounds()  {
        return mainScreenBounds;
    }


    @Bean(name = "largedialogscreenbounds")
    public Point2D getLargeDialogScreenBounds()  {
        return largeDialogScreenBounds;
    }



    @Bean(name = "writableimage")
    @Scope("prototype")
    public WritableImage getWritableImage()  {
        WritableImage writableImage = new WritableImage(300,400);
        return writableImage;
    }


    @Bean(name = "propbean")
    @Scope("singleton")
    public PropBean getPropBean( )  {
        PropBean pB = new PropBean();
        return pB;
    }

   public class PropBean {
       public String getNativePathProp() {
            return env.getProperty("app.video.native.dir");
        }

       public String getCamFile(String cam) { return env.getProperty(String.format("app.video.%s", cam));
       }
       
     }
}
