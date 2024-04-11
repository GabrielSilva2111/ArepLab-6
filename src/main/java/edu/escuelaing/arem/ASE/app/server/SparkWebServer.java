package edu.escuelaing.arem.ASE.app.server;

import static spark.Spark.get;
import static spark.Spark.port;
import edu.escuelaing.arem.ASE.app.connection.MongoConnection;

public class SparkWebServer {
    public static void main(String[] args){
        port(getPort());
        get("/logs", (req,res) -> {
            String log = req.queryParams("log");
            MongoConnection.insertLog(log);
            return MongoConnection.getLast10Logs();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568;
    }
}
