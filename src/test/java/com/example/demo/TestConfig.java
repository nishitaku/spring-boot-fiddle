package com.example.demo;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.ext.mysql.MySqlMetadataHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

@TestConfiguration
public class TestConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public IDatabaseConnection dbUnitDatabaseConnection() throws SQLException {
        // DatabaseDataSourceConnectionを使用する。テーブル名曖昧問題を解決するにはここでスキーマ名を設定する必要がある
        // afterTestMethodでconnectionのcloseがされるのでDataSourceベースでないクラスを設定すると
        // 2つ目以降のテストでconnectionがclose済みとなり失敗する
        IDatabaseConnection conn = new DatabaseDataSourceConnection(dataSource, "serio");
        // MetadataHandlerをMySqlMetadataHandlerに設定する
        // この設定をしないとSchema修飾が正しく処理されずスキーマ間で同じ名前のテーブルがあるDBを扱うと失敗する
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_METADATA_HANDLER, new MySqlMetadataHandler());
        conn.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
        return conn;
    }
}
