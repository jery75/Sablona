package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;

public class JsonFO implements FileOperation {
    private Gson gson;
    private static final String FILE_NAME = "./uložena data.json";
    public JsonFO(){
        gson=new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
    @Override
    public ListPrvku load(){
        try{
            Type targetType =new TypeToken<ListPrvku>(){}.getType();
            FileReader reader=new FileReader(FILE_NAME);
            ListPrvku listPrvku=gson.fromJson(reader,targetType);
            return listPrvku;
        }catch (IOException e){
            e.printStackTrace();
        }
        return new ListPrvku();
    }
    @Override
    public void write(ListPrvku model){
        String jsonText =gson.toJson(model);
        System.out.println(jsonText);
        try{
            FileWriter writer=new FileWriter(FILE_NAME);
            writer.write(jsonText);
            writer.flush();
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
