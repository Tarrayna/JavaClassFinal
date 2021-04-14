package smith.tarrayna.cards;

public class CardArt {
    public static final String ZERO = new StringBuilder("000000\n" +
            "00  00\n" +
            "00  00\n" +
            "00  00\n" +
            "000000\n").toString();
    public static final String ONE = new StringBuilder("1111\n" +
            "  11\n" +
            "  11\n" +
            "  11\n" +
            "111111\n").toString();
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
    public static final String DRAW_TWO = new StringBuilder("DDDDDD     222222\n" +
            "DD   DD         2\n" +
            "DD    DD   222222\n" +
            "DD   DD    2\n" +
            "DDDDD      222222\n").toString();
    public static final String SKIP = new StringBuilder(" SSSSSS\n" +
            "SS\n" +
            " SSSS \n" +
            "    SS\n" +
            "SSSSSS \n").toString();
    public static final String REVERSE = new StringBuilder("RRRRRRR \n" +
            "RR    RR\n" +
            "RRRRRR\n" +
            "RR   RR\n" +
            "RR    RR\n").toString();
    public static final String WILD = new StringBuilder("WW       WW\n" +
            "WW       WW\n" +
            "WW   W   WW\n" +
            " WW WWW WW\n" +
            "  WW   WW\n").toString();
    public static final String WILD_DRAW_FOUR = new StringBuilder(
                    "WW       WW      44    44\n" +
                    "WW       WW      44    44 \n" +
                    "WW   W   WW      44444444\n" +
                    " WW WWW WW             44\n" +
                    "  WW   WW              44\n"
    ).toString();
    //TODO: Replace this with Jack, Queen, King and ACE
    public static final String NOT_COMPLTE = new StringBuilder(
            ""
    ).toString();

    //We don't need to make this. The vaiables are all static. This could be enum. But I don't wanna
    private CardArt() {};



}
