package Dbs.PostgreSQL;

import Commons.UuidUtil;
import Dbs.Generic.AbstractWorker;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreWorker extends AbstractWorker {
    private PostgreSingleton singleton = new PostgreSingleton();
    private Connection con = null;

    @Override
    public Object setUp() {
        con = singleton.getConnection();

        try {
            Statement st = con.createStatement();
            st.executeQuery("CREATE TABLE IF NOT EXISTS bench (data jsonb);");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object insert() {
        for(int i = 0; i < numberOfElements; i++) {
            try {
                Statement st = con.createStatement();
                st.executeQuery("INSERT INTO bench VALUES ('{\"uuid\":\" " + getUuid(i) + " \"}')");
            } catch (SQLException e) {
                errorOccured = true;
            }
        }

        return 0;
    }

    @Override
    public Object updateAll() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("UPDATE FROM bench SET data = '{\"uuid\":\" " + getUuid(0) + " \"}'");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object selectAll() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("SELECT * FROM bench");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object selectOne(String itemToFind) {
        try {
            Statement st = con.createStatement();
            st.executeQuery("SELECT * FROM bench WHERE data->>'uuid' = 'magicEntry'");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object delete() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("DELETE FROM bench;");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object sort() {
        try {
            Statement st = con.createStatement();
            st.executeQuery("SELECT * FROM bench ORDER BY data->>'uuid' DESC");
        } catch (SQLException e) {
            errorOccured = true;
        }

        return 0;
    }

    @Override
    public Object tearDown() {
        return 0;
    }
}
