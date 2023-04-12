import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            //Chamar o menu que valida cpf e cnpj digitados
            Main.menu();

            //Criação dos objetos usados para as operações
            Seguradora seguradora = new Seguradora("Teste", "2356-8974", "teste@gmail.com", "Rua de Teste", null, null);

            Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT", 2015); 
            
            ClientePF pf = new ClientePF("465.744.250-34", LocalDate.of(2003, 8, 29), "Teste", "Rua de Teste", "Teste Educacao", LocalDate.of(2013, 07, 20), "Teste Genero", "Teste Classe", Arrays.asList(veiculo, veiculo));
            ClientePJ pj = new ClientePJ("47.771.224/0001-60", LocalDate.of(2013, 07, 20), "Teste", "Rua de Teste", LocalDate.of(2013, 07, 20), Arrays.asList(veiculo, veiculo));
            ClientePJ pj2 = new ClientePJ("83.160.132/0001-08", LocalDate.of(2013, 07, 20), "Teste", "Rua de Teste", LocalDate.of(2013, 07, 20), Arrays.asList(veiculo, veiculo));
            
            Sinistro sinistro = new Sinistro("14-03-2023", "Rua de Teste", seguradora, pj, veiculo);

            //Cadastrar clientes na seguradora
            seguradora.cadastrarCliente(pf);
            seguradora.cadastrarCliente(pj);
            seguradora.cadastrarCliente(pj2);

            //Gera um sinistro dentro da seguradora
            seguradora.gerarSinistro();

            //Lista clientes do tipo PJ
            List<Cliente> clientes = seguradora.listarClientes("pj");
            System.out.println("Clientes da Seguradora: \n" + clientes.toString() + "\n");

            //Remove um dos clientes PJ da seguradora
            seguradora.removerCliente("47.771.224/0001-60");

            //Lista todos os sinistros cadastrados na seguradora
            List<Sinistro> sinistros = seguradora.listarSinistros();
            System.out.println("Sinistros da Seguradora: \n" + sinistros.toString() + "\n");

            //Mostra o sinistro de determinado cliente dentro da seguradora
            System.out.println("Sinistro especifico: \n");
            seguradora.visualizarSinistro("465.744.250-34");

            //Teste das funções de validar CPF e CNPJ
            System.out.println("\nCPF 465.744.250-34 " + (ClientePF.validarCpf("465.744.250-34") ? "eh valido" : "eh invalido") + "\n");
            System.out.println("CNPJ 47.771.224/0001-60 " + (ClientePJ.validarCnpj("47.771.224/0001-60") ? "eh valido" : "eh invalido") + "\n");

            //Teste de toString das classes
            System.out.println("Veiculo:\n" + veiculo.toString() + "\n");
            System.out.println("Pessoa Fisica:\n" + pf.toString() + "\n");
            System.out.println("Pessoa Juridica:\n" + pj.toString() + "\n");
            System.out.println("Sinistro:\n" + sinistro.toString() + "\n");
            System.out.println("Seguradora:\n" + seguradora.toString());

        } catch(Exception err) {
            System.out.println("ERROR");
        }
    }

    private static void menu() {
        Scanner scan = new Scanner(System.in);  
        System.out.println("=============== Menu ===============");
        System.out.println("0 - Validar CPF");
        System.out.println("1 - Validar CNPJ");
        System.out.println("Digite o número correspondente a operacao que deseja realizar:");
        String opcao = scan.nextLine();
        switch (opcao) {
            case "0":
                System.out.print("Digite o CPF: ");
                String cpf = scan.nextLine();
                System.out.println(ClientePF.validarCpf(cpf));
                break;
            case "1":
                System.out.print("Digite o CNPJ: ");
                String cnpj = scan.nextLine();
                System.out.println(ClientePJ.validarCnpj(cnpj));
                break;
            default:
                break;
        }
        System.out.println("\n");
    }
}
