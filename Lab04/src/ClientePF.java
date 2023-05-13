import java.time.*;

public class ClientePF extends Cliente{
    private final String cpf;
    private LocalDate dataNascimento;
    protected LocalDate dataLicenca;
    protected String educacao;
    protected String genero;
    protected String classeEconomica;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/
    public ClientePF(String cpf,
    LocalDate dataNascimento,
    String nome,
    String endereco,
    String educacao,
    LocalDate licenca,
    String genero,
    String classeEconomica){
        super(nome,
        endereco);
        this.cpf = cpf;
        setDataNascimento(dataNascimento);
        setClasseEconomica(classeEconomica);
        setEducacao(educacao);
        setGenero(genero);
        setDataLicenca(licenca);
    }

    public String getCpf() {
        return cpf;
    }

    public String getClasseEconomica() {
        return classeEconomica;
    }

    public void setClasseEconomica(String classeEconomica) {
        this.classeEconomica = classeEconomica;
    }

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public String getEducacao() {
        return educacao;
    }

    public void setEducacao(String educacao) {
        this.educacao = educacao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }


    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        return "CPF: " + this.cpf
        + "\nEducacao: " + this.educacao
        + "\nClasse Economica: " + this.classeEconomica
        + "\nGenero: " + this.genero     
        + "\nData de Nascimento: " + this.dataNascimento.toString()
        + "\nData Licenca: " + this.dataLicenca.toString()
        + "\n" + super.toString();
    }

    /** 
     * Método que calcula a pontuação do cliente baseado em sua idade
     * @author Maria Eduarda Rocha
     * @return double - pontuação do cliente*/
    @Override
    public double calculaScore(){
        final Period periodo = Period.between(this.dataNascimento, LocalDate.now());
        double FATOR_IDADE = 0;
        if(periodo.getYears() < 30){
            FATOR_IDADE = CalcSeguro.FATOR_18_30.getValor();
        }
        else if(periodo.getYears() < 60){
            FATOR_IDADE = CalcSeguro.FATOR_30_60.getValor();
        }
        else{
            FATOR_IDADE = CalcSeguro.FATOR_60_90.getValor();
        }
        return CalcSeguro.VALOR_BASE.getValor() * FATOR_IDADE * super.listaVeiculos.size();
    }

}
