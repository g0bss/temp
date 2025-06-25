public class SessaoUsuario {

    private static Usuario currentUser;

    // Construtor privado para impedir a instanciação da classe
    // isso garante que a classe seja usada apenas como um "ponto global"
    private SessaoUsuario() {}

    public static void setCurrentUser(Usuario user) {
        currentUser = user;
    }

    public static Usuario getCurrentUser() {
        return currentUser;
    }
}