/*
package com.liu;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseConnection;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

*/
/**
 * @author Liush
 * @description
 * @date 2019/5/29 16:58
 **//*

@Configuration
@ConditionalOnClass(DataSource.class)
public class LiquiBaseConfig {

    @Autowired
    private DataSource  dataSource;

    @Bean
    public Liquibase createLiquibase()throws Exception{

            System.out.println("------------------------");
            DatabaseConnection connection = new JdbcConnection(dataSource.getConnection());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);
            database.setDatabaseChangeLogTableName("T_"+database.getDatabaseChangeLogTableName());
            database.setDatabaseChangeLogLockTableName("T_"+database.getDatabaseChangeLogLockTableName());

            Liquibase liquibase = new Liquibase("mysql.sql", new ClassLoaderResourceAccessor(), database);
            liquibase.update("liu");
            return liquibase;

    }

}
*/
