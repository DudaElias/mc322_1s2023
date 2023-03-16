public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor que setta as propriedades da classe usando os setters
    public Cliente(String nome,
            String cpf,
            String dataNascimento,
            int idade,
            String endereco) {
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setEndereco(endereco);
        setIdade(idade);
        setNome(nome);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    //Método toString que manda uma string formatada com as propriedades da classe
    public String toString() {
        return "Nome: " + this.nome
                + "\nCPF: " + this.cpf
                + "\nData de Nascimento: " + this.dataNascimento
                + "\nIdade: " + this.nome
                + "\nEndereco: " + this.endereco;
    }

    /*
        Método que verifica o cpf do cliente se baseando na quantidade de números e demais condições estipuladas para um cpf ser válido
    */
    public boolean validarCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        if (cpf.length() != 11)
            return false;
        boolean ehTudoIgual = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(i - 1)) {
                ehTudoIgual = false;
                break;
            }
        }
        if (ehTudoIgual)
            return false;

        int sum = 0;
        int peso = 10;
        int num = 0;
        for (int i = 0; i < 9; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sum = sum + (num * peso);
            peso = peso - 1;
        }

        int r = 11 - (sum % 11);
        int dig10 = 0, dig11 = 0;
        if ((r == 10) || (r == 11))
            dig10 = '0';
        else
            dig10 = (char) (r + 48);

        sum = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            num = (int) (cpf.charAt(i) - 48);
            sum = sum + (num * peso);
            peso = peso - 1;
        }

        r = 11 - (sum % 11);
        if ((r == 10) || (r == 11))
            dig11 = '0';
        else
            dig11 = (char) (r + 48);

        if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
            return true;
        else
            return false;
    }
}
