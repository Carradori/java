package styles;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

public class Estilos {
    //ESTILO LABELS
        public static void StyleLbl(JLabel lbl){
            lbl.setFont(new Font("Arial", Font.BOLD, 20));
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbl.setForeground(Color.WHITE);
        }
        public static void StyleLblN(JLabel lbl){
            lbl.setFont(new Font("Helvetica", Font.BOLD, 15));
            lbl.setForeground(Color.WHITE);
        }
        public static void StyleLblCheck(JLabel lbl){
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbl.setFont(new Font("Helvetica", Font.PLAIN, 16));
            lbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    //ESTILO TABLE  
        public static void StyleTable(JTable table){
            table.setBackground(new Color(135, 161, 204));
            table.setForeground(Color.WHITE);
            table.setFont(new Font("Arial", Font.PLAIN, 16));
        }
    //ESTILO DOS BOTÕES
        public static void StyleBtn(JButton btn){
            btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btn.setForeground(Color.WHITE);
            btn.setBackground(new Color(235, 64, 52));
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.setBorder(null);
            btn.setFocusPainted(false);
        }
        public static void StyleBtnIn(JButton btn){
            btn.setBackground(new Color(247, 102, 92));
        }
        public static void StyleBtnOut(JButton btn){
            btn.setBackground(new Color(235, 64, 52));
        }
}