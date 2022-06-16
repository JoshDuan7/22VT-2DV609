package com.group3.gymup.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DbConfig {

  @Bean(name = "init")
  public DataSource initSqlDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:mysql://localhost:3306/");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("root");
    return dataSourceBuilder.build();
  }

  @Bean(name = "gym")
  @Primary
  public DataSource mySqlDataSource() {
    DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(
        "jdbc:mysql://localhost:3306/gym?serverTimezone=Europe/Amsterdam&useUnicode=true&characterEncoding=utf8&autoReconnect=true&allowMultiQueries=true");
    dataSourceBuilder.username("root");
    dataSourceBuilder.password("root");
    return dataSourceBuilder.build();
  }
}