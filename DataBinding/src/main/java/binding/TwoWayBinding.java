package binding;

import view.View;
import model.Model;

public class TwoWayBinding implements Observer {
    
    private final Model model;
    private final View view;

    public TwoWayBinding(Model model, View view) {
        this.model = model;
        this.view = view;
        this.model.addObserver(this);
        this.view.addObserver(this);
    }

    @Override
    public void update() {
        view.setData(model.getData());
        model.setData(view.getData());
    }
}

