import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AcessoSistema extends JFrame {

    // Componentes da interface
    private JLabel labelUsuario;
    private JTextField campoUsuario;
    private JLabel labelSenha;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCancelar;
    private JButton botaoNovoUsuario;

    // Atributo para o serviço de login, declarado como a interface
    private LoginService servicoLogin;

    public AcessoSistema() {
        // Configurações da janela
        super("Acesso ao Sistema"); // Título da janela
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 250); // Tamanho da janela
        this.setLocationRelativeTo(null); // Centraliza a janela na tela
        this.setLayout(null); // Usei layout nulo para posicionar componentes manualmente

        //POLIMORFISMO:
        // A variável 'servicoLogin' é do tipo da interface (LoginService), mas recebe uma instância de uma classe que a implementa (ServicoAutenticacao).
        // O código a seguir funcionará com qualquer outra classe que implemente LoginService, sem precisar ser alterado. Isso demonstra a flexibilidade do polimorfismo.
        this.servicoLogin = new ServicoAutenticacao();

        // Inicializa e adiciona os componentes na tela
        inicializarComponentes();
        adicionarListeners();
    }

    private void inicializarComponentes() {
        labelUsuario = new JLabel("Usuário:");
        labelUsuario.setBounds(50, 30, 80, 25);
        this.add(labelUsuario);

        campoUsuario = new JTextField();
        campoUsuario.setBounds(130, 30, 200, 25);
        this.add(campoUsuario);

        labelSenha = new JLabel("Senha:");
        labelSenha.setBounds(50, 70, 80, 25);
        this.add(labelSenha);

        campoSenha = new JPasswordField();
        campoSenha.setBounds(130, 70, 200, 25);
        this.add(campoSenha);

        botaoEntrar = new JButton("Entrar");
        botaoEntrar.setBounds(50, 140, 90, 30);
        this.add(botaoEntrar);

        botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(150, 140, 90, 30);
        this.add(botaoCancelar);

        botaoNovoUsuario = new JButton("Novo Usuário");
        botaoNovoUsuario.setBounds(250, 140, 110, 30);
        this.add(botaoNovoUsuario);
    }

    private void adicionarListeners() {
        // Ação do botão "Entrar"
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String usuario = campoUsuario.getText();
                    String senha = new String(campoSenha.getPassword());

                    boolean sucesso = servicoLogin.login(usuario, senha);

                    if (sucesso) {
                        // Busca o nome do usuário da sessão para a mensagem
                        String nomeUsuario = SessaoUsuario.getCurrentUser().getUsername();
                        JOptionPane.showMessageDialog(AcessoSistema.this,
                                "Bem-vindo, " + nomeUsuario + "!",
                                "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(AcessoSistema.this,
                                "Login inválido!",
                                "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    // Tratamento de exceção
                    JOptionPane.showMessageDialog(AcessoSistema.this,
                            "Ocorreu um erro inesperado: " + ex.getMessage(),
                            "Erro Crítico",
                            JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Este bloco é executado sempre, tendo o login dado certo ou não.
                    campoSenha.setText("");
                }
            }
        });

        // Ação do botão "Cancelar"
        botaoCancelar.addActionListener(e -> {
            System.exit(0); // Encerra a aplicação
        });

        // Ação do botão "Novo Usuário"
        botaoNovoUsuario.addActionListener(e -> {
            JOptionPane.showMessageDialog(AcessoSistema.this,
                    "Em desenvolvimento.",
                    "Mensagem",
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public static void main(String[] args) {
        // Garante que a GUI seja executada na Thread de Despacho de Eventos (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AcessoSistema telaLogin = new AcessoSistema();
                telaLogin.setVisible(true);
            }
        });
    }
}