package models;

import javax.swing.table.AbstractTableModel;

public class TableModel extends AbstractTableModel {
    private ListPrvku listPrvku;
    public TableModel(ListPrvku listPrvku){this.listPrvku=listPrvku;}

    @Override public int getRowCount(){return  listPrvku.getItems().size();}

    @Override public int getColumnCount(){return 3;}

    @Override
    public Object getValueAt(int rowIndex,int columnIndex){
        if(columnIndex==0)
            return listPrvku.getItems().get(rowIndex).getName();
        else if(columnIndex==1)
            return listPrvku.getItems().get(rowIndex).getParametr1();
        else if (columnIndex==2)
            return listPrvku.getItems().get(rowIndex).getParametr2();
        String s=rowIndex+":"+columnIndex;
        return s;
    }
    @Override
    public String getColumnName(int column){
        if(column==0)
            return "jméno";
        return super.getColumnName(column);
    }
}
