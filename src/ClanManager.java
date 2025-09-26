public class ClanManager {
    public static void main(String[] args) {
        ClanData clashRoyale = new ClanData("%23QRLRPGG8", args[0]);
        ClanInfo clanInfo = new ClanInfo(clashRoyale);
        System.out.println(clanInfo.getMember()[0].getName());
    }
}
