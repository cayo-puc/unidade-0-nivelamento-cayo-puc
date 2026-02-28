import java.time.LocalDate;
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
        StringBuilder desc = new StringBuilder(super.toString()).append(", valor venda= R$" + valorVenda() + ", data validade = " + dataValidade + "}" );
        return desc.toString();
    }

}
