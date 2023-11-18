package dao;

import dao.ClienteDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import model.Cliente;

public class ClienteDAOSqlite implements ClienteDAO {
  private Connection connection;

  List<Cliente> clientes = new ArrayList<>();

  public ClienteDAOSqlite() {
    try {
      connection =
          DriverManager.getConnection(
              "jdbc:sqlite:/home/pedro/Documentos/javaProjects/projetos/sqllite/test.db");

      String sql =
          "CREATE TABLE IF NOT EXISTS cliente (\n"
              + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
              + "	nome text NOT NULL,\n"
              + "	email text NOT NULL\n"
              + ");";

      Statement stmt = connection.createStatement();
      stmt.execute(sql);

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void inserir(Cliente cliente) {
    Objects.requireNonNull(cliente);

    String sql = "INSERT INTO cliente(nome, email) VALUES (?, ?)";

    try {
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, cliente.getNome());
      pstmt.setString(2, cliente.getEmail());
      pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    //    clientes.add(cliente);
  }

  @Override
  public Cliente buscarPorId(int id) {

    String sql = "SELECT *" + "FROM cliente WHERE id = ?";

    try {
      Cliente clienteEncontrado = null;
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setDouble(1, id);
      ResultSet rs = pstmt.executeQuery();
      while (rs.next()) {
        clienteEncontrado = new Cliente(rs.getString("nome"), rs.getString("email"));
        System.out.println(
            rs.getInt("id") + "\t" + rs.getString("nome") + "\t" + rs.getString("email"));
      }
      if (clienteEncontrado == null) {
        throw new IllegalArgumentException("Cliente não encontrado");
      }
      return clienteEncontrado;
    } catch (SQLException e) {
      throw new IllegalArgumentException();
    }
  }

  @Override
  public List<Cliente> listarTodos() {
    String sql = "SELECT * FROM cliente ";

    try {
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(
            rs.getInt("id") + "\t" + rs.getString("nome") + "\t" + rs.getString("email"));
      }
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    return Collections.unmodifiableList(clientes);
  }

  @Override
  public void atualizar(Cliente cliente, int id) {
    String sql = "UPDATE cliente SET nome = ? , " + "email = ? " + "WHERE id = ?";

    try {
      PreparedStatement pstmt = connection.prepareStatement(sql);
      pstmt.setString(1, cliente.getNome());
      pstmt.setString(2, cliente.getEmail());
      pstmt.setInt(3, id);
      pstmt.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Não foi possível atualizar objeto, pois o mesmo não existe");
    }
  }

  @Override
  public void deletar(int id) {
    String sql = "DELETE FROM cliente WHERE id = ?";

    try {
      PreparedStatement pstmt = connection.prepareStatement(sql);

      pstmt.setInt(1, id);
      pstmt.executeUpdate();

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }

    clientes.remove(buscarPorId(id));
  }
}
