# OneWayBinding

A classe `OneWayBinding` implementa a interface `Observer` e fornece um mecanismo para sincronizar dados de um `Observable` para outro, em uma única direção.

## Construtor

### `OneWayBinding(Observable model, Observable view, boolean modelToView)`

Cria uma instância de `OneWayBinding` que sincroniza os dados entre a (`model`) e a (`view`).

- **Parâmetros:**
  - `model`: O objeto `Observable` que serve como fonte de dados.
  - `view`: O objeto `Observable` que serve como destino dos dados.
  - `modelToView`: Um booleano que indica a direção da sincronização dos dados. Se `true`, os dados fluem do `model` para o `view`. Se `false`, os dados fluem do `view` para o `model`.

- **Comportamento:**
  - O construtor adiciona a instância de `OneWayBinding` como um observador ao `model`.

## Métodos

### `update()`

Implementa o método `update` da interface `Observer`. Este método é chamado quando o `Observable` observado sofre uma mudança.

- **Comportamento:**
  - Se `modelToView` for `true` e `model` for uma instância de `Model` e `view` for uma instância de `View`, o método chama `updateFromModel` em `view` com os dados obtidos de `model`.
  - Se `modelToView` for `false` e `model` for uma instância de `View` e `view` for uma instância de `Model`, o método chama `updateFromView` em `view` com os dados obtidos de `model`.

## Exemplo de Uso

```java
Model model = new Model();
View view = new View();
OneWayBinding binding = new OneWayBinding(model, view, true);

// Quando o modelo é atualizado, a visão também será atualizada automaticamente.
model.setData("Novos dados");
```
# TwoWayBinding

A classe `TwoWayBinding` implementa a interface `Observer` e fornece um mecanismo para sincronizar dados entre um `Model` e uma `View` bidirecionalmente.

## Construtor

### `TwoWayBinding(Model model, View view)`

Cria uma instância de `TwoWayBinding` que sincroniza os dados entre o modelo (`model`) e a visão (`view`) em ambas as direções.

- **Parâmetros:**
  - `model`: O objeto `Model` que serve como uma das fontes e destinos dos dados.
  - `view`: O objeto `View` que serve como uma das fontes e destinos dos dados.

- **Comportamento:**
  - O construtor adiciona a instância de `TwoWayBinding` como um observador tanto ao `model` quanto à `view`.

## Métodos

### `update()`

Implementa o método `update` da interface `Observer`. Este método é chamado quando o `Observable` observado sofre uma mudança.

- **Comportamento:**
  - Atualiza a `view` com os dados do `model` chamando o método `updateFromModel`.
  - Atualiza o `model` com os dados da `view` chamando o método `updateFromView`.

## Exemplo de Uso

```java
Model model = new Model();
View view = new View();
TwoWayBinding binding = new TwoWayBinding(model, view);

// Quando o modelo ou a visão são atualizados, ambos são sincronizados automaticamente.
model.setData("Novos dados");
view.setData("Novos dados da visão");

