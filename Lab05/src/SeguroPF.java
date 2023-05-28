import java.time.LocalDate;
import java.time.Period;

public class SeguroPF extends Seguro{
    private Veiculo veiculo;
    private ClientePF cliente;

    public SeguroPF(
        int id,
        LocalDate dataInicio,
        LocalDate dataFim,
        Seguradora seguradora,
        Veiculo veiculo,
        ClientePF cliente
    ){
        super(id, dataInicio, dataInicio, seguradora);
        setCliente(cliente);
        setVeiculo(veiculo);
        setValorMensal(calcularValor());
    }

    public ClientePF getCliente() {
        return cliente;
    }

    public void setCliente(ClientePF cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /** 
     * Método que calcula o valor do seguro usando uma formula
     * @author Maria Eduarda Rocha
     * @return double - valor do seguro*/
    @Override
    public double calcularValor() {
        int qtdSinistrosPorCondutor = 0;
        for (Condutor condutor : listaCondutores) {
            qtdSinistrosPorCondutor += super.seguradora.getSinistrosPorCliente(condutor.getCpf()).size();
        }

        final Period periodo = Period.between(this.cliente.getDataNascimento(), LocalDate.now());
        double FATOR_IDADE = 0;
        if(periodo.getYears() < 30){
            FATOR_IDADE = CalcSeguro.FATOR_18_30.getValor();
        }
        else if(periodo.getYears() < 60){
            FATOR_IDADE = CalcSeguro.FATOR_30_60.getValor();
        }
        else{
            FATOR_IDADE = CalcSeguro.FATOR_60_90.getValor();
        }
        return CalcSeguro.VALOR_BASE.getValor() * FATOR_IDADE * (1 + 1/(this.cliente.listaVeiculos.size()+2)) * (2 + super.seguradora.getSinistrosPorCliente(this.cliente.getCpf()).size()/10)
        * (5 + qtdSinistrosPorCondutor/10);
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    public String toString(){
        return super.toString()
        + "\nCliente CPF: " + this.cliente.getCpf()
        + "\nVeiculo Placa: " + this.veiculo.getPlaca();
    }
}
