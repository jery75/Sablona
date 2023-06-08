package Gui;

import models.SablonaProPrvek;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class NewItemDialog extends JDialog {
    private MainFrame parent;
    private JTextField inputName,input1, input2;
    public NewItemDialog(MainFrame parent){
        super(parent,"Zadání položky",true);
        this.parent=parent;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initGui();
        pack();
        setVisible(true);
    }
    private void initGui(){
        JPanel mainPanel=new JPanel(new GridLayout(4,2));
        mainPanel.add(new JLabel("Název"));
        inputName=new JTextField(15);
        mainPanel.add(inputName);

        mainPanel.add(new JLabel("info1"));
        input1=new JTextField(15);
        mainPanel.add(input1);

        mainPanel.add(new JLabel("info2"));
        input2=new JTextField(15);
        mainPanel.add(input2);

        mainPanel.add(new JPanel());

        JButton btnOk =new JButton("Ok");
        mainPanel.add(btnOk);
        btnOk.addActionListener(e -> {
            var validationMessages=validateInputs();
            if(validationMessages.size()>0){
                StringBuilder errorMsg=new StringBuilder();
                for(String s:validationMessages){
                    errorMsg.append(s).append("/n");
                }
JOptionPane.showMessageDialog(this,
        errorMsg.toString(),
        "Chyba vstupu",
        JOptionPane.WARNING_MESSAGE);
                return;
            }
double para1=Double.parseDouble(input1.getText().trim());
double para2=Double.parseDouble(input2.getText().trim());
SablonaProPrvek newItem=new SablonaProPrvek(
        inputName.getText(),
                    para1,
                    para2
            );
parent.addNewItem(newItem);
dispose();
        });

        add(mainPanel);

    }
    private java.util.List<String> validateInputs(){
        List<String> errors = new ArrayList<>();
        if(inputName.getText().length()<1){
            errors.add("Název je povinný");
        }
        if(input1.getText().length()<1){
            errors.add("Cena je povinná");
        }
        else{
            try{
                double price = Double.parseDouble(input1.getText().trim());
            }catch (NumberFormatException e){
                errors.add("Cena je ve špatném formátu");
            }
        }
        //TODO validovat
        return errors;
    }
}
