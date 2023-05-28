import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    static List<Seguradora> seguradoras = new ArrayList<Seguradora>();

    /** 
     * Main que chama operações e adiciona objetos a seguradora Teste
     * @author Maria Eduarda Rocha
     * @param args String[] - cliente para se calcular o valor do seguro*/
    public static void main(String[] args) {
        try {

            // Criação dos objetos usados para as operações
            Seguradora seguradora = new Seguradora("Teste", "2356-8974", "teste@gmail.com", "Rua de Teste", "87.039.558/0001-69");

            seguradoras.add(seguradora);

            Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT", 2015);
            Veiculo veiculo2 = new Veiculo("def1234", "Honda", "CITY", 2010);

            ClientePF pf = new ClientePF("465.744.250-34", LocalDate.of(2003, 8, 29), "Teste", "Rua de Teste",
                    "Teste Educacao", "teste@gmail.com", "Teste genero", "Teste Classe");
            ClientePJ pj = new ClientePJ("83.160.132/0001-08", LocalDate.of(2013, 07, 20), "Teste", "Rua de Teste", "123456", "teste@gmail.com", 100);

            Condutor condutor = new Condutor("368.355.130-55", "Jose", "123456", "Rua de Teste", "teste@gmail.com", LocalDate.of(1975, 07, 20));
            Condutor condutor2 = new Condutor("062.377.060-11", "Maria", "123456", "Rua de Teste", "teste@gmail.com", LocalDate.of(1975, 07, 20));

            Frota frota = new Frota("123");
            
            frota.addVeiculo(veiculo2);
            pj.cadastrarFrota(frota);
            
            //Cadastrar veiculos nos clientes
            pf.listaVeiculos.add(veiculo);
            
            // Cadastrar clientes na seguradora
            seguradora.cadastrarCliente(pf);
            seguradora.cadastrarCliente(pj);
            
            
            SeguroPF seguroPF = new SeguroPF(0, LocalDate.of(2013, 07, 20), LocalDate.of(2020, 07, 20), seguradora, veiculo, pf);
            SeguroPJ seguroPJ = new SeguroPJ(1, LocalDate.of(2013, 07, 20), LocalDate.of(2023, 07, 20), seguradora, frota, pj);
            
            // Autorizar condutor
            seguroPF.autorizarCondutor(condutor);
            seguroPJ.autorizarCondutor(condutor2);

            // Gera um seguro dentro da seguradora
            seguradora.gerarSeguro(seguroPF);
            seguradora.gerarSeguro(seguroPJ);


            // Gera um sinistro dentro da seguradora
            seguradora.getListaSeguros().get(0).gerarSinistro("teste@gmail.com", "Rua de Teste", "368.355.130-55");
            seguradora.getListaSeguros().get(1).gerarSinistro("teste@gmail.com", "Rua de Teste", "062.377.060-11");

            // Lista clientes do tipo PJ
            List<Cliente> clientes = seguradora.listarClientes("pj");
            System.out.println("Clientes da Seguradora: \n" + clientes.toString() + "\n");

            // Lista todos os sinistros cadastrados num determinado cliente
            List<Sinistro> sinistros = seguradora.getSinistrosPorCliente("368.355.130-55", "pf");
            System.out.println("Sinistros do cliente: \n" + sinistros.toString() + "\n");

            // Lista todos os seguros cadastrados num determinado cliente
            List<Seguro> seguros = seguradora.getSegurosPorCliente("368.355.130-55", "pf");
            System.out.println("Seguros do cliente: \n" + seguros.toString() + "\n");

            // CALCULAR RECEITA
            System.out.printf("Receita da seguradora: %f\n", seguradora.calcularReceita());

            // Teste de toString das classes
            System.out.println("Veiculo:\n" + veiculo.toString() + "\n");
            System.out.println("Pessoa Fisica:\n" + pf.toString() + "\n");
            System.out.println("Pessoa Juridica:\n" + pj.toString() + "\n");
            System.out.println("Condutor:\n" + condutor.toString() + "\n");
            System.out.println("Frota:\n" + frota.toString() + "\n");
            System.out.println("Seguro PF:\n" + seguradora.getListaSeguros().get(0).toString() + "\n");
            System.out.println("Seguro PJ:\n" + seguradora.getListaSeguros().get(1).toString() + "\n");
            System.out.println("Sinistro:\n" + seguradora.getListaSeguros().get(0).listaSinistros.get(0).toString() + "\n");
            System.out.println("Seguradora:\n" + seguradora.toString());


            //Outros métodos da seguradora
            System.out.println("Listar Cliente: \n" + seguradora.listarClientes("pf").toString());
            System.out.println("Buscar Cliente:\n" + seguradora.buscarCliente("465.744.250-34"));
            System.out.println("Cancelar Seguro: \n" + (seguradora.cancelarSeguro(0) ? "Seguro cancelado" : "Erro ao cancelar seguro"));
            System.out.println("Remover Cliente: \n"+ (seguradora.removerCliente("465.744.250-34") ? "Cliente removido" : "Erro ao remover o cliente"));

            AppMain menus = new AppMain();
            menus.menuPrincipal();
        } catch (Exception err) {
            System.out.println("Erro ao realizar operação");
        }
    }

    /** 
     * Método que determina a operação do menu principal
     * @author Maria Eduarda Rocha*/
    public void menuPrincipal() {
        Scanner scan = new Scanner(System.in);
        MenuOperacoes opcao;
        do {
            System.out.println("=============== Menu Principal ===============");
            System.out.println("1 - Cadastros");
            System.out.println("2 - Listar");
            System.out.println("3 - Excluir");
            System.out.println("4 - Gerar Sinistro");
            System.out.println("5 - Calcular Receita Seguradora");
            System.out.println("6 - Gerar Seguro");
            System.out.println("0 - Sair");
            System.out.println("Digite o número correspondente a operacao que deseja realizar:");

            int operacao = scan.nextInt();
            opcao = MenuOperacoes.acharOperacao(operacao);
            switch (opcao) {
                case CADASTRAR:
                    cadastrar();
                    break;
                case LISTAR:
                    listar();
                    break;
                case EXCLUIR:
                    excluir();
                    break;
                case GERAR_SINISTRO:
                    gerarSinistroDados();
                    break;
                case CALCULAR_RECEITA_SEGURO:
                    calcularReceita();
                    break;
                case GERAR_SEGURO:
                    gerarSeguroDados();
                    break;
                case SAIR:
                    return;
                default:
                    break;
            }
        } while (opcao != MenuOperacoes.SAIR);
    }

    /** 
     * Método que mostra no terminal o menu de cadastro
     * @author Maria Eduarda Rocha*/
    public void menuCadastrar() {
        System.out.println("=============== Menu Cadastrar ===============");
        System.out.println("1.1 - Cadastrar Cliente PF/PJ");
        System.out.println("1.2 - Cadastrar Veiculo");
        System.out.println("1.3 - Cadastrar Seguradora");
        System.out.println("1.4 - Cadastrar Frota");
        System.out.println("7 - Voltar");
    }

    /** 
     * Método que mostra no terminal o menu de excluir
     * @author Maria Eduarda Rocha*/
    public void menuExcluir() {
        System.out.println("=============== Menu Excluir ===============");
        System.out.println("3.1 - Excluir Cliente");
        System.out.println("3.2 - Excluir Veiculo");
        System.out.println("3.3 - Excluir Sinistro");
        System.out.println("3.4 - Excluir Seguro");
        System.out.println("3.5 - Excluir Frota");
        System.out.println("7 - Voltar");
    }

    /** 
     * Método que mostra no terminal o menu de listar
     * @author Maria Eduarda Rocha*/
    public void menuListar() {
        System.out.println("=============== Menu Listar ===============");
        System.out.println("2.1 - Listar Cliente (PF/PJ) por Seg.");
        System.out.println("2.2 - Listar Sinistros por Seguradora");
        System.out.println("2.3 - Listar Sinistro por Cliente");
        System.out.println("2.4 - Listar Veiculo por Cliente");
        System.out.println("2.5 - Listar Veiculo por Seguradora");
        System.out.println("2.6 - Listar Seguro por Cliente");
        System.out.println("2.7 - Listar Veiculo por Frota");
        System.out.println("7 - Voltar");
    }

    /** 
     * Método que determina qual cadastro será feito
     * @author Maria Eduarda Rocha*/
    public void cadastrar() {
        MenuOperacoes opcao;
        do {
            Scanner scan = new Scanner(System.in);
            menuCadastrar();
            System.out.println("Digite o número correspondente a operacao que deseja realizar (Exemplo: 1.1): ");
            String operacao = scan.nextLine();
            opcao = MenuOperacoes.acharOperacao(Integer.parseInt(operacao.replace(".", "")));
            switch (opcao) {
                case CADASTRAR_CLIENTE:
                    cadastrarCliente();
                    break;
                case CADASTRAR_SEGURADORA:
                    cadastrarSeguradora();
                    break;
                case CADASTRAR_VEICULO:
                    cadastrarVeiculo();
                    break;
                case CADASTRAR_FROTA:
                    cadastrarFrota();
                    break;
                case VOLTAR:
                    menuPrincipal();
                    break;
                default:
                    break;
            }
        } while (opcao != MenuOperacoes.VOLTAR);
    }

    /** 
     * Método que determina qual listagem será feito
     * @author Maria Eduarda Rocha*/
    public void listar() {
        MenuOperacoes opcao;
        do {
            Scanner scan = new Scanner(System.in);
            menuListar();
            System.out.println("Digite o número correspondente a operacao que deseja realizar (Exemplo: 2.1): ");
            String operacao = scan.nextLine();
            opcao = MenuOperacoes.acharOperacao(Integer.parseInt(operacao.replace(".", "")));
            switch (opcao) {
                case LISTAR_CLIENTE_POR_SEGURADORA:
                    listarClientePorSeguradora();
                    break;
                case LISTAR_SINISTROS_POR_SEGURADORA:
                    listarSinistrosPorSeguradora();
                    break;
                case LISTAR_SINISTRO_CLIENTE:
                    listarSinistroCliente();
                    break;
                case LISTAR_VEICULO_CLIENTE:
                    listarVeiculoCliente();
                    break;
                case LISTAR_VEICULO_SEGURADORA:
                    listarVeiculoSeguradora();
                    break;
                case LISTAR_SEGURO_CLIENTE:
                    listarSeguroCliente();
                    break;
                case LISTAR_VEICULO_FROTA:
                    listarVeiculoFrota();
                    break;
                case VOLTAR:
                    break;
                default:
                    break;
            }
        } while (opcao != MenuOperacoes.VOLTAR);
    }

    /** 
     * Método que determina qual exclusão será feita
     * @author Maria Eduarda Rocha*/
    public void excluir() {
        MenuOperacoes opcao;
        do {
            Scanner scan = new Scanner(System.in);
            menuExcluir();
            System.out.println("Digite o número correspondente a operacao que deseja realizar (Exemplo: 3.1): ");
            String operacao = scan.nextLine();
            opcao = MenuOperacoes.acharOperacao(Integer.parseInt(operacao.replace(".", "")));
            switch (opcao) {
                case EXCLUIR_CLIENTE:
                    excluirCliente();
                    break;
                case EXCLUIR_SINISTRO:
                    excluirSinistro();
                    break;
                case EXCLUIR_VEICULO:
                    excluirVeiculo();
                    break;
                case EXCLUIR_SEGURO:
                    excluirSeguro();
                    break;
                case EXCLUIR_FROTA:
                    excluirFrota();
                    break;
                case VOLTAR:
                    break;
                default:
                    break;
            }
        } while (opcao != MenuOperacoes.VOLTAR);
    }

    /** 
     * Método que pede e retorna a seguradora que o user escolheu
     * @author Maria Eduarda Rocha
     * @return Seguradora - seguradora escolhida pelo user*/
    public Seguradora selecionarSeguradora() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual seguradora (nome)? ");
        String nomeSeguradora = scan.nextLine();
        Seguradora selecionada = null;
        for (Seguradora seguradora : seguradoras) {
            if (seguradora.getNome().equals(nomeSeguradora))
                selecionada = seguradora;
        }
        return selecionada;
    }

    /** 
     * Método que pede e retorna o seguro que o user escolheu
     * @author Maria Eduarda Rocha
     * @return Seguro - seguro escolhido pelo user*/
    public Seguro selecionarSeguro() {
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual seguro (id)? ");
        int id = scan.nextInt();
        Seguro selecionada = null;
        for (Seguro s : seguradora.getListaSeguros()) {
            if (s.getId() == id)
                selecionada = s;
        }
        return selecionada;
    }

    /** 
     * Método que seleciona o cliente baseado no seu documento digitado pelo user
     * @author Maria Eduarda Rocha
     * @param seguradora Seguradora - seguradora no qual o cliente foi adicionado
     * @return Cliente - cliente selecionado*/
    public Cliente selecionarCliente(Seguradora seguradora){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual o CPF/CNPJ do cliente?");
        String doc = scan.nextLine();
        return seguradora.buscarCliente(doc);
    }
    /** 
     * Método que pede e retorna a frota que o user escolheu
     * @author Maria Eduarda Rocha
     * @param c ClientePJ - cliente que tem a frota
     * @return Frota - frota escolhida pelo user*/
    public Frota selecionarFrota(ClientePJ c){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual o code da frota?");
        String doc = scan.nextLine();
        for (Frota f : c.listaFrota) {
            if(f.getCode().equals(doc))
                return f;
        }
        return null;
    }

    /** 
     * Método que pede e retorna o veiculo que o user escolheu
     * @author Maria Eduarda Rocha
     * @param c ClientePF - cliente que tem o veiculo
     * @return Veiculo - veiculo escolhido pelo user*/
    public Veiculo selecionarVeiculo(ClientePF c){
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual a placa do veiculo?");
        String doc = scan.nextLine();
        for (Veiculo v : c.listaVeiculos) {
            if(v.getPlaca().equals(doc))
                return v;
        }
        return null;
    }

    /** 
     * Método que adiciona uma nova seguradora na lista de seguradoras
     * @author Maria Eduarda Rocha*/
    public void cadastrarSeguradora() {
        Scanner scan = new Scanner(System.in);
        String nome = "";
        do {
            System.out.print("Qual o nome da seguradora? ");
            nome = scan.nextLine();
        } while (!Validacao.validaNome(nome));
        System.out.println("Qual o telefone da seguradora?");
        String telefone = scan.nextLine();
        String email = "";
        do {
            System.out.print("Qual o email da seguradora?");
            email = scan.nextLine();
        } while (!Validacao.validaEmail(email));
        String cnpj = "";
        do {
            System.out.print("Digite o CNPJ da seguradora (XX.XXX.XXX/XXXX-XX): ");
            cnpj = scan.nextLine();
        } while (!Validacao.validarCnpj(cnpj));
        System.out.println("Qual o endereco da seguradora?");
        String endereco = scan.nextLine();
        if (seguradoras.add(new Seguradora(nome, telefone, email, endereco, cnpj)))
            System.out.println("Seguradora adicionada com sucesso!");
        else
            System.out.println("Erro ao cadastrar a seguradora!");
    }

    /** 
     * Método que adiciona um novo veiculo em um cliente dentro de uma seguradora
     * @author Maria Eduarda Rocha*/
    public void cadastrarVeiculo() {
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = selecionarSeguradora();
        Cliente cliente = selecionarCliente(seguradora);
        System.out.println("Qual a placa do veiculo?");
        String placa = scan.nextLine();
        System.out.println("Qual a marca do veiculo?");
        String marca = scan.nextLine();
        System.out.println("Qual a modelo do veiculo?");
        String modelo = scan.nextLine();
        System.out.println("Qual o ano e fabricação do veiculo?");
        int ano = scan.nextInt();
        if(cliente instanceof ClientePJ){
            System.out.println("Qual o code da frota?");
            String code = scan.next();
            List<Frota> frotas = ((ClientePJ)cliente).getListaFrota();
            for (Frota f : frotas) {
                if(f.getCode().equals(code)){
                    if (f.listaVeiculos.add(new Veiculo(placa, marca, modelo, ano)))
                        System.out.println("Veiculo adicionado com sucesso!");
                    else
                        System.out.println("Erro ao cadastrar o veiculo!");
                }
            }
            
        }
        else{
            if (((ClientePF)cliente).listaVeiculos.add(new Veiculo(placa, marca, modelo, ano)))
                System.out.println("Veiculo adicionado com sucesso!");
            else
                System.out.println("Erro ao cadastrar o veiculo!");
        }
    }

    /** 
     * Método que adiciona uma nova frota dentro de um cliente pessoa juridica
     * @author Maria Eduarda Rocha*/
    public void cadastrarFrota() {
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = selecionarSeguradora();
        Cliente cliente = selecionarCliente(seguradora);
        if(cliente instanceof ClientePJ){
            System.out.println("Qual o code da frota?");
            String code = scan.nextLine();
            if (((ClientePJ)cliente).cadastrarFrota(new Frota(code)))
                System.out.println("Frota adicionada com sucesso!");
            else
                System.out.println("Erro ao cadastrar a frota!");
            
        }

    }

    /** 
     * Método que lista todos os clientes de um tipo especifico dentro de uma seguradora
     * @author Maria Eduarda Rocha*/
    public void listarClientePorSeguradora(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual o tipo de cliente a ser listado (PF/PJ)?");
        String tipo = scan.nextLine();
        List<Cliente> clientesSeguradora = seguradora.listarClientes(tipo.toLowerCase());
        for(Cliente cliente : clientesSeguradora){
            System.out.println("\t\tCliente:");
            System.out.println(cliente.toString());
        }
    }

    /** 
     * Método que lista todos os sinistros dentro da seguradora
     * @author Maria Eduarda Rocha*/
    public void listarSinistrosPorSeguradora(){
        Seguradora seguradora = selecionarSeguradora();
        List<Sinistro> sinistros = new ArrayList<Sinistro>();
        for(Seguro seguro : seguradora.getListaSeguros()){
            sinistros.addAll(seguro.listaSinistros);
        }
        for(Sinistro sinistro : sinistros){
            System.out.println("\t\tSinistro:");
            System.out.println(sinistro.toString());
        }
    }

    /** 
     * Método que lista todos os sinistros de um cliente especifico
     * @author Maria Eduarda Rocha*/
    public void listarSinistroCliente(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digitar documento do cliente: ");
        String doc = scan.nextLine();
        System.out.println("Pessoa Física ou Jurídica? (PF/PJ)");
        String tipo = scan.nextLine();
        List<Sinistro> sinistros = seguradora.getSinistrosPorCliente(doc, tipo);
        for (Sinistro sinistro : sinistros) {
            System.out.println("\nSinistro\n");
            System.out.println(sinistro.toString());
        }
    }

    /** 
     * Método que lista todos os veiculos dentro de um cliente
     * @author Maria Eduarda Rocha*/
    public void listarVeiculoCliente(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o documento do cliente: ");
        String doc = scan.nextLine();
        Cliente cliente = seguradora.buscarCliente(doc);
        listarVeiculos(cliente);
    }
    /** 
     * Método que lista todos os veiculos de um cliente passado como parametro
     * @param cliente Cliente - cliente que quer mostrar os veiculos
     * @author Maria Eduarda Rocha*/
    public void listarVeiculos(Cliente cliente){
        if(cliente instanceof ClientePF){
            for(Veiculo veiculo: ((ClientePF)cliente).listaVeiculos){
                System.out.println("\t\tVeiculo:");
                System.out.println(veiculo.toString());
            }
        }
        else{
            for(Frota frota : ((ClientePJ)cliente).listaFrota){
                for(Veiculo veiculo: frota.listaVeiculos){
                    System.out.println("\t\tVeiculo:");
                    System.out.println(veiculo.toString());
               }
            }
        }
    }

    /** 
     * Método que lista todos os veiculos de todos os clientes da seguradora
     * @author Maria Eduarda Rocha*/
    public void listarVeiculoSeguradora(){
        Seguradora seguradora = selecionarSeguradora();
        List<Cliente> clientes = seguradora.getListaClientes();
        for(Cliente cliente: clientes){
            listarVeiculos(cliente);
        }
    }

    /** 
     * Método que lista todos os seguros de um cliente especifico
     * @author Maria Eduarda Rocha*/
    public void listarSeguroCliente(){
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = selecionarSeguradora();
        System.out.println("Qual o CPF/CNPJ do cliente?");
        String doc = scan.nextLine();        
        System.out.println("Pessoa Física ou Juridica? (PF/PJ)");
        String tipo = scan.nextLine();
        List<Seguro> seguros = seguradora.getSegurosPorCliente(doc, tipo);
        for(Seguro seguro: seguros){
            System.out.println("\t\tSeguro:");
            System.out.println(seguro.toString());
        }
    }

    /** 
     * Método que lista todos os veiculos de uma frota especifica
     * @author Maria Eduarda Rocha*/
    public void listarVeiculoFrota(){
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = selecionarSeguradora();
        Cliente cliente = selecionarCliente(seguradora);
        if(cliente instanceof ClientePJ){
            System.out.println("Qual o code da frota?");
            String code = scan.nextLine();
            for (Frota f : ((ClientePJ)cliente).listaFrota) {
                if(f.getCode().equals(code)){
                    for(Veiculo v: f.listaVeiculos){
                        System.out.println("\t\tVeiculo:");
                        System.out.println(v.toString());
                    }
                }
            }
        }
    }

    /** 
     * Método que exclui um cliente da seguradora
     * @author Maria Eduarda Rocha*/
    public void excluirCliente(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Qual o documento do cliente? ");
        String doc = scan.nextLine();
        if(seguradora.removerCliente(doc))
            System.out.println("Cliente excluido com sucesso!");
        else
            System.out.println("Erro ao excluir cliente!");
    }

    /** 
     * Método que exclui um sinistro de um seguro
     * @author Maria Eduarda Rocha*/
    public void excluirSinistro(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o documento do cliente que deseja excluir o sinistro: ");
        String doc = scan.nextLine();
        List<Sinistro> sinistros = new ArrayList<Sinistro>();
        for(Seguro seguro : seguradora.getListaSeguros()){
            sinistros.addAll(seguro.listaSinistros);
        }

        boolean removeu = sinistros.removeIf(sinistro -> {
            Condutor condutor = sinistro.getCondutor();
            return doc.equals(condutor.getCpf()); 
        });

        if(removeu)
            System.out.println("Sinistro removido com sucesso!");
        else
            System.out.println("Erro ao remover Sinistro!");
    }

    /** 
     * Método que exclui uma frota de um cliente pessoa juridica
     * @author Maria Eduarda Rocha*/
    public void excluirFrota(){
        Scanner scan = new Scanner(System.in);

        Seguradora seguradora = selecionarSeguradora();
        Cliente cliente = selecionarCliente(seguradora);
        if(cliente instanceof ClientePF)
            System.out.println("O cliente deve ser pessoa juridica!");
        else{
            System.out.println("Qual o code da frota a ser excluida? ");
            String code = scan.nextLine();
            if(((ClientePJ)cliente).atualizarFrota(code, null)){
                System.out.println("Frota removida com sucesso!");
            }
            else{
                System.out.println("Erro ao remover Frota!");
            }
        }
    }

    /** 
     * Método que exclui um seguro da seguradora
     * @author Maria Eduarda Rocha*/
    public void excluirSeguro(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o id do seguro que deseja excluir: ");
        int id = scan.nextInt();
        if( seguradora.cancelarSeguro(id)){
            System.out.println("Seguro removido com sucesso!");
        }
        else{
            System.out.println("Erro ao remover seguro!");
        }
    }

    /** 
     * Método que exclui um veiculo de um cliente da seguradora
     * @author Maria Eduarda Rocha*/
    public void excluirVeiculo(){
        Seguradora seguradora = selecionarSeguradora();
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o documento do cliente: ");
        String doc = scan.nextLine();
        Cliente cliente = seguradora.buscarCliente(doc);
        System.out.println("Digite a placa do veiculo: ");
        String placa = scan.nextLine();
        if(cliente instanceof ClientePJ){
            System.out.println("Qual o code da frota?");
            String code = scan.nextLine();
            List<Frota> frotas = ((ClientePJ)cliente).getListaFrota();
            for (Frota f : frotas) {
                if(f.getCode().equals(code)){
                    boolean removeu = f.listaVeiculos.removeIf(veiculo -> {return veiculo.getPlaca().equals(placa);});
                    if(removeu)
                        System.out.println("Veiculo removido com sucesso!");
                    else
                        System.out.println("Erro ao excluir veiculo");
                }
            }
        }
        else{
            boolean removeu = ((ClientePF)cliente).listaVeiculos.removeIf(veiculo -> {return veiculo.getPlaca().equals(placa);});
            if(removeu)
                System.out.println("Veiculo removido com sucesso!");
            else
                System.out.println("Erro ao excluir veiculo");
        }
    }

    /** 
     * Método que pede as informações do sinistro a ser gerado e o gera dentro do seguro
     * @author Maria Eduarda Rocha*/
    public void gerarSinistroDados() {
        Scanner scan = new Scanner(System.in);
        Seguro seguro = selecionarSeguro();
        System.out.println("Qual o documento do condutor? ");
        String doc = scan.nextLine();
        String email = "";
        do {
            System.out.print("Qual o email do sinistro? ");
            email = scan.nextLine();
        } while (!Validacao.validaEmail(email));
        System.out.println("Qual o endereco do sinistro? ");
        String endereco = scan.nextLine();
        seguro.gerarSinistro(email, endereco, doc);
        return;

    }

    /** 
     * Método que pede as informações do seguro a ser gerado e o gera dentro da seguradora
     * @author Maria Eduarda Rocha*/
    public void gerarSeguroDados(){
        Scanner scan = new Scanner(System.in);
        Seguradora seguradora = selecionarSeguradora();
        Cliente cliente = selecionarCliente(seguradora);
        System.out.println("Pessoa Física ou Juridica? (PF ou PJ)");
        String tipo = scan.nextLine();
        if (tipo.toUpperCase().equals("PJ")) {
            System.out.println("Digite o id do seguro: ");
            int id = scan.nextInt();
            String cnpj = "";
            do {
                System.out.println("Digite o CNPJ do cliente (XX.XXX.XXX/XXXX-XX): ");
                cnpj = scan.next();
            } while (!Validacao.validarCnpj(cnpj));

            LocalDate dataInicio = LocalDate.now();
            boolean erro = false;
            do {
                System.out.print("Digite a data de inicio: ");
                String dataFundacaoString = scan.next();
                try {
                    dataInicio = LocalDate.parse(dataFundacaoString);
                    erro = false;
                } catch (Exception e) {
                    erro = true;
                    System.out.println("Data digitada eh invalida");
                }
            } while (erro);

            LocalDate dataFim = LocalDate.now();
            erro = false;
            do {
                System.out.print("Digite a data de fim: ");
                String dataFundacaoString = scan.next();
                try {
                    dataFim = LocalDate.parse(dataFundacaoString);
                    erro = false;

                } catch (Exception e) {
                    erro = true;
                    System.out.println("Data digitada eh invalida");
                }
            } while (erro);

            Frota frota = selecionarFrota((ClientePJ)cliente);

            if (seguradora.gerarSeguro(new SeguroPJ(id, dataInicio, dataFim, seguradora, frota, (ClientePJ)cliente)))
                System.out.println("Seguro gerado com sucesso!");
            else
                System.out.println("Erro ao gerar cliente!");
        } else {
            System.out.print("Digite o id do seguro: ");
            int id = scan.nextInt();

            LocalDate dataInicio = LocalDate.now();
            boolean erro = false;
            do {
                System.out.print("Digite a data de inicio: ");
                String dataFundacaoString = scan.next();
                try {
                    dataInicio = LocalDate.parse(dataFundacaoString);
                    erro = false;

                } catch (Exception e) {
                    erro = true;
                    System.out.println("Data digitada eh invalida");
                }
            } while (erro);

            LocalDate dataFim = LocalDate.now();
            erro = false;
            do {
                System.out.print("Digite a data de fim: ");
                String dataFundacaoString = scan.next();
                try {
                    dataFim = LocalDate.parse(dataFundacaoString);
                    erro = false;

                } catch (Exception e) {
                    erro = true;
                    System.out.println("Data digitada eh invalida");
                }
            } while (erro);

            Veiculo veiculo = selecionarVeiculo((ClientePF)cliente);

            if (seguradora.gerarSeguro(new SeguroPF(id, dataInicio, dataFim, seguradora, veiculo, (ClientePF)cliente)))
                System.out.println("Seguro gerado com sucesso!");
            else
                System.out.println("Erro ao gerar seguro!");
        }
    }

    /** 
     * Método que calcula e mostra na tela a receita de uma seguradora
     * @author Maria Eduarda Rocha*/
    public void calcularReceita() {
        Seguradora seguradora = selecionarSeguradora();
        double receita = seguradora.calcularReceita();
        System.out.printf("Valor da receita: %f\n", receita);
    }

    /** 
     * Método que adiciona um cliente dentro de uma seguradora
     * @author Maria Eduarda Rocha*/
    public void cadastrarCliente() {
        Seguradora seguradora = selecionarSeguradora();

        Scanner scan = new Scanner(System.in);
        System.out.println("Pessoa Física ou Juridica? (PF ou PJ)");
        String tipo = scan.nextLine();
        if (tipo.toUpperCase().equals("PJ")) {
            String nome = "";
            do {
                System.out.print("Digite o nome do cliente: ");
                nome = scan.nextLine();
            } while (!Validacao.validaNome(nome));

            System.out.print("Digite o endereço do cliente: ");
            String endereco = scan.nextLine();

            String cnpj = "";
            do {
                System.out.print("Digite o CNPJ do cliente (XX.XXX.XXX/XXXX-XX): ");
                cnpj = scan.nextLine();
            } while (!Validacao.validarCnpj(cnpj));

            LocalDate dataFundacao = LocalDate.now();
            boolean erro = false;
            do {
                System.out.print("Digite a data de fundação: ");
                String dataFundacaoString = scan.nextLine();
                try {
                    dataFundacao = LocalDate.parse(dataFundacaoString);
                    erro = false;
                } catch (Exception e) {
                    erro = true;
                    System.out.println("Data digitada eh invalida");
                }
            } while (erro);

            System.out.print("Digite a quantidade de funcionários do cliente: ");
            int qtdFuncionarios = scan.nextInt();
            System.out.print("Digite o telefone do cliente: ");
            String telefone = scan.next();
            String email = "";
            do {
                System.out.print("Digite o email do cliente: ");
                email = scan.next();
            } while (!Validacao.validaEmail(email));
            if (seguradora.cadastrarCliente(new ClientePJ(cnpj, dataFundacao, nome, endereco, telefone, email, qtdFuncionarios)))
                System.out.println("Cliente adicionado com sucesso!");
            else
                System.out.println("Erro ao cadastrar cliente!");
        } else {
            String nome = "";
            do {
                System.out.print("Digite o nome do cliente: ");
                nome = scan.nextLine();
            } while (!Validacao.validaNome(nome));

            System.out.print("Digite o endereço do cliente: ");
            String endereco = scan.nextLine();

            String cpf = "";
            do {
                System.out.print("Digite o CPF (XXX.XXX.XXX-XX): ");
                cpf = scan.nextLine();
            } while (!Validacao.validarCpf(cpf));

            System.out.print("Digite a data de nascimento: ");
            String data = scan.nextLine();
            LocalDate dataNascimento = LocalDate.parse(data);

            System.out.print("Digite a escolaridade: ");
            String educacao = scan.nextLine();

            System.out.print("Digite o gênero do cliente: ");
            String genero = scan.nextLine();

            System.out.print("Digite o telefone do cliente: ");
            String telefone = scan.nextLine();

            String email = "";
            do {
                System.out.print("Digite o email do cliente: ");
                email = scan.nextLine();
            } while (!Validacao.validaEmail(email));

            if (seguradora.cadastrarCliente(new ClientePF(cpf, dataNascimento, nome, endereco, educacao, email, genero,
                    telefone)))
                System.out.println("Cliente adicionado com sucesso!");
            else
                System.out.println("Erro ao adicionar cliente!");
        }

    }
}
