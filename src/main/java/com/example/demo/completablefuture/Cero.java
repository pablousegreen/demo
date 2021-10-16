package com.example.demo.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Cero {

    private static  Integer handleException1(Throwable throwable){
        System.out.println("Error: "+ throwable);
        return   0;
    }

    private static  Integer handleException(Throwable throwable) {
        System.out.println("Error: "+ throwable);
        throw  new RuntimeException("it is beyond all hope");
    }
    private static int getMyBasicNumber(int number){
        return number;
    }

    public static CompletableFuture<Integer> givePreMeError(int otherNumber) {
        //throw new Exception("Error data");
        return CompletableFuture.supplyAsync(()-> 0/otherNumber);
    }

    public static CompletableFuture<Integer> givePreMe(int otherNumber){
         return CompletableFuture.supplyAsync(()-> otherNumber)
                .thenCompose(Cero::givePreMeError)
                .exceptionally(throwable -> handleException1(throwable))
                .thenCompose(Cero::giveMe);
    }

    public static CompletableFuture<Integer> givePreMe1(int otherNumber){
         return CompletableFuture.supplyAsync(()-> otherNumber)
                .thenCompose(Cero::givePreMeError)
                ///.exceptionally(throwable -> handleException1(throwable))
                .exceptionally(throwable -> handleException(throwable));
         //return CompletableFuture.supplyAsync(()-> otherNumber);
    }

    public static CompletableFuture<Integer> giveMe(int otherNumber){
        /*if(otherNumber<1){
            throw new Exception("Test error");
        }*/
        return CompletableFuture.supplyAsync(()-> otherNumber+10);
    }

    public static CompletableFuture<Integer> create(int number){
        return CompletableFuture.supplyAsync(()-> Cero.getMyBasicNumber(number));
    }

    public static CompletableFuture<Integer> getAll()throws Exception{
        CompletableFuture<Integer> result = givePreMe(5)
                .thenCompose(data -> giveMe(data));

        Integer res =  result.get();
        return CompletableFuture.supplyAsync(()-> res);
    }
    public static void main(String[] args)  {
        // _NewCLass.see();
        givePreMe1(5);
        /*try {
            Integer res = Cero.getAll().get();
            if(res <1){
                throw new Exception("Ten excpetion");
            }
        } catch (Exception e) {
            System.out.println("Error: "+e);
        }
        System.out.println("--------------------------");
        create(2).thenCombine(create(3), (result1, result2) -> result1 + result2)
                .thenAccept(System.out::println);*/
    }

    private static class _NewCLass{
        public static Integer see()  {
            Cero.givePreMe1(5);
            return 1;
        }
    }
}
