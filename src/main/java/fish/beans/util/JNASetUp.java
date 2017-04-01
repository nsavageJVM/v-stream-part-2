package fish.beans.util;

import com.sun.jna.NativeLibrary;
import fish.beans.config.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

/**
 * Created by ubu on 12.12.16.
 */
@Slf4j
@Qualifier("jnasetup")
@Component
public class JNASetUp  {

    private static final String JNA_LIBRARY_PATH_PROP = "jna.library.path";
    private static final boolean debug = false;
    @Qualifier("propbean")
    @Autowired
    private AppConfig.PropBean nativeLibPath;
    /**
     * Initializes vlc library.
     */
    public  void initialize( ) {

        // log full path to vlc libs
        if(debug) {
        System.out.println(String.format("nativeLibPath %s", nativeLibPath.getNativePathProp()));
        System.out.println(String.format("getLibVlcCoreName %s", RuntimeUtil.getLibVlcCoreName()));
        System.out.println(String.format("getLibVlcLibraryName %s", RuntimeUtil.getLibVlcLibraryName()));
        System.out.println(String.format("getLibVlcName) %s", RuntimeUtil.getLibVlcName()));
                }
        // add path to vlc
        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(),nativeLibPath.getNativePathProp());
        // set system property for vlc
        System.setProperty(JNA_LIBRARY_PATH_PROP, nativeLibPath.getNativePathProp());

    }


}
