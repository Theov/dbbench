package Dbs.Mongo;

import Dbs.Generic.AbstractWorker;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import java.util.Collection;

public class MongoWorker extends AbstractWorker {
    private MongoSingleton singleton = new MongoSingleton();
    private MongoClient cli;
    private MongoDatabase db;

    @Override
    public Object setUp() {
        cli = singleton.getConnection();
        db = cli.getDatabase("bench");
        //db.createCollection("bench");

        return 0;
    }

    @Override
    public Object insert() {
        for (int i = 0; i < numberOfElements; i++){
            db.getCollection("bench").insertOne(new Document().append("uuid", getUuid(i)));
        }

        return 0;
    }

    @Override
    public Object updateAll() {
        db.getCollection("bench").updateMany(Filters.eq("uuid", "magicEntry"), new Document("$set", new Document("uuid", getUuid(0))));
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
