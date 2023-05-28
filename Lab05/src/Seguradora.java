import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Seguro> listaSeguros;
    private List<Cliente> listaClientes;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/
    public Seguradora(String nome,
            String telefone,
            String email,
            String endereco, String cnpj) {
        setEmail(email);
        setEndereco(endereco);
        setNome(nome);
        setTelefone(telefone);
        this.cnpj = cnpj;
        listaClientes = new ArrayList<Cliente>();
        listaSeguros = new ArrayList<Seguro>();
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

    public List<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(List<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
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
     * Método que gera um seguro e o adiciona na lista de seguros da seguradora
     * @author Maria Eduarda Rocha
     * @param seguro Seguro - seguro a ser gerado
     * @return boolean - se a adição deu certo ou não*/
    public boolean gerarSeguro(Seguro seguro) {
        return listaSeguros.add(seguro);
    }

    /** 
     * Método que remove um seguro da seguradora
     * @author Maria Eduarda Rocha
     * @param id int - id do seguro a ser removido
     * @return boolean - se a remoção deu certo ou não*/
    public boolean cancelarSeguro(int id){
        return listaSeguros.removeIf(seguro -> { return seguro.getId() == id;});
    }

    /** 
     * Método que retorna uma lista de seguros baseado no cliente passado como parametro
     * @author Maria Eduarda Rocha
     * @param idCliente String - pode ser um CPF ou um CNPJ
     * @param tipo String - "PF" ou "PJ"
     * @return List<Seguro> - lista de seguros do cliente*/
    public List<Seguro> getSegurosPorCliente(String idCliente, String tipo){
        List<Seguro> listaRetorno = new ArrayList<Seguro>();
        if(tipo.toLowerCase().equals("pf")){
            for(Seguro seguro : listaSeguros){
                for(Condutor condutor : seguro.listaCondutores){
                    if(condutor.getCpf().equals(idCliente))
                        listaRetorno.add(seguro);
                }
                
                if(seguro instanceof SeguroPF){
                    if(((SeguroPF)seguro).getCliente().getCpf().equals(idCliente))
                        listaRetorno.add(seguro);
                }
            }
        }
        else{
            for(Seguro seguro: listaSeguros){
                if(seguro instanceof SeguroPJ){
                    if(((SeguroPJ)seguro).getCliente().getCnpj().equals(idCliente))
                        listaRetorno.add(seguro);
                }
            }
        }
        return listaRetorno;
    }

    //GET SINISTROS POR CLIENTE

    public List<Sinistro> getSinistrosPorCliente(String idCliente){
        List<Sinistro> sinistros = new ArrayList<Sinistro>();
        for(Seguro seguro : listaSeguros){
            seguro.listaSinistros.get(0).
        }
    }

    /** 
     * Método que retorna a receita da seguradora
     * @author Maria Eduarda Rocha
     * @return double - valor que a seguradora faz*/
    public double calcularReceita(){
        double receita = 0;
        for (Seguro seguro : listaSeguros) {
            receita += seguro.getValorMensal();
        }
        return receita;
    }

    /** 
     * Método toString que formata a lista de clientes e de seguros para deixar a leitura no console mais simples
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    public String toString(){
        String clientesString = "";
        int i = 1;
        for (Cliente cliente : listaClientes) {
            clientesString = clientesString.concat("\tCliente "+ i + ": \n" + cliente.toString() + "\n");
            i++;
        }

        String segurosString = "";
        i = 1;
        for (Seguro seguro : listaSeguros) {
            segurosString = segurosString.concat("\n\tSinistro "+ i + ": \n" 
            + "Id: " + seguro.getId());
            i++;
        }
        return "Nome: " + this.nome
             + "\nEmail: " + this.email
             + "\nEndereco: " + this.endereco
             + "\nTelefone: " + this.telefone
             + "\nClientes: \n" + clientesString
             + "\nSinistros: " + segurosString;
    }
}
