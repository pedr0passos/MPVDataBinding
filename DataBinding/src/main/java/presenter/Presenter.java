package presenter;

import binding.OneWayBinding;
import binding.TwoWayBinding;
import model.Model;
import view.View;

public class Presenter {
    
    private final Model model;
    private final View view;

    public Presenter(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void setModelData(String data) {
        model.setData(data);
    }

    public void setViewData(String data) {
        view.setData(data);
    }

    public void bindOneWayModelToView() {
        new OneWayBinding(model, view, true);
    }

    public void bindOneWayViewToModel() {
        new OneWayBinding(view, model, false);
    }

    public void bindTwoWay() {
        new TwoWayBinding(model, view);
    }
}