import java.util.Arrays;
import java.util.List;

public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    TRANSFERIR_SEGURO(5),
    CALCULAR_RECEITA_SEGURO(6),
    SAIR (0),
    CADASTRAR_CLIENTE(11),
    CADASTRAR_VEICULO(12),
    CADASTRAR_SEGURADORA(13),
    LISTAR_CLIENTE_POR_SEGURADORA(21),
    LISTAR_SINISTROS_POR_SEGURADORA(22),
    LISTAR_SINISTRO_CLIENTE(23),
    LISTAR_VEICULO_CLIENTE(24),
    LISTAR_VEICULO_SEGURADORA(25),
    EXCLUIR_CLIENTE(31),
    EXCLUIR_VEICULO(32),
    EXCLUIR_SINISTRO(33),
    VOLTAR(7);
    
    private final int valor;

    MenuOperacoes(int opcao){
        valor = opcao;
    }

    public int getValor(){
        return valor;
    }

    /** 
     * Método que que retorna qual a operacao dentro do enum baseado no inteiro que o representa
     * @author Maria Eduarda Rocha
     * @param int - valor inteiro da operação escolhida
     * @return MenuOperacoes - objeto que representa a operacao mandada como parametro*/
    public static MenuOperacoes acharOperacao(int opcao){
        List<MenuOperacoes> operacoes = Arrays.asList(MenuOperacoes.values());
        for(MenuOperacoes operacao : operacoes){
            if(operacao.getValor() == opcao)
                return operacao;
        }
        return null;
    }
}
