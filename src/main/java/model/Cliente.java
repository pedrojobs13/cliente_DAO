package model;

import java.util.Objects;

public class Cliente {

  private static int count = 1;
  private int id;
  private String nome;
  private String email;

  public Cliente(String nome, String email) {
    setId(count++);
    this.nome = nome;
    this.email = email;
  }

  public int getId() {
    return id;
  }
  private void setId(int id){
    this.id = id;
  }
  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return id == cliente.id;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "model.Cliente{" +
        "id=" + id +
        ", nome='" + nome + '\'' +
        ", email='" + email + '\'' +
        '}';
  }
}
