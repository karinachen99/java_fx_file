/**********************************************
 Workshop 5
 Course:<JAC 444> - Semester 4
 Last Name:<CHEN>
 First Name:<ZIWEI>
 ID:<124609199>
 Section:<NEE>
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature ZIWEI CHEN
 Date:<07/03/2021>
 **********************************************/

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sun.security.ssl.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main extends Application {

    @Override
    public void start(Stage ps) throws Exception {
        final long[] start = new long[1];
        HBox fn = new HBox(10);
        Label fnLabel = new Label("First Name: ");
        TextField fnText = new TextField();
        fn.setPrefWidth(600);
        fn.getChildren().addAll(fnLabel,fnText);


        HBox ln = new HBox(10);
        Label lnLabel = new Label("Last Name: ");
        TextField lnText = new TextField();
        ln.getChildren().addAll(lnLabel,lnText);


        HBox ct = new HBox(10);
        Label ctLabel = new Label("City: ");
        TextField ctText = new TextField();
        ct.setPrefWidth(150);
        Label provin = new Label("Province: ");
        ChoiceBox<Object> pv = new ChoiceBox<>();
        pv.setItems(FXCollections.observableArrayList("ON","BC","QC"));
        pv.setPrefWidth(100);
        Label pos = new Label("Postal Code: ");
        TextField posText = new TextField();
        pos.setPrefWidth(100);
        ct.getChildren().addAll(ctLabel,ctText,provin,pv,pos,posText);


        HBox op = new HBox(5);
        Button btnAdd = new Button("Add");
        Button btnFirst = new Button("First");
        Button btnNext = new Button("Next");
        Button btnPrevious = new Button("Previous");
        Button btnLast = new Button("Last");
        Button btnUpdate = new Button("Update");


        btnAdd.setPrefWidth(110);
        btnFirst.setPrefWidth(110);
        btnLast.setPrefWidth(110);
        btnNext.setPrefWidth(110);
        btnPrevious.setPrefWidth(110);
        btnUpdate.setPrefWidth(110);

        op.getChildren().addAll(btnAdd, btnFirst, btnLast, btnNext, btnPrevious, btnUpdate);

        VBox vb = new VBox(20);
        vb.setPadding(new Insets(20,20,20,20));
        vb.getChildren().setAll(fn,ln,ct,op);

        Scene sc = new Scene(vb, 700, 220);

        ps.setTitle("Address Book");
        ps.setScene(sc);
        ps.show();

        btnAdd.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                String firstName = fnText.getText();
                String lastName = lnText.getText();
                String city = ctText.getText();
                String province = pv.getValue().toString();
                String postalCode = posText.getText();
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"),"rw");
                    raf.seek(raf.length());
                    raf.write("\n".getBytes());
                    raf.write(firstName.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(lastName.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(city.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(province.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(postalCode.getBytes());
                    System.out.println("Button Add is clicked.");
                    raf.close();
                }catch(IOException err){err.printStackTrace();
                }
            }
        });


        btnFirst.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"),"rw");
                    raf.seek(0);
                    StringBuffer sb = new StringBuffer();
                    String tmp = raf.readLine();
                    sb.append(tmp);
                    String[] strings = sb.toString().split(" ");
                    fnText.setText(strings[0]);
                    lnText.setText(strings[1]);
                    ctText.setText(strings[2]);
                    pv.setValue(strings[3]);
                    posText.setText(strings[4]);
                    System.out.println("Button First is clicked.");
                    start[0] = raf.getFilePointer();
                    raf.close();
                }catch(IOException err){err.printStackTrace();
                }
            }
        });


        btnLast.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                StringBuffer sb = new StringBuffer();
                try {
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"), "rw");
                    long len = raf.length();
                    if (len != 0L) {
                        long pos = len - 1;
                        while (pos > 0) {
                            pos--;
                            raf.seek(pos);
                            if (raf.readByte() == '\n') {
                                String tmp = raf.readLine();
                                sb.append(tmp);
                                break;
                            }
                        }

                        raf.close();
                    }
                    String[] strings = sb.toString().split(" ");
                    fnText.setText(strings[0]);
                    lnText.setText(strings[1]);
                    ctText.setText(strings[2]);
                    pv.setValue(strings[3]);
                    posText.setText(strings[4]);
                    System.out.println("Button Last is clicked.");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            });

        btnNext.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"),"rw");
                    if(start[0]!=raf.length()){
                        raf.seek(start[0]);
                        StringBuffer sb = new StringBuffer();
                        String tmp = raf.readLine();
                        sb.append(tmp);
                        String[] strings = sb.toString().split(" ");
                        fnText.setText(strings[0]);
                        lnText.setText(strings[1]);
                        ctText.setText(strings[2]);
                        pv.setValue(strings[3]);
                        posText.setText(strings[4]);
                        System.out.println("Button Next is clicked.");
                        start[0] = raf.getFilePointer();
                    }
                    raf.close();
                }catch(IOException err){err.printStackTrace();
                }
            }
        });


        btnPrevious.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"),"rw");
                    long len = start[0];
                    int count = 0;
                    if (len != 0L) {
                        long pos = len - 1;
                        while (pos > 0) {
                            pos--;
                            raf.seek(pos);
                            if (raf.readByte() == '\n' || pos == 0) {
                                count++;
                            }
                        }
                            if(count == 2){
                                String tmp = raf.readLine();
                                StringBuffer sb = new StringBuffer();
                                sb.append(tmp);
                                String[] strings = sb.toString().split(" ");
                                fnText.setText(strings[0]);
                                lnText.setText(strings[1]);
                                ctText.setText(strings[2]);
                                pv.setValue(strings[3]);
                                posText.setText(strings[4]);
                                System.out.println("Button Previous is clicked.");
                                start[0] = raf.getFilePointer();
                            }
                        raf.close();
                    }
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnUpdate.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                String firstName = fnText.getText();
                String lastName = lnText.getText();
                String city = ctText.getText();
                String province = pv.getValue().toString();
                String postalCode = posText.getText();
                try{
                    RandomAccessFile raf = new RandomAccessFile(new File("./src/random access file.txt"),"rw");
                    raf.seek(start[0]);
                    raf.write(firstName.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(lastName.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(city.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(province.getBytes());
                    raf.write(" ".getBytes());
                    raf.write(postalCode.getBytes());
                    start[0] = raf.getFilePointer();
                    System.out.println("Button Update is clicked.");
                    raf.close();
                }catch(IOException err){err.printStackTrace();
                }
            }
        });

    }



    public static void main(String[] args) {
        launch(args);
    }

}
