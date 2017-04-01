package fish.beans.video;

import fish.beans.util.JNASetUp;
import javafx.scene.image.WritableImage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.direct.BufferFormat;
import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


@Component
@Slf4j
public class VlcjMediaPlayerFactory implements ApplicationContextAware {

    private DirectMediaPlayer directMediaPlayer;
    private MediaPlayerFactory vlcjFactory;

    private ApplicationContext applicationContext;


    @Qualifier("jnasetup")
    @Autowired
    private JNASetUp jnasetup;


    public DirectMediaPlayer mediaPlayer(WritableImage writableimage) {
        DefaultRenderCallback renderCallback = applicationContext.getBean(DefaultRenderCallback.class);

        renderCallback.setWritableImage(writableimage);


        directMediaPlayer = vlcjFactory.newDirectMediaPlayer(new VLCJBufferFormatCallback(writableimage), renderCallback);

        return directMediaPlayer;
    }

    @PostConstruct
    public void init() {
        jnasetup.initialize();
        // vlcj Factory class
        vlcjFactory = new MediaPlayerFactory();

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    class VLCJBufferFormatCallback implements BufferFormatCallback {

        WritableImage writableimage;

        public VLCJBufferFormatCallback(WritableImage writableimage) {
            this.writableimage =writableimage;
        }

        @Override
        public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {

            return new RV32BufferFormat((int) this.writableimage.getWidth(), (int) this.writableimage.getHeight());

        }
    }

    @PreDestroy
    public void onDestroy() {

        if (directMediaPlayer != null) {
            directMediaPlayer.release();
        }

        if (vlcjFactory != null) {
            vlcjFactory.release();
        }
    }





}
