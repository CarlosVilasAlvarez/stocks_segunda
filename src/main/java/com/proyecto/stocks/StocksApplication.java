package com.proyecto.stocks;

import com.proyecto.stocks.infrastructure.IEX.IEX;
import com.proyecto.stocks.infrastructure.IEX.Task;
import com.proyecto.stocks.model.DaoInterface;
import com.proyecto.stocks.model.DaoMongo;
import com.proyecto.stocks.model.DaoNeodatis;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StocksApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(StocksApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Seleccione la BD que quiere utilizar:");
        do{
            System.out.println("1.- MongoDB." +
                    "\n2.- Neodatis." +
                    "\nOpcion ===> ");
            try {
                op = Byte.parseByte(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (op == 1) {
                dao = new DaoMongo();
            } else if (op == 2) {
                dao = new DaoNeodatis();
            } else {
                System.out.println("Seleccione una opcion correcta:");
            }
        }while (op != 1 && op != 2);

        Task task = new Task();
        Timer timer = new Timer();
        Integer seconds = 86400;

        timer.scheduleAtFixedRate(task, 0, seconds);
    }
    public static DaoInterface dao = null;
    private byte op = 0;

}
