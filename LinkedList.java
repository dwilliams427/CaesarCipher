/**
 * Name: @author Daniel Williams (dlw1l), @author Nikolas Pavlovic
 * Date: @since 2018-10-04
 * @version 1.0
 * 
 * UML
 * CAESAR CIPHER
 * 
 * @param head  initializes the linked list
 * @param next  holds the pointer to the next Node
 * @param text  String that will become our user input
 * 				for cesar cipher
 * @param lowercase  Linked list containing lowercase chars
 * @param uppercase  Linked list containing uppercase chars
 * 
 * 
 * 
 * 
 * 
 * 
 *
 * This program will take in user input of any sentence they want to type, and will add each character to a 
 * linked list of either lowercase and uppercase, and will encrypt that message an int "lettershift" amount
 *  forwards down the alphabet. For example: A with a letter shift of 3 will become D, B becomes E, and so on. 
 * Then, the program decrypts the message backwards the "lettershift" amount to display the original message. 
 * 
 * This program begins with an imported and initialized scanner, to read in user input.
 * The Node and Linked list fields are initialized as Static because we have one instantiation of each list
 * and we are not copying each individual list. This also allows the program to have a smaller "footprint."
 * We also have 2 lists, which will hold our lowercase and uppercase chars.
 * 
 *
 * 
 * 
 * 
 * 
 * 
 */

import java.util.Scanner;



public class LinkedList {
	
	static Scanner keyboard = new Scanner(System.in);
	static Node head;
	static Node next;
	public static String text;
	public static LinkedList lowercase = new LinkedList(); 
	public static LinkedList uppercase = new LinkedList(); 
	
	
	/**
	 * 
	 * @author dlw1l
	 * We must define Node() in its own separate class in order to instantiate a Node object which will hold 
	 * our user's data and pointer to the next Node. This class contains a Node() constructor as well
	 * 
	 * @see Node
	 * 
	 *
	 */
	static class Node { 
        String text; 
        Node next; 
        Node(String t)  { text = t;  next = null; } // Constructor 
		public Node(char ch) {
			// TODO Auto-generated constructor stub
		}
    } 

	
	/**
	 * @author dlw1l
	 * @param args
	 * 
	 * Next we have our main() method, in which the program begins and asks and assigns user input to 
	 * @param text String, This input is contained in a do while loop to continue the prompt until the parameters
	 * are fulfilled, making sure input is not null and is not numeric.
	 * @param str  String, then replaces all special chars with whitespace. We then input str into encode method that
	 * will append each char to its corresponding linked list.
	 * 
	 * Once this is done, we intialize our @param cipher which is a String, to now encrypt our str
	 * list with a letter shift of 5. We then print the result
	 * 
	 * After our encryption, we can decrypt it back through @param decrypted, which is a String, that will 
	 * be decrypted back to it's original state with a letter shift of 5. We then print that result
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 
		/// Get user input, IFF: it is not numeric and it is not null
		 do {
		 System.out.println("Enter a phrase:");
		 text = keyboard.nextLine();
		 } while (text.length() <= 0 || isNumeric(text) == true); 
		 
		 // REGEX - replaces special characters with whitespace//
		 String str = text.replaceAll("[^\\p{Alnum}]+", " ");
		 

			//// INTIALIZES AND POPULATES LIST ////
			LinkedList.encode(str);
			
			
	        /// PRINTS LIST. USE THIS TO CHECK YOUR LIST IS WORKING
//			System.out.print("Full List: ");
//	        lowercase.printList();
	      
			
			//// ENCRYPTION /////		
			System.out.println();
	       String cipher = LinkedList.encrypt(str, 5);
	       System.out.println("Encrypted Message: " + cipher);
	       
	       /// DECRYPTION ///
	       System.out.println();
	       String decrypted = LinkedList.decrypt(cipher, 5);
		   System.out.println("Decryption: " + decrypted);
	}
	
	
	/**
	 * @author dlw1l
	 * @param text String
	 * @param length int
	 * @param i int
	 * @return result String, the full list of chars
	 * 
	 * This method loops through the user input, checking to see if each char at i (ch), is Alphabetical
	 * and is not numeric. Then checks to see if the the char is lowercase/uppercase, and appends each char
	 * to its corresponding list. 
	 * @see append 
	 * 
	 */
	 public static String encode(String text) {
			String result = "";
			int length = text.length();
			char ch = 0;
			
			 for(int i = 0; i < text.length(); ++i) {
				 ch = text.charAt(i);
		            if(Character.isAlphabetic(ch) || isNumeric(Character.toString(ch)) == false) {

		            
		             if(Character.isLowerCase(ch)) {
		                    lowercase.append(Character.toString(text.charAt(i)));
		             }
		                else {
//		                    ch = (char) ('A' + ((ch - 'A' - 3 + 26) % 26));
		                uppercase.append(Character.toString(text.charAt(i)));
		                }
		            }
		            result += ch;
			 }
//			 System.out.println("encode loop result:" + result); // CHECKS ENCODED "Result"
		        return result;
		    }
	 
	 
	 /**
	  * @author dlw1l
	  * @param cesarCipher String
	  * @param letterShift int
	  * @param length int
	  * @param i int
	  * @return cipherText String, the full encrypted message
	  * 
	  * This method brings in our linked list  @see encode , created from our user input, and first checks to 
	  * see if its alphabetical value is greater than 26 or less than 0, and puts it between 0 and 26 range,
	  * disallowing the lettershift to go beyond the alphabet. 
	  * We then loop through each element of the linked list/ cesarCipher and once again, checks to see if 
	  * each char(ch) at i , is Alphabetical and is not numeric. Then checks to see if the the char is 
	  * lowercase/uppercase and then outputs the letterShift amount to the right, up the Alphabet.
	  */
	 public static String encrypt(String cesarCipher, int letterShift) {
		 
			String cipherText = "";
			int length = cesarCipher.length();
				
			/*vvvvvv  takes in the letterletterShift number, and if it is greater
			 * than 26 or less than 0, puts it between 0 and 26 range
			 */
				if (letterShift > 26 ) {
					letterShift = letterShift%26;
				}
				else if (letterShift < 0) {
					letterShift = (letterShift%26)+26;
				}
				
				// loop thru each element of the list and then shift each element by "letterShift" amount
				for (int i =0; i<length; i++) {
					char ch = cesarCipher.charAt(i);
					
				//// user input validation checking for letters only ////
					if (Character.isLetter(ch) || isNumeric(Character.toString(ch)) == true) {
						if(Character.isLowerCase(ch)) {//// checking for lowercase ascii /////
							char c = (char)(ch+letterShift);
							
			/// our letters/letterShift must loop through the 26 lowercase letters and must not go out of bounds ////
							if(c>'z') {
								cipherText += (char)(ch - (26 - letterShift));
							}
							else {
								cipherText += c;
							}
						}
						
			/// our letters/letterShift must loop through the 26 uppercase letters and must not go out of bounds ////
						else if (Character.isUpperCase(ch)) {/// checking for uppercase ascii ///
							char c = (char)(ch+letterShift);
							if(c >'Z') {
								cipherText += (char)(ch - (26 - letterShift));
							}
							else {
								cipherText += c;
							}
						}
					}
					else { 
						cipherText += ch;
					}
				}
				return cipherText;
			}
	 
	 
	 /**
	  * @author dlw1l
	  * @param cesarCipher String
	  * @param letterShift int
	  * @param length int
	  * @param i int
	  * @return cipherText String, the full decrypted message
	  * 
	  * This method brings in our encrypted list @see encrypt, created in @param encrypt, and first checks to 
	  * see if its alphabetical value is greater than 26 or less than 0, and puts it between 0 and 26 range,
	  * disallowing the lettershift to go beyond the alphabet. 
	  * We then loop through each element of the linked list/ cesarCipher and once again, checks to see if 
	  * each char(ch) at i , is Alphabetical and is not numeric. Then checks to see if the the char is 
	  * lowercase/uppercase and then outputs the letterShift amount to the left, down the Alphabet, 
	  * reverting the list back to the pre-encrypted state
	  * 
	  */
	 public static String decrypt(String cesarCipher, int letterShift) {
		 
			String cipherText = "";
			int length = cesarCipher.length();
				
				/*vvvvvv  takes in the letterShift number, and if it is greater
			 * than 26 or less than 0, it puts it between 0 and 26 range
			 */
				if (letterShift > 26 ) {
					letterShift = letterShift%26;
				}
				else if (letterShift < 0) {
					letterShift = (letterShift%26)+26;
				}

			// loop thru each element of the list and then shift each element by "letterShift" amount
				for (int i =0; i<length; i++) {
					char ch = cesarCipher.charAt(i);
					
			//// user input validation checking for letters only ////
					if (Character.isLetter(ch) || isNumeric(Character.toString(ch)) == true) {
						if(Character.isLowerCase(ch)) {//// changed to (ch-letterShift)///
							char c = (char)(ch-letterShift);
							
			/// change to (c < 'a') and (ch + (26-letterShift)) to DEcrypt the message  //
//							[[[[DONT TOUCH]]]]]
							if(c<'a') {
								cipherText += (char)(ch + (26 - letterShift));
							}
							else {
								cipherText += c;
							}
						}
						
				/// change to (c < 'A') and (ch + (26-letterShift))  [[[[DONT TOUCH]]]
						else if (Character.isUpperCase(ch)) {//// changed to (ch-letterShift)///
							char c = (char)(ch-letterShift);
				
							if(c <'A') {
								cipherText += (char)(ch + (26 - letterShift));
							}
							else {
								cipherText += c;
							}
						}
					}
					else { 
						cipherText += ch;
					}
				}
				return cipherText;
			}
	 
	 
	/**
	 * @author dlw1l
	 * @param new_data
	 * @return linkedlist
	 * 
	 * This method appends each char from ( @see encode ) our user input, to the end of its corresponding list
	 * It creates a new node, set its next to @param null . If the list is empty, sets the new node the new head
	 * of the list and set its pointer next to null. After that first node, it will make its next pointer set
	 * to the new incoming node, setting that new node's next pointer to null, and repeat the process
	 * 
	 */
	 public void append(String new_data) 
	    { 
	        /// Allocate the Node, put in the data, set next as null ///
	        Node new_node = new Node(new_data); 
	  
	        /* If the Linked List is empty, then make the 
	              new node as head */
	        if (head == null) 
	        { 
	            head = new Node(new_data); 
	            return; 
	        } 
	  
	        /* This new node is going to be the last node, so 
	              make next of it as null */
	        new_node.next = null; 
	  
	        /* Else traverse till the last node */
	        Node last = head;  
	        while (last.next != null) 
	            last = last.next; 
	  
	        /* Change the next of last node */
	        last.next = new_node; 
	        return; 
	    } 
	 
	/**
	 * @author dlw1l
	 * @param strNum String
	 * @return boolean
	 * 
	 * This method is called: @see main , @see encode , @see encrypt , @see decrypt
	 * to check to see if user input is isNumeric by parsing through it. 
	 * If it is NOT numeric, return false. 
	 * If it is numeric, return true.
	 * 
	 */
	 // checks to make sure user input is not a number /////
	 public static boolean isNumeric(String strNum) {
		    try {
		        int i = Integer.parseInt(strNum);
		        
		    } catch (NumberFormatException | NullPointerException nfe) {
		        return false;
		    }
		    return true;
		}
	 
	 /**
	  * @author dlw1l
	  * This method simply prints the linked list by printing each element
	  * of the list until the end of the list is reached.
	  */
	 public void printList() 
	    { 
		 
	        Node n = head; // set list head to n
	        while (n != null) // go until list is empty
	        { 
	            System.out.print(n.text+" "); // print out each element of the list
	            n = n.next; 
	        } 
	        System.out.println();
	    } 

}
