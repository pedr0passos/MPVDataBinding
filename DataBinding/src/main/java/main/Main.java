package main;

import model.Model;
import presenter.Presenter;
import view.View;

public class Main {
    public static void main(String[] args) {
        
        Model model = new Model();
        View view = new View();
        Presenter presenter = new Presenter(model, view);

        presenter.bindOneWayModelToView();
        model.setData("Hello, World!");
        System.out.println(view.getData()); // Deve exibir "Hello, World!"

        presenter.bindOneWayViewToModel();
        view.setData("Hello, Java!");
        System.out.println(model.getData()); // Deve exibir "Hello, Java!"

        presenter.bindTwoWay();
        view.setData("Hello, Two-Way!");
        System.out.println(model.getData()); // Deve exibir "Hello, Two-Way!"
        model.setData("Hello, Bidirectional!");
        System.out.println(view.getData()); // Deve exibir "Hello, Bidirectional!"
    }
}

