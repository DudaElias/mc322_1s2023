import java.time.LocalDate;
import java.util.List;

public class ClientePF extends Cliente{
    private final String cpf;
    private LocalDate dataNascimento;
    protected String educacao;
    protected String genero;
    protected String classeEconomica;

    public ClientePF(String cpf,
    LocalDate dataNascimento,
    String nome,
    String endereco,
    String educacao,
    LocalDate licenca,
    String genero,
    String classeEconomica,
    List<Veiculo> listaVeiculos) throws Exception{
        super(nome,
        endereco,
        licenca,
        listaVeiculos);
        if(!validarCpf(cpf)){
            throw new Exception("CPF invalido");
        }
        this.cpf = cpf;
        setDataNascimento(dataNascimento);
        setClasseEconomica(classeEconomica);
        setEducacao(educacao);
        setGenero(genero);
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


    /*
        Método que verifica o cpf do cliente se baseando na quantidade de números e demais condições estipuladas para um cpf ser válido
    */
    public static boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11)
            return false;
        boolean ehTudoIgual = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(i - 1)) {
                ehTudoIgual = false;
                break;
            }
        }
        if (ehTudoIgual)
            return false;

        int sum = 0;
        int peso = 10;
        int num = 0;
        for (int i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sum = sum + (num * peso);
            peso = peso - 1;
        }

        int r = 11 - (sum % 11);
        int dig10 = 0, dig11 = 0;
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else
            dig10 = (char) (r + 48);

        sum = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sum = sum + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sum % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else
            dig11 = (char) (r + 48);

        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return "CPF: " + this.cpf
        + "\nEducacao: " + this.educacao
        + "\nClasse Economica: " + this.classeEconomica
        + "\nGenero: " + this.genero     
        + "\nData de Nascimento: " + this.dataNascimento.toString()
        + "\n" + super.toString();
    }

}
