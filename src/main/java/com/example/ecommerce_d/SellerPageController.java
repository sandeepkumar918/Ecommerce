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

public class SellerPageController {
    @FXML
    TextField name,price,sellerid;
    @FXML
    public void AddProduct(MouseEvent e) throws SQLException {
        int productID=1;
        ResultSet response2=HelloApplication.connection.executeQuery("select max(productID) as productID from product");
        if(response2.next()){
            productID=response2.getInt("productID")+1;
        }
        String query=String.format("insert into product values(%s,'%s',%s,'%s')",productID,name.getText(),price.getText(),sellerid.getText());
        int response=HelloApplication.connection.executeUpdate(query);
        if(response>0){
            Dialog<String> dialog=new Dialog<>();
            dialog.setTitle("Login");
            ButtonType type=new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().add(type);
            dialog.setContentText("New Product is Added");
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
