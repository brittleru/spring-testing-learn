package guru.springframework;


public class Greeting {

    private static final String HELLO = "Hello";
    private static final String WORLD = "World";

    public String helloWorld(){
        return HELLO + " " + WORLD;
    }

    public String helloWorld(String name){
        return HELLO + " " + name;
    }

    public static void main(String[] args) {
        Greeting greating = new Greeting();
        System.out.println(greating.helloWorld());
    }


}
