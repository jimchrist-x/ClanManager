package Example1;
import com.jimchrist.clanmanager.*;
import java.sql.*;

public class Example1 {
    public static String apiKey = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiIsImtpZCI6IjI4YTMxOGY3LTAwMDAtYTFlYi03ZmExLTJjNzQzM2M2Y2NhNSJ9.eyJpc3MiOiJzdXBlcmNlbGwiLCJhdWQiOiJzdXBlcmNlbGw6Z2FtZWFwaSIsImp0aSI6ImIxMjIxNTgzLWI2ZTEtNDRmYy04ZDQ0LWMzMTZmN2UxODFkZCIsImlhdCI6MTc1OTQ4NjI1Mywic3ViIjoiZGV2ZWxvcGVyLzM0NGFlOGIzLWU3MGItYjU1MC0wNGUwLTkyNGUyOTM1ZDgzNSIsInNjb3BlcyI6WyJyb3lhbGUiXSwibGltaXRzIjpbeyJ0aWVyIjoiZGV2ZWxvcGVyL3NpbHZlciIsInR5cGUiOiJ0aHJvdHRsaW5nIn0seyJjaWRycyI6WyI2Mi43NC4zOC4xMSJdLCJ0eXBlIjoiY2xpZW50In1dfQ.ut38GOfmsFUitwoIE0YLArmXYw0QVcMjIAp8_GSRvBzHSPSLmXcTIdFVVOV9KvnUWxQ2xlWQDAeBdufmVs-UCA";
    public static DatabaseHandler brah = null;


    public static ClanInfo serializeClanIntoSql(String clan){
        ClanData dataAPI = new ClanData("%23"+clan,apiKey);
        dataAPI.loadAll();
        ClanInfo clanInfo = new ClanInfo(dataAPI);

        brah.insertBoth(clanInfo);

        return clanInfo;
    }
    public static void main(String[] args) {
        brah = new DatabaseHandler("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","tiger123");

        chartGenerator chart = new chartGenerator();

        System.out.println(chart.returnHTMLChartOfMembersValues(serializeClanIntoSql("8Q8R9LQ0"),"getExpLevel"));
        //sql test sect
        brah.deleteTables(); // if tables already created, delete them
        brah.defaultTableSetup(); // to create tables

        serializeClanIntoSql("8Q8R9LQ0");
        serializeClanIntoSql("QUYPRGR0");
        serializeClanIntoSql("QRUPC0CG");
        serializeClanIntoSql("YVV0VC8J");
    }
}
