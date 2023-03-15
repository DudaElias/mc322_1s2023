import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;

    public Sinistro(String data, String endereco){
        setData(data);
        setEndereco(endereco);
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

    public void setId(int id) {
        this.id = id;
    }
    
    public int gerarId(){
        Random random = new Random();
        return random.nextInt(10000);
    }

}
