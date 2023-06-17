public final class Validacao {
    
    /** 
     * Verifica o cpf do cliente se baseando na quantidade de números 
     * e demais condições estipuladas para um cpf ser válido
     * @author Maria Eduarda Rocha
     * @param cpf String - cpf do cliente
     * @return boolean - cpf é valido ou não*/
    public static boolean validarCpf(String cpf) {
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

    
    /** 
     * Método que valida o CNPJ fazendo as validaçoes padrão
     * @author Maria Eduarda Rocha
     * @param cnpj String - cnpj do cliente
     * @return boolean - cnpj é valido ou não*/
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
    
        if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
            return(true);
        else return(false);
    }

    /** 
     * Método que valida se o nome contém apenas letras
     * @author Maria Eduarda Rocha
     * @param nome String - nome do cliente
     * @return boolean - nome é valido ou não*/
    public static boolean validaNome(String nome){
        return nome != null && nome.matches("^[a-zA-Z]*$");
    }

    /** 
     * Método que valida se o email é válido
     * @author Maria Eduarda Rocha
     * @param email String - email do cliente
     * @return boolean - email é valido ou não*/
    public static boolean validaEmail(String email){
        return email != null && email.matches("^(.+)@(.+)$");
    }
}