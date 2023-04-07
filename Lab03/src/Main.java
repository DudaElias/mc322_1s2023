import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Main.menu();
        Seguradora seguradora = new Seguradora("Teste", "2356-8974", "teste@gmail.com", "Rua de Teste", null, null);
        Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT", 2015); 
        ClientePF pf = new ClientePF("465.744.250-34", LocalDate.of(2003, 8, 29), "Teste", "Rua de Teste", "Teste Educacao", LocalDate.of(2013, 07, 20), "Teste Genero", "Teste Classe", null);
        ClientePJ pj = new ClientePJ("47.771.224/0001-60", LocalDate.of(2013, 07, 20), "Teste", "Rua de Teste", LocalDate.of(2013, 07, 20), null);
        Sinistro sinistro = new Sinistro("14-03-2023", "Rua de Teste", seguradora, pj, veiculo);
        seguradora.cadastrarCliente(pf);
        seguradora.cadastrarCliente(pj);
        seguradora.gerarSinistro();

        List<Cliente> clientes = seguradora.listarClientes("pj");
        System.out.println(clientes.toString());
        seguradora.listarSinistros();
        seguradora.visualizarSinistro("465.744.250-34");
        
        seguradora.removerCliente(pf.getCpf());


        System.out.println("CPF 465.744.250-34 " + (ClientePF.validarCpf("465.744.250-34") ? "eh valido" : "eh invalido"));
        System.out.println("CNPJ 47.771.224/0001-60 " + (ClientePJ.validarCnpj("47.771.224/0001-60") ? "eh valido" : "eh invalido"));
        System.out.println(veiculo.toString() + "\n");
        System.out.println(pf.toString() + "\n");
        System.out.println(pj.toString() + "\n");
        System.out.println(sinistro.toString());
    }

    private static void menu() {
        Scanner scan = new Scanner(System.in);  
        System.out.println("=============== Menu ===============");
        System.out.println("0 - Adicionar Cliente PJ");
        System.out.println("1 - Adicionar Cliente PF");
        System.out.println("2 - Remover Cliente PJ");
        System.out.println("3 - Remover Cliente PF");
        System.out.println("4 - Gerar Sinistro");
        System.out.println("5 - Validar CPF");
        System.out.println("6 - Validar CNPJ");
        System.out.println("7 - Mostar Objetos");
        System.out.println("Digite o número correspondente a operacao que deseja realizar:");
        String opcao = scan.nextLine();
        System.out.println(opcao);
        switch (opcao) {
            case "0":
                
                break;
            case "1":
                
                break;
            
            case "2":
                
                break;
            case "3":
                
                break;
            case "4":
                
                break;
            
            case "5":
                
                break;

            case "6":
                
                break;
            
            case "7":
                
                break;
            default:
                break;
        }
    }
}
