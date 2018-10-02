# CaesarCipher
/*
 * Name: Daniel Williams
 * Date: 10/02/18
 * 
 * CAESAR CIPHER
 * 
 * 
 * 
 * 
 */

import java.util.Scanner;

import javax.xml.soap.Node;

public class LinkedList {
	
	static Scanner keyboard = new Scanner(System.in);
	static Node head;
	private Node next;
	public static String text;
	public static LinkedList lowercase = new LinkedList(); 
	public static LinkedList uppercase = new LinkedList(); 
	
	
	static class Node { 
        String text; 
        Node next; 
        Node(String t)  { text = t;  next = null; } // Constructor 
		public Node(char ch) {
			// TODO Auto-generated constructor stub
		}
    } 

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 
		/// Get user input, IFF it not numeric and they actually typed something
		 do {
		 System.out.println("Enter a phrase:");
		 text = keyboard.nextLine();
		 } while (text.length() <= 0 || isNumeric(text) == true); 

			
			
			LinkedList.head  = new Node(Character.toString(text.charAt(0)));
//			lowercase.push(text);// adds first letter to head of list
			
//			lowercase.head.next = new Node(text.charAt(1));
			
			LinkedList.encode(text);
			
	       lowercase.printList(); /// PRINTS LIST. USE TO CHECK YOUR LIST IS WORKING
	       //TODO Find out why print list is adding the first letter twice
	       
	       
	       
	       //// ENCRYPTION /////
	       String cipher = encrypt(text, 5);
	       System.out.println(cipher);
	       
	       /// DECRYPTION ///
//	       String decrypted = decrypt(cipher, 5);
//			System.out.println(decrypted);
	        
		
	}
	
	 public static String encode(String text) {
			String result = "";
			int length = text.length();
			char ch;
			 for(int i = 0; i < text.length(); ++i) {
		            ch = text.charAt(i);
//		            System.out.println(ch);
		            if(Character.isAlphabetic(ch)) {
		            	System.out.println(ch);
		            
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
		        return result;
		    }
	 
	 public static String encrypt(String cesarCipher, int letterShift) {
			
			/*vvvvvv  takes in the letterletterShift number, and if it is greater
			 * than 26 or less than 0, puts it between 0 and 26 range
			 */
				if (letterShift > 26 ) {
					letterShift = letterShift%26;
				}
				else if (letterShift < 0) {
					letterShift = (letterShift%26)+26;
				}
				
				
				String cipherText = "";
				
				int length = cesarCipher.length();
				
				for (int i =0; i<length; i++) {
					char ch = cesarCipher.charAt(i);
					
				//// user input validation checking for letters only ////
					if (Character.isLetter(ch)) {
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
	 
	 public static String decrypt(String cesarCipher, int letterShift) {
			/*vvvvvv  takes in the letterShift number, and if it is greater
			 * than 26 or less than 0, it puts it between 0 and 26 range
			 */
				if (letterShift > 26 ) {
					letterShift = letterShift%26;
				}
				else if (letterShift < 0) {
					letterShift = (letterShift%26)+26;
				}
				
				
				String cipherText = "";
				
				int length = cesarCipher.length();
				
				// loops through 
				for (int i =0; i<length; i++) {
					char ch = cesarCipher.charAt(i);
					
				//// user input validation checking for letters only ////
					if (Character.isLetter(ch)) {
						if(Character.isLowerCase(ch)) {//// changed to (ch-letterShift)///
							char c = (char)(ch-letterShift);
							
				/// change to (c < 'a') and (ch + (26-letterShift)) to DEcrypt the message //
							if(c<'a') {
								cipherText += (char)(ch + (26 - letterShift));
							}
							else {
								cipherText += c;
							}
						}
						
				
						else if (Character.isUpperCase(ch)) {//// changed to (ch-letterShift)///
							char c = (char)(ch-letterShift);
				/// change to (c < 'A') and (ch + (26-letterShift)) //
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
	 

	 public void append(String new_data) 
	    { 
	        /// Allocate the Node, put in the data, set next as null ///
	        Node new_node = new Node(new_data); 
	  
	        /* 4. If the Linked List is empty, then make the 
	              new node as head */
	        if (head == null) 
	        { 
	            head = new Node(new_data); 
	            return; 
	        } 
	  
	        /* 4. This new node is going to be the last node, so 
	              make next of it as null */
	        new_node.next = null; 
	  
	        /* 5. Else traverse till the last node */
	        Node last = head;  
	        while (last.next != null) 
	            last = last.next; 
	  
	        /* 6. Change the next of last node */
	        last.next = new_node; 
	        return; 
	    } 
	 
	
	 // checks to make sure user input is a number or not /////
	 public static boolean isNumeric(String strNum) {
		    try {
		        int i = Integer.parseInt(strNum);
		    } catch (NumberFormatException | NullPointerException nfe) {
		        return false;
		    }
		    return true;
		}
	 
	 
	 /// prints the entire list ///
	 public void printList() 
	    { 
	        Node n = head; 
	        while (n != null) 
	        { 
	            System.out.print(n.text+" "); 
	            n = n.next; 
	        } 
	    } 

}

