package org.jbpm.cases.orderithwapp;

import javax.sql.DataSource;
import javax.transaction.TransactionManager;

import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.dbcp2.managed.BasicManagedDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfiguration {

    @Autowired
    private TransactionManager tm;

    @Value("${spring.custom.datasource.url}")
    private String url;

    @Value("${spring.custom.datasource.driver-class-name}")
    private String driverName;

    @Value("${spring.custom.datasource.username}")
    private String username;

    @Value("${spring.custom.datasource.password}")
    private String password;

    // @Bean
    // DataSource getDataSource() {
    //     BasicManagedDataSource ds = new BasicManagedDataSource();
    //     ds.setDriverClassName(driverName);
    //     ds.setUrl(url);
    //     ds.setUsername(username);
    //     ds.setPassword(password);

    //     ds.setInitialSize(10);
    //     ds.setMaxTotal(30);
    //     ds.setMaxWaitMillis(30000);
    //    // ds.setRemoveAbandonedOnBorrow(true);
    //    //  ds.setAbandonedUsageTracking(true);
    //     if (tm != null) {
    //         try {
    //             tm.setTransactionTimeout(240);
    //         } catch (Exception e) {
    //             System.out.println("error setting tm timeout");
    //         }
    //     } else System.out.println("tm is null..");
    //     ds.setTransactionManager(tm);
    //     return ds;
    // }

    @Bean
    DataSource getDataSource() {
        BasicManagedDataSource ds = new BasicManagedDataSource();
        
        ds.setDriverClassName(driverName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);

        ds.setInitialSize(10);
        ds.setMaxTotal(30);
        ds.setMaxWaitMillis(30000);
        ds.setAbandonedUsageTracking(true);
       //  ds.setRemoveAbandonedOnBorrow(true);
       //  ds.setAbandonedUsageTracking(true);
        if (tm != null) {
            try {
                tm.setTransactionTimeout(240);
            } catch (Exception e) {
                System.out.println("error setting tm timeout");
            }
        } else System.out.println("tm is null..");
        ds.setTransactionManager(tm);
        return ds;
    }
}
