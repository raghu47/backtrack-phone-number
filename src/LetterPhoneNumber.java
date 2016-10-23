import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 
 * @author raghunathanmj
 * root, reject, accept, first, next, output
 */

public class LetterPhoneNumber {
	private Map<Character, Set<Character>> symbols; 
	
	public LetterPhoneNumber(Map<Character, Set<Character>> symbols) {
		this.symbols = symbols;
	}
	
	public String root() {
		return "";
	}
	
	public boolean reject(String p, String s) {
		if (s == null) {
			return true;
		}
		if (s.length() == 0) {
			return false;
		} else {
			int last = s.length()-1;
			if (symbols.get(p.charAt(last)).contains(s.charAt(last))) {
				return false;
			}
		}
		return true;
	}
	
	public boolean accept(String p, String s) {
		if (s != null && s.length() == p.length()) {
			return true;
		}
		return false;
	}
	
	public String first(String p, String s) {
		if (s.length() == p.length()) {
			return null;
		}
		return s + "a";
	}
	
	public String next(String p, String s) {
		char lastAlpha = s.charAt(s.length()-1);
		if (lastAlpha == 'z') {
			return null;
		} else {
			return s.substring(0, s.length()-1) + String.valueOf((char) (lastAlpha+1));
		}
	}
	
	public String output(String s) {
		return s;
	}
	
	public List<String> backTrack(String p, String s) {
		List<String> returnValue = new ArrayList<>();
		if (reject(p, s)) {
			return returnValue;
		}
		if (accept(p, s)) {
			returnValue.add(s);
		}
		String first = first(p, s);
		while (first != null) { // don't do !reject(p, first) here, you are to visit all children of a unrejected node and not stop at the first rejected child
			returnValue.addAll(backTrack(p, first));
			first = next(p, first);
		}
		return returnValue;
	}
	
    public List<String> letterCombinations(String digits) {
    	return backTrack(digits, root()); 
    }
    
	public static void main(String[] args) {
		Map<Character, Set<Character>> symbols = new HashMap<>();
		symbols.put('2', new HashSet<>(Arrays.asList('a', 'b', 'c')));
		symbols.put('3', new HashSet<>(Arrays.asList('d', 'e', 'f')));
		symbols.put('4', new HashSet<>(Arrays.asList('g', 'h', 'i')));
		symbols.put('5', new HashSet<>(Arrays.asList('j', 'k', 'l')));
		symbols.put('6', new HashSet<>(Arrays.asList('m', 'n', 'o')));
		symbols.put('7', new HashSet<>(Arrays.asList('p', 'q', 'r', 's')));
		symbols.put('8', new HashSet<>(Arrays.asList('t', 'u', 'v')));
		symbols.put('9', new HashSet<>(Arrays.asList('w', 'x', 'y', 'z')));
		System.out.println(new LetterPhoneNumber(symbols).letterCombinations("23"));
	}

}
