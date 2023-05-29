import java.io.IOException;

public class Item extends Sprite {
    private final boolean isBad;
    private final String description;

    public Item(int x, int y, String img, boolean isBad) throws IOException {
        super(x, y, img);
        this.isBad = isBad;
        if (img.equals("water")) {
            this.description = """
                    \t                WATER
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("soup")) {
            this.description = """
                    \t                 SOUP
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("bed")) {
            this.description = """
                    \t                   BED
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("cold-med")) {
            this.description = """
                    \t          COLD MEDICINE
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("pill")) {
            this.description = """
                    \t       NON-COLD MEDICINE
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("pepper")) {
            this.description = """
                    \t             SPICY FOODS
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else if (img.equals("chips")) {
            this.description = """
                    \t             HARD FOODS
                    
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
                    
                    \t\t[press ENTER to continue]""";
        }
        else {
            this.description = "";
        }
    }

    public String getDescription() {
        return description;
    }
}
