package com.example.ecommerce_d;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginpageController {
    @FXML
    TextField email;
    @FXML
    PasswordField password;
    @FXML
    public void login(MouseEvent e) throws SQLException, IOException {
        String query=String.format("Select * from user where emailId='%s' AND pass='%s'", email.getText(),password.getText());
        ResultSet res=HelloApplication.connection.executeQuery(query);
        if(res.next()){
            HelloApplication.emailId=res.getString("emailId");
            String userType=res.getString("userType");
            if(userType.equals("Seller")){
                AnchorPane sellerPage= FXMLLoader.load(getClass().getResource("sellerpage.fxml"));
                HelloApplication.root.getChildren().add(sellerPage);
            }else if(userType.equals("Buyer")){
                System.out.println("We are Logged in As Buyer");
                ProductPage productPage=new ProductPage();

                Header header=new Header();
                AnchorPane productPane=new AnchorPane();
                productPane.getChildren().add(productPage.products());
                productPane.setLayoutX(150);
                productPane.setLayoutY(100);
                HelloApplication.root.getChildren().clear();
                HelloApplication.root.getChildren().addAll(header.root,productPane);
            }else if(userType.equals("Admin")){
                System.out.println("We are Logged in As Admin");
                AnchorPane adminPage=FXMLLoader.load(getClass().getResource("adminpage.fxml"));
                HelloApplication.root.getChildren().add(adminPage);
            }
            System.out.println("This User is Present in User table");
        }else{
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("Login Failed, Please check user name or password and try again");
            dialog.showAndWait();
        }
    }
}
