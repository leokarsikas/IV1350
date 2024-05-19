package se.kth.iv1350.pointOfSale;

public class SystemOutLogger implements MessageCreator{

    @Override
    public void log(String msg){
        System.out.println(msg);
    }
}