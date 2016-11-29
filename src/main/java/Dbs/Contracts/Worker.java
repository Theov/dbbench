package Dbs.Contracts;

public interface Worker {
    Object setUp();
    Object insert();
    Object update();
    Object select();
    Object delete();
    Object stat();
    String getWorkerName();
}
