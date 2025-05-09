public class ReverseImplementation extends ReverseModule.ReversePOA {
  public ReverseImplementation() {
    super();
  }
  
  public String reverse(String str) {
    return new StringBuilder(str).reverse().toString();
  }
}
