package view;

import binding.Observable;
import binding.Observer;
import model.Model;
import java.util.ArrayList;

public class View implements Observable {
    
    private final ArrayList<Observer> observers = new ArrayList<>();
    private String data;
    private boolean atualizou = false;
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        if (atualizou) return;

        try {
            atualizou = true;
            this.data = data;
            notificaObservers();
        } finally {
            atualizou = false;
        }
    }
        
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notificaObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void update(Observable model) {
        if (model instanceof Model) {
            this.data = ((Model) model).getData();
        }
    }
}