package datastructuretester;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sort.SimpleSorts;
import sort.ComplexSorts;

/**
 * A JavaFX 8 program to help experiment with data structures and algorithms.
 *
 * @author John Phillips
 */
public class DataStructureTester extends Application {

    Stage pStage;
    TextArea taStatus;
    ScrollPane spStatus;
    TextArea taData;
    ScrollPane spData;

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;

        taData = new TextArea();
        spData = new ScrollPane(taData);
        spData.setFitToWidth(true);
        spData.setFitToHeight(true);

        taStatus = new TextArea();
        spStatus = new ScrollPane(taStatus);
        spStatus.setFitToWidth(true);
        spStatus.setPrefViewportHeight(100);
//        spStatus.setFitToHeight(true);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(myMenuBar());
        borderPane.setCenter(spData);
        borderPane.setBottom(spStatus);

//        Scene scene = new Scene(borderPane, 800, 500);
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Data Structures");
        primaryStage.setScene(scene);

//        primaryStage.setMaximized(true);
//        primaryStage.setFullScreen(true);
        primaryStage.hide();
        primaryStage.show();
    }

    /**
     * Displays a menu for this application.
     *
     * FYI: menu accelerator key codes are listed at:
     * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/input/KeyCode.html
     *
     * @return
     */
    public MenuBar myMenuBar() {
        MenuBar myBar = new MenuBar();
        final Menu fileMenu = new Menu("File");
        final Menu dataMenu = new Menu("Data");
        final Menu sortMenu = new Menu("Sort");
        final Menu searchMenu = new Menu("Search");
        final Menu helpMenu = new Menu("Help");

        myBar.getMenus().addAll(fileMenu, dataMenu, sortMenu, searchMenu, helpMenu);

        /**
         * *********************************************************************
         * File Menu Section
         */
        MenuItem newCanvas = new MenuItem("New");
        newCanvas.setOnAction((ActionEvent e) -> {
            taData.clear();
        });
        fileMenu.getItems().add(newCanvas);

        MenuItem open = new MenuItem("Open");
        open.setOnAction((ActionEvent e) -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(pStage);
            if (file != null) {
                readFile(file);
            }
        });
        fileMenu.getItems().add(open);

        MenuItem save = new MenuItem("Save");
        save.setOnAction((ActionEvent e) -> {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showSaveDialog(pStage);
            if (file != null) {
                writeFile(file);
            }
        });
        fileMenu.getItems().add(save);

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exit);

        /**
         * *********************************************************************
         * Data Menu Section
         */
        MenuItem miGenerateIntegers = new MenuItem("Generate Integers");
        miGenerateIntegers.setOnAction(e ->  generateNumbers());
        dataMenu.getItems().add(miGenerateIntegers);

        MenuItem miRandom = new MenuItem("Randomize Data");
        miRandom.setOnAction(e -> randomizeNumbers());
        dataMenu.getItems().add(miRandom);

        /**
         * *********************************************************************
         * Sort Menu Section
         */
        MenuItem miBubbleSortAsc = new MenuItem("Bubble Sort Ascending");
        sortMenu.getItems().add(miBubbleSortAsc);
        miBubbleSortAsc.setOnAction(e-> bubbleSortAsc());

        MenuItem miBubbleSortDsc = new MenuItem("Bubble Sort Descending");
        sortMenu.getItems().add(miBubbleSortDsc);
        miBubbleSortDsc.setOnAction(e-> bubbleSortDsc());
        
        MenuItem miSelectionSortAsc = new MenuItem("Selection Sort Ascending");
        sortMenu.getItems().add(miSelectionSortAsc);
        miSelectionSortAsc.setOnAction(e-> selectionSortAsc());

        MenuItem miSelectionSortDsc = new MenuItem("Selection Sort Descending");
        sortMenu.getItems().add(miSelectionSortDsc);
        miSelectionSortDsc.setOnAction(e-> selectionSortDsc());
        
        MenuItem miInsertionSortAsc = new MenuItem("Insertion Sort Ascending");
        sortMenu.getItems().add(miInsertionSortAsc);
        miInsertionSortAsc.setOnAction(e-> insertionSortAsc());

        MenuItem miInsertionSortDsc = new MenuItem("Insertion Sort Descending");
        sortMenu.getItems().add(miInsertionSortDsc);
        miInsertionSortDsc.setOnAction(e-> insertionSortDsc());

        MenuItem miMergeSortAsc = new MenuItem("Merge Sort Ascending");
        sortMenu.getItems().add(miMergeSortAsc);
        miMergeSortAsc.setOnAction(e-> mergeSortAsc());

        MenuItem miMergeSortDsc = new MenuItem("Merge Sort Descending");
        sortMenu.getItems().add(miMergeSortDsc);
        miMergeSortDsc.setOnAction(e-> mergeSortDsc());
        
        MenuItem miQuickSortAsc = new MenuItem("Quick Sort Ascending");
        sortMenu.getItems().add(miQuickSortAsc);
        miQuickSortAsc.setOnAction(e-> quickSortAsc());

        MenuItem miQuickSortDsc = new MenuItem("Quick Sort Descending");
        sortMenu.getItems().add(miQuickSortDsc);
        miQuickSortDsc.setOnAction(e-> quickSortDsc());

        /**
         * *********************************************************************
         * Search Menu Section
         */
        MenuItem miSequentialSearch = new MenuItem("Sequential Search");
        searchMenu.getItems().add(miSequentialSearch);

        MenuItem miBinarySearch = new MenuItem("Binary Search");
        searchMenu.getItems().add(miBinarySearch);

        /**
         * *********************************************************************
         * Help Menu Section
         */
        MenuItem about = new MenuItem("About");
        about.setOnAction((ActionEvent e) -> {
            String message = "DATA STRUCTURES AND ALGORITHMS\n";
            Alert alert = new Alert(Alert.AlertType.INFORMATION, message);
            alert.setTitle("About");
            alert.setHeaderText("v1.0 by John Phillips");
            alert.showAndWait();
        });
        helpMenu.getItems().add(about);

        return myBar;
    }

    /**
     * *************************************************************************
     * File helper methods
     */
    private void readFile(File myFile) {
        int y = 0;
        try (Scanner sc = new Scanner(myFile)) {
            taData.clear();
            while (sc.hasNextLine()) {
                taData.appendText(sc.nextLine() + "\n");
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DataStructureTester.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void writeFile(File myFile) {
        try (PrintWriter writer = new PrintWriter(myFile)) {
            for (String line : taData.getText().split("\\n")) {
                writer.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(DataStructureTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static int[] text2IntArray(String s){
        //Convert text from the data text area to an array
        ArrayList<Integer> intList = new ArrayList<>();
        Scanner sc = new Scanner(s);
        while(sc.hasNextLine()){
            try{
                intList.add(Integer.parseInt(sc.nextLine()));
            }
            catch(Exception e){
                System.out.println("Wasn't an int, carry on");
            }
        }
        return intList.stream().mapToInt(d->d).toArray();
    }
    
    public static String intArray2Text(int[] a) {
        StringBuilder sb = new StringBuilder();
        String newLine = "\n";
        for (int value : a) {
            sb.append(Integer.toString(value)).append(newLine);
        }
        return sb.toString();
    }
    public void outputArrayToTextArea(int[] array){
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < array.length; i++){
            sb.append(array[i]).append("\n");
        }
        
        taData.clear();
        taData.setText(sb.toString());
    }
    public void generateNumbers(){
        int numberOfValues = 0;
        TextInputDialog tid = new TextInputDialog("10000");
        tid.setTitle("Enter number of values.");
        tid.setHeaderText("Set the size of the list to sort");
        tid.setContentText("Enter how many values you would like");
        Optional<String> result = tid.showAndWait();
        if(result.isPresent()){
            try{
                numberOfValues = Integer.parseInt(result.get());
            }catch(NumberFormatException nfe){
                numberOfValues = 100;
                System.out.println("You did not enter a number.");
            }
        }
        StringBuilder sb = new StringBuilder("");
        for(int i = 0; i < numberOfValues; i++){
            sb.append(i).append("\n");
        }
        taData.setText(sb.toString());
    }
    public void randomizeNumbers() {
        Random rand = new Random();
        int[] numbers = text2IntArray(taData.getText());
        for(int i = 0; i < numbers.length; i++){
            SimpleSorts.swap(numbers, rand.nextInt(numbers.length), rand.nextInt(numbers.length));
        }
        outputArrayToTextArea(numbers);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void bubbleSortAsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.bubbleSort(data, true);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Bubble sort asc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);
    }

    private void bubbleSortDsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.bubbleSort(data, false);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Bubble sort dsc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);       
    }

    private void selectionSortAsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.selectionSort(data, true);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Selection sort asc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);    
      
    }

    private void selectionSortDsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.selectionSort(data, false);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Selection sort dsc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);    
       
    }

    private void insertionSortAsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.insertionSort(data, true);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Insertion sort asc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);    
       
    }

    private void insertionSortDsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        SimpleSorts.insertionSort(data, false);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Insertion sort dsc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);    
      
    }

    private void mergeSortAsc() {       
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        ComplexSorts.mergeSort(data, true);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Merge sort asc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);
    }

    private void mergeSortDsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        ComplexSorts.mergeSort(data, false);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Merge sort dsc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);
    }

    private void quickSortAsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        ComplexSorts.quickSort(data, true);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Quick sort asc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);
    }

    private void quickSortDsc() {
        int[] data = text2IntArray(taData.getText());
        MyTimer.startMicroTime();
        ComplexSorts.quickSort(data, false);
        long milliTime = MyTimer.elapsedMicroTime();
        taStatus.appendText("Quick sort dsc took: " + milliTime + " micro seconds\n");
        outputArrayToTextArea(data);
    }

}