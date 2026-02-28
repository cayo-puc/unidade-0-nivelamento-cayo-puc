public abstract class Produto {

    private static final double MARGEM_PADRAO = 0.2;

    protected String descricao;
    protected double precoCusto;
    protected double margemLucro;

    // Construtor com margem de lucro informada
    protected Produto(String desc, double precoCusto, double margemLucro) {
        init(desc, precoCusto, margemLucro);
    }

    // Construtor utilizando margem padrão
    protected Produto(String desc, double precoCusto) {
        init(desc, precoCusto, MARGEM_PADRAO);
    }

    // Método auxiliar de inicialização
    private void init(String desc, double precoCusto, double margemLucro) {
        this.descricao = desc;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    public abstract String gerarDadosTexto();

    static Produto criarDoTexto(String linha){
        /*Você deve implementar aqui a lógica que separa os dados existentes na String linha, verifica se o produto é do
        tipo 1 ou 2 e constrói o objeto adequado, com os dados fornecidos de acordo com seu tipo. O objeto construído é
        retornado pelo método*/
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Produto novoProduto = null;
        String[] partes = linha.split(";");
        int tipo = Integer.parseInt(partes[0]);
        String desc = partes[1];
        Double preco = Double.parseDouble(partes[2]);
        Double margem = Double.parseDouble(partes[3]);
        if(tipo == 1){
            novoProduto = new ProdutoNaoPerecivel(desc, preco, margem);
        }else if(partes[0] == 2){
            LocalDate data = LocalDate.parse(partes[4])
            novoProduto = new ProdutoPerecivel(desc, preco, margem, data);
        }else{
            throw new IllegalArgumentException ("O tipo do produto não existe");
        }
        return novoProduto;
    }

    // Calcula o valor de venda com base no custo e na margem
    public abstract double valorVenda();

    public abstract String gerarDadosTexto();

    @Override
    public String toString() {
        return "Produto{" +
                "descricao='" + descricao + '\'' +
                ", precoCusto=" + precoCusto +
                ", margemLucro=" + margemLucro + ", descrição=" + desc;
    }

    @Override
        public boolean equals(Object obj){
        Produto outro = (Produto)obj;
        return this.descricao.toLowerCase().equals(outro.descricao.toLowerCase());
    }   
}