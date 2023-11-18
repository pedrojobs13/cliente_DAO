package service;

import dao.ClienteDAO;
import java.util.List;
import model.Cliente;

public class ClienteService {
  private ClienteDAO clienteDAO;

  public ClienteService(ClienteDAO clienteDAO) {
    this.clienteDAO = clienteDAO;
  }

  public void adicionarCliente(String nome, String email) {
    Cliente cliente = new Cliente(nome, email);
    clienteDAO.inserir(cliente);
  }

  public Cliente buscarPorId(int id) {
    return clienteDAO.buscarPorId(id);
  }

  public List<Cliente> listarTodosCliente() {
    return clienteDAO.listarTodos();
  }

  public void atualizarCliente(Cliente cliente, int id) {
    clienteDAO.atualizar(cliente, id);
  }

  public void apagarCliente(int id) {
    clienteDAO.deletar(id);
  }
}
