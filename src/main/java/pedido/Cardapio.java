package pedido;

import ingredientes.Ingrediente;

import java.util.Collections;
import java.util.TreeMap;

public class Cardapio {
    private TreeMap<Ingrediente,Double> precos;

    public Cardapio(){
        this.precos= new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos(){
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente,Double preco){
        if(preco == null){
            throw new IllegalArgumentException("Preco invalido.");
        }
        precos.put(ingrediente,preco);
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente,Double preco){
       //TODO
        var itemEncontrado = precos.get(ingrediente);

        if(itemEncontrado != null){
            if(itemEncontrado < 0){
                throw new IllegalArgumentException("Preco invalido.");
            }
            precos.replace(ingrediente,preco);
        }else{
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }

        return true;
    }

    public boolean removerIngrediente(Ingrediente ingrediente){
        var itemEncontrado = precos.get(ingrediente);

        if(itemEncontrado != null){
            precos.remove(ingrediente);
        }else{
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
        return true;
    }

    public Double buscarPreco(Ingrediente ingrediente){
        var itemEncontrado = precos.get(ingrediente);

        if(itemEncontrado != null){
            return itemEncontrado;
        }else{
            throw new IllegalArgumentException("Ingrediente nao existe no cardapio.");
        }
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
