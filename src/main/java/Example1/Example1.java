package Example1;
import com.jimchrist.clanmanager.*;

public class Example1 {
    public static void main(String[] args) {
        ClashAPI apiInstance = new ClashAPI("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjdlY2ZiZTBlLTk5OGUtNDFhNy1hZjY0LWU2OTdmOTM5YTVkOSIsImlhdCI6MTc1OTMxNzAwNywic3ViIjoiZGV2ZWxvcGVyLzM0NGFlOGIzLWU3MGItYjU1MC0wNGUwLTkyNGUyOTM1ZDgzNSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI2Mi43NC41NS44NCJdLCJ0eXBlIjoiY2xpZW50In1dfQ.Mbcq3EhOjpRq-Crm6pNzd9SpMQLmvOOoRoGTGFWGhUyG6mGA8P2oW13qO6UG4ZNKxfjvp0B_g0cILOlq64bL-Q");

        ApiResponse req = apiInstance.request("bruh");
        String body = req.getBody();

        System.out.println( body);
    }
}
