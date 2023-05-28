import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Condutor {
    private final String cpf;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private LocalDate dataNasc;
    protected List<Sinistro> listaSinistros;
    
    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/ 
    public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNasc){
        this.cpf = cpf;
        setNome(nome);
        setTelefone(telefone);
        setEndereco(endereco);
        setEmail(email);
        setDataNasc(dataNasc);
        listaSinistros = new ArrayList<Sinistro>();
    }

    public String getCpf() {
        return cpf;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /** 
     * Método para adicionar um sinistro ao condutor
     * @author Maria Eduarda Rocha
     * @param sinistro Sinistro - sinistro a ser adicionado ao condutor
     * @return boolean - se a adição deu certo*/
    public boolean adicionarSinistro(Sinistro sinistro){
        return listaSinistros.add(sinistro);
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        String sinistrosString = "";
        int i = 1;
        for (Sinistro sinistro : listaSinistros) {
            sinistrosString = sinistrosString.concat("\nSinistro "+ i + ": \n" + sinistro.toString() + "\n");
            i++;
        }
        return "CPF: " + this.cpf
        + "\nEmail: " + this.email
        + "\nEndereco: " + this.endereco
        + "\nNome: " + this.nome
        + "\nTelefone: " + this.telefone
        + "\nSinistros: \n" + sinistrosString;
    }
}
