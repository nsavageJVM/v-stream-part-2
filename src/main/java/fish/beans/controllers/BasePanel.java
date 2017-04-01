package fish.beans.controllers;

import fish.beans.config.AppConfig;
import fish.beans.video.DefaultRenderCallback;
import fish.beans.video.VlcjMediaPlayerFactory;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.events.MediaPlayerEventType;

import javax.annotation.PostConstruct;



/**
 * Created by ubu on 12.12.16.
 */
@Slf4j
@Component
@Lazy
public class BasePanel  implements ApplicationContextAware {

    private ApplicationContext applicationContext;


    @Autowired
    VlcjMediaPlayerFactory vlcFactory;


    @Qualifier("propbean")
    @Autowired
    private AppConfig.PropBean videosource;


    @FXML
    private ImageView videoImageView1;

    @FXML
    private ImageView videoImageView2;

    @FXML
    private ImageView videoImageView3;

    private WritableImage writableimage1, writableimage2, writableimage3;
    private MediaPlayer mediaPlayer1, mediaPlayer2, mediaPlayer3;

    @PostConstruct
    void init() {

        writableimage1 =
                applicationContext.getBean("writableimage", WritableImage.class);
        mediaPlayer1 = vlcFactory.mediaPlayer(writableimage1);
        writableimage2 =
                applicationContext.getBean("writableimage", WritableImage.class);
        mediaPlayer2 = vlcFactory.mediaPlayer(writableimage2);
        writableimage3 =
                applicationContext.getBean("writableimage", WritableImage.class);
        mediaPlayer3 = vlcFactory.mediaPlayer(writableimage3);
    }


    @FXML
    void initialize() {
        mediaPlayer1.enableEvents(MediaPlayerEventType.ALL.value());
        videoImageView1.setImage(writableimage1);
        videoImageView1.setFitHeight(300);
        videoImageView1.setFitWidth(400);

        mediaPlayer2.enableEvents(MediaPlayerEventType.ALL.value());
        videoImageView2.setImage(writableimage2);
        videoImageView2.setFitHeight(300);
        videoImageView2.setFitWidth(400);


        mediaPlayer3.enableEvents(MediaPlayerEventType.ALL.value());
        videoImageView3.setImage(writableimage3);
        videoImageView3.setFitHeight(300);
        videoImageView3.setFitWidth(400);
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void playCam1(ActionEvent actionEvent) {
        if (mediaPlayer1.isPlaying()) {
            mediaPlayer1.stop();
        }
        mediaPlayer1.prepareMedia(videosource.getCamFile("cam1"));
        mediaPlayer1.start();
    }

    public void playCam2(ActionEvent actionEvent) {
        if (mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
        }
        mediaPlayer2.prepareMedia(videosource.getCamFile("cam2"));
        mediaPlayer2.start();
    }

    public void playCam3(ActionEvent actionEvent) {

        if (mediaPlayer3.isPlaying()) {
            mediaPlayer3.stop();
        }
        mediaPlayer3.prepareMedia(videosource.getCamFile("cam3"));
        mediaPlayer3.start();

    }

    public void stopCam1(ActionEvent actionEvent) {
        if (mediaPlayer1.isPlaying()) {
            mediaPlayer1.stop();
        }
    }

    public void stopCam2(ActionEvent actionEvent) {
        if (mediaPlayer2.isPlaying()) {
            mediaPlayer2.stop();
        }
    }

    public void stopCam3(ActionEvent actionEvent) {
        if (mediaPlayer3.isPlaying()) {
            mediaPlayer3.stop();
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
