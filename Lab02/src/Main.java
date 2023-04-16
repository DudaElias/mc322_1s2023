public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("Teste", "123.456.789-78", "29-08-2003", 19, "Rua de Teste");
        System.out.println(cliente.toString());
        Seguradora seguradora = new Seguradora("Teste", "2356-8974", "teste@gmail.com", "Rua de Teste");
        Sinistro sinistro = new Sinistro("14-03-2023", "Rua Teste");
        Veiculo veiculo = new Veiculo("abc1234", "Honda", "FIT");
    }
}
