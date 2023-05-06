package com.example.ecommerce_d;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPageController {
    @FXML
    TextField email,userName,password,userType;
    @FXML
    public void addUser(MouseEvent e) throws SQLException {
        String query=String.format("insert into User values('%s','%s','%s','%s')",email.getText(),userName.getText(),password.getText(),userType.getText());
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("New User is Added");
            dialog.showAndWait();
        }
    }
    public void homePage(MouseEvent e) throws IOException, SQLException {
        AnchorPane sellerPage= FXMLLoader.load(getClass().getResource("header.fxml"));
        HelloApplication.root.getChildren().add(sellerPage);
        ProductPage productPage=new ProductPage();

        Header header=new Header();
        AnchorPane productPane=new AnchorPane();
        productPane.getChildren().add(productPage.products());
        productPane.setLayoutX(150);
        productPane.setLayoutY(100);
        HelloApplication.root.getChildren().clear();
        HelloApplication.root.getChildren().addAll(header.root,productPane);
    }
}
