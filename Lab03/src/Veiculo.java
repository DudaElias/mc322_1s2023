public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    // Construtor da classe que setta as propriedades do objeto através de setters
    public Veiculo(String placa, 
    String marca, 
    String modelo,
    int anoFabricacao) {
        setMarca(marca);
        setModelo(modelo);
        setPlaca(placa);
        setAnoFabricacao(anoFabricacao);
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    @Override
    public String toString() {
        return "Placa: " + this.placa
        + "\nMarce: " + this.marca
        + "\nModelo: " + this.modelo
        + "\nAno de Fabricacao: " + this.anoFabricacao;
    }

}
