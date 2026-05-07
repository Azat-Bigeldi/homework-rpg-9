package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Calculates total carry weight of an inventory. Added as the 4th visitor to prove the Open/Closed Principle:
 * no artifact class was modified to add this new report.
 */
public class WeightCalculator implements ArtifactVisitor {

    private int totalWeight = 0;
    public int getTotalWeight() {
        return totalWeight;
    }

    @Override
    public void visit(Weapon weapon) {
        totalWeight += weapon.getWeight();
        System.out.println("  [WeightCalculator] Weapon '" + weapon.getName()
                + "' -> " + weapon.getWeight() + " kg");
    }

    @Override
    public void visit(Potion potion) {
        totalWeight += potion.getWeight();
        System.out.println("  [WeightCalculator] Potion '" + potion.getName()
                + "' -> " + potion.getWeight() + " kg");
    }

    @Override
    public void visit(Scroll scroll) {
        totalWeight += scroll.getWeight();
        System.out.println("  [WeightCalculator] Scroll '" + scroll.getName()
                + "' -> " + scroll.getWeight() + " kg");
    }

    @Override
    public void visit(Ring ring) {
        totalWeight += ring.getWeight();
        System.out.println("  [WeightCalculator] Ring '" + ring.getName()
                + "' -> " + ring.getWeight() + " kg");
    }

    @Override
    public void visit(Armor armor) {
        totalWeight += armor.getWeight();
        System.out.println("  [WeightCalculator] Armor '" + armor.getName()
                + "' -> " + armor.getWeight() + " kg");
    }
}
