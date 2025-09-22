package com.spring.Springweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//public class SpringwebApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(SpringwebApplication.class, args);
//	}
//
//}
import javax.sql.DataSource;
@SpringBootApplication
public class SpringwebApplication {
    public static void main(String[] args) throws Exception {
        var ctx = SpringApplication.run(SpringwebApplication.class, args);
        DataSource ds = ctx.getBean(DataSource.class);
        try(var conn = ds.getConnection()){
            System.out.println("Connected! DB: " + conn.getMetaData().getDatabaseProductName() +
                               " v" + conn.getMetaData().getDatabaseProductVersion());
        }
    }
}