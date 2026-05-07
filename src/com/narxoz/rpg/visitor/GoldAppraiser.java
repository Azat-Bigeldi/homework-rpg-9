package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Estimates the resale value of each artifact in the vault.
 * Weapons and armor get a 20% markup because they are harder to find.
 */
public class GoldAppraiser implements ArtifactVisitor {

    private int totalValue = 0;

    public int getTotalValue() {
        return totalValue;
    }

    @Override
    public void visit(Weapon weapon) {
        int appraisedValue = (int) (weapon.getValue() * 1.2);
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Weapon '" + weapon.getName()
                + "' -> " + appraisedValue + " gold (attack bonus: +" + weapon.getAttackBonus() + ")");
    }

    @Override
    public void visit(Potion potion) {
        totalValue += potion.getValue();
        System.out.println("  [GoldAppraiser] Potion '" + potion.getName()
                + "' -> " + potion.getValue() + " gold (heals " + potion.getHealing() + " HP)");
    }

    @Override
    public void visit(Scroll scroll) {
        totalValue += scroll.getValue();
        System.out.println("  [GoldAppraiser] Scroll '" + scroll.getName()
                + "' -> " + scroll.getValue() + " gold (spell: " + scroll.getSpellName() + ")");
    }

    @Override
    public void visit(Ring ring) {
        totalValue += ring.getValue();
        System.out.println("  [GoldAppraiser] Ring '" + ring.getName()
                + "' -> " + ring.getValue() + " gold (magic bonus: +" + ring.getMagicBonus() + ")");
    }

    @Override
    public void visit(Armor armor) {
        int appraisedValue = (int) (armor.getValue() * 1.2);
        totalValue += appraisedValue;
        System.out.println("  [GoldAppraiser] Armor '" + armor.getName()
                + "' -> " + appraisedValue + " gold (defense bonus: +" + armor.getDefenseBonus() + ")");
    }
}
