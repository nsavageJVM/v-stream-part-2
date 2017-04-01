package fish.beans;

import fish.beans.config.IoCFXMLLoader;
import fish.beans.util.JNASetUp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VStreamApp extends Application {

	private ConfigurableApplicationContext context;
	private static String[] savedArgs;
	private static final String rootNodePath = "basepanelview.fxml";

	/**
	 * Note that this is configured in application.properties
	 */
	@Value("${app.stage.header:Look and Feel FX}")//
	private String stageHeader;


	@Qualifier("screenBounds")
	@Autowired
	private Point2D bounds;


	@Autowired
	private IoCFXMLLoader iocLoader;

	private Stage stage;

	@Override
	public void init() throws Exception {
		context = SpringApplication.run(getClass(), savedArgs);
		context.getAutowireCapableBeanFactory().autowireBean(this);

	}


	@Override
	public void stop() throws Exception {
		super.stop();
		context.close();
		// if we dont call System.exit here shutdown hooks wont get called.
		System.exit(0);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.setProperty("glass.accessible.force", "false");
		FXMLLoader loader = iocLoader.loader(rootNodePath);
		loader.load();
		Scene scene = new Scene(loader.getRoot(), bounds.getX(), bounds.getY());
		stage = primaryStage;
		stage.setTitle(stageHeader);
		stage.setScene(scene);
		stage.setResizable(true);
		stage.centerOnScreen();
		stage.show();



	}

	public static void main(String[] args) {
		savedArgs = args;
		launch(VStreamApp.class, args);


	}

}
