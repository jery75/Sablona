import Gui.MainFrame;
import models.FileOperation;
import models.JsonFO;

public class Main {
    public static void main(String[] args) {
        System.out.println("Start...");
        FileOperation io;
        io=new JsonFO();
        MainFrame frame=new MainFrame(800,500,io);

    }
}