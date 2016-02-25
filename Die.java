 /*
 * Algorithmics
 * 14708689 Orla Cullen
 * 05641349 Gavin Keaveney
 * 14343826 Jonathan Sweeney
 * 
 */

public class Die {
	private int faceValue; 

	public Die() {
		roll(); 
	}

	public int value() {
		return faceValue;
	}

	public void roll() {
		faceValue = 1 + (int) (Math.random() * 6.0);
	}

	public int getDie() {
		return faceValue;
	}

} 