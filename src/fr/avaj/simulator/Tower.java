package fr.avaj.simulator;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Tower {

    private List<Flyable> observers;

    public void register(Flyable p_flyable) {
        if (observers == null) {
            observers = new CopyOnWriteArrayList<>(); // I initialize a new ArrayList here because I don't want any memory to be used unnecessarily.
        }
        observers.add(p_flyable);
        System.out.println("Tower Says: " + p_flyable.getClass().getSimpleName() + "#" + ((Aircraft) p_flyable).name + "(" + ((Aircraft) p_flyable).id + ") registered to " + (this.getClass().getSimpleName().substring(0, 1).toLowerCase() + this.getClass().getSimpleName().substring(1).replaceAll("([A-Z])", " $1").toLowerCase()) + ".");
    }

    public void unregister(Flyable p_flyable) {
        observers.remove(p_flyable);
        System.out.println("Tower Says: " + p_flyable.getClass().getSimpleName() + "#" + ((Aircraft) p_flyable).name + "(" + ((Aircraft) p_flyable).id + ") unregistered to " + (this.getClass().getSimpleName().substring(0, 1).toLowerCase() + this.getClass().getSimpleName().substring(1).replaceAll("([A-Z])", " $1").toLowerCase()) + ".");
    }

    /* * * * * * * * * * * * *  * * * * * * * * *  
      Part of the Observer design pattern, 
      we notify the observers that the conditions 
      changed and they return a message based on 
      updated conditions 
    * * * * * * * * * * * * * * * * * * * * * * * */
    protected void conditionChanged() {
        for (Flyable flyable : observers) {
            flyable.updateConditions();
        }
    }
}
