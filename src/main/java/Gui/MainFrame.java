package Gui;

import models.FileOperation;
import models.ListPrvku;
import models.SablonaProPrvek;
import models.TableModel;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class MainFrame extends JFrame {
    private ListPrvku listPrvku;
    private TableModel tableModel;
    private FileOperation fileOperation;
    private JLabel lblI;

    public MainFrame(int width, int leight, FileOperation fileOperation) {
        super("Sablona");
        this.fileOperation = fileOperation;
        setSize(width, leight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        listPrvku = fileOperation.load();
        initMenu();
        initGui();
        setVisible(true);
    }

    private void initMenu() {
        JMenuBar bar = new JMenuBar();
        JMenu menu1 = new JMenu("Seznam");
        bar.add(menu1);
        JMenuItem menuNewItem = new JMenuItem("nova položka");
        menuNewItem.addActionListener(e -> {
            System.out.println("kliknuto na menuNewItem");
            NewItemDialog dialog = new NewItemDialog(this);
        });
        menu1.add(menuNewItem);

        JMenuItem menuNewItem2 = new JMenuItem("uložit");
        menuNewItem2.addActionListener(e -> {
            System.out.println("save");
            fileOperation.write(listPrvku);
            JOptionPane.showMessageDialog(this,
                    "Seznam uložen",
                    "ok",
                    JOptionPane.INFORMATION_MESSAGE);
        });
        menu1.add(menuNewItem2);

        JMenuItem menuNewItem3 = new JMenuItem("načíst");
        menuNewItem3.addActionListener(e -> {
            Runnable loadData = () -> {
                try {
                    System.out.println("load...");
                    TimeUnit.SECONDS.sleep(5);
                    listPrvku.setItems(fileOperation.load().getItems());
                    JOptionPane.showMessageDialog(this,
                            "Seznam uložen",
                            "ok",
                            JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            };
            Thread loadDataThread = new Thread(loadData);
            loadDataThread.start();
        });
        menu1.add(menuNewItem3);

        setJMenuBar(bar);
    }

    private void initGui() {
        JPanel panelMain = new JPanel(new BorderLayout());
        panelMain.add(initPanelN(), BorderLayout.NORTH);
        panelMain.add(initPanelC(), BorderLayout.CENTER);
        panelMain.add(initPanelS(), BorderLayout.SOUTH);
        add(panelMain);
    }

    private JPanel initPanelN() {
        JPanel panelN = new JPanel();
        panelN.add(new JLabel("north panel"));

        return panelN;
    }

    private JPanel initPanelC() {
        JPanel panelC = new JPanel();
        tableModel = new TableModel(listPrvku);
        listPrvku.addActionListener(e -> {
            if (e.getID() != 1)
                return;
            tableModel.fireTableDataChanged();
        });
        JTable table = new JTable();
        table.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panelC.add(scrollPane);

        return panelC;
    }

    private JPanel initPanelS() {
        JPanel panelS = new JPanel();
        lblI = new JLabel("Info");
        listPrvku.addActionListener(e -> {
            updateSummaryInfo();
        });
        panelS.add(lblI);
        updateSummaryInfo();
        return panelS;
    }

    public void addNewItem(SablonaProPrvek newItem) {
        System.out.println("nová položka");
        listPrvku.addItem(newItem);
    }

    private void testList() {
        listPrvku.getItems().add(new SablonaProPrvek("Tom", 15, 3));
    }

    private void updateSummaryInfo() {
        StringBuilder text = new StringBuilder();
        text.append("Počet: ");
        text.append(listPrvku.getItems().stream().count());
        text.append(", počet2: ");
        int countSum = 0;
        for (SablonaProPrvek item :
                listPrvku.getItems()) {
            countSum += item.getParametr2();
        }
        text.append(countSum);
        lblI.setText(text.toString());
    }
}
