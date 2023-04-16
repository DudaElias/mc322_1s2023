import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente {
    protected String nome;
    protected String endereco;
    protected LocalDate dataLicenca;
    protected List<Veiculo> listaVeiculos;

    // Construtor que setta as propriedades da classe usando os setters
    public Cliente(String nome,
            String endereco,
            LocalDate licenca,
            List<Veiculo> listaVeiculos) {
        setNome(nome);
        setEndereco(endereco);
        setDataLicenca(licenca);
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

    public LocalDate getDataLicenca() {
        return dataLicenca;
    }

    public void setDataLicenca(LocalDate dataLicenca) {
        this.dataLicenca = dataLicenca;
    }

    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public boolean adicionarVeiculo(Veiculo veiculo) {
        if(this.listaVeiculos == null)
            this.listaVeiculos = new ArrayList<Veiculo>();
        return this.listaVeiculos.add(veiculo);
    }

    public boolean removerVeiculo(String placa) {
        return this.listaVeiculos.removeIf((veiculo) -> (veiculo.getPlaca().equals(placa)));
    }

    //Método toString que manda uma string formatada com as propriedades da classe
    @Override
    public String toString() {
        String veiculosString = "";
        int i = 1;
        for (Veiculo veiculo : listaVeiculos) {
            veiculosString = veiculosString.concat("\tVeiculo "+ i + ": \n" + veiculo.toString() + "\n");
            i++;
        }

        return "Nome: " + this.nome
                + "\nData Licenca: " + this.dataLicenca.toString()
                + "\nEndereco: " + this.endereco
                + "\nVeiculos: \n" + veiculosString;
    }

}
