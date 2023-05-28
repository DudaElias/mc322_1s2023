import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;
    protected List<Frota> listaFrota;
    private int qtdFuncionarios;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/  
    public ClientePJ(
        String cnpj,
        LocalDate dataFundacao,
        String nome,
        String endereco,
        String telefone,
        String email,
        int qtdFunc
        ){
        super(nome, endereco, telefone, email);
        setDataFundacao(dataFundacao);
        setQtdFuncionarios(qtdFunc);
        this.cnpj = cnpj;
        listaFrota = new ArrayList<Frota>();
    }

    
    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public int getQtdFuncionarios() {
        return qtdFuncionarios;
    }

    public void setQtdFuncionarios(int qtdFuncionarios) {
        this.qtdFuncionarios = qtdFuncionarios;
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        return  "CNPJ: " + this.cnpj
        + "\nData Fundacao: " + this.dataFundacao.toString()
        + "\n" + super.toString();
    }

    /** 
     * Método para cadastrar uma nova frota dentro do cliente
     * @author Maria Eduarda Rocha
     * @param  frota Frota - frota que deseja adicionar no cliente
     * @return boolean - retorno se a adição da frota deu certo*/
    public boolean cadastrarFrota(Frota frota){
        return listaFrota.add(frota);
    }

    /** 
     * Método para atualizar uma frota
     * @author Maria Eduarda Rocha
     * @param code String - codigo da frota que quer atualizar
     * @param veiculos List<Veiculo> - lista de veiculos que será colocado na nova frota
     * @return boolean - se a atualização da frota deu certo*/
    public boolean atualizarFrota(String code, List<Veiculo> veiculos){
        Frota frotaParaAtualizar = this.listaFrota.stream().filter(frota -> frota.getCode().equals(code)).findFirst().get();
        if(frotaParaAtualizar == null || frotaParaAtualizar.listaVeiculos == null)
            return false;
        frotaParaAtualizar.listaVeiculos = veiculos;
        return true;
    }

    /** 
     * Método para retornar a lista de veiculos de uma frota
     * @author Maria Eduarda Rocha
     * @param code String - code da frota que deseja obter os veiculos
     * @return List<Veiculo> - lista de veiculos da frota com o code passado como parametro*/
    public List<Veiculo> getVeiculosPorFrota(String code){
        for(Frota frota : this.listaFrota)
            if(frota.getCode().equals(code))
                return frota.listaVeiculos;
        return null;
    }
}
