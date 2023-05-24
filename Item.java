public class Item extends Sprite {
    private boolean isBad;

    public Item(int x, int y, String img, boolean isBad) {
        super(x, y, img);
        this.isBad = isBad;
    }
}
