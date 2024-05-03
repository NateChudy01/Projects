//public class HelloWorld:
//This class is here to print out "Hello World" when ran initally
public class HelloWorld {
    public static void main(String[] args) {
        if (args.length > 0) { // This line checks to see if there was anything input in the inital args string
                               // and if there was it uses it as a name
            System.out.println("Hello " + args[0] + "!"); // Printing "Hello" and then the name given on the command
                                                          // line
        } else {
            System.out.println("Hello World!"); // If there was nothing input on the command line then this message is
                                                // printed to say "Hello World"
        }
    }
}