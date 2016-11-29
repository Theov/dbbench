package Dbs.Mongo;

import Commons.DataSetGenerator.DataSet;
import Dbs.Generic.GenericWorker;
import com.mongodb.*;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoWorker extends GenericWorker {
    private MongoSingleton singleton = new MongoSingleton();
    private MongoClient cli;
    private MongoDatabase db;

    public MongoWorker(DataSet dataSet) {
        super(dataSet);
        workerName = "MongoDB";
    }

    @Override
    public Object setUp() {
        cli = singleton.getConnection();
        db = cli.getDatabase("bench");
        //db.createCollection("bench");

        return 0;
    }

    @Override
    public Object insert() {
        //dataSet.getDataset().forEach(item->db.getCollection("bench").insertOne(new Document(JSON.parse(item))));
        return 0;
    }

    @Override
    public Object updateAll() {
        db.getCollection("bench").updateMany(Filters.eq("uuid", "magicEntry"), new Document("$set", new Document("uuid", "XXXX")));
        return 0;
    }

    @Override
    public Object selectAll() {
        db.getCollection("bench").find();

        return 0;
    }

    @Override
    public Object selectOne(String itemToFind) {
        db.getCollection("bench").find(Filters.eq("uuid", "magicEntry"));
        return 0;
    }

    @Override
    public Object sort() {
        Bson sort = Sorts.ascending("uuid");
        db.getCollection("bench").find().sort(sort);
        return 0;
    }

    @Override
    public Object delete() {
        db.getCollection("bench").deleteMany(new Document());
        return 0;
    }

    @Override
    public Object tearDown() {
        return 0;
    }
}
