package com.narxoz.rpg.visitor;

import com.narxoz.rpg.artifact.*;

import java.util.ArrayList;
import java.util.List;

public class CurseDetector implements ArtifactVisitor {

    private final List<String> cursedItems = new ArrayList<>();

    public List<String> getCursedItems() {
        return cursedItems;
    }

    @Override
    public void visit(Weapon weapon) {
        // Weapons with suspiciously high attack bonuses might be cursed
        if (weapon.getAttackBonus() >= 15) {
            cursedItems.add(weapon.getName());
            System.out.println("  [CurseDetector] *** WARNING *** Weapon '" + weapon.getName()
                    + "' radiates dark energy! Possible curse.");
        } else {
            System.out.println("  [CurseDetector] Weapon '" + weapon.getName() + "' -> Clean");
        }
    }

    @Override
    public void visit(Potion potion) {
        // Potions with very low weight are probably fake and could be cursed
        if (potion.getWeight() <= 1) {
            cursedItems.add(potion.getName());
            System.out.println("  [CurseDetector] *** WARNING *** Potion '" + potion.getName()
                    + "' is suspiciously light. Tampered!");
        } else {
            System.out.println("  [CurseDetector] Potion '" + potion.getName() + "' -> Safe");
        }
    }

    @Override
    public void visit(Scroll scroll) {
        String spell = scroll.getSpellName().toLowerCase();
        if (spell.contains("death") || spell.contains("drain") || spell.contains("shadow")) {
            cursedItems.add(scroll.getName());
            System.out.println("  [CurseDetector] *** WARNING *** Scroll '" + scroll.getName()
                    + "' contains forbidden spell [" + scroll.getSpellName() + "]. Do not read aloud!");
        } else {
            System.out.println("  [CurseDetector] Scroll '" + scroll.getName() + "' -> Harmless spell");
        }
    }

    @Override
    public void visit(Ring ring) {
        // Rings with very high magic bonuses often carry a price
        if (ring.getMagicBonus() >= 10) {
            cursedItems.add(ring.getName());
            System.out.println("  [CurseDetector] *** WARNING *** Ring '" + ring.getName()
                    + "' pulses with unstable magic. Cursed!");
        } else {
            System.out.println("  [CurseDetector] Ring '" + ring.getName() + "' -> Stable aura");
        }
    }

    @Override
    public void visit(Armor armor) {
        System.out.println("  [CurseDetector] Armor '" + armor.getName() + "' -> No curse detected");
    }
}
