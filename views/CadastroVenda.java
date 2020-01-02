package views;

import cliente.bean.Cliente;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import produto.bean.Produto;
import styles.Estilos;
import venda.bean.Venda;
import venda.dao.VendaDAO;

public class CadastroVenda extends JPanel {

    private JLabel lblQtd, lblIdC, lblIdP, lblCkeckIDC, lblCkeckIDP, lblSair;
    private JTextField txtNome, txtIdC, txtIdP;
    private JButton btnCadastrar;

    public CadastroVenda() {
        iniciarComponentes();
        iniciarEventos();
        this.setBackground(new Color(135, 161, 204));
    }

    private void iniciarComponentes() {
        setLayout(null);
        //ESTILO TODAS LABELS
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
            lblCkeckIDC = new JLabel("<html><u>Confira o id do cliente</html></u>");
            Estilos.StyleLblCheck(lblCkeckIDC);
            lblCkeckIDC.setBounds(120, 80, 170, 23);
            lblCkeckIDP = new JLabel("<html><u>Confira o id do produto</html></u>");
            Estilos.StyleLblCheck(lblCkeckIDP);
            lblCkeckIDP.setBounds(120, 130, 170, 23);
            lblQtd = new JLabel("Qtd");
            Estilos.StyleLblN(lblQtd);
            lblQtd.setBounds(10, 10, 50, 20);
            lblIdC = new JLabel("Id Cliente");
            Estilos.StyleLblN(lblIdC);
            lblIdC.setBounds(10, 60, 80, 20);
            lblIdP = new JLabel("Id Produto");
            Estilos.StyleLblN(lblIdP);
            lblIdP.setBounds(10, 110, 100, 20);
        txtNome = new JTextField();
        txtNome.setBounds(10, 30, 100, 20);
        txtIdC = new JTextField();
        txtIdC.setBounds(10, 80, 100, 20);
        txtIdP = new JTextField();
        txtIdP.setBounds(10, 130, 100, 20);
        //ESTILO BOTÃO
            btnCadastrar = new JButton("Cadastrar");
            Estilos.StyleBtn(btnCadastrar);
            btnCadastrar.setBounds(180, 250, 200, 35);

        add(lblCkeckIDC);
        add(lblCkeckIDP);
        add(lblQtd);
        add(txtNome);
        add(lblIdC);
        add(txtIdC);
        add(lblIdP);
        add(txtIdP);
        add(btnCadastrar);
        add(lblSair);
    }

    private void iniciarEventos() {

        lblSair.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {}
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {}
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                System.exit(0);
            }
        });

        btnCadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnIn(btnCadastrar);
            }
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                Estilos.StyleBtnOut(btnCadastrar);
            }
        });

        btnCadastrar.addActionListener((ActionEvent e) -> {
            if (txtIdC.getText().isEmpty() || txtIdP.getText().isEmpty() || txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira os dados corretamente!");
            } else {
                Venda venda = new Venda();
                VendaDAO vendao = new VendaDAO();
                venda.setId_cliente(Integer.parseInt(txtIdC.getText()));
                venda.setId_produto(Integer.parseInt(txtIdP.getText()));
                venda.setQuantidade_prod(Integer.parseInt(txtNome.getText()));
                vendao.adicionar(venda);

                txtIdC.setText("");
                txtIdP.setText("");
                txtNome.setText("");
            }
        });
        lblCkeckIDC.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtIdC.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insira o ID do cliente para verificar");
                } else {
                    Cliente c = new Cliente();
                    VendaDAO vdao = new VendaDAO();
                    c.setId_cliente(Integer.parseInt(txtIdC.getText()));
                    vdao.pesquisaIDCliente(Integer.parseInt(txtIdC.getText()));
                }
            }
        });
        lblCkeckIDP.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (txtIdP.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Insira o ID do produto para verificar");
                } else {
                    Produto p = new Produto();
                    VendaDAO vdao = new VendaDAO();
                    p.setId(Integer.parseInt(txtIdP.getText()));
                    vdao.pesquisaIDProduto(Integer.parseInt(txtIdP.getText()));
                }
            }
        });
    }
}