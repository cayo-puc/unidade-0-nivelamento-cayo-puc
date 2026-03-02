import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class ProdutoPerecivel extends Produto {

    private static final double desconto = 0.25;
    private static final int prazo = 7;
    private LocalDate dataValidade;

    public ProdutoPerecivel(String desc, double precoCusto, double margemLucro, LocalDate dataValidade){
        super(desc, precoCusto, margemLucro);
        if(LocalDate.now().isAfter(dataValidade)){
            throw new IllegalArgumentException("O produto deve estar na data de validade!!!");
        }else{
            this.dataValidade = dataValidade;
        }
    }

    @Override
    public double valorVenda() {
        if(LocalDate.now().isAfter(dataValidade)){
            throw new IllegalArgumentException("O produto deve estar na data de validade!!!");
        }
        if(ChronoUnit.DAYS.between(dataValidade, LocalDate.now()) <= 7){
            return precoCusto * (1 + margemLucro) - (precoCusto * (1 + margemLucro) * 0.25);
        }else{
            return precoCusto * (1 + margemLucro);
        }
    }

    @Override
    public String toString() {
        
        StringBuilder desc = new StringBuilder(super.toString()).append(", valor venda= R$")
        .append(", valor venda= R$")
        .append(String.format("%.2f", valorVenda()).replace(".", ","))
        .append(", data validade = ")
        .append(formato.format(dataValidade))
        .append("}");
        return desc.toString();
    }

    @Override
    public String gerarDadosTexto() {
        /*Você deve implementar aqui a lógica que monta a String com os atributos do objeto ProdutoNaoPerecivel,
        respeitando o formato do arquivo de dados. */
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String precoFormatado = String.format("%.2f", precoCusto).replace(",", ".");
        String margemFormatado = String.format("%.2f", margemLucro).replace(",", ".");
        String dataFormatada = formato.format(dataValidade);
        return String.format("2;%s;%s;%s;%s", descricao, precoFormatado, margemFormatado, dataFormatada);
    }
}