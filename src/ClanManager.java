public class ClanManager {
    public static void main(String[] args) {
        clashAPI api = new clashAPI(args[0]);
        System.out.println(api.request("/clans/%23QRLRPGG8"));
    }
}
