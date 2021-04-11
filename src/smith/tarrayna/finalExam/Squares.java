
package smith.tarrayna.finalExam;

public enum Squares {
    WHITE ("\u001B[31m"), BLUE("\u001B[44m"), RED("\u001B[41m"), GREEN("\u001B[32m"), YELLOW("\u001B[43m"),MAGENTA("\u001B[45m"); 
    private final String color; 
    
    private Squares(String color)
    {
        this.color = color; 
    }
    
    public String getColor()
    {
        return color; 
    }
}
