import java.util.ArrayList;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    // Construtor que setta as propriedades do objeto usando os setters
    public Seguradora(String nome,
            String telefone,
            String email,
            String endereco,
            List<Sinistro> listaSinistros,
            List<Cliente> listaClientes) {
        setEmail(email);
        setEndereco(endereco);
        setNome(nome);
        setTelefone(telefone);
        setListaClientes(listaClientes);
        setListaSinistros(listaSinistros);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public boolean cadastrarCliente(Cliente cliente) {
        if(listaClientes == null)
            listaClientes = new ArrayList<Cliente>();
        return listaClientes.add(new Cliente(
            cliente.getNome(),
            cliente.getEndereco(),
            cliente.getDataLicenca(),
            cliente.getListaVeiculos()
        ));
    }

    public boolean removerCliente(String id) {

        boolean removeu = listaClientes.removeIf(elemento -> {
            String idElemento = "";

            if(elemento instanceof ClientePF)
                idElemento = ((ClientePF)elemento).getCpf();
            else if(elemento instanceof ClientePJ)
                idElemento = ((ClientePJ)elemento).getCnpj();
            return idElemento.equals(id);  
        });

        listaSinistros.removeIf(sinistro -> {
            String idElemento = "";

            Cliente cliente = sinistro.getCliente();
            if(cliente instanceof ClientePF)
                idElemento = ((ClientePF)cliente).getCpf();
            else if(cliente instanceof ClientePJ)
                idElemento = ((ClientePJ)cliente).getCnpj();
            return idElemento.equals(id);          
        });

        return removeu;
    }

    public List<Cliente> listarClientes(String tipoCliente) {
        List<Cliente> clientesTipo = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if(tipoCliente == "pj" && cliente instanceof ClientePJ){
                clientesTipo.add(cliente);
            }
            else if(tipoCliente == "pf" && cliente instanceof ClientePF){
                clientesTipo.add(cliente);
            }
        }
        return clientesTipo;
    }

    public boolean gerarSinistro() {
        Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT", 2015); 
        if(listaSinistros == null)
            listaSinistros = new ArrayList<Sinistro>();
        return listaSinistros.add(new Sinistro(email, endereco, this, listaClientes.get(0),veiculo));
    }

    public boolean visualizarSinistro(String idCliente) {
        return true;
    }

    public List<Sinistro> listarSinistros(){
        return listaSinistros;
    }
}
