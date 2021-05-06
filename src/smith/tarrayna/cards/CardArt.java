package smith.tarrayna.cards;

public class CardArt {
    public static final String TWO = new StringBuilder("222222\n" +
            "     2\n" +
            "222222\n" +
            "2\n" +
            "222222\n").toString();
    public static final String THREE = new StringBuilder("333333\n"+
            "    33\n" +
            "333333\n" +
            "    33\n" +
            "333333\n").toString();
    public static final String FOUR = new StringBuilder("44  44\n" +
            "44  44\n" +
            "444444\n" +
            "    44\n" +
            "    44\n").toString();
    public static final String FIVE = new StringBuilder("555555\n" +
            "55\n" +
            "555555\n" +
            "    55\n" +
            "555555\n").toString();
    public static final String SIX = new StringBuilder("666666\n" +
            "66\n" +
            "666666\n" +
            "66  66\n" +
            "666666\n").toString();
    public static final String SEVEN = new StringBuilder("777777\n" +
            "    77\n" +
            "    77\n" +
            "    77\n" +
            "    77\n").toString();
    public static final String EIGHT = new StringBuilder("888888\n" +
            "88  88\n" +
            "888888\n" +
            "88  88\n" +
            "888888\n").toString();
    public static final String NINE = new StringBuilder("999999\n" +
            "99  99\n" +
            "999999\n" +
            "    99\n" +
            "999999\n").toString();
    public static final String TEN = new StringBuilder("1111    000000\n" +
            "  11    00  00\n" +
            "  11    00  00\n" +
            "  11    00  00\n" +
            "111111  000000\n").toString();

    public static final String ACE = new StringBuilder(
                    "AAAAAAAAA\n" +
                    "AA     AA\n" +
                    "AAAAAAAAA\n" +
                    "AA     AA\n" +
                    "AA     AA\n"
    ).toString();
    public static final String JACK = new StringBuilder(
                    "     JJ\n" +
                    "     JJ\n" +
                    "     JJ\n" +
                    "JJ   JJ \n" +
                    "JJJJJJJ\n"
    ).toString();
    public static final String KING = new StringBuilder(
                    "KK    KK\n" +
                    "KK  KK\n" +
                    "KKKKK\n" +
                    "KK  KK\n" +
                    "KK    KK\n"
    ).toString();
    public static final String QUEEN = new StringBuilder(
                    "AAAAAAAAA\n" +
                    "AA   A AA\n" +
                    "AA  A  AA\n" +
                    "AA A   AA\n" +
                    "AAAAAAAAAA\n"
    ).toString();

    //We don't need to make this. The vaiables are all static. This could be enum. But I don't wanna
    private CardArt() {};



}
