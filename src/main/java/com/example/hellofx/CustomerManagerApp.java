package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerManagerApp extends Application {

    public static class Customer {
        private final String name;
        private final String province;

        public Customer(String name, String province) {
            this.name = name;
            this.province = province;
        }

        public String getName() { return name; }
        public String getProvince() { return province; }
    }

    private final ObservableList<Customer> customers = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {
        stage.setTitle("Customer Manager - Practical Lab 2");

        Label nameLabel = new Label("Customer name");
        TextField nameField = new TextField();
        nameField.setPromptText("e.g., Mary Banda");
        nameLabel.setLabelFor(nameField);

        Label provinceLabel = new Label("Province");
        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll("Central", "Lusaka", "Copperbelt", "Southern", "Eastern", "Western", "North-Western", "Northern", "Muchinga", "Luapula");
        provinceBox.setPromptText("Choose a province");
        provinceLabel.setLabelFor(provinceBox);

        Label statusLabel = new Label();

        Button saveButton = new Button("Save customer");
        saveButton.setDefaultButton(true);

        Button deleteButton = new Button("Delete selected");

        TableView<Customer> table = new TableView<>();
        table.setItems(customers);

        TableColumn<Customer, String> nameCol = new TableColumn<>("Customer name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(160);

        TableColumn<Customer, String> provinceCol = new TableColumn<>("Province");
        provinceCol.setCellValueFactory(new PropertyValueFactory<>("province"));
        provinceCol.setPrefWidth(160);

        table.getColumns().add(nameCol);
        table.getColumns().add(provinceCol);

        saveButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                statusLabel.setText("Enter the customer name.");
                nameField.requestFocus();
                return;
            }

            String province = provinceBox.getValue();
            if (province == null) {
                statusLabel.setText("Choose a province.");
                provinceBox.requestFocus();
                return;
            }

            customers.add(new Customer(name, province));
            statusLabel.setText("Customer saved.");

            nameField.clear();
            provinceBox.setValue(null);
            nameField.requestFocus();
        });

        deleteButton.setOnAction(e -> {
            Customer selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                statusLabel.setText("Select a customer from the table to delete.");
                return;
            }

            ButtonType deleteType = new ButtonType("Delete");
            Alert ask = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete the selected customer?",
                    deleteType, ButtonType.CANCEL);
            ask.setHeaderText("Confirm deletion");

            if (ask.showAndWait().orElse(ButtonType.CANCEL) == deleteType) {
                customers.remove(selected);
                statusLabel.setText("Customer deleted.");
            }
        });

        VBox formLayout = new VBox(8, nameLabel, nameField, provinceLabel, provinceBox, new HBox(10, saveButton, deleteButton), statusLabel);
        formLayout.setPadding(new Insets(15));

        VBox root = new VBox(15, formLayout, table);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 400, 500);
        stage.setScene(scene);
        stage.show();
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}

