package Dbs.Contracts;

public interface Worker {
    Object setUp();
    Object insert();
    Object updateAll();
    Object selectAll();
    Object selectOne(String itemToFind);
    Object delete();
    Object sort();
    Object tearDown();
}
