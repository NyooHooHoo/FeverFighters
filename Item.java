import java.io.IOException;

public class Item extends Sprite {
    private final boolean isBad;
    private final String description;

    public Item(int x, int y, String type, boolean isBad) throws IOException {
        super(x, y, type);
        this.isBad = isBad;

        switch (type) {
            case "water" -> this.description = """
                    \t                WATER
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "soup" -> this.description = """
                    \t                 SOUP
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "bed" -> this.description = """
                    \t                   BED
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "cold-med" -> this.description = """
                    \t          COLD MEDICINE
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "pill" -> this.description = """
                    \t     NON-COLD MEDICINE
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "pepper" -> this.description = """
                    \t            SPICY FOODS
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            case "chips" -> this.description = """
                    \t             HARD FOODS
                                        
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                                        
                    \t\t[press ENTER to continue]""";
            default -> this.description = "";
        }
    }

    public String getDescription() {
        return description;
    }

    public boolean isBad() {
        return isBad;
    }
}
