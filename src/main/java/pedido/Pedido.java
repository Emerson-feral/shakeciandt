package pedido;

import ingredientes.Ingrediente;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double total= 0;

        for (var item:itens) {
            double preco =0.0;
            var shake = item.getShake();
            var tamanho = shake.getTipoTamanho().toString();
            var quantidade = item.getQuantidade();
            var base = cardapio.buscarPreco(shake.getBase());

            if (tamanho == "M") {
                preco = base * 1.3;
            }else if (tamanho == "G") {
                preco = base * 1.5;
            }else{
                preco = base;
            }

            if(shake.getAdicionais() != null){
                for (var adicional : shake.getAdicionais()) {
                    var resultado = cardapio.buscarPreco((Ingrediente) adicional);
                    preco += resultado;

                }
            }
            total += quantidade * preco;
        }

        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){

        var itemPedidoEncontrado = itens.stream()
                .filter(item -> item.getShake().equals(itemPedidoAdicionado.getShake()))
                .findFirst()
                .orElse(null);

        if (itemPedidoEncontrado != null) {
            itemPedidoEncontrado.addQuantidade(itemPedidoAdicionado.getQuantidade());
        } else {
            itens.add(itemPedidoAdicionado);
        }
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {

        var itemPedidoEncontrado = itens.stream()
                .filter(item -> item.getShake().equals(itemPedidoRemovido.getShake()))
                .findFirst()
                .orElse(null);


        if (itemPedidoEncontrado != null) {
            itemPedidoEncontrado.subtrairQuantidade(1);
            if(itemPedidoEncontrado.getQuantidade() <= 0){
                itens.remove(itemPedidoEncontrado);
            }
            return true;
        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }

    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
