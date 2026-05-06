package com.narxoz.rpg.memento;

import com.narxoz.rpg.combatant.HeroMemento;
import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Stores hero snapshots for the Chronomancer's Vault rewind mechanic.
 * This class intentionally sits in a different package from {@link HeroMemento}
 * so it can only treat mementos as opaque values.
 */
public class Caretaker {

    private final Deque<HeroMemento> history = new ArrayDeque<>();
    /**
     * Saves a snapshot to the caretaker history.
     *
     * @param memento the snapshot to store
     */
    public void save(HeroMemento memento) {
        // TODO: push the snapshot onto the history stack.
        if (memento != null) {
            history.push(memento);
        }
    }

    /**
     * Removes and returns the most recent snapshot.
     *
     * @return the latest stored snapshot, or null in the scaffold
     */
    public HeroMemento undo() {
        // TODO: pop the most recent snapshot from the history stack.
        return history.isEmpty() ? null : history.pop();
    }

    /**
     * Returns the most recent snapshot without removing it.
     *
     * @return the latest stored snapshot, or null in the scaffold
     */
    public HeroMemento peek() {
        // TODO: read the top snapshot without exposing its internals.
        return history.isEmpty() ? null : history.peek();
    }

    /**
     * Reports how many snapshots are stored.
     *
     * @return the number of saved snapshots
     */
    public int size() {
        // TODO: return the history size.
        return history.size();
    }
}
