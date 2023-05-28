import java.util.UUID;

public class Sinistro {
    private final int id;
    private String data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/    
    public Sinistro(String data, 
    String endereco){
        setData(data);
        setEndereco(endereco);
        this.id = gerarId();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public int getId() {
        return id;
    }
    
    /** 
     * Método que gera um id aleatorio para o sinistro
     * @author Maria Eduarda Rocha
     * @return int - id gerado para o sinistro*/    
    public int gerarId(){
        return Math.abs(UUID.randomUUID().hashCode());
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString(){
        String condutorString = "CPF: " + this.condutor.getCpf()
        + "\nEmail: " + this.condutor.getEmail()
        + "\nEndereco: " + this.condutor.getEndereco()
        + "\nNome: " + this.condutor.getNome()
        + "\nTelefone: " + this.condutor.getTelefone();
        
        return "Id: " + this.id
        + "\nData: " + this.data
        + "\nEndereco: " + this.endereco
        + "\nCondutor:\n" + condutorString
        + "\nSeguro: \n" + this.seguro.toString();
    }
}
