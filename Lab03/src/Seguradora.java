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
        if(this.listaClientes == null) // verifica se ainda não existe uma lista instanciada
            this.listaClientes = new ArrayList<Cliente>();
        return this.listaClientes.add(cliente);
    }

    public boolean removerCliente(String id) {

        //Determina qual o tipo de cada cliente dentro da lista se baseando na instancia

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
        //Cria um veiculo default e gera um sinistro com o primeiro cliente da lista e esse veiculo
        Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT", 2015); 
        if(listaSinistros == null) //Verifica se já existe a lista de sinistros
            listaSinistros = new ArrayList<Sinistro>();
        return listaSinistros.add(new Sinistro(email, endereco, this, listaClientes.get(0),veiculo));
    }

    public boolean visualizarSinistro(String idCliente) {
        for (Sinistro sinistro : this.listaSinistros) {
             String idElemento = "";
            Cliente cliente = sinistro.getCliente();
            //Define se é cpf ou cnpj baseado na instancia de cada cliente dentro da lista de clientes do sinistro
            if(cliente instanceof ClientePF)
                idElemento = ((ClientePF)cliente).getCpf();
            else if(cliente instanceof ClientePJ)
                idElemento = ((ClientePJ)cliente).getCnpj();
            if(idElemento.equals(idCliente)){
                //Quando achar o cliente dentro da lista de sinistros ele mostra o sinistro atual
                System.out.println(sinistro.toString());
            }
        }
        return true;
    }

    public List<Sinistro> listarSinistros(){
        return listaSinistros;
    }

    //Método toString que formata a lista de clientes e de sinistros para deixar a leitura no console mais simples
    public String toString(){
        String clientesString = "";
        int i = 1;
        for (Cliente cliente : listaClientes) {
            clientesString = clientesString.concat("\tCliente "+ i + ": \n" + cliente.toString() + "\n");
            i++;
        }

        String sinistrosString = "";
        i = 1;
        for (Sinistro sinistro : listaSinistros) {
            sinistrosString = sinistrosString.concat("\n\tSinistro "+ i + ": \n" 
            + "Id: " + sinistro.getId()
            + "\nData: " + sinistro.getData()
            + "\nEndereco: " + sinistro.getEndereco()
            + "\nVeiculo: " + sinistro.getVeiculo().toString()
            + "\nCliente: " + sinistro.getCliente().toString() + "\n");
            i++;
        }
        return "Nome: " + this.nome
             + "\nEmail: " + this.email
             + "\nEndereco: " + this.endereco
             + "\nTelefone: " + this.telefone
             + "\nClientes: \n" + clientesString
             + "\nSinistros: " + sinistrosString;
    }
}
