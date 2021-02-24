package eci.arsw.covidanalyzer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A Camel Application
 */
public class CovidAnalyzerTool implements Runnable{

    private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private int amountOfFilesTotal;
    private AtomicInteger amountOfFilesProcessed;
    private ConcurrentLinkedDeque<CovidAnalyzerThread> covidAnalyzerThreads;
    public static final int NUMBER_THREADS = 5;
     private boolean pausa;

    public CovidAnalyzerTool() {
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
        amountOfFilesTotal = -1;
        covidAnalyzerThreads = new ConcurrentLinkedDeque<>();
        pausa = false;
    }

    public void processResultData() {
        amountOfFilesProcessed.set(0);
        List<File> resultFiles = getResultFileList();
        amountOfFilesTotal = resultFiles.size();
        int amountOfFileByThread = amountOfFilesTotal/NUMBER_THREADS;
        for (int i = 0; i < NUMBER_THREADS; i++) {
            int aux = i+1==NUMBER_THREADS?amountOfFilesTotal%NUMBER_THREADS:0;
            List<File> numberFiles = resultFiles.subList(i*amountOfFileByThread, (i+1)*amountOfFileByThread+aux);
        }
        System.out.println(amountOfFileByThread);
        for (File resultFile : resultFiles) {
            List<Result> results = testReader.readResultsFromFile(resultFile);
            for (Result result : results) {
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();
        }
    }
 

    private List<File> getResultFileList() {
        List<File> csvFiles = new ArrayList<>();
        try (Stream<Path> csvFilePaths = Files.walk(Paths.get("src/main/resources/")).filter(path -> path.getFileName().toString().endsWith(".csv"))) {
            csvFiles = csvFilePaths.map(Path::toFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvFiles;
    }


    public Set<Result> getPositivePeople() {
        return resultAnalyzer.listOfPositivePeople();
    }

    private  void esperaSegura() {
        pausa=true;
        for (CovidAnalyzerThread thread :covidAnalyzerThreads) {
            thread.esperaSegura();
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }
    
     public void reanudarHilo() {
        
        pausa= false;
        for (CovidAnalyzerThread thread:covidAnalyzerThreads) {
            thread.reanudarHilo();
        }
    }
    public void mostrarMensaje(){
         String message = "Processed %d out of %d files.\nFound %d positive people:\n%s";
         Set<Result> positivePeople = getPositivePeople();
         String affectedPeople = positivePeople.stream().map(Result::toString).reduce("", (s1, s2) -> s1 + "\n" + s2);
         message = String.format(message, amountOfFilesProcessed.get(), amountOfFilesTotal, positivePeople.size(), affectedPeople);
         System.out.println(message);
    }
    
    
     public void run() {
        Thread thread = new Thread(this::processResultData);
        thread.start();

        while (amountOfFilesTotal==-1 || amountOfFilesProcessed.get()<amountOfFilesTotal) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();

            if (line.contains("exit")) {
                break;
            } else if (line.isEmpty()) {
                if (pausa) {
                    reanudarHilo();
                } else {
                    esperaSegura();
                    mostrarMensaje();
                }
            } else if (!pausa && !line.isEmpty()) {
                mostrarMensaje();
            }
        }
    }



    /**
     * A main() so we can easily run these routing rules in our IDE
     */
    public static void main(String... args) throws Exception {
        CovidAnalyzerTool covidAnalyzerTool = new CovidAnalyzerTool();
        Thread processingThread = new Thread(() -> covidAnalyzerTool.processResultData());
        processingThread.start();
        
        
    }

}

