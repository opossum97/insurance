package com.example.insurance;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Paint;

import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientController {
    public ClientController() {}

    static com.example.insurance.TableView tablev;
    static ArrayList<TableViewUse> tableViews;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private TableView table;
    @FXML
    private TableView vidiTable;
    @FXML
    private Label name;
    @FXML
    private Button addVid;
    @FXML
    private Button back;
    @FXML
    private Button pay;
    @FXML
    private Label tableName;
    @FXML
    private Label paySheet;
    @FXML
    private Label code;
    @FXML
    private Label contractName;
    @FXML
    private TextField nameContract;
    @FXML
    private Label exam;
    @FXML
    private Button of;
    @FXML
    private TextField numKv;
    @FXML
    private Label labKv;
    @FXML
    private TextField sumTF;
    @FXML
    private Label sumL;
    @FXML
    private Label dateLO;
    @FXML
    private Label dateLZ;
    @FXML
    private Label priceL;
    @FXML
    private Label invalData;
    @FXML
    private Button payB;
    @FXML
    private Button otchet;

    @FXML
    public void initialize() {
        payB.setVisible(false);
        invalData.setVisible(false);
        priceL.setVisible(false);
        dateLO.setVisible(false);
        dateLZ.setVisible(false);
        numKv.setVisible(false);
        labKv.setVisible(false);
        sumTF.setVisible(false);
        sumL.setVisible(false);
        of.setVisible(false);
        exam.setVisible(false);
        nameContract.setVisible(false);
        contractName.setVisible(false);
        code.setVisible(false);
        name.setVisible(false);
        back.setVisible(false);
        table.setVisible(false);
        paySheet.setVisible(false);
        Client client = HelloController.client;
        firstName.setText("Имя: " + client.getFirstName());
        lastName.setText("Фамилия: " + client.getLastName());
        getTableVidi(client);
    }

    @FXML
    public void addVidInsurance(ActionEvent actionEvent) throws SQLException, IOException {
        code.setVisible(false);
        paySheet.setVisible(false);
        pay.setVisible(false);
        tableName.setVisible(false);
        addVid.setVisible(false);
        getVidiUslugi();
    }

    @FXML
    public void back(ActionEvent actionEvent) throws SQLException, IOException{
        invalData.setVisible(false);
        contractName.setVisible(false);
        nameContract.setVisible(false);
        exam.setVisible(false);
        labKv.setVisible(false);
        numKv.setVisible(false);
        sumL.setVisible(false);
        sumTF.setVisible(false);
        name.setVisible(false);
        addVid.setVisible(true);
        pay.setVisible(true);
        tableName.setVisible(true);
        vidiTable.setVisible(true);
        otchet.setVisible(true);
        firstName.setVisible(true);
        lastName.setVisible(true);
        table.setVisible(false);
        back.setVisible(false);
        priceL.setVisible(false);
        dateLO.setVisible(false);
        dateLZ.setVisible(false);
        payB.setVisible(false);
        getTableVidi(HelloController.client);
    }

    @FXML
    public void payBill(ActionEvent actionEvent) throws SQLException, IOException {
        of.setVisible(false);
        invalData.setVisible(false);
        dateLO.setVisible(false);
        dateLZ.setVisible(false);
        priceL.setVisible(false);
        code.setVisible(false);
        addVid.setVisible(false);
        firstName.setVisible(false);
        lastName.setVisible(false);
        code.setVisible(false);
        name.setVisible(false);
        back.setVisible(true);
        table.setVisible(false);
        paySheet.setVisible(false);
        tableName.setVisible(false);
        vidiTable.setVisible(false);
        otchet.setVisible(false);
        pay.setVisible(false);
        exam.setVisible(true);
        payB.setVisible(true);
        priceL.setVisible(true);
        dateLO.setVisible(true);
        dateLZ.setVisible(true);
        numKv.setVisible(true);
        labKv.setVisible(true);
        sumTF.setVisible(true);
        sumL.setVisible(true);
        nameContract.setVisible(true);
        contractName.setVisible(true);
    }

    @FXML
    public void makePayBill(ActionEvent actionEvent) throws SQLException, IOException {
        if(nameContract.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите название контракта");
            invalData.setTextFill(Paint.valueOf("red"));
        } else if(numKv.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите номер квитанции");
            invalData.setTextFill(Paint.valueOf("red"));
        } else if(sumTF.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите сумму оплаты");
            invalData.setTextFill(Paint.valueOf("red"));
        } else {
            JavaPostgersSQL.makePayBill(nameContract.getText().toString(), Integer.parseInt(sumTF.getText().toString()),
                    Integer.parseInt(numKv.getText().toString()));
            invalData.setVisible(false);
            contractName.setVisible(false);
            nameContract.setVisible(false);
            exam.setVisible(false);
            labKv.setVisible(false);
            numKv.setVisible(false);
            sumL.setVisible(false);
            sumTF.setVisible(false);
            name.setVisible(false);
            addVid.setVisible(true);
            pay.setVisible(true);
            tableName.setVisible(true);
            vidiTable.setVisible(true);
            otchet.setVisible(true);
            firstName.setVisible(true);
            lastName.setVisible(true);
            table.setVisible(false);
            back.setVisible(false);
            priceL.setVisible(false);
            dateLO.setVisible(false);
            dateLZ.setVisible(false);
            getTableVidi(HelloController.client);
        }
    }

    public void getVidiUslugi() {
        vidiTable.setVisible(false);
        otchet.setVisible(false);
        firstName.setVisible(false);
        lastName.setVisible(false);
        back.setVisible(true);
        table.setVisible(true);
        table.getItems().clear();
        table.getColumns().clear();
        ArrayList<com.example.insurance.TableView> tableIn = JavaPostgersSQL.getVidiAndUslugi();
        ObservableList<com.example.insurance.TableView> data = FXCollections.observableArrayList(tableIn);

        name.setVisible(true);
        name.setText("Виды услуг");

        TableColumn<com.example.insurance.TableView, com.example.insurance.TableView> selectCol = new TableColumn<>("Выбрать");
        selectCol.setMinWidth(50);
        selectCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        selectCol.setCellFactory(param -> new TableCell<com.example.insurance.TableView, com.example.insurance.TableView>() {
            private final Button deleteButton = new Button("Выбрать");

            @Override
            protected void updateItem(com.example.insurance.TableView tableView, boolean empty) {
                super.updateItem(tableView, empty);

                if (tableView == null) {
                    setGraphic(null);
                    return;
                }

                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> selectItem(tableView));
            }
        });

        TableColumn<com.example.insurance.TableView, Integer> counterCol = new TableColumn<>("№");
        counterCol.setMinWidth(30);
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
        table.getColumns().addAll(counterCol, nameCol, priceCol, numberCol, numFamilyCol, nameUslugaCol, placeCol, selectCol);
    }

    public void getTableVidi(Client client) {
        tableViews = JavaPostgersSQL.getVidClienta(client);
        ObservableList<TableViewUse> data = FXCollections.observableArrayList(tableViews);

        tableName.setText("Виды услуг");

        TableColumn<TableViewUse, TableViewUse> selectCol = new TableColumn<>("Воспользоваться");
        selectCol.setMinWidth(50);
        selectCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        selectCol.setCellFactory(param -> new TableCell<TableViewUse, TableViewUse>() {
            private final Button deleteButton = new Button("Воспользоваться");

            @Override
            protected void updateItem(TableViewUse tableView, boolean empty) {
                super.updateItem(tableView, empty);

                if (tableView == null) {
                    setGraphic(null);
                    return;
                }

                Client client = HelloController.client;
                setGraphic(deleteButton);
                deleteButton.setOnAction(event -> useItem(client, tableView));
            }
        });
        TableColumn<TableViewUse, Integer> counterCol = new TableColumn<>("№");
        counterCol.setMinWidth(50);
        counterCol.setCellValueFactory(new PropertyValueFactory<>("counter"));

        TableColumn<TableViewUse, String> nameCol = new TableColumn<>("Название");
        nameCol.setMinWidth(150);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<TableViewUse, Integer> priceCol = new TableColumn<>("Цена");
        priceCol.setMinWidth(80);
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<TableViewUse, Integer> numberCol = new TableColumn<>("Количество\nпосещений");
        numberCol.setMinWidth(100);
        numberCol.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<TableViewUse, Integer> polzCol = new TableColumn<>("Количество раз\nиспользования");
        polzCol.setMinWidth(100);
        polzCol.setCellValueFactory(new PropertyValueFactory<>("polzovanie"));

        TableColumn<TableViewUse, Integer> numFamilyCol = new TableColumn<>("Количество\nчленов семьи");
        numFamilyCol.setMinWidth(100);
        numFamilyCol.setCellValueFactory(new PropertyValueFactory<>("numFamily"));

        TableColumn<TableViewUse, String> nameUslugaCol = new TableColumn<>("Название\nуслуги");
        nameUslugaCol.setMinWidth(100);
        nameUslugaCol.setCellValueFactory(new PropertyValueFactory<>("nameUslugi"));

        TableColumn<TableViewUse, String> placeCol = new TableColumn<>("Место\nпроведения");
        placeCol.setMinWidth(100);
        placeCol.setCellValueFactory(new PropertyValueFactory<>("place"));

        TableColumn<TableViewUse, Integer> oplCol = new TableColumn<>("Оплата");
        oplCol.setMinWidth(50);
        oplCol.setCellValueFactory(new PropertyValueFactory<>("oplata"));

        TableColumn<TableViewUse, Date> dateZCol = new TableColumn<>("Дата заключения\nдоговора");
        dateZCol.setMinWidth(100);
        dateZCol.setCellValueFactory(new PropertyValueFactory<>("dateZacl"));

        TableColumn<TableViewUse, Date> dateOCol = new TableColumn<>("Дата окончания\nдоговора");
        dateOCol.setMinWidth(100);
        dateOCol.setCellValueFactory(new PropertyValueFactory<>("dateOcon"));

        vidiTable.setItems(data);
        vidiTable.getColumns().addAll(counterCol, nameCol, priceCol, polzCol, numberCol, numFamilyCol, nameUslugaCol, placeCol,
                oplCol, dateZCol, dateOCol, selectCol);
    }

    public void selectItem(com.example.insurance.TableView tableView) {
        code.setVisible(false);
        firstName.setVisible(false);
        lastName.setVisible(false);
        code.setVisible(false);
        name.setVisible(false);
        back.setVisible(true);
        table.setVisible(false);
        paySheet.setVisible(false);
        exam.setVisible(true);
        of.setVisible(true);
        priceL.setVisible(true);
        dateLO.setVisible(true);
        dateLZ.setVisible(true);
        numKv.setVisible(true);
        labKv.setVisible(true);
        sumTF.setVisible(true);
        sumL.setVisible(true);
        nameContract.setVisible(true);
        contractName.setVisible(true);
        tablev = tableView;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String currentDate = formatter.format(date).toString();
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        cal.add(Calendar.YEAR, 1);
        Date nextYear = cal.getTime();
        String nextDate = formatter.format(nextYear).toString();
        priceL.setText("Цена: " + tableView.getPrice());
        dateLZ.setText("Дата заключения договора: " + currentDate);
        dateLO.setText("Дата окончания действия договора: " + nextDate);
        exam.setText("Введите один вариант\nОказание терапевтических услуг\nОказание стоматологических услуг\nОказание хирургических услуг\nОказание неврологических услуг\nОказание кардиологических услуг");
    }

    public void useItem(Client client, TableViewUse tableViewUse) {
        code.setVisible(false);
        paySheet.setVisible(false);
        if(tableViewUse.getPolzovanie() < tableViewUse.getNumber() && tableViewUse.getPrice().equals(tableViewUse.getOplata())) {
                JavaPostgersSQL.changePosechenie(tableViewUse);
                code.setVisible(true);
                code.setTextFill(Paint.valueOf("red"));
                code.setText("Ваш код, который надо предъявить: " + String.valueOf(Math.round(Math.random() * 100000)));
        }

        if(tableViewUse.getPolzovanie() < tableViewUse.getNumber() && !tableViewUse.getPrice().equals(tableViewUse.getOplata())) {
            paySheet.setVisible(true);
            paySheet.setTextFill(Paint.valueOf("red"));
        }
        getTableVidi(HelloController.client);
    }

    @FXML
    public void addContract() {
        if(nameContract.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите название контракта");
            invalData.setTextFill(Paint.valueOf("red"));
        } else if(numKv.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите номер квитанции");
            invalData.setTextFill(Paint.valueOf("red"));
        } else if(sumTF.getText().toString().isEmpty()) {
            invalData.setVisible(true);
            invalData.setText("Введите сумму оплаты");
            invalData.setTextFill(Paint.valueOf("red"));
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String currentDate = formatter.format(date).toString();
            Calendar cal = Calendar.getInstance();
            Date today = cal.getTime();
            cal.add(Calendar.YEAR, 1);
            Date nextYear = cal.getTime();
            String nextDate = formatter.format(nextYear).toString();
            JavaPostgersSQL.insertContractAndBill(nameContract.getText().toString(), tablev.getPrice(),
                    Integer.parseInt(sumTF.getText().toString()), currentDate, nextDate,
                    tablev.getIdVida(), HelloController.client.getId(), Integer.parseInt(numKv.getText().toString()));
            invalData.setVisible(false);
            contractName.setVisible(false);
            nameContract.setVisible(false);
            exam.setVisible(false);
            labKv.setVisible(false);
            numKv.setVisible(false);
            sumL.setVisible(false);
            sumTF.setVisible(false);
            name.setVisible(false);
            addVid.setVisible(false);
            pay.setVisible(false);
            tableName.setVisible(false);
            vidiTable.setVisible(true);
            otchet.setVisible(true);
            firstName.setVisible(true);
            lastName.setVisible(true);
            table.setVisible(false);
            back.setVisible(false);
            priceL.setVisible(false);
            dateLO.setVisible(false);
            dateLZ.setVisible(false);
            getVidiUslugi();
        }
    }

    @FXML
    public void createOtchet() {
        try (PrintWriter writer = new PrintWriter("D:\\Univer\\Kurs4\\KPP\\" + HelloController.client.getFirstName() +
                HelloController.client.getLastName() + ".csv")) {
            StringBuilder sb = new StringBuilder();
            sb.append("FirstName");
            sb.append(',');
            sb.append("LastName");
            sb.append(',');
            sb.append("Vid");
            sb.append(',');
            sb.append("Price");
            sb.append(',');
            sb.append("NumberOfFamily");
            sb.append(',');
            sb.append("NumberOfUses");
            sb.append('\n');

            tableViews.forEach(tv -> {
                sb.append(HelloController.client.getFirstName());
                sb.append(',');
                sb.append(HelloController.client.getLastName());
                sb.append(',');
                sb.append(tv.getName());
                sb.append(',');
                sb.append(tv.getPrice());
                sb.append(',');
                sb.append(tv.getNumFamily());
                sb.append(',');
                sb.append(tv.getPolzovanie());
                sb.append('\n');
            });
            writer.write(sb.toString());

            Logger lgr = Logger.getLogger(ClientController.class.getName());
            lgr.log(Level.INFO, "Created file!");
        } catch (IOException e) {
            Logger lgr = Logger.getLogger(ClientController.class.getName());
            lgr.log(Level.WARNING, e.getMessage(), e);
        }
    }
}
