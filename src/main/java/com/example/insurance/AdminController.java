package com.example.insurance;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class AdminController {

    public AdminController(){}

    @FXML
    private Label name;
    @FXML
    private TableView<com.example.insurance.TableView> table;
    @FXML
    private TableView<TableViewClient> tableClient;
    @FXML
    private TextField nameUslugi;
    @FXML
    private TextField place;
    @FXML
    private TextField nameVid;
    @FXML
    private TextField price;
    @FXML
    private TextField number;
    @FXML
    private TextField numFamily;
    @FXML
    private Label usl;
    @FXML
    private Label nameUsl;
    @FXML
    private Label placeLabel;
    @FXML
    private Label vid;
    @FXML
    private Label nameV;
    @FXML
    private Label priceLabel;
    @FXML
    private Label numP;
    @FXML
    private Label numF;
    @FXML
    private Button viewClients;
    @FXML
    private Button viewVidi;
    @FXML
    private Button add;

    @FXML
    public void initialize() {
        getVidiUslugi();
    }

    @FXML
    public void addData(ActionEvent actionEvent) throws SQLException, IOException {
        JavaPostgersSQL.insertVidUsluga(nameUslugi.getText().toString(), place.getText().toString(), nameVid.getText().toString(),
                Integer.parseInt(price.getText().toString()),
                Integer.parseInt(number.getText().toString()),
                Integer.parseInt(numFamily.getText().toString())
        );
        getVidiUslugi();
    }

    @FXML
    public void backToVidi(ActionEvent actionEvent) throws SQLException, IOException {
        getVidiUslugi();
    }

    @FXML
    public void viewContacts(ActionEvent actionEvent) throws SQLException, IOException {
        tableClient.setVisible(true);
        table.setVisible(false);
        nameUslugi.setVisible(false);
        place.setVisible(false);
        nameVid.setVisible(false);
        price.setVisible(false);
        number.setVisible(false);
        numFamily.setVisible(false);
        usl.setVisible(false);
        nameUsl.setVisible(false);
        placeLabel.setVisible(false);
        vid.setVisible(false);
        nameV.setVisible(false);
        priceLabel.setVisible(false);
        numP.setVisible(false);
        numF.setVisible(false);
        viewClients.setVisible(false);
        add.setVisible(false);
        viewVidi.setVisible(true);
        tableClient.getItems().clear();
        tableClient.getColumns().clear();
        ArrayList<TableViewClient> tableViewClients = JavaPostgersSQL.getContactsToDelete();
        ObservableList<TableViewClient> data = FXCollections.observableArrayList(tableViewClients);

        name.setText("Контакты, у которых истек срок договора");

        TableColumn<TableViewClient, TableViewClient> delCol = new TableColumn<>("Удалить");
        delCol.setMinWidth(50);
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<TableViewClient, TableViewClient>() {
            private final Button deleteButton = new Button("Удалить");

            @Override
            protected void updateItem(TableViewClient tableViewClient, boolean empty) {
                super.updateItem(tableViewClient, empty);

                if (tableViewClient == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> deleteContact(data, tableViewClient));
            }
        });

        TableColumn<TableViewClient, Integer> counterCol = new TableColumn<>("№");
        counterCol.setMinWidth(50);
        counterCol.setCellValueFactory(new PropertyValueFactory<>("counter"));

        TableColumn<TableViewClient, String> firstNameCol = new TableColumn<>("Имя");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<TableViewClient, String> lastNameCol = new TableColumn<>("Фамилия");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<TableViewClient, Integer> ispCol = new TableColumn<>("Использование");
        ispCol.setMinWidth(100);
        ispCol.setCellValueFactory(new PropertyValueFactory<>("polzovanie"));

        TableColumn<TableViewClient, Integer> priceCol = new TableColumn<>("Цена");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<TableViewClient, Integer> oplCol = new TableColumn<>("Оплата");
        oplCol.setMinWidth(100);
        oplCol.setCellValueFactory(new PropertyValueFactory<>("oplata"));

        TableColumn<TableViewClient, Date> dateZCol = new TableColumn<>("Дата заключения\nдоговора");
        dateZCol.setMinWidth(100);
        dateZCol.setCellValueFactory(new PropertyValueFactory<>("dateZacl"));

        TableColumn<TableViewClient, Date> dateOCol = new TableColumn<>("Дата окончания\nдоговора");
        dateOCol.setMinWidth(100);
        dateOCol.setCellValueFactory(new PropertyValueFactory<>("dateOcon"));

        tableClient.setItems(data);
        tableClient.getColumns().addAll(counterCol, firstNameCol, lastNameCol, ispCol, priceCol, oplCol, dateZCol, dateOCol, delCol);
    }

    public void getVidiUslugi() {
        tableClient.setVisible(false);
        viewVidi.setVisible(false);
        table.setVisible(true);
        nameUslugi.setVisible(true);
        place.setVisible(true);
        nameVid.setVisible(true);
        price.setVisible(true);
        number.setVisible(true);
        numFamily.setVisible(true);
        usl.setVisible(true);
        nameUsl.setVisible(true);
        placeLabel.setVisible(true);
        vid.setVisible(true);
        nameV.setVisible(true);
        priceLabel.setVisible(true);
        numP.setVisible(true);
        numF.setVisible(true);
        viewClients.setVisible(true);
        add.setVisible(true);
        table.getItems().clear();
        table.getColumns().clear();
        ArrayList<com.example.insurance.TableView> tableViews = JavaPostgersSQL.getVidiAndUslugi();
        ObservableList<com.example.insurance.TableView> data = FXCollections.observableArrayList(tableViews);

        name.setText("Виды услуг");

        TableColumn<com.example.insurance.TableView, com.example.insurance.TableView> delCol = new TableColumn<>("Удалить");
        delCol.setMinWidth(50);
        delCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        delCol.setCellFactory(param -> new TableCell<com.example.insurance.TableView, com.example.insurance.TableView>() {
            private final Button deleteButton = new Button("Удалить");

            @Override
            protected void updateItem(com.example.insurance.TableView tableView, boolean empty) {
                super.updateItem(tableView, empty);

                if (tableView == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> deleteItem(data, tableView));
            }
        });

        TableColumn<com.example.insurance.TableView, Integer> counterCol = new TableColumn<>("№");
        counterCol.setMinWidth(50);
        counterCol.setCellValueFactory(new PropertyValueFactory<>("counter"));

        TableColumn<com.example.insurance.TableView, String> nameCol = new TableColumn<>("Название");
        nameCol.setMinWidth(250);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<com.example.insurance.TableView, Integer> priceCol = new TableColumn<>("Цена");
        priceCol.setMinWidth(100);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<com.example.insurance.TableView, Integer> numberCol = new TableColumn<>("Количество\nпосещений");
        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<com.example.insurance.TableView, Integer> numFamilyCol = new TableColumn<>("Количество\nчленов семьи");
        numFamilyCol.setMinWidth(100);
        numFamilyCol.setCellValueFactory(new PropertyValueFactory<>("numFamily"));

        TableColumn<com.example.insurance.TableView, String> nameUslugaCol = new TableColumn<>("Название\nуслуги");
        nameUslugaCol.setMinWidth(100);
        nameUslugaCol.setCellValueFactory(new PropertyValueFactory<>("nameUslugi"));

        TableColumn<com.example.insurance.TableView, String> placeCol = new TableColumn<>("Место\nпроведения");
        placeCol.setMinWidth(100);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        table.setItems(data);
        table.getColumns().addAll(counterCol, nameCol, priceCol, numberCol, numFamilyCol, nameUslugaCol, placeCol, delCol);
    }

    public void deleteItem(ObservableList<com.example.insurance.TableView> data, com.example.insurance.TableView tableView) {
        data.remove(tableView);
        JavaPostgersSQL.deleteVidUluga(tableView);
    }

    public void deleteContact(ObservableList<TableViewClient> data, TableViewClient tableViewClient) {
        data.remove(tableViewClient);
        JavaPostgersSQL.deleteClient(tableViewClient);
    }
}
