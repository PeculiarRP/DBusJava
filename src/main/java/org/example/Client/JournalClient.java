package org.example.Client;

import org.example.Server.JournalServer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class JournalClient extends JFrame {

    private JPanel rootPanel;
    private JLabel testLabel;
    private JTable studentTable;
    private JButton deleteBtn;

    private JButton addBtn;
    private JScrollPane sheet;
    final JTextField studentName = new JTextField();
    final JTextField studentSurname = new JTextField();
    final JTextField studentClass = new JTextField();
    private final Object[] message = {
            "Имя студента:", studentName,
            "Фамилия студента:", studentSurname,
            "Класс студента", studentClass
    };
    private String[] data;
    private DefaultTableModel defModel;

    private final JournalServer server;
    private final Object[] options = {
        "Подтвердить",
        "Отмена"
    };
    private String[] columns = {
            "ID",
            "Имя",
            "Фамилия",
            "Класс"
    };

    private String[] getSelectRow(){
        Vector<String> vector = defModel.getDataVector()
                .elementAt(studentTable.convertRowIndexToModel(studentTable.getSelectedRow()));
        return vector.toArray(new String[vector.size()]);
    }

    public void setTestLabel(String text){
        testLabel.setText(text);
    }

    private void addBtnAction(){
        int n = JOptionPane.showOptionDialog(this,
                message,
                "Добавить пользователя",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
        if (n==0) {
            server.AddStudent(studentName.getText(), studentSurname.getText(), studentClass.getText());
            refreshTable();
        }
    }

    private void deleteBtnAction(){
        if(studentTable.getSelectedRow() != -1) {
            String[] selectStudent = getSelectRow();
            int n = JOptionPane.showOptionDialog(this, "Вы уверены, что хотите удалить ученика" +
                    "\n\r" + selectStudent[0] + " " + selectStudent[1] + " " + selectStudent[2] + " из " + selectStudent[3] + "?",
                    "Подтверждение",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if(n==0){
                server.DeleteStudent(selectStudent[0]);
                refreshTable();
            }
        }
    }

    public void refreshTable(){
        cleanTable();
        this.data = server.GetStudents();
        for (String item: data){
            defModel.addRow(item.split(":"));
        }
    }

    private void cleanTable(){
        defModel.getDataVector().removeAllElements();
    }

    public JournalClient(JournalServer server){
        this.server = server;
        setContentPane(rootPanel);
        defModel = new DefaultTableModel();
        defModel.setColumnIdentifiers(columns);
        studentTable.setModel(defModel);
        setSize(550,360);
        setLocationRelativeTo(null);
        refreshTable();
        deleteBtn.addActionListener(e -> deleteBtnAction());
        addBtn.addActionListener(e -> addBtnAction());
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
