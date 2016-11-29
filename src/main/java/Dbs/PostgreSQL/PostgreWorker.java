package Dbs.PostgreSQL;

import Commons.DataSetGenerator.DataSet;
import Dbs.Generic.GenericWorker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreWorker extends GenericWorker {
    private PostgreSingleton singleton = new PostgreSingleton();
    private Connection con = null;

    public PostgreWorker(DataSet dataSet) {
        super(dataSet);
        workerName = "PostgreSQL";
    }

    @Override
    public Object setUp() {
        con = singleton.getConnection();

        try {
            Statement st = con.createStatement();
            st.executeQuery("CREATE TABLE IF NOT EXISTS bench (data jsonb);");
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return 0;
    }

    @Override
    public Object insert() {
        for(String document : dataSet.getDataset()) {
            try {
                Statement st = con.createStatement();
                st.executeQuery("INSERT INTO bench VALUES ('"+document+"')");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }

        return 0;
    }

    @Override
    public Object update() {
        for(int i = 0; i < dataSet.getNumberOfElements(); i++) {
            try {
                Statement st = con.createStatement();
                st.executeQuery("UPDATE FROM bench SET data = '" + dataSet.getUpdatedDocument() + "' WHERE data->>firstname = '"+dataSet.getMagicFirstName()+"");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Object select() {
        for(int i = 0; i < dataSet.getNumberOfElements(); i++) {
            try {
                Statement st = con.createStatement();
                st.executeQuery("SELECT * FROM bench WHERE data->>firstname = '"+dataSet.getMagicFirstName()+"'");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public Object delete() {
        for(int i = 0; i < dataSet.getNumberOfElements(); i++) {
            try {
                Statement st = con.createStatement();
                st.executeQuery("DELETE FROM bench WHERE data = '"+ dataSet.getDataset().get(i) +"'");
            } catch (SQLException e) {
                //e.printStackTrace();
            }
        }

        return 0;
    }


    @Override
    public Object stat(){

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT pg_size_pretty(pg_total_relation_size('\"public\".\"bench\"'));");

            while (rs.next()) {
                String out = rs.getString(1);
                System.out.println(out);
                rs.close();
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return 0;
    }
}
