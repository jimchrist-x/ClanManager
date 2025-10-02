package Example1;
import com.jimchrist.clanmanager.*;
import java.sql.*;

public class Example1 {
    public static void main(String[] args) {
        String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6IjViZmQyMmI5LWZlYmQtNDE5ZS04ODU1LTM3ODc5MDIwMDBiNyIsImlhdCI6MTc1OTM5OTYxNCwic3ViIjoiZGV2ZWxvcGVyLzM0NGFlOGIzLWU3MGItYjU1MC0wNGUwLTkyNGUyOTM1ZDgzNSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI2Mi43NC4yMC4xNjYiXSwidHlwZSI6ImNsaWVudCJ9XX0.jKu4uC4Gvd769Wf-PrGWsXro2UkTJAdpjVqBMJfHeNvv96DCvVVYp69TUjtc4LFuPsgoP3gLwfwzPw5rAEyxTQ";

        DatabaseHandler brah = new DatabaseHandler("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","tiger123");

        ClanData dataAPI = new ClanData("%23"+"8Q8R9LQ0",apiKey);
        dataAPI.loadAll();
        ClanInfo clanInfo = new ClanInfo(dataAPI);

        /*System.out.println(dataAPI.getWarLog().getBody());
        System.out.println(dataAPI.getClanInfo().getBody());
        System.out.println(dataAPI.getCurrentRiverRace().getBody());
        System.out.println(dataAPI.getCurrentWar().getBody());
        System.out.println(dataAPI.getMembers().getBody());
        System.out.println(dataAPI.getRiverRaceLog().getBody());*/

        //sql test sect
        brah.deleteTables(); // if tables already created, delete them
        brah.defaultTableSetup(); // to create tables
        brah.insertBoth(clanInfo);
    }
}
