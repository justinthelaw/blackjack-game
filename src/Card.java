public class Card {
   private int value;
   private String type;

   Card(String type, int value) {
      this.type = type;
      this.value = value;
   }

   public void setValue(int newValue) {
      this.value = newValue;
   }

   public String toString() {
      return "Type: " + this.type + " | Value: " + this.value;
   }
}
