import java.util.ArrayList;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/
    public Seguradora(String nome,
            String telefone,
            String email,
            String endereco) {
        setEmail(email);
        setEndereco(endereco);
        setNome(nome);
        setTelefone(telefone);
        listaClientes = new ArrayList<Cliente>();
        listaSinistros = new ArrayList<Sinistro>();
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

    /** 
     * Método que adiciona um novo cliente a lista de clientes da seguradora
     * @author Maria Eduarda Rocha
     * @param cliente Cliente - cliente que será adicionado na seguradora
     * @return boolean - se a adição funcionou ou não*/
    public boolean cadastrarCliente(Cliente cliente) {
        return this.listaClientes.add(cliente);
    }

    /** 
     * Método que remove um cliente e seus respectivos sinitros
     * @author Maria Eduarda Rocha
     * @param id String - id(CPF/CNPJ) do cliente a ser removido
     * @return boolean - se a remoção funcionou*/
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

    /** 
     * Método que lista os clientes de um determinado tipo
     * @author Maria Eduarda Rocha
     * @param tipoCliente String - tipo do cliente "pj" ou "pf"
     * @return List<Cliente> - clientes da seguradora de determinado tipo*/
    public List<Cliente> listarClientes(String tipoCliente) {
        List<Cliente> clientesTipo = new ArrayList<Cliente>();
        for (Cliente cliente : listaClientes) {
            if(tipoCliente.equals("pj") && cliente instanceof ClientePJ){
                clientesTipo.add(cliente);
            }
            else if(tipoCliente.equals("pf") && cliente instanceof ClientePF){
                clientesTipo.add(cliente);
            }
        }
        return clientesTipo;
    }

    /** 
     * Método que busca um determinado cliente dentro da seguradora
     * @author Maria Eduarda Rocha
     * @param doc String - documento do cliente a ser buscado
     * @return Cliente - cliente buscado*/
    public Cliente buscarCliente(String doc){
        for(Cliente cliente:listaClientes){
            String idElemento = "";

            if(cliente instanceof ClientePF)
                idElemento = ((ClientePF)cliente).getCpf();
            else if(cliente instanceof ClientePJ)
                idElemento = ((ClientePJ)cliente).getCnpj();
            if(idElemento.equals(doc))
                return cliente;
        }
        return null;
    }

    /** 
     * Método que gera um sinistro e o adiciona na lista de sinistros da seguradora
     * @author Maria Eduarda Rocha
     * @param veiculo Veiculo - veiculo do sinistro a ser gerado     
     * @param email String - email do sinistro a ser gerado
     * @param endereco String - endereco do sinistro a ser gerado
     * @param cliente Cliente - cliente do sinistro a ser gerado
     * @return boolean - se a adição deu certo ou não*/
    public boolean gerarSinistro(Veiculo veiculo, String email, String endereco, Cliente cliente) {
        return listaSinistros.add(new Sinistro(email, endereco, this, cliente, veiculo));
    }

    /** 
     * Método que busca os sinistros dentro da lista e mostra na tela
     * @author Maria Eduarda Rocha
     * @param idCliente String - documento do cliente que quer os sinistros
     * @return boolean - se achou o sinistro*/
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

    /** 
     * Método que calcula o preço do seguro para um cliente especifico
     * @author Maria Eduarda Rocha
     * @param cliente Cliente - cliente para se calcular o valor do seguro
     * @return double - valor do seguro para o cliente*/
    public double calcularPrecoSeguroCliente(Cliente cliente){
        List<Sinistro> sinistros = new ArrayList<Sinistro>();
        String idCliente = "";
        if(cliente instanceof ClientePF)
            idCliente = ((ClientePF)cliente).getCpf();
        else if(cliente instanceof ClientePJ)
            idCliente = ((ClientePJ)cliente).getCnpj();
        for (Sinistro sinistro : this.listaSinistros) {
            String idElemento = "";
            Cliente clienteSinistro = sinistro.getCliente();
            //Define se é cpf ou cnpj baseado na instancia de cada cliente dentro da lista de clientes do sinistro
            if(clienteSinistro instanceof ClientePF)
                idElemento = ((ClientePF)clienteSinistro).getCpf();
            else if(clienteSinistro instanceof ClientePJ)
                idElemento = ((ClientePJ)clienteSinistro).getCnpj();
            if(idElemento.equals(idCliente)){
                sinistros.add(sinistro);
            }
        }

        return cliente.calculaScore() * (1 + sinistros.size());
    }

    /** 
     * Método que retorna a receita da seguradora
     * @author Maria Eduarda Rocha
     * @return double - valor que a seguradora faz*/
    public double calcularReceita(){
        double receita = 0;
        for (Cliente cliente : listaClientes) {
            receita += this.calcularPrecoSeguroCliente(cliente);
        }
        return receita;
    }

    /** 
     * Método que transfere o seguro de um cliente para outro
     * @author Maria Eduarda Rocha
     * @param docRementente String - CPF/CNPJ do cliente que terá sua lista de veiculos apagada     
     * @param docDestino String - CPF/CNPJ do cliente que terá a lista de veiculos concatenada*/
    public void transferirSeguro(String docRemetente, String docDestino){
        Cliente remetente = buscarCliente(docRemetente);
        Cliente destino = buscarCliente(docDestino);
        destino.getListaVeiculos().addAll(remetente.getListaVeiculos());
        remetente.getListaVeiculos().clear();
        remetente.setValorSeguro(this.calcularPrecoSeguroCliente(remetente));
        destino.setValorSeguro(this.calcularPrecoSeguroCliente(destino));
    }

    /** 
     * Método toString que formata a lista de clientes e de sinistros para deixar a leitura no console mais simples
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
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
