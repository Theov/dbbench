package Dbs.Mongo;

import com.mongodb.MongoClient;

public class MongoSingleton {
    MongoClient connection = null;
    private final String host = "localhost:27017";

    public MongoClient getConnection(){
        try{
            connection = new MongoClient(host, 27017);
            System.out.println("Connection to MongoDB established");
        }catch(Exception e){
            System.out.println("Connection to MongoDB Failed! Check output console");
            e.printStackTrace();
        }

        return connection;
    }
}
