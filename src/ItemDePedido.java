public class ItemDePedido {

    // Atributos encapsulados
    private Produto produto;
    private int quantidade;
    private double precoVenda;

    /**
     * Construtor da classe ItemDePedido.
     * O precoVenda deve ser capturado do produto no momento da criação do item,
     * garantindo que alterações futuras no preço do produto não afetem este pedido.
     */
    public ItemDePedido(Produto produto, int quantidade, double precoVenda) {
        if(produto == null){
            throw new IllegalArgumentException("O produto deve existir");
        }
        if(quantidade < 0){
            throw new IllegalArgumentException("A quantidade deve ser maior que 0");
        }
        if(precoVenda < 0){
            throw new IllegalArgumentException("O preço de venda nao pode sert negativo");
        }
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoVenda = precoVenda;
    }


    public double calcularSubtotal() {    
        return precoVenda;
    }

    public Produto getProduto(){
        return this.produto;
    }

    // --- Sobrescrita do método equals ---

    /**
     * Compara a igualdade entre dois itens de pedido.
     * A regra de negócio define que dois itens são iguais se possuírem o mesmo Produto.
     */
    @Override
    public boolean equals(Object obj) {
        ItemDePedido outro = (ItemDePedido)obj;
        if(this.getProduto().equals(outro.getProduto())){
            return true;
        }
        return false;
    }
}
