package concurs.client;

import concurs.client.gui.LoginPageViewController;
import concurs.services.IServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartObjectClient extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:sping-client.xml");
            IServer server = (IServer) factory.getBean("service");
            System.out.println("Obtained a reference to remote server");

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(ClassLoader.getSystemResource("view/loginPageView.fxml"));
            BorderPane root = loader.load();

            LoginPageViewController loginPageViewController = loader.getController();
            loginPageViewController.setService(server);

            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Autentificare");
            primaryStage.show();
        } catch (Exception e){
            System.err.println("Initialization  exception:"+e);
            e.printStackTrace();
        }
    }
}
