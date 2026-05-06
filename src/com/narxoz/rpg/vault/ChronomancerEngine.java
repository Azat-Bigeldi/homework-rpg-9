package com.narxoz.rpg.vault;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.visitor.CurseDetector;
import com.narxoz.rpg.visitor.EnchantmentScanner;
import com.narxoz.rpg.visitor.GoldAppraiser;
import com.narxoz.rpg.visitor.WeightCalculator;

import java.util.List;

/**
 * Orchestrates the Chronomancer's Vault demo run.
 */
public class ChronomancerEngine {

    /**
     * Runs the vault sequence for the supplied party.
     *
     * @param party the heroes entering the vault
     * @return a placeholder result in the scaffold
     */
    public VaultRunResult runVault(List<Hero> party) {
        // TODO: wire together mementos, visitors, and the vault sequence.
        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        // ---------------------------------------------------------------
        // Build the vault artifact inventory
        // ---------------------------------------------------------------
        Inventory vaultInventory = new Inventory();
        vaultInventory.addArtifact(new Weapon("Shadowbane Sword",  250, 8,  18));
        vaultInventory.addArtifact(new Potion("Elixir of Life",     80, 1,  50));
        vaultInventory.addArtifact(new Scroll("Scroll of Shadowdrain", 120, 1, "Shadow Drain"));
        vaultInventory.addArtifact(new Ring("Ring of the Archmage", 300, 0, 12));
        vaultInventory.addArtifact(new Armor("Dragonscale Chestplate", 400, 15, 20));
        vaultInventory.addArtifact(new Weapon("Iron Dagger",         40, 3,   5));

        artifactsAppraised = vaultInventory.size();

        System.out.println("\n--- Vault Inventory: " + artifactsAppraised + " artifacts detected ---");
        for (Artifact a : vaultInventory.getArtifacts()) {
            System.out.println("  * " + a.getName());
        }

        // Visitor 1: GoldAppraiser
        System.out.println("\n[VISITOR 1] Running GoldAppraiser...");
        GoldAppraiser appraiser = new GoldAppraiser();
        vaultInventory.accept(appraiser);
        System.out.println("  Total estimated value: " + appraiser.getTotalValue() + " gold");

        // Visitor 2: EnchantmentScanner
        System.out.println("\n[VISITOR 2] Running EnchantmentScanner...");
        vaultInventory.accept(new EnchantmentScanner());

        // Visitor 3: CurseDetector
        System.out.println("\n[VISITOR 3] Running CurseDetector...");
        CurseDetector detector = new CurseDetector();
        vaultInventory.accept(detector);
        if (!detector.getCursedItems().isEmpty()) {
            System.out.println("  Cursed items found: " + detector.getCursedItems());
        }

        // Memento: snapshot each hero before the vault trap fires
        System.out.println("\n--- Heroes enter the vault ---");
        Caretaker caretaker = new Caretaker();

        for (Hero hero : party) {
            System.out.println("  Saving snapshot for: " + hero);
            HeroMemento snapshot = hero.createMemento();
            caretaker.save(snapshot);
            mementosCreated++;
        }
        System.out.println("  Snapshots stored: " + caretaker.size());

        // Vault trap fires — heroes take damage and lose gold
        System.out.println("\n*** VAULT TRAP ACTIVATED — Time crystals shatter! ***");
        for (Hero hero : party) {
            int dmg = 35;
            hero.takeDamage(dmg);
            hero.spendGold(50);
            System.out.println("  " + hero.getName() + " takes " + dmg
                    + " damage and loses 50 gold -> " + hero);
        }

        // Chronomancer rewinds — restore each hero from memento
        System.out.println("\n*** CHRONOMANCER REWIND — Turning back time! ***");
        for (int i = party.size() - 1; i >= 0; i--) {
            Hero hero = party.get(i);
            HeroMemento snapshot = caretaker.undo();
            if (snapshot != null) {
                hero.restoreFromMemento(snapshot);
                restoredCount++;
                System.out.println("  " + hero.getName() + " restored -> " + hero);
            }
        }

        // Visitor 4: WeightCalculator (open/closed proof)
        System.out.println("\n[VISITOR 4 - Open/Closed Proof] Running WeightCalculator...");
        WeightCalculator calculator = new WeightCalculator();
        vaultInventory.accept(calculator);
        System.out.println("  Total carry weight: " + calculator.getTotalWeight() + " kg");

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}
