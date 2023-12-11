package ua.lpnu.lab5;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ua.lpnu.lab5.methods.*;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Лабораторна робота №5 Зозуля Роман КН-304");
        // ====================
        Scene scene = new Scene(table(), 900, 700);
        stage.setScene(scene);
        // ====================
        stage.getIcons().add(new Image("file:src/main/resources/images/lab5.png"));
        akm.setOnAction(actionEvent -> akmButtonClick());
        mkm.setOnAction(actionEvent -> mkmButtonClick());
        zkm.setOnAction(actionEvent -> zkmButtonClick());
        stage.show();
    }

    private static CategoryAxis x = new CategoryAxis();
    private static NumberAxis y = new NumberAxis();
    private static CategoryAxis x2 = new CategoryAxis();
    private static NumberAxis y2 = new NumberAxis();
    private static TextField N = new TextField();
    private static TextField X0 = new TextField();
    private static TextField X1 = new TextField();
    private static TextField A = new TextField();
    private static TextField C = new TextField();
    private static TextField B = new TextField();
    private static ComboBox comboBoxIntegral;
    private static ComboBox comboBoxPoints;
    private static Button akm = new Button("Адитивний метод");
    private static Button mkm = new Button("Мультиплікативний метод");
    private static Button zkm = new Button("Змішаний метод");
    private static BarChart bc1;
    private static XYChart.Series ds1;
    private static BarChart bc2;
    private static XYChart.Series ds2;
    private static TextArea textArea1 = new TextArea();
    private static TextArea textArea2 = new TextArea();
    public static FlowPane table() {
        // txt fields
        Label labelN = new Label("N  ");
        labelN.setLabelFor(N);
        Label labelX0 = new Label("X0");
        labelX0.setLabelFor(X0);
        Label labelX1 = new Label("X1");
        labelX1.setLabelFor(X1);
        Label labelA = new Label("A  ");
        labelA.setLabelFor(A);
        Label labelC = new Label("C  ");
        labelC.setLabelFor(C);
        Label labelB = new Label("B  ");
        labelB.setLabelFor(B);
        N.setPrefWidth(60);
        X0.setPrefWidth(60);
        X1.setPrefWidth(60);
        A.setPrefWidth(60);
        C.setPrefWidth(60);
        B.setPrefWidth(60);
        FlowPane rootTemp2 = new FlowPane(Orientation.HORIZONTAL, 10, 15);
        FlowPane rootTemp3 = new FlowPane(Orientation.HORIZONTAL, 10, 15);
        rootTemp2.getChildren().addAll(labelN, N,labelX0,X0,labelX1,X1);
        rootTemp3.getChildren().addAll(rootTemp2
                ,labelA,A,labelC,C,labelB,B);
        rootTemp3.setTranslateX(150);
        rootTemp3.setTranslateY(60);

        // dropdown
        String forIntegral[] = {"0.90", "0.95", "0.99"};
        String forPoints[] = {"0.01", "0.05", "0.25", "0.50", "0.75", "0.95", "0.99"};
        comboBoxIntegral = new ComboBox(FXCollections.observableArrayList(forIntegral));
        comboBoxPoints = new ComboBox(FXCollections.observableArrayList(forPoints));
        FlowPane rootTemp4 = new FlowPane(Orientation.VERTICAL, 10, 15);
        rootTemp4.getChildren().addAll(new Text("Інтервальний метод\nКолмогорова-Смірнова"),comboBoxIntegral);
        FlowPane rootTemp5 = new FlowPane(Orientation.VERTICAL, 10, 15);
        rootTemp5.getChildren().addAll(new Text("Точковий метод\nКолмогорова-Смірнова"),comboBoxPoints);

        // buttons
        FlowPane rootTemp6 = new FlowPane(Orientation.HORIZONTAL, 10, 10);
        rootTemp6.getChildren().addAll(akm, mkm, zkm);
        zkm.setTranslateY(-36);
        zkm.setTranslateX(295);

        // chart
        bc1 = new BarChart(x, y);
        bc1.setPrefWidth(300);
        bc1.setPrefHeight(300);
        ds1 = new XYChart.Series();
        bc1.setTranslateY(250);
        bc1.setTranslateX(-780);
        bc2 = new BarChart(x2, y2);
        bc2.setPrefWidth(300);
        bc2.setPrefHeight(300);
        ds2 = new XYChart.Series();
        bc2.setTranslateY(-72);
        bc2.setTranslateX(-350);


        akm.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        mkm.setStyle("-fx-background-color: #008CBA; -fx-text-fill: white;");
        zkm.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");

        // txt
        Text integralText = new Text("Інтервальний метод");
        Text pointText = new Text("Точковий метод");
        integralText.setTranslateX(-680);
        integralText.setTranslateY(-100);
        pointText.setTranslateX(-235);
        pointText.setTranslateY(-140);

        // txt area
        textArea1.setEditable(false);
        textArea2.setEditable(false);
        textArea1.setPrefRowCount(3);
        textArea1.setPrefColumnCount(23);
        textArea1.setTranslateY(580);
        textArea1.setTranslateX(-1080);
        textArea2.setPrefRowCount(3);
        textArea2.setPrefColumnCount(23);
        textArea2.setTranslateY(485);
        textArea2.setTranslateX(-650);

        FlowPane root = new FlowPane(Orientation.VERTICAL, 22, 22);
        root.getChildren().addAll(rootTemp3,rootTemp4,rootTemp5,rootTemp6);
        rootTemp4.setTranslateY(-50);
        rootTemp4.setTranslateX(490);
        rootTemp5.setTranslateY(40);
        rootTemp5.setTranslateX(240);
        rootTemp6.setTranslateX(-180);
        rootTemp6.setTranslateY(-250);
        root.getChildren().add(bc1);
        root.getChildren().add(bc2);
        root.getChildren().addAll(integralText,pointText,textArea1,textArea2);
        root.setStyle("-fx-background-color: #ADD8E6;" );
        return root;
    }

    private static void akmButtonClick(){
        bc1.getData().clear();
        bc1.layout();
        bc2.getData().clear();
        bc2.layout();
        ds1 = new XYChart.Series();
        ds2 = new XYChart.Series();

        Akm m = new Akm(Integer.parseInt(X0.getText()), Integer.parseInt(X1.getText()),
                Integer.parseInt(B.getText()), Integer.parseInt(N.getText()));
        double[] arr = m.getArray();
        Distribution dis = new Distribution(arr);
        List<Double> intervals = dis.getIntervals();
        List<Integer> countElemOnIntervals = dis.getCountElementOnInterval();

        PointsMethodKS pKS = new PointsMethodKS(arr);
        double[] theorArr = pKS.getTheorArr();
        double[] sortArr = pKS.getSortArr();

        for (int i = 0; i < intervals.size() - 1; i++){
            ds1.getData().add(new XYChart.Data(""+i,dis.getTheorIntervals()[i]));
            ds1.getData().add(new XYChart.Data(""+i,dis.getExperIntervals()[i]));
        }

        for (int i = 0; i < sortArr.length - 1; i++){
            ds2.getData().add(new XYChart.Data(""+i,theorArr[i]));
            ds2.getData().add(new XYChart.Data(""+i,sortArr[i]));
        }
        bc1.getData().add(ds1);
        bc2.getData().add(ds2);
        bc1.setBarGap(0);
        bc2.setBarGap(0);

        hypothesis(dis,pKS);
    }

    private static void mkmButtonClick(){
        bc1.getData().clear();
        bc1.layout();
        bc2.getData().clear();
        bc2.layout();
        ds1 = new XYChart.Series();
        ds2 = new XYChart.Series();

        Mkm m = new Mkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()),
                Integer.parseInt(B.getText()), Integer.parseInt(N.getText()));
        double[] arr = m.getArray();
        Distribution dis = new Distribution(arr);
        List<Double> intervals = dis.getIntervals();
        List<Integer> countElemOnIntervals = dis.getCountElementOnInterval();

        PointsMethodKS pKS = new PointsMethodKS(arr);
        double[] theorArr = pKS.getTheorArr();
        double[] sortArr = pKS.getSortArr();

        for (int i = 0; i < intervals.size() - 1; i++){
            ds1.getData().add(new XYChart.Data(""+i,dis.getTheorIntervals()[i]));
            ds1.getData().add(new XYChart.Data(""+i,dis.getExperIntervals()[i]));
        }

        for (int i = 0; i < sortArr.length - 1; i++){
            ds2.getData().add(new XYChart.Data(""+i,theorArr[i]));
            ds2.getData().add(new XYChart.Data(""+i,sortArr[i]));
        }
        bc1.getData().add(ds1);
        bc2.getData().add(ds2);
        bc1.setBarGap(0);
        bc2.setBarGap(0);

        hypothesis(dis,pKS);
    }

    private static void zkmButtonClick(){
        bc1.getData().clear();
        bc1.layout();
        bc2.getData().clear();
        bc2.layout();
        ds1 = new XYChart.Series();
        ds2 = new XYChart.Series();

        Zkm m = new Zkm(Integer.parseInt(X0.getText()), Integer.parseInt(A.getText()),
                Integer.parseInt(C.getText()), Integer.parseInt(B.getText()), Integer.parseInt(N.getText()));
        double[] arr = m.getArray();
        Distribution dis = new Distribution(arr);
        List<Double> intervals = dis.getIntervals();
        List<Integer> countElemOnIntervals = dis.getCountElementOnInterval();

        PointsMethodKS pKS = new PointsMethodKS(arr);
        double[] theorArr = pKS.getTheorArr();
        double[] sortArr = pKS.getSortArr();

        for (int i = 0; i < intervals.size() - 1; i++){
            ds1.getData().add(new XYChart.Data(""+i,dis.getTheorIntervals()[i]));
            ds1.getData().add(new XYChart.Data(""+i,dis.getExperIntervals()[i]));
        }

        for (int i = 0; i < sortArr.length - 1; i++){
            ds2.getData().add(new XYChart.Data(""+i,theorArr[i]));
            ds2.getData().add(new XYChart.Data(""+i,sortArr[i]));
        }
        bc1.getData().add(ds1);
        bc2.getData().add(ds2);
        bc1.setBarGap(0);
        bc2.setBarGap(0);

        hypothesis(dis,pKS);
    }

    private static void hypothesis(Distribution dis, PointsMethodKS pKS){
        double Kn = dis.getKN();
        double crit = dis.findCriticalValue(String.valueOf(comboBoxIntegral.getValue()));
        textArea1.setText("Значення критерію : " + Kn + "\n");
        textArea1.appendText("Критичне значення : " + crit + "\n");
        if (Kn < crit)
            textArea1.appendText("Гіпотеза підтверджується");
        else
            textArea1.appendText("Гіпотеза НЕ підтверджується");

        Kn = pKS.getKn();
        crit = pKS.findCriticalValue(String.valueOf(comboBoxPoints.getValue()));
        textArea2.setText("Значення критерію : " + Kn + "\n");
        textArea2.appendText("Критичне значення : " + crit + "\n");
        if (Kn < crit)
            textArea2.appendText("Гіпотеза підтверджується");
        else
            textArea2.appendText("Гіпотеза НЕ підтверджується");
    }

    public static void main(String[] args) {
        launch();
    }
}