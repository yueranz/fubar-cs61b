/*  Nuke2.java  */

import java.io.*;

class Nuke2 {
	/*  Prompts the user for an input. It then returns that input with the second character nuked.
	 * @param arg is not used
	 * @exception Exception thrown if there are any problems parsing the user's input.
	 *
	 */
	public static void main(String[] arg) throws Exception {
		BufferedReader keyboard;
		String inputLine;

		keyboard = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Please enter your least favorite saying: ");
		System.out.flush();
		inputLine = keyboard.readLine();
		System.out.println(inputLine.substring(0,1) + inputLine.substring(2,inputLine.length()));
	}
}
