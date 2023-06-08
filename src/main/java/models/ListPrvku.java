package models;

import com.google.gson.annotations.Expose;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListPrvku {
    @Expose(serialize = true, deserialize = true)
    private String name;
    @Expose(serialize = true, deserialize = true)
    private List<SablonaProPrvek>items;
    @Expose(serialize = false,deserialize = false)
    private List<ActionListener> listeners = new ArrayList<>();

    public ListPrvku() {
        name = "Seznam";
        items = new ArrayList<>();
    }
    public List<SablonaProPrvek> getItems() {
        return items;
    }
    public void setItems(List<SablonaProPrvek> items){
        this.items=items;
        raiseEventItemsChanged();
    }
    public String getName(){
        return name;
    }
    public  void setName(String name){this.name=name;}
    public void addActionListener(ActionListener add){
        listeners.add(add);
    }
    public void addItem(SablonaProPrvek newItem){
            items.add(newItem);
            raiseEventItemsChanged();

    }
    private void raiseEventItemsChanged(){
        for(ActionListener al:
        listeners){
            al.actionPerformed(new ActionEvent(this,1,"itemsChanged"));
        }
    }
}