package views;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import cliente.bean.Cliente;
import cliente.dao.ClienteDAO;
import java.awt.Color;
import styles.Estilos;

public class CadastroCliente extends JPanel {

    private JLabel lblNome, lblCpf, lblData, lblSair;
    private JTextField txtNome;
    private JFormattedTextField txtData, txtCpf;
    private MaskFormatter mascaraData, mascaraCpf;
    private JButton btnCadastrar;

    public CadastroCliente() {
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
            lblNome = new JLabel("Nome");
            Estilos.StyleLblN(lblNome);
            lblNome.setBounds(10, 10, 50, 20);
            lblCpf = new JLabel("Cpf");
            Estilos.StyleLblN(lblCpf);
            lblCpf.setBounds(10, 50, 80, 20);
            lblData = new JLabel("Data nascimento");
            Estilos.StyleLblN(lblData);
            lblData.setBounds(10, 110, 120, 20);
        //ESTILO BOTÃO
            btnCadastrar = new JButton("Cadastrar");
            Estilos.StyleBtn(btnCadastrar);
            btnCadastrar.setBounds(180, 250, 200, 35);
        try {
            mascaraData = new MaskFormatter("####/##/##");
            mascaraCpf = new MaskFormatter("###.###.###-##");
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        txtNome = new JTextField();
        txtNome.setBounds(10, 30, 300, 20);
        txtData = new JFormattedTextField(mascaraData);
        txtData.setBounds(10, 130, 100, 20);
        txtCpf = new JFormattedTextField(mascaraCpf);
        txtCpf.setBounds(10, 80, 300, 20);

        add(lblNome);
        add(txtNome);
        add(lblCpf);
        add(txtCpf);
        add(lblData);
        add(txtData);
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
            if (txtCpf.getText().isEmpty() || txtData.getText().isEmpty() || txtNome.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Insira os dados corretamente!");
            } else {
                Cliente cliente = new Cliente();
                ClienteDAO cDAO = new ClienteDAO();
                cliente.setNome_cliente(txtNome.getText());
                cliente.setCpf_cliente(txtCpf.getText());
                cliente.setData_nasc(txtData.getText());
                cDAO.adicionar(cliente);

                txtCpf.setText("");
                txtData.setText("");
                txtNome.setText("");
            }
        });
    }
}