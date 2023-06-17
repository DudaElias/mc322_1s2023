import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class ClientePF extends Cliente{
    private final String cpf;
    private LocalDate dataNascimento;
    protected String educacao;
    protected String genero;
    protected List<Veiculo> listaVeiculos;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/
    public ClientePF(String cpf,
    LocalDate dataNascimento,
    String nome,
    String endereco,
    String educacao,
    String email,
    String genero,
    String telefone){
        super(nome,
        endereco, telefone, email);
        this.cpf = cpf;
        setDataNascimento(dataNascimento);
        setEducacao(educacao);
        setGenero(genero);
        listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCpf() {
        return cpf;
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
        + "\nGenero: " + this.genero     
        + "\nData de Nascimento: " + this.dataNascimento.toString()
        + "\n" + super.toString();
    }

    /** 
     * Método para adicionar um veiculo dentro da lista de veiculos do cliente
     * @author Maria Eduarda Rocha
     * @param veiculo Veiculo - veiculo para ser adicionado ao cliente
     * @return boolean - se o cadastro foi feito corretamente*/
    public boolean cadastrarVeiculo(Veiculo veiculo){
        return listaVeiculos.add(veiculo);
    }

    /** 
     * Método para remover um veiculo dentro da lista de veiculos do cliente
     * @author Maria Eduarda Rocha
     * @param placa String - placa do veiculo a ser removido do cliente
     * @return boolean - se a remoção foi feita corretamente*/
    public boolean removerVeiculo(String placa){
        return listaVeiculos.removeIf((veiculo) -> {return veiculo.getPlaca().equals(placa);});
    }
}
