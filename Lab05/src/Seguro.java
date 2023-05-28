import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Seguro {
    private final int id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    protected Seguradora seguradora;
    protected List<Sinistro> listaSinistros;
    protected List<Condutor> listaCondutores;
    private double valorMensal;

    public Seguro(int id,
    LocalDate dataInicio,
    LocalDate dataFim,
    Seguradora seguradora){
        this.id = id;
        setDataInicio(dataInicio);
        setDataFim(dataFim);
        setSeguradora(seguradora);
        listaSinistros = new ArrayList<Sinistro>();
        listaCondutores = new ArrayList<Condutor>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    /** 
     * Método que remove um condutor da lista do seguro
     * @author Maria Eduarda Rocha
     * @param cpf String - cpf do condutor a ser removido
     * @return boolean - se a remoção deu certo ou não*/
    public boolean desautorizarCondutor(String cpf){
        return listaCondutores.removeIf((condutor) -> {return condutor.getCpf().equals(cpf);});
    }

    /** 
     * Método que adiciona um condutor ao seguro
     * @author Maria Eduarda Rocha
     * @param condutor Condutor - condutor a ser adicionado ao seguro
     * @return boolean - se a adição deu certo ou não*/
    public boolean autorizarCondutor(Condutor condutor){
        return listaCondutores.add(condutor);
    }

    public abstract double calcularValor();
    
    /** 
     * Método que gera um sinistro e o adiciona na lista de sinistros do seguro
     * @author Maria Eduarda Rocha
     * @param email String - email do sinistro a ser gerado
     * @param endereco String - endereco do sinistro a ser gerado
     * @return boolean - se a adição deu certo ou não*/
    public boolean gerarSinistro(String email, String endereco) {
        return listaSinistros.add(new Sinistro(email, endereco));
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    public String toString(){
        String condutores = "", sinistros="";
        for (Condutor condutor : listaCondutores) {
            condutores += "Condutor: " + condutor.getCpf() + "\n";
        }
        for (Sinistro sinistro : listaSinistros) {
            sinistros += "Sinistro: " + sinistro.getId() + "\n";

        }
        return "Id: " + this.getId()
        + "\nValor: " + this.getValorMensal()
        + "\nData Inicio: " + this.getDataInicio().toString()
        + "\nData Fim: " + this.getDataFim().toString()
        + "\nNome Seguradora: " + this.seguradora.getNome()
        + "\n Lista Sinistros: \n" + sinistros
        + "\n Lista Condutores: \n" + condutores;
    }
}
