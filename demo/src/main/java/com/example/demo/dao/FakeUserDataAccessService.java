package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

import com.google.cloud.spanner.*;


@Repository
public class FakeUserDataAccessService implements UserDao {

    private static List<User> DB = new ArrayList<>();
    public static Boolean found;
    Spanner spanner = createSpannerService();
    DatabaseClient dbClient = createDbClient(spanner);
    DatabaseAdminClient dbAdminClient = spanner.getDatabaseAdminClient();

    private Spanner createSpannerService() {
        SpannerOptions options = SpannerOptions.newBuilder()
                .build();
        Spanner spanner = options.getService();
        return spanner;
    }


    private DatabaseClient createDbClient(Spanner spanner) {
        SpannerOptions options = spanner.getOptions();
        String instance = "spanner-instance";
        String database = "spanner-db";

        // Creates a database client
        DatabaseClient dbClient = spanner.getDatabaseClient(DatabaseId.of(options.getProjectId(), instance, database));

        return dbClient;
    }


    static void createAUser(DatabaseClient dbClient, Integer id, String username, String password) {
        dbClient.readWriteTransaction()
                .run(new TransactionRunner.TransactionCallable<Void>() {
                    @Override
                    public Void run(TransactionContext transaction) throws Exception {
                        transaction.buffer(Mutation.newInsertBuilder("users")
                                .set("id")
                                .to(id)
                                .set("username")
                                .to(username)
                                .set("password")
                                .to(password)
                                .build());
                        return null;
                    }
                });
    }


    @Override
    public int insertPerson(Integer id, User user) {
        System.out.print(user);
        createAUser(dbClient, id, user.getUsername(), user.getPassword());
        //saving this for testing
        DB.add(new User(id, user.getUsername(), user.getPassword()));
        return 1;
    }

    @Override
    public Boolean selectPersonByUsername(User user) {
        try (ResultSet resultSet = dbClient
                .singleUse() // Execute a single read or query against Cloud Spanner.
                .executeQuery(Statement.of("SELECT * FROM users WHERE username='"+user.getUsername()+"'"))) {
            while (resultSet.next()) {
                if(resultSet.getString("username").equals(user.getUsername())){
                    System.out.println("Found!");
                    found=true;
                } else {
                    System.out.println("Not Found!");
                    found=false;
                }
            }
        }
        return found;
    }
;
}