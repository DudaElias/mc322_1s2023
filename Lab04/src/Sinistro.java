import java.util.UUID;

public class Sinistro {
    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Cliente cliente;
    private Veiculo veiculo;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/    
    public Sinistro(String data, 
    String endereco, 
    Seguradora seguradora, 
    Cliente cliente, 
    Veiculo veiculo){
        setData(data);
        setEndereco(endereco);
        setSeguradora(seguradora);
        setCliente(cliente);
        setVeiculo(veiculo);
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString(){
        return "Id: " + this.id
        + "\nData: " + this.data
        + "\nEndereco: " + this.endereco
        + "\nSeguradora: " + this.seguradora.toString()
        + "\nVeiculo: " + this.veiculo.toString()
        + "\nCliente: " + this.cliente.toString();
    }
}
