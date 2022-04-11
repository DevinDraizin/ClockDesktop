import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main extends Application {

    clock.HandNum num = clock.HandNum.HAND1;

    private void setNum(clock.HandNum num) {
        this.num = num;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) {
        window.setTitle("Clock");
        window.setHeight(1000);
        window.setWidth(1000);

        clock newClock = new clock(800,400);

        BorderPane mainLayout = new BorderPane();
        ToolBar toolbar = new ToolBar();
        Button toggleHandButton = new Button("1");
        Button button1 = new Button("60");
        Button button2 = new Button("80");
        Button button3 = new Button("-30");
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        toggleHandButton.setOnAction(e -> {
            if (num == clock.HandNum.HAND1) {
                toggleHandButton.setText("Hand 2");
                setNum(clock.HandNum.HAND2);
            }else {
                toggleHandButton.setText("Hand 1");
                setNum(clock.HandNum.HAND1);
            }
        });

        newClock.moveHandToAngle(60,num);
        newClock.moveHandToAngle(80,num);
        newClock.moveHandToAngle(-30,num);


        newClock.setHand1Speed(40);

        toolbar.getItems().addAll(button1,button2,button3,toggleHandButton,closeButton);

        mainLayout.setTop(toolbar);
        mainLayout.setCenter(newClock);


        Scene scene = new Scene(mainLayout);
        window.setScene(scene);
        window.show();
    }
}
