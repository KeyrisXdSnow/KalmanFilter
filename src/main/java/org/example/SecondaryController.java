package org.example;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.example.model.KamalFilter;
import org.example.model.Sinusoid;

import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SecondaryController {

    @FXML
    private LineChart<?, ?> sinLineChart;

    @FXML
    private TextField scopeTextFiled, voltTextFiled, processTextFiled;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private ScrollPane scrollPane;

    private final int[] arr = new int[] {
            127,130,133,136,139,142,145,148,151,154,180,222,240,235,230,172,175,178,181,
            184,187,189,192,195,197,200,202,205,207,210,212,214,217,219,221,223,225,227,
            229,200,222,234,236,237,239,240,242,243,244,245,180,155,100,120,155,180,222,
            252,252,253,253,253,253,253,254,253,253,253,253,252,252,251,251,250,249,249,
            248,247,246,245,243,242,241,239,238,236,235,233,231,230,228,226,224,222,220,
            218,215,213,211,209,206,204,201,199,196,193,191,188,185,182,180,177,174,171,
            168,165,162,159,156,153,150,147,144,141,137,134,131,128,125,122,119,116,112,
            109,106,103,100,97,94,91,88,85,82,7 ,76,73,71,68,65,62,60,57,54,52,49,47,80,
            90,100,60,35,33,31,29,27,25,23,22,20,18,17,15,14,12,11,10,8,7,6,5,4,4,3,2,2,
            1,1,0,0,0,0,0,0,0,0,0,0,1,1,2,2,3,4,5,6,7,8,9,10,11,13,14,16,17,19,21,22,24,
            26,28,30,32,34,36,39,41,43,46,48,5 ,53,56,58,61,64,66,69,72,75,78,81,84,87,
            89,93,96,99,102,105,108,111,114,117,120,123,127};

    @FXML
    void initialize() {
        sinLineChart.setCreateSymbols(false);

        scrollPane.widthProperty().addListener( ((observableValue, number, t1) -> {
            reSizeWindow();
        }));

    }


    private int stepAmount = 255;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void onActionGoButton() {

        sinLineChart.getData().clear();

        var seriesCorrect = new XYChart.Series();
        var seriesIncorrect = new XYChart.Series();
        var seriesFilter = new XYChart.Series();

        seriesCorrect.setName("Изначальный график");
        seriesIncorrect.setName("Искаженный график");
        seriesFilter.setName("Отфильтрованный график");

        var sinusoid = new Sinusoid();

        var correctPoints = sinusoid.generatePoints(IntStream.range(0,256).toArray() ,127,127,stepAmount);
        var incorrectPoints = sinusoid.generateCurvePoints(arr,stepAmount);


        try {

            if (!voltTextFiled.getText().isEmpty() && !processTextFiled.getText().isEmpty()) {

                var volt = Double.parseDouble(voltTextFiled.getText());
                var process = Double.parseDouble(processTextFiled.getText());
                KamalFilter.setVarVolt(volt);
                KamalFilter.setVarProcess(process);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        for (int i=0; i < stepAmount; i++) {

            var correctPoint = correctPoints.get(i);
            var incorrectPoint = incorrectPoints.get(i);
            seriesCorrect.getData().add(new XYChart.Data<>(String.valueOf(correctPoint.getX()), correctPoint.getY()));
            seriesIncorrect.getData().add(new XYChart.Data<>(String.valueOf(incorrectPoint.getX()), incorrectPoint.getY()));
            seriesFilter.getData().add(new XYChart.Data<>(String.valueOf(incorrectPoint.getX()), KamalFilter.filter(incorrectPoint.getY())));

        }
       scrollPane.setContent(sinLineChart);
       sinLineChart.setMinSize(anchorPane.getWidth(),anchorPane.getHeight());
       sinLineChart.getData().addAll(seriesCorrect,seriesIncorrect,seriesFilter);


    }


    private void reSizeWindow() {
        anchorPane.setMinSize(scrollPane.getWidth(),scrollPane.getHeight());
        sinLineChart.setMinSize(scrollPane.getWidth(),anchorPane.getHeight());
        sinLineChart.getXAxis().setMinWidth(sinLineChart.getWidth());
    }

    @FXML
    private void resizeLineChart() {
        Double scope = 1.0;
        try {
            scope = Double.valueOf(scopeTextFiled.getText());
            sinLineChart.setMinSize(anchorPane.getWidth()*scope,anchorPane.getHeight());
        } catch (Exception e) {

        }


    }

}