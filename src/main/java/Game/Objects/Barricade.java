package Game.Objects;

/**
 * Barricade Game object
 */
public class Barricade extends GameObject {
    public Player getOwner() {
        return owner;
    }

    private final Player owner;

    public Barricade(Player owner) {
        this.owner = owner;
    }

    @Override
    protected String getImageName() {
        return "barricade";
    }
}
