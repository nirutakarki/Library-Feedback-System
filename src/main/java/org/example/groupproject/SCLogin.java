package org.example.groupproject;

import com.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class SCLogin {
    @FXML
    private Label userINFO;

    @FXML
    private Label LoginINFO;
    @FXML
    private TextField SurveyCreatorEmail;
    @FXML
    private PasswordField SurveyCreatorPassword;

    String pathToSurveyCData = "C:\\Users\\user\\Desktop\\Niruta Documents\\Group Assignment\\src\\main\\resources\\org\\example\\groupproject\\SurveyCreator.csv";


    public void buttonForSurveyCreatorLogin(ActionEvent event){
        String emailText = SurveyCreatorEmail.getText();
        String passwordText = SurveyCreatorPassword.getText();
        try {
            FileReader fileReader = new FileReader(pathToSurveyCData);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (emailText.equals(rows[2]) && passwordText.equals(rows[7])){
                    LoginINFO.setText("Login Successful");
                    //loadStage("/org/example/groupproject/AdminDashboard.fxml");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SCDashboard.fxml"));
                    Scene scene = new Scene(fxmlLoader.load());
                    Stage stage = Loader.getCurrentStage(event);
                    stage.setScene(scene);
                    found = true;
                    break;
                }
            }
            if (!found) {
                LoginINFO.setText("Invalid Credentials");
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
    }
    public void buttonForSurveyCreatorSignUp(ActionEvent event) throws IOException {
        // loadStage("AdminRegister.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SCRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Loader.getCurrentStage(event);
        stage.setScene(scene);
    }

}
