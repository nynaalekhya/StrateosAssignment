import java.io.*;
import java.util.*;

class Solution {
  public static void main(String[] args) {

    Scanner input = new Scanner(System.in);
    String sequence = input.nextLine();
    printMoves(sequence);

    input.close();

  }

  public static void printMoves(String sequence) {
    if (sequence.length() == 0) return;

    if (!checkIsValid(sequence)) {
      System.out.println("Invalid Sequence");
      return;
    }

    // hashmap to store character and its wrong index
    HashMap < Character, Integer > map = new HashMap < > ();

    // stores characters at wrong position
    mapCharToPos(map, sequence);

    char[] sequenceArray = sequence.toCharArray();
    int underscoreIndex = -1;

    while (map.size() != 0) {

      underscoreIndex = findUnderScore(sequenceArray);

      if (underscoreIndex == -1) return;

      // index of character to be swapped with underscore
      int charIndex = -1;
      // the character that has to be swapped with underscore
      char swapChar;

      // if underscore is at end swap with the first character entry in map
      if (underscoreIndex == sequence.length() - 1) {
        Map.Entry < Character, Integer > entry = getFirstEntry(map);
        swapChar = entry.getKey();
        charIndex = entry.getValue();
        map.put(swapChar, underscoreIndex);
      } else {
        // if underscore is at any other index then swap with the character 
        // that should be present at the index 
        swapChar = (char)('A' + underscoreIndex);
        charIndex = map.get(swapChar);
      }

      swap(sequenceArray, charIndex, underscoreIndex);

      // print underscore index and character index which are swapped
      System.out.println(charIndex + " " + underscoreIndex);

      // remove the character from the map when at correct position
      if (underscoreIndex == (swapChar - 'A'))
        map.remove(swapChar);

    }

  }

  // checks whether the sequence has underscore or has any invalid lowercase letters 
  static boolean checkIsValid(String sequence) {

    int underscoreIndex = -1;
    for (int index = 0; index < sequence.length(); index += 1) {
      char currChar = sequence.charAt(index);
      if (currChar == '_') {
        underscoreIndex = index;
        continue;
      }
      if (currChar < 'A' || currChar > 'Z') return false;
    }
    return underscoreIndex >= 0;

  }

  // swaps two characters in the sequence character array
  static void swap(char[] sequenceArray, int charIndex, int underscoreIndex) {
    char temp = sequenceArray[charIndex];
    sequenceArray[charIndex] = sequenceArray[underscoreIndex];
    sequenceArray[underscoreIndex] = temp;
  }

  // stores the characters which are present in the map at wrong index
  static void mapCharToPos(HashMap < Character, Integer > map, String sequence) {

    for (int index = 0; index < sequence.length(); index++) {

      char currChar = sequence.charAt(index);
      if (currChar == '_') continue;
      int val = currChar - 'A';
      if (val != index) {
        map.put(currChar, index);
      }

    }

  }

  // returns the position of underscore, if not present returns -1
  static int findUnderScore(char[] seqArr) {
    int underscoreIndex = -1;
    for (int index = 0; index < seqArr.length; index++) {
      if (seqArr[index] == '_') underscoreIndex = index;
    }
    return underscoreIndex;
  }

  // returns the first entry present in map
  static Map.Entry < Character, Integer > getFirstEntry(HashMap < Character, Integer > map) {

    Iterator < Map.Entry < Character, Integer >> itr = map.entrySet().iterator();
    Map.Entry < Character, Integer > entry = itr.next();
    return entry;

  }

}
