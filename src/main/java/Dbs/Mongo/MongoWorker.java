package Dbs.Mongo;

import Commons.DataSetGenerator.DataSet;
import Dbs.Generic.GenericWorker;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoWorker extends GenericWorker {
    private MongoSingleton singleton = new MongoSingleton();
    private MongoCollection col;
    private MongoDatabase db;

    public MongoWorker(DataSet dataSet) {
        super(dataSet);
        workerName = "MongoDB";
    }

    @Override
    public Object setUp() {
        MongoClient cli = singleton.getConnection();
        db = cli.getDatabase("bench");

        if(null != db.getCollection("bench")){
            db.getCollection("bench").drop();
        }else{
            db.createCollection("bench");
        }

        col = db.getCollection("bench");

        return 0;
    }

    @Override
    public Object insert() {
        for(int i = 0; i < dataSet.getNumberOfElements(); i++){
            col.insertOne(dataSet.getDocumentDocuments().get(i));
        }

        return 0;
    }

    @Override
    public Object update() {
        for(int i = 0; i < dataSet.getNumberOfElements(); i++) {
            col.updateOne(dataSet.getMagicFilter(), dataSet.getUpdatedDocumentDocument());
        }
        

        return 0;
    }

    @Override
    public Object select() {
        dataSet.getDocumentCriterias().forEach(
                item->col.find(item).iterator()
        );

        return 0;
    }

    @Override
    public Object delete() {
        dataSet.getDocumentCriterias().forEach(
                item->col.deleteOne(item)
        );

        return 0;
    }

    @Override
    public Object stat(){
        Document stat = db.runCommand(new Document("collStats", "bench"));
        System.out.println(stat);

        return 0;
    }
}
