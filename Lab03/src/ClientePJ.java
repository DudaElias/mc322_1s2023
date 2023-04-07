import java.time.LocalDate;
import java.util.List;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private LocalDate dataFundacao;

    public ClientePJ(
        String cnpj,
        LocalDate dataFundacao,
        String nome,
        String endereco,
        LocalDate licenca,
        List<Veiculo> lista
    ) throws Error{
        super(nome, endereco, licenca, lista);
        if(!validarCnpj(cnpj))
            throw new Error("CNPJ invalido");
        setDataFundacao(dataFundacao);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    @Override
    public String toString() {
        return super.toString()
        + "\nCNPJ: " + this.cnpj
        + "\nData Fundacao: " + this.dataFundacao.toString();
    }

    public static boolean validarCnpj(String cnpj){
        cnpj = cnpj.replaceAll("[^0-9]", "");
        if (cnpj.length() != 14)
            return false;
        boolean ehTudoIgual = true;
        for (int i = 1; i < 14; i++) {
            if (cnpj.charAt(i) != cnpj.charAt(i - 1)) {
                ehTudoIgual = false;
                break;
            }
        }
        if (ehTudoIgual)
            return false;
        
        char dig13, dig14;
        int sm, i, r, num, peso;
        
        sm = 0;
        peso = 2;
        for (i=11; i>=0; i--) {
  // converte o i-ésimo caractere do CNPJ em um número:
  // por exemplo, transforma o caractere '0' no inteiro 0
  // (48 eh a posição de '0' na tabela ASCII)
          num = (int)(cnpj.charAt(i) - 48);
          sm = sm + (num * peso);
          peso = peso + 1;
          if (peso == 10)
             peso = 2;
        }
  
        r = sm % 11;
        if ((r == 0) || (r == 1))
           dig13 = '0';
        else dig13 = (char)((11-r) + 48);
  
  // Calculo do 2o. Digito Verificador
        sm = 0;
        peso = 2;
        for (i=12; i>=0; i--) {
          num = (int)(cnpj.charAt(i)- 48);
          sm = sm + (num * peso);
          peso = peso + 1;
          if (peso == 10)
             peso = 2;
        }
  
        r = sm % 11;
        if ((r == 0) || (r == 1))
           dig14 = '0';
        else dig14 = (char)((11-r) + 48);
  
        // Verifica se os dígitos calculados conferem com os dígitos informados.
        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
           return(true);
        else return(false);
    }

}
