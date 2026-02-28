public class ProdutoNaoPerecivel extends Produto {
     public ProdutoNaoPerecivel(String desc, double precoCusto){
        if(precoCusto < 0){
            throw new IllegalArgumentException("O preço nao deve ser negativo");
        }
        super(desc, precoCusto);
    }
    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro){
        super(desc, precoCusto, margemLucro);
    }

    @Override
    public double valorVenda() {
        return precoCusto * (1 + margemLucro);
    }

    @Override
    public String toString() {
        StringBuilder desc = new StringBuilder(super.toString()).append(", valor venda= R$" + valorVenda() + "}" );
        return desc.toString();
    }
    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoNaoPerecivel,
        respeitando o formato do arquivo de dados. */
    }
}
