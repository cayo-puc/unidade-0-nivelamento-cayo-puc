public class ProdutoNaoPerecivel extends Produto {
     public ProdutoNaoPerecivel(String desc, double precoCusto){
        super(desc, precoCusto);
        if(precoCusto < 0){
            throw new IllegalArgumentException("O preço nao deve ser negativo");
        }  
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
        StringBuilder desc = new StringBuilder(super.toString());
        desc.append(", valor venda= R$")
        .append(String.format("%.2f", valorVenda()).replace(".", ","))
        .append("}");
        return desc.toString();
    }
    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoNaoPerecivel,
        respeitando o formato do arquivo de dados. */
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace(",", ".");
        return String.format("1;%s;%s;%s;%s", descricao, precoFormatado, margemFormatado);
    }
}