package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

/**
 * Scans artifacts for magical properties and reports enchantment levels.
 */
public class EnchantmentScanner implements ArtifactVisitor {

    @Override
    public void visit(Weapon weapon) {
        int enchantLevel = weapon.getAttackBonus() / 3;
        String tag = enchantLevel > 0 ? "Enchanted (level " + enchantLevel + ")" : "No enchantment";
        System.out.println("  [EnchantmentScanner] Weapon '" + weapon.getName() + "' -> " + tag);
    }

    @Override
    public void visit(Potion potion) {
        // Potions are alchemical, not enchanted
        System.out.println("  [EnchantmentScanner] Potion '" + potion.getName()
                + "' -> Alchemical brew, no arcane signature");
    }

    @Override
    public void visit(Scroll scroll) {
        System.out.println("  [EnchantmentScanner] Scroll '" + scroll.getName()
                + "' -> Contains spell [" + scroll.getSpellName() + "], strong arcane signature detected");
    }

    @Override
    public void visit(Ring ring) {
        int enchantLevel = ring.getMagicBonus();
        System.out.println("  [EnchantmentScanner] Ring '" + ring.getName()
                + "' -> Enchantment level " + enchantLevel + " (strong magical resonance)");
    }

    @Override
    public void visit(Armor armor) {
        int enchantLevel = armor.getDefenseBonus() / 4;
        String tag = enchantLevel > 0 ? "Enchanted (level " + enchantLevel + ")" : "Plain material";
        System.out.println("  [EnchantmentScanner] Armor '" + armor.getName() + "' -> " + tag);
    }
}
