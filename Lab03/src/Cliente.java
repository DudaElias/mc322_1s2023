import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected List<Veiculo> listaVeiculos;

    // Construtor que setta as propriedades da classe usando os setters
    public Cliente(String nome,
            String endereco,
            List<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setListaVeiculos(listaVeiculos);
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

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    //Método toString que manda uma string formatada com as propriedades da classe
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

}
