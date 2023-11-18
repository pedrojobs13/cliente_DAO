package dao;

import java.util.List;
import model.Cliente;

public interface ClienteDAO {

  void inserir(Cliente cliente);

  Cliente buscarPorId(int id);

  List<Cliente> listarTodos();

  void atualizar(Cliente cliente, int id);

  void deletar(int id);
}
