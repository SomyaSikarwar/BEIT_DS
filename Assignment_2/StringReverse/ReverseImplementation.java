public class ReverseImplementation extends ReverseModule.ReversePOA {

    ReverseImplementation() {
        super();
        System.out.println("ReverseImplementation object created.");
    }

    @Override
    public String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }
}
