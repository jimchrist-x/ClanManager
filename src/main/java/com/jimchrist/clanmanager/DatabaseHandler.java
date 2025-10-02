// library needed for interaction with oracle sql
// got it from here https://www.oracle.com/database/technologies/appdev/jdbc-downloads.html

// tutorial in case it dont work by default: https://www.youtube.com/watch?v=-MDPYDi01dM

package com.jimchrist.clanmanager;
import java.sql.*;

public class DatabaseHandler {
    //Connection conn = DriverManager.getConnection("url", "user", "");
    Connection conn = null;
    public DatabaseHandler(String url,String user,String password){
        // url is fucked up, raw database oracle url must be defined by specific protocol, example that works for me: jdbc:oracle:thin:@localhost:1521:XE
        try{
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        defaultTableSetup(); // may cause errors if table already created
    }

    public boolean defaultTableSetup() {
        try (Statement stmt = conn.createStatement()) {
            String createClans = "CREATE TABLE clans (\n" +
                    "    clan_tag VARCHAR2(20) PRIMARY KEY,\n" +
                    "    name VARCHAR2(100) NOT NULL,\n" +
                    "    type VARCHAR2(50),\n" +
                    "    description VARCHAR2(500),\n" +
                    "    clan_chest_status VARCHAR2(50),\n" +
                    "    badge_id NUMBER,\n" +
                    "    clan_score NUMBER,\n" +
                    "    clan_war_trophies NUMBER,\n" +
                    "    required_trophies NUMBER,\n" +
                    "    donations_per_week NUMBER,\n" +
                    "    clan_chest_level NUMBER,\n" +
                    "    clan_chest_max_level NUMBER,\n" +
                    "    members_count NUMBER,\n" +
                    "    created_date DATE DEFAULT SYSDATE\n" +
                    ")";

            String createMembers = "CREATE TABLE clan_members (\n" +
                    "    member_id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,\n" +
                    "    clan_tag VARCHAR2(20) NOT NULL,\n" +
                    "    member_tag VARCHAR2(20) NOT NULL,\n" +
                    "    name VARCHAR2(100),\n" +
                    "    role VARCHAR2(50),\n" +
                    "    last_seen VARCHAR2(50),\n" +
                    "    exp_level NUMBER,\n" +
                    "    trophies NUMBER,\n" +
                    "    arena_id NUMBER NOT NULL,\n" +
                    "    arena_name VARCHAR2(100) NOT NULL,\n" +
                    "    clan_rank NUMBER,\n" +
                    "    prev_clan_rank NUMBER,\n" +
                    "    donations NUMBER,\n" +
                    "    donations_received NUMBER,\n" +
                    "    clan_chest_points NUMBER,\n" +
                    "    created_date DATE DEFAULT SYSDATE,\n" +
                    "    CONSTRAINT fk_clan_tag FOREIGN KEY (clan_tag)\n" +
                    "        REFERENCES clans(clan_tag)\n" +
                    "        ON DELETE CASCADE\n" +
                    ")";

            stmt.execute(createClans);
            stmt.execute(createMembers);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteTables(){
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE clan_members CASCADE CONSTRAINTS;");
            stmt.execute("DROP TABLE clans CASCADE CONSTRAINTS;");
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



    // i didnt include LOCATION logging because it errored too much for me to bother caring to fix
    // check ClanInfo.java:159 i put stuff in try condition temporarily
    public boolean insertClanData(ClanInfo clanInfo) {
        try {
            String sql = "INSERT INTO clans (\n" +
                    "    clan_tag, name, type, description, clan_chest_status, \n" +
                    "    badge_id, clan_score, clan_war_trophies, required_trophies, \n" +
                    "    donations_per_week, clan_chest_level, clan_chest_max_level, \n" +
                    "    members_count\n" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, clanInfo.getTag());
                pstmt.setString(2, clanInfo.getName());
                pstmt.setString(3, clanInfo.getType());
                pstmt.setString(4, clanInfo.getDescription());
                pstmt.setString(5, clanInfo.getClanChestStatus());
                pstmt.setInt(6, clanInfo.getBadgeId());
                pstmt.setInt(7, clanInfo.getClanScore());
                pstmt.setInt(8, clanInfo.getClanWarTrophies());
                pstmt.setInt(9, clanInfo.getRequiredTrophies());
                pstmt.setInt(10, clanInfo.getDonationsPerWeek());
                pstmt.setInt(11, clanInfo.getClanChestLevel());
                pstmt.setInt(12, clanInfo.getClanChestMaxLevel());
                pstmt.setInt(13, clanInfo.getMembers());

                pstmt.executeUpdate();
                System.out.println("Inserted clan data for: " + clanInfo.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean insertClanMembers(ClanInfo clanInfo) {
        try {
            String sql = "INSERT INTO clan_members (\n" +
                    "    clan_tag, member_tag, name, role, last_seen, \n" +
                    "    exp_level, trophies, arena_id, arena_name, \n" +
                    "    clan_rank, prev_clan_rank, donations, \n" +
                    "    donations_received, clan_chest_points\n" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                for (int i = 0; i < clanInfo.getMembers(); i++) {
                    Member m = clanInfo.getMember(i);
                    Arena a = m.getArena();

                    pstmt.setString(1, clanInfo.getTag());
                    pstmt.setString(2, m.getTag());
                    pstmt.setString(3, m.getName());
                    pstmt.setString(4, m.getRole());
                    pstmt.setString(5, m.getLastSeen());
                    pstmt.setInt(6, m.getExpLevel());
                    pstmt.setInt(7, m.getTrophies());
                    pstmt.setInt(8, a.getId());
                    pstmt.setString(9, a.getName());
                    pstmt.setInt(10, m.getClanRank());
                    pstmt.setInt(11, m.getPrevClanRank());
                    pstmt.setInt(12, m.getDonations());
                    pstmt.setInt(13, m.getDonationsReceived());
                    pstmt.setInt(14, m.getClanChestPoints());

                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                System.out.println("Inserted " + clanInfo.getMembers() + " members for clan: " + clanInfo.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void insertBoth(ClanInfo clanInfo) {
        insertClanData(clanInfo);
        insertClanMembers(clanInfo);
    }
}
