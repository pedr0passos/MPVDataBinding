# Arquitetura e Configuração de Binding com o Padrão Observer

## Visão Geral

Este projeto implementa uma arquitetura baseada no padrão Observer, onde os objetos podem ser configurados para observar e reagir a mudanças em outros objetos. Ele suporta tanto bindings unidirecionais quanto bidirecionais entre modelos (`Model`) e visões (`View`).

## Padrão Observer

O padrão Observer define uma relação um-para-muitos entre objetos, de modo que quando um objeto muda de estado, todos os seus dependentes são notificados automaticamente. Este padrão é útil para implementar notificações de eventos em sistemas.

### Interfaces Principais

#### `Observable`

A interface `Observable` define os métodos que uma classe observável deve implementar:

```java
public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notificaObservers();
}
```

- `addObserver(Observer observer)`: Adiciona um observador à lista de observadores.
- `removeObserver(Observer observer)`: Remove um observador da lista de observadores.
- `notificaObservers()`: Notifica todos os observadores sobre uma mudança no estado do objeto.

#### `Observer`

A interface `Observer` define o método que um observador deve implementar:

```java
public interface Observer {
    void update();
} 
```

- `update()`: Método chamado quando o estado do objeto observado muda.

### Implementação do Padrão

O projeto inclui implementações concretas do padrão Observer através das classes `Model`, `View`, `OneWayBinding` e `TwoWayBinding`.

## Classes de Binding

### `OneWayBinding`

A classe `OneWayBinding` implementa o padrão Observer para suportar binding unidirecional entre um modelo (`Model`) e uma visão (`View`).

```java
public class OneWayBinding implements Observer {
    
    private final Observable model;
    private final Observable view;
    private final boolean modelToView;
    
    public OneWayBinding(Observable model, Observable view, boolean modelToView) {
        this.model = model;
        this.view = view;
        this.modelToView = modelToView;
        this.model.addObserver(this);
    }

    @Override
    public void update() {
        if (modelToView && model instanceof Model && view instanceof View) 
            ((View) view).updateFromModel(((Model) model).getData());
        else if (!modelToView && model instanceof View && view instanceof Model) 
            ((Model) view).updateFromView(((View) model).getData());
    }
}
```

- **Construtor:** Cria um binding unidirecional. O parâmetro `modelToView` define a direção do binding.
- **Método `update()`:** Atualiza a `view` com dados do `model` se `modelToView` for `true`, ou atualiza o `model` com dados da `view` se `modelToView` for `false`.

### `TwoWayBinding`

A classe `TwoWayBinding` implementa o padrão Observer para suportar binding bidirecional entre um modelo (`Model`) e uma visão (`View`).

```java
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
        view.updateFromModel(model.getData());
        model.updateFromView(view.getData());
    }
}
```

- **Construtor:** Cria um binding bidirecional entre `model` e `view`.
- **Método `update()`:** Atualiza a `view` com dados do `model` e o `model` com dados da `view`.

## Exemplo de Uso

### Binding Unidirecional

```java
Model model = new Model();
View view = new View();
OneWayBinding binding = new OneWayBinding(model, view, true);

// Quando o modelo é atualizado, a visão também será atualizada automaticamente.
model.setData("Novos dados");
```

### Binding Bidirecional

```java
Model model = new Model();
View view = new View();
TwoWayBinding binding = new TwoWayBinding(model, view);

// Quando o modelo ou a visão são atualizados, ambos são sincronizados automaticamente.
model.setData("Novos dados");
view.setData("Novos dados da visão");
```

