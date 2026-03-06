import java.util.Locale;

public class ProdutoNaoPerecivel extends Produto {
     public ProdutoNaoPerecivel(String desc, double precoCusto){
        super(desc, precoCusto);
        if(precoCusto < 0){
            throw new IllegalArgumentException("O preço nao deve ser negativo");
        }  
    }
    public ProdutoNaoPerecivel(String desc, double precoCusto, double margemLucro){
        super(desc, precoCusto, margemLucro);
        if(precoCusto < 0){
            throw new IllegalArgumentException("O preço nao deve ser negativo");
        }  
        if(margemLucro < 0){
            throw new IllegalArgumentException("A margem nao deve ser negativo");
        }  
    }

    @Override
    public double valorVenda() {
        return precoCusto * (1 + margemLucro);
    }

    @Override
    public String toString() {
        return super.toString()
                + ", valor venda= R$ "
                + String.format(Locale.US, "%.2f", valorVenda()).replace(".", ",")
                + "}";
    }

    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoNaoPerecivel,
        respeitando o formato do arquivo de dados. */
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace(",", ".");
        return String.format("1;%s;%s;%s", descricao, precoFormatado, margemFormatado);
    }
}