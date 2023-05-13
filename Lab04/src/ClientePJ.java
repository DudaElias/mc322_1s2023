import java.time.LocalDate;


public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    private int qtdFuncionarios;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/  
    public ClientePJ(
        String cnpj,
        LocalDate dataFundacao,
        String nome,
        String endereco,
        int qtdFuncionarios
        ){
        super(nome, endereco);
        setDataFundacao(dataFundacao);
        this.cnpj = cnpj;
        setQtdFuncionarios(qtdFuncionarios);
    }

    
    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        return  "CNPJ: " + this.cnpj
        + "\nQuantidade de Funcionarios: " + this.qtdFuncionarios
        + "\nData Fundacao: " + this.dataFundacao.toString()
        + "\n" + super.toString();
    }

    /** 
     * Método que calcula a pontuação do cliente baseado na quantidade de funcionarios
     * @author Maria Eduarda Rocha
     * @return double - pontuação do cliente*/
    @Override
    public double calculaScore(){
        return CalcSeguro.VALOR_BASE.getValor() * (1 + (qtdFuncionarios)/100) * super.getListaVeiculos().size();
    }
}
