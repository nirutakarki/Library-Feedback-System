package org.example.groupproject;

import com.opencsv.CSVReader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileReader;
import java.io.IOException;

public class UserLogin {
    @FXML
    private Label userINFO;

    @FXML
    private Label LoginINFO;

    @FXML
    private TextField UserSignUpEmail;

    @FXML
    private PasswordField UserSignUpPassword;


    String pathToUserData = "C:\\Users\\user\\Desktop\\Niruta Documents\\Group Assignment\\src\\main\\resources\\org\\example\\groupproject\\User.csv";

    public void buttonForUserLogin(ActionEvent event){
        String emailText = UserSignUpEmail.getText();
        String passwordText = UserSignUpPassword.getText();
        try {
            FileReader fileReader = new FileReader(pathToUserData);
            CSVReader csvReader = new CSVReader(fileReader);
            String[] rows;
            boolean found = false;
            while ((rows = csvReader.readNext()) != null){
                if (rows.length >= 7 && emailText.equals(rows[1]) && passwordText.equals(rows[6])){ // Check row length before accessing
                    LoginINFO.setText("Login Successful");
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
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
    public void buttonForUserSignUp(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UserRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = Loader.getCurrentStage(event);
        stage.setScene(scene);
    }


    @FXML
    public void loadStage(String sceneName) throws IOException{
        try {
            System.out.println("Loading FXML file: " + sceneName);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sceneName));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) userINFO.getScene().getWindow();
            stage.setScene(new Scene (root));
            stage.show();
        }catch (IOException e){
            userINFO.setText("Failed to load scene: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
