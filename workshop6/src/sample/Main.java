

package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main extends Application {

    int[] ranking = {0};
    String[] _year = {"\0"};
    String[] _gender = {"\0"};
    String[] _name = {"\0"};

    @Override
    public void start(Stage ps) throws Exception{

        HBox year = new HBox();
        Label yearLabel = new Label("Enter the Year: ");
        TextField yearText = new TextField();
        year.getChildren().addAll(yearLabel,yearText);

        HBox gender = new HBox();
        Label genderLabel = new Label("Enter the Gender: ");
        TextField genderText = new TextField();
        gender.getChildren().addAll(genderLabel,genderText);
        genderText.setPrefWidth(30);


        HBox name = new HBox();
        Label nameLabel = new Label("Enter the Name: ");
        TextField nameText = new TextField();
        name.getChildren().addAll(nameLabel,nameText);


        HBox op = new HBox(100);
        Button sbmq = new Button("Submit Query");
        Button exit = new Button("Exit");
        op.getChildren().addAll(sbmq,exit);
        sbmq.setPrefWidth(120);
        exit.setPrefWidth(120);


        VBox vb = new VBox(30);
        vb.setPadding(new Insets(30,20,20,80));
        vb.getChildren().setAll(year,gender,name,op);
        Scene sc = new Scene(vb, 450, 260);
        ps.setTitle("Search Name Ranking Application");
        ps.setScene(sc);
        ps.show();



        HBox result1 = new HBox();
        Label resLabel = new Label();
        result1.getChildren().add(resLabel);


        HBox result2 = new HBox();
        Label resLabel2 = new Label("Do you want to Search for another Name:");
        result2.getChildren().add(resLabel2);

        HBox op2 = new HBox(100);
        Button yes = new Button("Yes");
        Button no = new Button("No");
        op2.getChildren().addAll(yes,no);
        yes.setPrefWidth(90);
        no.setPrefWidth(90);

        VBox vb2 = new VBox(30);
        vb2.setPadding(new Insets(30,20,20,80));
        vb2.getChildren().setAll(result1,result2,op2);
        Stage stage2 = new Stage();
        Scene sc2 = new Scene(vb2, 450, 240);
        stage2.setTitle("Search Name Ranking Application");
        stage2.setScene(sc2);
        stage2.show();
        stage2.toBack();




        sbmq.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                _year[0] = yearText.getText();
                _gender[0] = genderText.getText();
                _name[0] = nameText.getText();
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/sample/babyNames/babynamesranking" + _year[0] + ".txt"),"rw");
                    String pline;
                    do{
                        pline = raf.readLine();
                        String[] strArray = pline.split("\\s{1,}");
                        if(strArray[1].equals(_name[0]) || strArray[3].equals(_name[0])){
                            ranking[0] = Integer.parseInt(strArray[0]);
                            if(_gender[0].equals("M")){
                                resLabel.setText("Boy name " + _name[0] + " ranked #" + ranking[0] + " in " + _year[0] + " year");
                            }else{
                                resLabel.setText("Girl name " + _name[0] + " ranked #" + ranking[0] + " in " + _year[0] + " year");
                            }
                            stage2.toFront();
                            ps.toBack();
                            break;
                        }
                    }while(pline!=null);
                    System.out.println("Button Add is clicked.");
                    raf.close();
                }catch(IOException err){err.printStackTrace();
                }
            }
        });


        yes.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                ps.toFront();
                nameText.setText("\0");
                genderText.setText("\0");
                yearText.setText("\0");
                stage2.toBack();
            }
        });


        exit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                ps.close();
                stage2.close();
            }
        });

        no.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                ps.close();
                stage2.close();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
