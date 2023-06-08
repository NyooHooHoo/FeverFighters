import java.io.IOException;

public class Item extends Sprite {
    private final boolean isBad;
    private final String description;

    public Item(int x, int y, String type, boolean isBad) throws IOException {
        super(x, y, type);
        this.isBad = isBad;

        switch (type) {
            case "water" -> this.description = """
                    \t            WATER
                                        
                    Water helps the body flush out toxins to keep your immune system in good condition. Staying hydrated is essential for good health, and your body needs it even more when you are sick!
                                                                       
                                                                                                                                               
                    \t                [press ENTER to continue]""";
            case "soup" -> this.description = """
                    \t             SOUP
                                        
                    Drinking soup can help you get over your cold because the sodium in the recipe helps to relieve sore throat pain. The heat of the soup helps with nasal congestion, pain, and sinus pressure.
                                             
                                                                                                                                                           
                    \t                [press ENTER to continue]""";
            case "bed" -> this.description = """
                    \t             REST
                                        
                    Getting enough rest and sleep recharges your immune system, helps your body fight the cold, and is one of the best ways to help your body heal.
                                          
                                          
                                                                                                                                                           
                    \t                [press ENTER to continue]""";
            case "cold-med" -> this.description = """
                    \t     COLD MEDICINE
                                        
                    Cold medicine often contains drugs that can relieve your pain, suppress your cough, and loosen up your mucus to make it easier to cough it up.
                         
                         
                                                                                                                                                                  
                    \t                [press ENTER to continue]""";
            case "pill" -> this.description = """
                    \tNON-COLD MEDICINE
                                        
                    Pills such as antibiotics and some over the counter medications have actually proved to have no effect against viruses that cause the common cold, and can even have potentially serious side effects.
                                          
                                                                                                                                                               
                    \t                [press ENTER to continue]""";
            case "pepper" -> this.description = """
                    \t      SPICY FOODS
                                        
                    Spicy foods can cause bloating, nausea, or pain, are more difficult to digest, and can get your nose running and aggravate your cold. Spicy foods can also cause internal irritation and inflammation.
                                                 
                                                                                                                                                          
                    \t                [press ENTER to continue]""";
            case "chips" -> this.description = """
                    \t       JUNK FOODS
                                        
                    Chips, cookies, and other junk foods are unhealthy, don’t contain any of the nutrients your body needs to fight your cold, and on top of that, the hardness and scratchiness of the chips will worsen your sore throat.
                                                                                                                                                        
                    \t                [press ENTER to continue]""";
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
