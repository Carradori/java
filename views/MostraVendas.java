package views;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
import styles.Estilos;

import venda.dao.VendaDAO;

public class MostraVendas extends JPanel {

    private JScrollPane jpane;
    private JLabel lblNome, lblCpf, lblData, lblSair;
    private JTextField txtNome;
    private JFormattedTextField txtData, txtCpf;
    private MaskFormatter mascaraData, mascaraCpf;
    private JButton btnAlterar, btnPesquisa;
    private JTable table;

    public MostraVendas() {
        iniComponents();
        iniEvents();
        this.setBackground(new Color(135, 161, 204));
        lerTabela();
    }

    private void iniComponents() {
        setLayout(null);
        //ESTILO LABEL SAIR
            lblSair = new JLabel("X");
            Estilos.StyleLbl(lblSair);
            lblSair.setBounds(580, 5, 25, 25);
        //ESTILO TABLE
            table = new JTable();
            Estilos.StyleTable(table);
        jpane = new JScrollPane();
        lblCpf = new JLabel("Cpf");
        lblCpf.setBounds(150, 10, 50, 20);
        lblData = new JLabel("Data nascimento");
        lblData.setBounds(290, 10, 100, 20);
        lblNome = new JLabel("Nome");
        lblNome.setBounds(10, 10, 50, 20);
        txtNome = new JTextField();
        txtNome.setBounds(10, 30, 100, 20);
        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(420, 30, 100, 20);
        btnPesquisa = new JButton("Pesquisar");
        btnPesquisa.setBounds(420, 60, 100, 20);
        txtData = new JFormattedTextField(mascaraData);
        txtData.setBounds(290, 30, 100, 20);
        txtCpf = new JFormattedTextField(mascaraCpf);
        txtCpf.setBounds(150, 30, 100, 20);
        jpane.setViewportView(table);
        jpane.setBounds(0, 175, 600, 200);

        add(jpane);
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
        
        /*btnPesquisa.addActionListener(new ActionListener() {
			
         @Override
         public void actionPerformed(ActionEvent arg0) {
         lerNome(txtNome.getText());
         txtNome.setText("");
         }
         });*/
        btnAlterar.addActionListener((ActionEvent e) -> {
            /*if(table.getSelectedRow() != -1){
            Cliente c = new Cliente();
            ClienteDAO cdao = new ClienteDAO();
            c.setNome_cliente(txtNome.getText());
            c.setCpf_cliente(txtCpf.getText());
            c.setData_nasc(txtData.getText());
            c.setId_cliente((int)table.getValueAt(table.getSelectedRow(),0));
            cdao.atualizar(c);
            txtCpf.setText("");
            txtData.setText("");
            txtNome.setText("");
            lerTabela();
            }else{
            JOptionPane.showMessageDialog(null, "Selecione uma linha");
            }*/
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
        String[] colunas = {"Quantidade", "Cliente", "Produto", "Preço", "Total"};
        String[][] objetos = {};
        DefaultTableModel modelo = (new DefaultTableModel(objetos, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setModel(modelo);
        modelo.setNumRows(0);
        VendaDAO vdao = new VendaDAO();

        vdao.ler().stream().forEach((v) -> {
            modelo.addRow(new Object[]{
                v.getQuantidade_prod(),
                v.getNome_cliente(),
                v.getNome_produto(),
                v.getPreco_produto(),
                v.getTotal()
            });
        });
    }
}