public abstract class Cliente {
    protected String nome;
    protected String endereco;
    protected String telefone;
    protected String email;

    /** 
     * Construtor da classe que setta as propriedades do objeto através de setters
     * @author Maria Eduarda Rocha*/    
    public Cliente(String nome,
            String endereco,
            String telefone,
            String email) {
        setNome(nome);
        setEndereco(endereco);
    }

    
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /** 
     * Método toString para formatar os atributos da classe em formato de texto
     * @author Maria Eduarda Rocha
     * @return String - toString da classe*/
    @Override
    public String toString() {
        return "Nome: " + this.nome
                + "\nEndereco: " + this.endereco
                + "\nEmail: " + this.email
                +"\nTelefone: " + this.telefone;
    }

}
