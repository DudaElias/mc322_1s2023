import java.util.ArrayList;
import java.util.List;

public class Frota {
    private String code;
    protected List<Veiculo> listaVeiculos;
    
    public Frota(String code){
        setCode(code);
        listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /** 
     * Método para adicionar um veiculo na frota
     * @author Maria Eduarda Rocha
     * @param veiculo Veiculo - veiculo a ser adicionado na frota
     * @return boolean - se a adição do veiculo foi um sucesso*/
    public boolean addVeiculo(Veiculo veiculo){
        return listaVeiculos.add(veiculo);
    }

    /** 
     * Método para remover um veiculo na frota
     * @author Maria Eduarda Rocha
     * @param placa String - placa do veiculo a ser removido da frota
     * @return boolean - se a remoção do veiculo foi um sucesso*/
    public boolean removeVeiculo(String placa){
        return listaVeiculos.removeIf((veiculo) -> {return veiculo.getPlaca().equals(placa);});
    }


    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        String veiculos = "";
        for (Veiculo veiculo : listaVeiculos) {
            veiculos += "Veiculo: " + veiculo.getPlaca() + "\n";
        }
        return "Code: " + this.getCode()
        + "\nVeiculos:\n" + veiculos;
    }
}
