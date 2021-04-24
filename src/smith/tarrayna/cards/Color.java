package smith.tarrayna.cards;

public enum Color {
    //TODO: Add the color ASCII as a variable Please!!
    RED("\u001b[31m"), YELLOW("\u001b[33m"), GREEN("\u001b[32m"), BLUE("\u001b[34m"), CLEAR("\u001b[0m"), BLACK("\u001b[30m");

    private final String colorCode;

     Color(String colorCode)
    {
        this.colorCode = colorCode;

    }

    public String getColorCode()
    {
        return colorCode;
    }


}
