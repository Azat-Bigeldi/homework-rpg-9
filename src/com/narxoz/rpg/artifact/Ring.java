package com.narxoz.rpg.artifact;

/**
 * A ring with a small enchantment or mystical bonus.
 */
public class Ring extends Artifact {

    private final int magicBonus;

    public Ring(String name, int value, int weight, int magicBonus) {
        super(name, value, weight);
        this.magicBonus = magicBonus;
    }

    public int getMagicBonus() {
        return magicBonus;
    }

    @Override
    public void accept(ArtifactVisitor visitor) {
        // TODO: call visitor.visit(this) for double dispatch.
        visitor.visit(this);
    }
}
