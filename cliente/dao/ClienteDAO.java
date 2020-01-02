package cliente.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import cliente.bean.Cliente;
import connection.ConnectionFactory;

public class ClienteDAO {

    public void adicionar(Cliente c) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("INSERT INTO tb_cliente (nome_cliente, cpf_cliente, data_nasc) VALUES (?, ?, ?)");
            stmt.setString(1, c.getNome_cliente());
            stmt.setString(2, c.getCpf_cliente());
            stmt.setString(3, c.getData_nasc());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante o cadastro");
        }finally{
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }

    public void atualizar(Cliente c) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement("UPDATE tb_cliente SET nome_cliente = ?, cpf_cliente = ?, data_nasc = ? WHERE id_cliente = ?");
            stmt.setString(1, c.getNome_cliente());
            stmt.setString(2, c.getCpf_cliente());
            stmt.setString(3, c.getData_nasc());
            stmt.setInt(4, c.getId_cliente());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a alteração");
        }finally{
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }
    
    public void remover(Cliente c) {
        Connection conexao = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        PreparedStatement stmt2 = null;
        try {
            stmt = conexao.prepareStatement("DELETE FROM tb_venda WHERE id_cliente = ?");
            stmt2 = conexao.prepareStatement("DELETE FROM tb_cliente WHERE id_cliente = ?");
            stmt.setInt(1, c.getId_cliente());
            stmt2.setInt(1, c.getId_cliente());
            stmt.executeUpdate();
            stmt2.executeUpdate();
            JOptionPane.showMessageDialog(null, "Removido com sucesso");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a exclus�o");
        }finally{
            ConnectionFactory.fecharConexao(conexao, stmt);
        }
    }

    public List<Cliente> ler() {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_cliente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                cliente.setCpf_cliente(rs.getString("cpf_cliente"));
                cliente.setData_nasc(rs.getString("data_nasc"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a pesquisa dos clientes");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
        return clientes;
    }

    public List<Cliente> pesquisaNome(String nome) {
        Connection con = ConnectionFactory.conectar();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tb_cliente WHERE nome_cliente LIKE ?");
            stmt.setString(1, "%" + nome + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setId_cliente(rs.getInt("id_cliente"));
                cliente.setNome_cliente(rs.getString("nome_cliente"));
                cliente.setCpf_cliente(rs.getString("cpf_cliente"));
                cliente.setData_nasc(rs.getString("data_nasc"));
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Ops! Ocorreu um erro durante a pesquisa");
        } finally {
            ConnectionFactory.fecharConexao(con, stmt, rs);
        }
        return clientes;
    }
}