package Commons;

import Dbs.Contracts.Worker;

import java.util.concurrent.Callable;

public class WorkerAnalyser {
    private String workerName;
    private Worker worker;

    public WorkerAnalyser(Worker w, String workerName){
        this.workerName = workerName;
        this.worker = w;
    }

    public void execBench(){
        System.out.println("Bench launched for " + workerName);

        executor(() -> worker.setUp(), "setUp");
        executor(() -> worker.insert(), "insert");
        executor(() -> worker.selectAll(), "selectAll");
        executor(() -> worker.selectOne("magicEntry"), "selectOne");
        executor(() -> worker.sort(), "sort");
        executor(() -> worker.updateAll(), "updateAll");
        executor(() -> worker.delete(), "delete");
        executor(() -> worker.tearDown(), "tearDown");

        System.out.println("Bench finished for " + workerName);
    }

    public void executor(Callable<?> func, String operation){
        long startTime = System.currentTimeMillis();

        try {
            func.call();
        } catch (Exception e) {
            e.printStackTrace();
        }

        long stopTime = System.currentTimeMillis();

        System.out.println("Elapsed time was " + (stopTime - startTime) + " miliseconds for worker " + workerName + " on " + operation + " operation");
    }
}
