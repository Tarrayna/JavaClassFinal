package smith.tarrayna.cards;

/**
 * Color. Used when printing out a card to give it a color in the terminal.
 *
 * @author Tarrayna Smith
 * Final Project
 */
public enum Color {
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
