package com.group3.gymup.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

@Component
public class InitDBTables implements CommandLineRunner {


    @Autowired
    @Qualifier("init")
    DataSource initDataSource;   // Inject DataSource
    @Autowired
    @Qualifier("gym")
    DataSource gymDataSource;
    public void run(String... args) {
        ScriptRunner runner;
        try {
            runner = new ScriptRunner(initDataSource.getConnection());
            runner.setAutoCommit(true);
            runner.setStopOnError(true);
            //sql folder in scr/main/resources/scripts
            runner.runScript(Resources.getResourceAsReader("scripts/gymup.sql"));
            initDataSource.getConnection().close();
            gymDataSource.getConnection();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}


