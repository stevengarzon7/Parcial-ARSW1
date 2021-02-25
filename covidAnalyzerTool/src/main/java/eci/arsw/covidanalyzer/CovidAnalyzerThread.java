
package eci.arsw.covidanalyzer;


import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author imac
 */
class CovidAnalyzerThread extends Thread{
     private ResultAnalyzer resultAnalyzer;
    private TestReader testReader;
    private AtomicInteger amountOfFilesProcessed;
    private List<File> resultFiles;
    private boolean pausa;
    
     public CovidAnalyzerThread(ResultAnalyzer resultAnalyzer, TestReader testReader, AtomicInteger amountOfFilesProcessed, List<File> resultFiles){
        this.resultAnalyzer = resultAnalyzer;
        this.amountOfFilesProcessed = amountOfFilesProcessed;
        this.testReader = testReader;
        this.resultFiles = resultFiles;

    }

    public void esperaSegura() {
        pausa=true;
    }

    public void reanudarHilo() {
        pausa=false;
         synchronized (this) {
            notifyAll();
        }
    }
       public void run() {
        for (File file:resultFiles) {
            synchronized (this) {
                while (pausa) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            List<Result> results = testReader.readResultsFromFile(file);

            for (Result result:results) {
                resultAnalyzer.addResult(result);
            }

            amountOfFilesProcessed.incrementAndGet();
        }
    }
}

    

