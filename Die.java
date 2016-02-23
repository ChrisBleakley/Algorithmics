 public class Die {
       private int faceValue;   // Number showing on the first die.
      
        
        public Die() {
                roll();  // Call the roll() method to roll the dice.
        }
        public int value(){
            return faceValue;
         }
        
        public void roll() {
                // Roll the dice by setting each of the dice to be
                // a random number between 1 and 6.
            faceValue = 1 + (int)(Math.random()*6.0);
            //die2 = 1 + (int)(Math.random()*6.0);
        }
                 
        public int getDie() {
              
           return faceValue;
        }
        public int rollDice(){
        	
			int winner=0;
			return winner;
            
         }
        
       

        
     }  