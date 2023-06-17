import java.time.LocalDate;
import java.time.Period;

public class SeguroPJ extends Seguro{
    private Frota frota;
    private ClientePJ cliente;

    public SeguroPJ(
        int id,
        LocalDate dataInicio,
        LocalDate dataFim,
        Seguradora seguradora,
        Frota frota,
        ClientePJ cliente
    ){
        super(id, dataInicio, dataFim, seguradora);
        setFrota(frota);
        setCliente(cliente);
        setValorMensal(calcularValor());
    }

    public Frota getFrota() {
        return frota;
    }

    public void setFrota(Frota frota) {
        this.frota = frota;
    }

    public ClientePJ getCliente() {
        return cliente;
    }

    public void setCliente(ClientePJ cliente) {
        this.cliente = cliente;
    }

    /** 
     * Método que calcula o valor do seguro usando uma formula
     * @author Maria Eduarda Rocha
     * @return double - valor do seguro*/
    @Override
    public double calcularValor(){
            final Period periodo = Period.between(this.cliente.getDataFundacao(), LocalDate.now());

            int qtdSinistrosCondutor = 0;
            for (Condutor condutor : listaCondutores) {
                qtdSinistrosCondutor += this.seguradora.getSinistrosPorCliente(condutor.getCpf(), "pf").size();
            }

            return (CalcSeguro.VALOR_BASE.getValor() * (10 + this.cliente.getQtdFuncionarios()/10)*
            (1 + 1/( frota.listaVeiculos.size() + 2) ) *
            (1 + 1/( periodo.getYears() +2) ) *
            (2 + this.seguradora.getSinistrosPorCliente(this.cliente.getCnpj(), "pj").size() /10) *
            (5 + qtdSinistrosCondutor /10));
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    public String toString(){
        return super.toString()
        + "\nCNPJ Cliente: " + this.cliente.getCnpj()
        + "\nCode da Frota: " + this.frota.getCode();
    }
}
