package binding;

import view.View;
import model.Model;

public class OneWayBinding implements Observer {
    
    private final Observable model;
    private final Observable view;
    private final boolean modelToView;
    private boolean atualizou = false;
    
    public OneWayBinding(Observable model, Observable view, boolean modelToView) {
        this.model = model;
        this.view = view;
        this.modelToView = modelToView;
        this.model.addObserver(this);
    }

    @Override
    public void update() {
        
        if (atualizou) return;
        
        try {
            atualizou = true;
            if (modelToView && model instanceof Model && view instanceof View) 
                ((View) view).setData(((Model) model).getData());
            else if (!modelToView && model instanceof View && view instanceof Model) 
                ((Model) view).setData(((View) model).getData());
        } finally {
            atualizou = false;
        }

    }
}

