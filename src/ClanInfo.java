import org.json.JSONArray;
import org.json.JSONObject;

class Arena {
    private int id;
    private String name;
    public Arena(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
class Location {
    private int id;
    private String name;
    private boolean isCountry;
    private String countryCode;
    public  Location(int id, String name, boolean isCountry, String countryCode) {
        this.id = id;
        this.name = name;
        this.isCountry = isCountry;
        this.countryCode = countryCode;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public boolean isCountry() {
        return isCountry;
    }
    public String getCountryCode() {
        return countryCode;
    }
}
class Member {
    private String tag;
    private String name;
    private String role;
    private String lastSeen;
    private int expLevel;
    private int trophies;
    private Arena arena;
    private int clanRank;
    private int prevClanRank;
    private int donations;
    private int donationsReceived;
    private int clanChestPoints;

    // All-args constructor
    public Member(
            String tag,
            String name,
            String role,
            String lastSeen,
            int expLevel,
            int trophies,
            Arena arena,
            int clanRank,
            int prevClanRank,
            int donations,
            int donationsReceived,
            int clanChestPoints
    ) {
        this.tag = tag;
        this.name = name;
        this.role = role;
        this.lastSeen = lastSeen;
        this.expLevel = expLevel;
        this.trophies = trophies;
        this.arena = arena;
        this.clanRank = clanRank;
        this.prevClanRank = prevClanRank;
        this.donations = donations;
        this.donationsReceived = donationsReceived;
        this.clanChestPoints = clanChestPoints;
    }
    String getTag() {
        return tag;
    }
    String getName() {
        return name;
    }
    String getLastSeen() {
        return lastSeen;
    }
    int getExpLevel() {
        return expLevel;
    }
    int getTrophies() {
        return trophies;
    }
    Arena getArena() {
        return arena;
    }

    String getRole() {
        return role;
    }

    int getClanRank() {
        return clanRank;
    }

    int getPrevClanRank() {
        return prevClanRank;
    }

    int getDonations() {
        return donations;
    }

    int getDonationsReceived() {
        return donationsReceived;
    }

    int getClanChestPoints() {
        return clanChestPoints;
    }
}
public class ClanInfo {
    String tag, name, type, description, clanChestStatus;
    int badgeId, clanScore, clanWarTrophies, requiredTrophies, donationsPerWeek, clanChestLevel, clanChestMaxLevel, members;
    Location location;
    Member[] member;
    public ClanInfo(ClanData clan) {
        clan.loadClanInfo();
        if (clan.getClanInfo().getStatusCode()==200) {
            String clanInfo = clan.getClanInfo().getBody();
            JSONObject data = new JSONObject(clanInfo);
            this.tag=data.getString("tag");
            this.name=data.getString("name");
            this.type=data.getString("type");
            this.description=data.getString("description");
            this.clanChestStatus=data.getString("clanChestStatus");
            this.badgeId=data.getInt("badgeId");
            this.clanScore=data.getInt("clanScore");
            this.clanWarTrophies = data.getInt("clanWarTrophies");
            this.requiredTrophies = data.getInt("requiredTrophies");
            this.donationsPerWeek = data.getInt("donationsPerWeek");
            this.clanChestLevel = data.getInt("clanChestLevel");
            this.clanChestMaxLevel = data.getInt("clanChestMaxLevel");
            this.members=data.getInt("members");
            JSONObject locationData = data.getJSONObject("location");
            location=new Location(locationData.getInt("id"), locationData.getString("name"),
                    locationData.getBoolean("isCountry"), locationData.getString("countryCode"));
            member=new Member[members];
            for (int i=0;i<members;i++) {
                JSONObject memberData = data.getJSONArray("memberList").getJSONObject(i);
                member[i]=new Member(memberData.getString("tag"), memberData.getString("name"),
                        memberData.getString("role"), memberData.getString("lastSeen"),
                        memberData.getInt("expLevel"), memberData.getInt("trophies"),
                        new Arena(memberData.getJSONObject("arena").getInt("id"), memberData.getJSONObject("arena").getString("name")),
                        memberData.getInt("clanRank"), memberData.getInt("previousClanRank"),
                        memberData.getInt("donations"), memberData.getInt("donationsReceived"),
                        memberData.getInt("clanChestPoints"));
            }
        }
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getClanChestStatus() {
        return clanChestStatus;
    }

    public int getBadgeId() {
        return badgeId;
    }

    public int getClanScore() {
        return clanScore;
    }

    public int getClanWarTrophies() {
        return clanWarTrophies;
    }

    public int getRequiredTrophies() {
        return requiredTrophies;
    }

    public int getDonationsPerWeek() {
        return donationsPerWeek;
    }

    public int getClanChestLevel() {
        return clanChestLevel;
    }

    public int getClanChestMaxLevel() {
        return clanChestMaxLevel;
    }

    public int getMembers() {
        return members;
    }

    public Location getLocation() {
        return location;
    }

    public Member getMember(int index) {
        return member[index];
    }
}
