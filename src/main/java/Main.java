import dao.ClienteDAOSqlite;
import service.ClienteService;

public class Main {

  public static void main(String[] args) {
    ClienteDAOSqlite clienteDAOSqlite = new ClienteDAOSqlite();

    ClienteService service = new ClienteService(clienteDAOSqlite);

//        service.adicionarCliente("Pedro", "pedro@pedro.com");
//        service.listarTodosCliente();

    //service.buscarPorId(20);
  }
}
