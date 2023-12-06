module com.example.airlinereservastionsystem_team_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.airlinereservastionsystem_team_1 to javafx.fxml;
    exports com.example.airlinereservastionsystem_team_1;
}