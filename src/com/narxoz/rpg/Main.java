package com.narxoz.rpg;

import com.narxoz.rpg.artifact.Inventory;
import com.narxoz.rpg.artifact.Potion;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.List;

/**
 * Entry point for Homework 9 — Chronomancer's Vault: Visitor + Memento.
 *
 * The scaffold prints the banner only; students fill in the vault demo.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");
        Inventory heroInventory = new Inventory();
        heroInventory.addArtifact(new Potion("Minor Healing Potion", 30, 1, 20));

        // 1. Create at least 2 heroes with different starting states.
        Hero warrior = new Hero("Aldric the Warrior", 120, 10, 15, 10, 200, heroInventory);
        Hero mage = new Hero("Lyra the Mage", 70, 80, 10,  8,  5, 150, new Inventory());

        // 2. Build an artifact inventory and exercise the visitor interface.
        // 3. Capture a hero snapshot through the memento workflow.
        // 4. Rewind the hero after a vault trap changes state.
        // 5. Run the ChronomancerEngine demo sequence.
        // 6. Print a final VaultRunResult summary.

        System.out.println("\n-- Starting Party --");
        System.out.println("  Hero 1: " + warrior);
        System.out.println("  Hero 2: " + mage);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult result = engine.runVault(List.of(warrior, mage));

        System.out.println("\n=== Vault Run Complete ===");
        System.out.println("  " + result);
        System.out.println("\n-- Final Party State --");
        System.out.println("  Hero 1: " + warrior);
        System.out.println("  Hero 2: " + mage);
    }
}
