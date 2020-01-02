package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import cliente.bean.Cliente;
import cliente.dao.ClienteDAO;
import styles.Estilos;

public class MostrarClientes extends JPanel {

    private JScrollPane jpane;
    private JLabel lblNome, lblCpf, lblData, lblSair;
    private JTextField txtNome;
    private JFormattedTextField txtData, txtCpf;
    private MaskFormatter mascaraData, mascaraCpf;
    private JButton btnAlterar, btnPesquisa, btnExclui;
    private JTable table;

    public MostrarClientes() {
        iniComponents();
        iniEvents();
        lerTabela();
        this.setBackground(new Color(135, 161, 204));
    }

    private void iniComponents() {
        setLayout(null);
        //ESTILO TODAS AS LABELS
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
            lblCpf = new JLabel("Cpf");
            Estilos.StyleLblN(lblCpf);
            lblCpf.setBounds(150, 10, 50, 20);
            lblData = new JLabel("Data nascimento");
            Estilos.StyleLblN(lblData);
            lblData.setBounds(290, 10, 120, 20);
            lblNome = new JLabel("Nome");
            Estilos.StyleLblN(lblNome);
            lblNome.setBounds(10, 10, 50, 20);
        //ESTILO TODOS OS BOTÕES
            btnAlterar = new JButton("Alterar");
            Estilos.StyleBtn(btnAlterar);
            btnAlterar.setBounds(420, 30, 100, 20);
            btnPesquisa = new JButton("Pesquisar");
            Estilos.StyleBtn(btnPesquisa);
            btnPesquisa.setBounds(420, 55, 100, 20);
            btnExclui = new JButton("Excluir");
            Estilos.StyleBtn(btnExclui);
            btnExclui.setBounds(420, 80, 100, 20);
        //ESTILO DA TABLE
            table = new JTable();
            Estilos.StyleTable(table);
        jpane = new JScrollPane();
        jpane.setViewportView(table);
        jpane.setBounds(0, 175, 600, 200);
        txtNome = new JTextField();
        txtNome.setBounds(10, 30, 100, 20);
        try {
            mascaraData = new MaskFormatter("####-##-##");
            mascaraCpf = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        txtData = new JFormattedTextField(mascaraData);
        txtData.setBounds(290, 30, 100, 20);
        txtCpf = new JFormattedTextField(mascaraCpf);
        txtCpf.setBounds(150, 30, 100, 20);
        
        add(jpane);
        add(lblNome);
        add(lblCpf);
        add(lblData);
        add(txtNome);
        add(txtCpf);
        add(txtData);
        add(btnAlterar);
        add(btnPesquisa);
        add(btnExclui);
        add(lblSair);
    }

    private void iniEvents() {

        lblSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        btnAlterar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnAlterar);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnAlterar);
            }
        });

        btnPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnPesquisa);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnPesquisa);
            }
        });

        btnExclui.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnExclui);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnExclui);
            }
        });

        btnPesquisa.addActionListener((ActionEvent arg0) -> {
            if (table.getSelectedRow() != -1) {
                lerNome(txtNome.getText());
                txtNome.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        });

        btnAlterar.addActionListener((ActionEvent e) -> {
            if (table.getSelectedRow() != -1) {
                Cliente c = new Cliente();
                ClienteDAO cdao = new ClienteDAO();
                c.setNome_cliente(txtNome.getText());
                c.setCpf_cliente(txtCpf.getText());
                c.setData_nasc(txtData.getText());
                c.setId_cliente((int) table.getValueAt(table.getSelectedRow(), 0));
                cdao.atualizar(c);

                txtCpf.setText("");
                txtData.setText("");
                txtNome.setText("");

                lerTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        });
        btnExclui.addActionListener((ActionEvent e) -> {
            if (table.getSelectedRow() != -1) {
                Cliente c = new Cliente();
                ClienteDAO cdao = new ClienteDAO();
                c.setId_cliente((int) table.getValueAt(table.getSelectedRow(), 0));
                cdao.remover(c);
                txtCpf.setText("");
                txtData.setText("");
                txtNome.setText("");
                lerTabela();
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }
        });

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table.getSelectedRow() != -1) {
                    txtNome.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
                    txtCpf.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
                    txtData.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione uma linha");
                }
            }
        });
    }

    @SuppressWarnings({"serial"})
    public void lerTabela() {
        String[] colunas = {"Id", "Nome", "CPF", "Data de nascimento"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        ClienteDAO cdao = new ClienteDAO();

        cdao.ler().stream().forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getId_cliente(),
                c.getNome_cliente(),
                c.getCpf_cliente(),
                c.getData_nasc()
            });
        });
    }

    public void lerNome(String nome) {
        String[] colunas = {"Id", "Nome", "CPF", "Data de nascimento"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        ClienteDAO cdao = new ClienteDAO();

        cdao.pesquisaNome(nome).stream().forEach((c) -> {
            modelo.addRow(new Object[]{
                c.getId_cliente(),
                c.getNome_cliente(),
                c.getCpf_cliente(),
                c.getData_nasc()
            });
        });
    }
}