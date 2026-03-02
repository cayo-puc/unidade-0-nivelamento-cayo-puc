import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Comercio {
    /** Para inclusão de novos produtos no vetor */
    static final int MAX_NOVOS_PRODUTOS = 10;
    /** Nome do arquivo de dados. O arquivo deve estar localizado na raiz do projeto */
    static String nomeArquivoDados;
    /** Scanner para leitura do teclado */
    static Scanner teclado;
    /** Vetor de produtos cadastrados. Sempre terá espaço para 10 novos produtos a cada execução */
    static Produto[] produtosCadastrados;
    /** Quantidade produtos cadastrados atualmente no vetor */
    static int quantosProdutos;
    /** Gera um efeito de pausa na CLI. Espera por um enter para continuar */
    static void pausa(){
    System.out.println("Digite enter para continuar...");
        teclado.nextLine();
    }
    
    /** Cabeçalho principal da CLI do sistema */
    static void cabecalho(){
        System.out.println("AEDII COMÉRCIO DE COISINHAS");
        System.out.println("===========================");
    }

    /** Imprime o menu principal, lê a opção do usuário e a retorna (int).
    * Perceba que poderia haver uma melhor modularização com a criação de uma classe Menu.
    * @return Um inteiro com a opção do usuário.
    */
    static int menu(){
        cabecalho();
        System.out.println("1 - Listar todos os produtos");
        System.out.println("2 - Procurar e listar um produto");
        System.out.println("3 - Cadastrar novo produto");
        System.out.println("0 - Sair");
        System.out.print("Digite sua opção: ");
        return Integer.parseInt(teclado.nextLine());
    }
    /**
    * Lê os dados de um arquivo texto e retorna um vetor de produtos. Arquivo no formato
    * N (quantiade de produtos) <br/>
    * tipo; descrição;preçoDeCusto;margemDeLucro;[dataDeValidade] <br/>
    * Deve haver uma linha para cada um dos produtos. Retorna um vetor vazio em caso de problemas com o arquivo.
    * @param nomeArquivoDados Nome do arquivo de dados a ser aberto.
    * @return Um vetor com os produtos carregados, ou vazio em caso de problemas de leitura.
    */
    static Produto[] lerProdutos(String nomeArquivoDados) {
        /*Ler a primeira linha do arquivoDados contendo a quantidade de produtos armazenados no arquivo.
        Instanciar o vetorProdutos com o tamanho necessário para acomodar todos os produtos do arquivo + o
        espaço reserva MAX_NOVOS_PRODUTOS. Após isso, ler uma após a outra o restante das linhas do arquivo,
        convertendo, a cada leitura de linha, seus dados em objetos do tipo Produto (utilizar o método
        criarDoTexto()). Cada objeto Produto instanciado será armazenado no vetorProdutos.*/
        Produto[] vetorProdutos;
        try(BufferedReader br = new BufferedReader(new FileReader(nomeArquivoDados))){
            quantosProdutos = Integer.parseInt(br.readLine());
            vetorProdutos = new Produto[quantosProdutos + MAX_NOVOS_PRODUTOS ];
            for(int i = 0; i < quantosProdutos; i ++){
                vetorProdutos[i] = Produto.criarDoTexto(br.readLine());
            }
        }catch(IOException e){
            System.out.println("Arquivo não encontrado. Iniciando com cadastro vazio.");
            vetorProdutos = new Produto[MAX_NOVOS_PRODUTOS];
        }
        return vetorProdutos;
    }
    /** Lista todos os produtos cadastrados, numerados, um por linha */
    static void listarTodosOsProdutos(){
        /*Percorrer o vetor de produtosCadastrados, escrevendo na tela os dados de cada um (utilizar o método
        toString() já implementado).*/
        for(int i = 0; i < quantosProdutos; i++){
            System.out.println("Produto " + (i+1) + ":");
            System.out.println(produtosCadastrados[i]);
        }
    }
    /** Localiza um produto no vetor de cadastrados, a partir do nome (descrição), e imprime seus dados.
    * A busca não é sensível ao caso. Em caso de não encontrar o produto, imprime mensagem padrão */
    static void localizarProdutos(){
        /*Ler do teclado a descrição (nome) do produto que o usuário deseja localizar, procurar no vetor de
        produtosCadastrados o produto em questão (utilizar o método equals() já implementado na classe Produto)
        e imprimir na tela seus dados.*/
        Produto produto = null;
        System.out.println("Insira a descrição (nome) do produto que deseja buscar:");
        String desc = teclado.nextLine();
        System.out.println("Digite 1 para produto não perecivel e 2 para produto perecivel");
        int tipo = teclado.nextInt();
        if(tipo == 1){
            produto = new ProdutoNaoPerecivel(desc, 0, 0);

        }else if (tipo == 2){
            produto = new ProdutoPerecivel(desc, 0, 0, null);
        }else{
            throw new IllegalArgumentException("Tipo invalido");
        }
        for(int i = 0; i < quantosProdutos; i++){
            if(produtosCadastrados[i].equals(produto)){
                System.out.println(produtosCadastrados[i]);
            }
        }
    }

    /**
    * Rotina de cadastro de um novo produto: pergunta ao usuário o tipo do produto, lê os dados correspondentes,
    * cria o objeto adequado de acordo com o tipo, inclui no vetor. Este método pode ser feito com um nível muito
    * melhor de modularização. As diversas fases da lógica poderiam ser encapsuladas em outros métodos.
    * Uma sugestão de melhoria mais significativa poderia ser o uso de padrão Factory Method para criação dos
    objetos.
    */
    static void cadastrarProduto(){
        /*Implementar a sub-rotina de exibir o novo menu para cadastro de novo produto, ler os dados necessários
        conforme o tipo desejado, criar o objeto correspondente, salvando-o no vetor de produtosCadastrados e
        incrementando a variável de controle da quantidade de produtos.*/
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Produto produto = null;
        System.out.println("=== Bem vindo ao cadastro de produto ===");
        System.out.println("Digite a descrição do produto: ");
        String desc = teclado.nextLine();
        System.out.println("Digite o preço de custo do produto: ");
        Double preco = teclado.nextDouble();
        System.out.println("Digite a margem de lucro do produto: ");
        Double margem = teclado.nextDouble();
        System.out.println("Digite 1 para produto Nao perecivel e 2 para produto perecivel: ");
        int tipo = teclado.nextInt();
        teclado.nextLine(); // consumir quebra de linha
        if(tipo == 1){
            produto = new ProdutoNaoPerecivel(desc, preco, margem);

        }else if (tipo == 2){
            System.out.println("Digite a data de validade:");
            LocalDate data = LocalDate.parse(teclado.nextLine(), formatoData);
            produto = new ProdutoPerecivel(desc, preco, margem, data);
        }else{
            throw new IllegalArgumentException("Tipo invalido");
        }
        produtosCadastrados[quantosProdutos] = produto;
        quantosProdutos ++;
    }
    /**
    * Salva os dados dos produtos cadastrados no arquivo csv informado. Sobrescreve todo o conteúdo do arquivo.
    * @param nomeArquivo Nome do arquivo a ser gravado.
    */
    public static void salvarProdutos(String nomeArquivo){
        /*Você deve implementar aqui a lógica que abrirá um arquivo para escrita com o nome informado no
        parâmetro, percorrerá um por um todos os produtos existentes no vetor de produtosCadastrados, gerando
        uma linha de texto com os dados de cada objeto Produto, escrevendo-a no arquivo.*/
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo))){
            bw.write(String.valueOf(quantosProdutos));
            bw.newLine();
           for(int i = 0; i < quantosProdutos; i++){
                bw.write(produtosCadastrados[i].gerarDadosTexto());
                bw.newLine();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        teclado = new Scanner(System.in, Charset.forName("ISO-8859-2"));
        nomeArquivoDados = "dadosProdutos.csv";
        produtosCadastrados = lerProdutos(nomeArquivoDados);
        int opcao = -1;
        do{
            opcao = menu();
            switch (opcao) {
                case 1 -> listarTodosOsProdutos();
                case 2 -> localizarProdutos();
                case 3 -> cadastrarProduto();
            }
            pausa();
        }while(opcao !=0);
        salvarProdutos(nomeArquivoDados);
        teclado.close();
        }
    }