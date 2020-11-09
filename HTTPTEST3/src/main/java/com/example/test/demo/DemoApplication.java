package com.example.test.demo;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;

import java.io.IOException;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) throws IOException {
    //    SpringApplication.run(DemoApplication.class, args);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://localhost:8080/greeting")
                .method("GET", null)
                .addHeader("mock-delay", "100ms")
                .addHeader("http-status", "not-acceptable2")
                .addHeader("testfolks.delay-time", "1200")
                .build();
        for (int i=1; i<11; i++) {
            Response response = client.newCall(request).execute();
            System.out.println("For loop: " + i + " " + response.toString());
            response.close();
        }
        for (int i = 1; i<10000; i++) {
            //Response response = client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                                                                    @Override
                                                                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                                                                    }

                                                                    @Override
                                                                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                                                                        System.out.println(response.toString());
                                                                        response.close();
                                                                    }
                                                                });

        }
    }

}
