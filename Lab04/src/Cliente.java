import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected List<Veiculo> listaVeiculos;
    protected double valorSeguro;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/    
    public Cliente(String nome,
            String endereco) {
        setNome(nome);
        setEndereco(endereco);
        listaVeiculos = new ArrayList<Veiculo>();
    }

    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        //Formatar o array de veiculos para ficar melhor no console
        String veiculosString = "";
        int i = 1;
        for (Veiculo veiculo : listaVeiculos) {
            veiculosString = veiculosString.concat("\tVeiculo "+ i + ": \n" + veiculo.toString() + "\n");
            i++;
        }

        return "Nome: " + this.nome
                + "\nEndereco: " + this.endereco
                + "\nVeiculos: \n" + veiculosString;
    }

    /** 
     * Método para calcular a pontuação do cliente (é sobrescrito nas classes filha)
     * @author Maria Eduarda Rocha
     * @return double - valor default 0*/
    public double calculaScore(){
        return 0;
    }

}
