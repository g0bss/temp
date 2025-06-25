public class ServicoAutenticacao implements LoginService {

    private Usuario currentUser;

    @Override // Validação das credenciais conforme solicitado pelo professor
    public boolean login(String username, String password) {
        
        if ("denys".equals(username) && "poo123456".equals(password)) {
            this.currentUser = new Usuario(username, password);
            SessaoUsuario.setCurrentUser(this.currentUser); // Atualiza a sessão global
            return true;
        }
        this.currentUser = null;
        SessaoUsuario.setCurrentUser(null);
        return false;
    }

    @Override // Implementa o método logout da interface LoginService, responsável por encerrar a sessão do usuário
    public void logout() {
        this.currentUser = null;
        SessaoUsuario.setCurrentUser(null);
    }

    @Override // Implementa o método getCurrentUser da interface LoginService, retornando o usuário autenticado atualmente
    public Usuario getCurrentUser() {
        return this.currentUser;
    }
}