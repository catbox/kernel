/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-06
 * Project: Opus Dei
 * Package: com.opusdei.common
 * File: StringTools.java
 * Description: String utilities
 *
 */
package com.opusdei.common;

import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.regex.*;
import java.net.URLDecoder;

@SuppressWarnings("unchecked")
public class StringTools {
	
	public static final String EMPTY_STRING = "";

	public static final String SINGLE_SPACE = " ";
	
	public static final String ZERO = "0";
	
	/**
	 * This collator is for the default locale, and uses the default strength (TERTIARY).
	 * @see  Collator#TERTIARY
	 * @uml.property  name="collator"
	 */
	private static Collator collator = Collator.getInstance();

	/**
	 * This collator is for the default locale, and uses the PRIMARY strength,
	 * which ignores differences in case, accents and control chars.
	 * @see Collator#PRIMARY
	 */
	private static Collator collatorPrimary = (Collator) collator.clone();

	static
	{
		collatorPrimary.setStrength(Collator.PRIMARY);
	}

	/** Field COMMA */
	public static final char COMMA = ',';

	public static boolean contains(String s, String substring)
	{
		return s.indexOf(substring) != -1;
	}

	/**
	 * Method arrayToCSVList is called with ',' as a delimiter
	 *
	 * @param arr array of strings to be delimited.
	 *
	 * @return delimited string
	 *
	 * @see #arrayToDelimitedList(String[], char)
	 *
	 */
	public static String arrayToCSVList(Object arr[])
	{
		return arrayToDelimitedList(arr, COMMA);
	}

	public static String concat(Object[] arr)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < arr.length; i++)
		{
			sb.append(arr[i]);
		}

		return sb.toString();
	}

	/**
	 * Creates a delimited string out of tokens given in the array
	 * using the given character as the delimiter.
	 * If the array parameter is null it returns an empty String.
	 *
	 * @param arr array of strings to be delimited.
	 * @param delimiter Char
	 *
	 * @return delimited string
	 */
	public static String arrayToDelimitedList(Object arr[], char delimiter)
	{
		StringBuffer sb = new StringBuffer();
		if((arr != null) && (arr.length > 0))
		{
			sb.append(arr[0]);

			for(int i = 1; i < arr.length; i++)
			{
				if(arr[i] != null)
				{
					sb.append(delimiter);
					sb.append(arr[i]);
				}
			}
		}

		return sb.toString();
	}

	/**
	 * assumes all elements of the collection are strings..
	 * uses StringTools.COMMA as a delimiter
	 *
	 * @param collection
	 * @return Comma separated string representing the items in the given Collection.
	 */
	public static String collectionToCSVList(Collection collection)
	{
		return collectionToDelimitedList(collection, COMMA);
	}

	/**
	 * Generates a string of length length. If the string is longer than length
	 * it gets cut off. Otherwise it gets filled up with the filler String.
	 *
	 * @param length
	 * @param in
	 * @param filler
	 * @return a string of length length.
	 */
	public static String generateStringOfLength(int length, String in, String filler)
	{
		if((length < 1) || (in == null) || (filler == null))
		{
			return EMPTY_STRING;
		}

		if(in.length() > length)
		{
			return in.substring(0, length);
		}

		StringBuffer buffer = new StringBuffer(length);
		buffer.append(in);

		do
		{
			buffer.append(filler);
		}
		while(buffer.length() < length);

		return buffer.substring(0, length).toString();
	}

	/**
	 * Assumes all elements of the collection are strings..
	 * If collection is null, it will return null string.
	 * @param collection
	 * @param delimiter
	 * @return delimited string or null
	 */
	public static String collectionToDelimitedList(Collection collection, char delimiter)
	{
		if(collection == null)
		{
			return null;
		}

		return iteratorToDelimitedList(collection.iterator(), delimiter);
	}

	/**
	 * <H3>Method iteratorToDelimitedList</H3>
	 * Creates character delimited string from given iterator.
	 * @param iter
	 * @param delimiter
	 * @return  character delimited string from given iterator.
	 */
	public static String iteratorToDelimitedList(Iterator iter, char delimiter)
	{
		if(iter == null)
		{
			return null;
		}

		StringBuffer buf = new StringBuffer();
		while(iter.hasNext())
		{
			Object element = iter.next();
			buf.append(element.toString());

			if(iter.hasNext())
			{
				buf.append(delimiter);
			}
		}

		return buf.toString();
	}

	/**
	 * <H3>Method iteratorToDelimitedList</H3>
	 * Creates comma delimited string from given iterator.
	 * @param iter
	 * @return  comma delimited string from given iterator.
	 */
	public static String iteratorToCommaDelimitedList(Iterator iter)
	{
		return iteratorToDelimitedList(iter, COMMA);
	}

	/**
	 * Assumes all elements of the collection are strings..
	 * If collection is null, it will return null string.
	 * @param collection
	 * @param delimiter
	 * @return delimited string or null
	 */
	public static String collectionToDelimitedList(Collection collection, String delimiter)
	{
		if(collection == null)
		{
			return null;
		}

		return iteratorToDelimitedList(collection.iterator(), delimiter);
	}

	/**
	 * <H3>Method iteratorToDelimitedList</H3>
	 * Creates character delimited string from given iterator.
	 * @param iter
	 * @param delimiter
	 * @return  character delimited string from given iterator.
	 */
	public static String iteratorToDelimitedList(Iterator iter, String delimiter)
	{
		if(iter == null)
		{
			return null;
		}

		StringBuffer buf = new StringBuffer();
		while(iter.hasNext())
		{
			Object element = iter.next();
			buf.append(element.toString());

			if(iter.hasNext())
			{
				buf.append(delimiter);
			}
		}

		return buf.toString();
	}

	/**
	 * Methods <I>removeDuplicates</I> is called with ',' - comma, as a delimiter
	 *
	 *
	 * @param string string to be tokenised
	 *
	 * @return new delimited String that contains no duplicate tokens
	 *
	 * @see #removeDuplicates(String, char)
	 */
	public static String removeDuplicates(String string)
	{
		return removeDuplicates(string, COMMA);
	}

	/**
	 * Removes duplicates in delimited String.
	 * <BR>
	 * For example for string: "AAA,BBB,CCC,BBB,AAA," it will return "AAA,BBB,CCC"
	 * if the delimiter is ',' - comma.
	 * <BR>
	 * Method tokeniseStringToVector is called with <I>removeDuplicates</I> set to <B>true</B>
	 * and then its results are turned back into delimited string.
	 *
	 * @param string string to be tokenised
	 * @param delimiter character used as delimiter in given string
	 *
	 * @return new delimited String that contains no duplicate tokens
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static String removeDuplicates(String string, char delimiter)
	{
		if((string == null) || (string.length() < 1))
		{
			return string;
		}

		Vector store = tokeniseStringToVector(string, true, delimiter);
		StringBuffer result = new StringBuffer(string.length());
		int size = store.size();
		for(int i = 0; i < size; i++)
		{
			result.append((String) store.elementAt(i));
			result.append(delimiter);
		}

		store = null;

		return result.toString();
	}

	/**
	 * Return the substring of <tt>str</tt> before the specified character.
	 * The first occurrence of the character is used.
	 *
	 * @param str  A string containing the character to be found.
	 * @param c    The character to break the string at.
	 * @return     The substring if found or an empty string otherwise.
	 */
	public static String stringBefore(String str, char c)
	{
		return stringBefore(str, c, false);
	}

	/**
	 * Return the substring of <tt>str</tt> before the specified character.
	 *
	 * @param str  A string containing the character to be found.
	 * @param c    The character to break the string at.
	 * @param isLastOccurence - defines whether to look for the first or last occurence of character c
	 * @return     The substring if found. If not found - an empty string if the first occurence is needed, the actual string otherwise.
	 */
	public static String stringBefore(String str, char c, boolean isLastOccurence)
	{
		int pos = isLastOccurence ? str.lastIndexOf(c) : str.indexOf(c);
		if(pos != -1)
		{
			return str.substring(0, pos);
		}
		else if(isLastOccurence)
		{
			return str;
		}
		else
		{
			return EMPTY_STRING;
		}
	}

	/**
	 * Return the substring of <tt>str</tt> after the specified character.
	 * The first occurrence of the character is used.
	 *
	 * @param str  A string containing the character to be found.
	 * @param c    The character to break the string at.
	 * @return     The substring if found or an empty string otherwise.
	 */
	public static String stringAfter(String str, char c)
	{
		int pos = str.indexOf(c);
		return (pos == -1) ? EMPTY_STRING : str.substring(pos + 1);
	}

	/**
	 * Return the substring of <tt>str</tt> before the specified substring.
	 * The first occurance of the substring is used. Note that the
	 * substring before "" is just "".
	 *
	 * @param str   A string containing the substring to be found.
	 * @param subs  The substring to break the string at.
	 * @return      The substring if found or an empty string otherwise.
	 */
	public static String stringBefore(String str, String subs)
	{
		int pos = str.indexOf(subs);
		return (pos == -1) ? EMPTY_STRING : str.substring(0, pos);
	}

	/**
	 * Return the substring of <tt>str</tt> after the specified substring.
	 * The first occurance of the substring is used. Note that the
	 * substring after "" is the entire string.
	 *
	 * @param str   A string containing the substring to be found.
	 * @param subs  The substring to break the string at.
	 * @return      The substring if found or an empty string otherwise.
	 */
	public static String stringAfter(String str, String subs)
	{
		int pos = str.indexOf(subs);
		return (pos == -1) ? EMPTY_STRING : str.substring(pos + subs.length());
	}

	/**
	 * Find the first occurance of <tt>sOld</tt> in the buffer and replace it
	 * with <tt>sNew</tt>. The first occurance of <tt>sOld</tt> is used.
	 *
	 * @param buf    The buffer to be modified.
	 * @param sOld   The string to find in buffer. Must be non-null. If this
	 *                 string does not exist in the buffer, this method
	 *                 quietly does nothing.
	 * @param sNew   The string to replace with, If this string is null or
	 *                 empty, then sOld is simply removed from the buffer.
	 * @return       The original buffer, possibly modified.
	 */
	public static StringBuffer replace(StringBuffer buf, String sOld, String sNew)
	{
		if(sOld == null)
		{
			throw new NullPointerException();
		}

		if(sNew == null)
		{
			sNew = EMPTY_STRING;
		}

		int pos = buf.indexOf(sOld);

		if(pos != -1)
		{
			buf.replace(pos, pos + sOld.length(), sNew);
		}

		return buf;
	}

	/**
	 * Method <I>tokeniseStringToArray</I> is called with ',' - comma, as a delimiter
	 * and flag <I>removeDuplicates</I> set to <B>false</B>
	 *
	 * @param string string to be tokenised
	 *
	 * @return Array of String objects containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToArray(String, boolean, char)
	 */
	public static String[] tokeniseStringToArray(String string)
	{
		return tokeniseStringToArray(string, false, COMMA);
	}

	/**
	 * Method <I>tokeniseStringToArray</I> is called with ',' - comma, as a delimiter
	 *
	 * @param string string to be tokenised
	 * @param removeDuplicates indicating whether to keep or remove duplicate tokens
	 *
	 * @return Array of String objects containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToArray(String, boolean, char)
	 */
	public static String[] tokeniseStringToArray(String string, boolean removeDuplicates)
	{
		return tokeniseStringToArray(string, removeDuplicates, COMMA);
	}

	/**
	 * Method <I>tokeniseStringToVector</I> is called with ',' - comma, as a delimiter
	 * and flag <I>removeDuplicates</I> set to <B>false</B>
	 *
	 * @param string string to be tokenised
	 *
	 * @return Vector containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static Vector tokeniseStringToVector(String string)
	{
		return tokeniseStringToVector(string, false, COMMA);
	}

	/**
	 * Method <I>tokeniseStringToVector</I> is called with ',' - comma, as a delimiter
	 *
	 * @param string string to be tokenised
	 * @param removeDuplicates indicating whether to keep or remove duplicate tokens
	 *
	 * @return Vector containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static Vector tokeniseStringToVector(String string, boolean removeDuplicates)
	{
		return tokeniseStringToVector(string, removeDuplicates, COMMA);
	}

	/**
	 * Method <I>tokeniseStringToVector</I> is called and then it is converted to
	 * an array of String objects using <I>Vector.toArray()</I>.
	 *
	 * @param string string to be tokenised
	 * @param removeDuplicates indicating whether to keep or remove duplicate tokens
	 * @param delimiter character used as delimiter in given string
	 *
	 * @return Array of String objects containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static String[] tokeniseStringToArray(String string, boolean removeDuplicates, char delimiter)
	{
		Vector store = tokeniseStringToVector(string, removeDuplicates, delimiter);
		String array[] = new String[store.size()];
		store.toArray(array);

		return array;
	}

	/**
	 * Tokenizes given string using given character as a delimiter.
	 * <BR>
	 * To remove duplicate tokens specify <I>removeDuplicates</I>
	 * parameter as <B>true</B>.
	 *
	 * @param string string to be tokenised
	 * @param removeDuplicates indicating whether to keep or remove duplicate tokens
	 * @param delimiter character used as delimiter in given string
	 *
	 * @return Vector containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static Vector tokeniseStringToVector(String string, boolean removeDuplicates, char delimiter)
	{
		Vector result = new Vector();
		tokeniseStringToCollection(result, string, removeDuplicates, delimiter);

		return result;
	}

	public static Collection tokeniseStringToCollection(Collection store, String string)
	{
		return tokeniseStringToCollection(store, string, false, COMMA);
	}

	/**
	 * Tokenizes given string using COMMA as a delimiter.
	 * <BR>
	 * To remove duplicate tokens specify <I>removeDuplicates</I>
	 * parameter as <B>true</B>.
	 *
	 * @param store
	 * @param string delimited string to be tokenised
	 * @param removeDuplicates flag indicating whether to keep or remove duplicate tokens
	 *
	 * @return Collection containing tokens that given string consisted of
	 *
	 * @see #COMMA
	 */
	public static Collection tokeniseStringToCollection(Collection store, String string, boolean removeDuplicates)
	{
		return tokeniseStringToCollection(store, string, removeDuplicates, COMMA);
	}

	/**
	 * Tokenizes given string using given character as a delimiter.
	 * <BR>
	 * To remove duplicate tokens specify <I>removeDuplicates</I>
	 * parameter as <B>true</B>.
	 *
	 * @param store
	 * @param string delimited string to be tokenised
	 * @param removeDuplicates flag indicating whether to keep or remove duplicate tokens
	 * @param delimiter character used as delimiter in given string
	 *
	 * @return Vector containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, boolean, char)
	 */
	public static Collection tokeniseStringToCollection(Collection store, String string, boolean removeDuplicates, char delimiter)
	{
		return tokeniseStringToCollection(store, string, removeDuplicates, false, delimiter);
	}

	public static Collection tokeniseStringToCollection(Collection store, String str, boolean removeDuplicates, boolean returnEmptyElements, char delimiter)
	{
		if(str == null)
		{
			return store;
		}
        String string = null;
        try
        {
            try 
            {
                string = URLDecoder.decode(str, System.getProperty("file.encoding")); //$NON-NLS-1$
            }
            catch (UnsupportedEncodingException e)
            {
                System.out.println("The Character Encoding" +  System.getProperty("file.encoding") + "is not supported"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                string = str;
            }
        }
        catch(IllegalArgumentException iae)
        {
            string = str;
        }       

		if(!returnEmptyElements)
		{
			string = string.trim();

			while((string.length() > 0) && (string.charAt(0) == delimiter))
			{
				string = string.substring(1).trim();
			}
		}

		if(string.length() == 0)
		{
			return store;
		}

		int pos = string.indexOf(delimiter);
		int prevPos = 0;
		while((pos >= 0) && (pos < string.length()))
		{
			String element = string.substring(prevPos, pos);
			String trimmedElement = element.trim();
			if((trimmedElement.length() > 0) ||!returnEmptyElements)
			{
				element = trimmedElement;
			}

			if((element.length() > 0) || returnEmptyElements)
			{
				if(!(removeDuplicates && store.contains(element)))
				{
					store.add(element);
				}
			}

			prevPos = pos + 1;
			pos = string.indexOf(delimiter, prevPos);
		}

		if(prevPos < string.length())
		{
			String element = string.substring(prevPos);
			String trimmedElement = element.trim();
			if((trimmedElement.length() > 0) ||!returnEmptyElements)
			{
				element = trimmedElement;
			}

			if((element.length() > 0) || returnEmptyElements)
			{
				if(!(removeDuplicates && store.contains(element)))
				{
					store.add(element);
				}
			}
		}

		return store;
	}

	public static String[] tokeniseStringToArray(String string, String delimiters)
	{
		return tokeniseStringToArray(string, delimiters, false, false);
	}

	/**
	 * Method <I>tokeniseStringToVector</I> is called and then elements of the resulting Vector are moved to array.
	 *
	 * @param string string to be tokenised
	 * @param returnDelimiters indicating whether to treat delimiters found in the string as separate tokens or to discard them
	 * @param removeDuplicates indicating whether to keep or remove duplicate tokens
	 * @param delimiters String used as collection of delimiters in given string
	 *
	 * @return Array of String objects containing tokens that given string consisted of
	 *
	 * @see #tokeniseStringToVector(String, String, boolean, boolean)
	 */
	public static String[] tokeniseStringToArray(String string, String delimiters, boolean returnDelimiters, boolean removeDuplicates)
	{
		Vector store = tokeniseStringToVector(string, delimiters, returnDelimiters, removeDuplicates);
		String array[] = new String[store.size()];
		store.toArray(array);

		store = null;

		return array;
	}

	/**
	 * Tokenizes given string using characters of the delimiter string as a delimiters.
	 * <BR>
	 * To discard found delimiters within the string specify <I>returnDelimiters</I>
	 * parameter as <B>false</B>. Otherwise to return delimiters found in the given
	 * string as separate tokens specify it as <B>true</B>
	 * <BR>
	 * To remove duplicate tokens specify <I>removeDuplicates</I>
	 * parameter as <B>true</B>.
	 *
	 * @param string delimited string to be tokenised
	 * @param removeDuplicates flag indicating whether to keep or remove duplicate tokens
	 * @param delimiters characters used as delimiter in given string
	 * @param returnDelimiters
	 *
	 * @return Vector containing tokens that given string consisted of
	 */
	public static Vector tokeniseStringToVector(String string, String delimiters, boolean returnDelimiters, boolean removeDuplicates)
	{
		char theDelimiterArray[] = delimiters.toCharArray();
		char maxDelimChar = theDelimiterArray[0];
		for(int i = 0; i < theDelimiterArray.length; i++)
		{
			maxDelimChar = (maxDelimChar > theDelimiterArray[i]) ? maxDelimChar : theDelimiterArray[i];
		}

		int currentPosition = 0;
		int start = 0;
		char tmpChar = ' ';
		Vector store = new Vector(10);
		while(currentPosition < string.length())
		{
			start = currentPosition;

			while(currentPosition < string.length())
			{
				tmpChar = string.charAt(currentPosition);

				if((tmpChar <= maxDelimChar) && (delimiters.indexOf(tmpChar) >= 0))
				{
					break;
				}

				currentPosition++;
			}

			if(returnDelimiters && (start == currentPosition))
			{
				tmpChar = string.charAt(currentPosition);

				if((tmpChar <= maxDelimChar) && (delimiters.indexOf(tmpChar) >= 0))
				{
					currentPosition++;
				}
			}

			String token = string.substring(start, currentPosition);
			if((token.length() > 0) &&!(removeDuplicates && store.contains(token)))
			{
				store.add(token);
			}

			if(!returnDelimiters)
			{
				while(currentPosition < string.length())
				{
					tmpChar = string.charAt(currentPosition);

					if((tmpChar > maxDelimChar) || (delimiters.indexOf(tmpChar) < 0))
					{
						break;
					}

					currentPosition++;
				}
			}
		}

		return store;
	}

	public static String trimAll(String str)
	{
		return trimAll(str, "`~@#$?;,:\\ \t\r\n");		//$NON-NLS-1$
	}

	public static String trimAll(String str, String forbiddenCharacters)
	{
		if((str == null) || (str.length() == 0))
		{
			return EMPTY_STRING;
		}

		if((forbiddenCharacters == null) || (forbiddenCharacters.length() == 0))
		{
			return str;
		}

		char ch[] = str.toCharArray();
		int gap = 0;
		for(int i = 0; i < ch.length; i++)
		{
			if(forbiddenCharacters.indexOf(ch[i]) != -1)
			{
				if(gap == 0)
				{
					gap = 1;
				}

				int nextCharPos = i + gap;
				while((nextCharPos < ch.length) && (forbiddenCharacters.indexOf(ch[nextCharPos]) != -1))
				{
					gap++;

					nextCharPos = i + gap;
				}

				if(nextCharPos < ch.length)
				{
					ch[i] = ch[nextCharPos];
					ch[nextCharPos] = forbiddenCharacters.charAt(0);

					gap--;
				}
				else
				{
					break;
				}
			}
		}

		return new String(ch, 0, ch.length - gap);
	}

	public static String replaceString(String originalStr, String replacedStr)
	{
		return replaceString(originalStr, replacedStr, "`~!@#$%^&*()?.;'\"{}[]\\/|,*+-!:\\/()<>= ");	//$NON-NLS-1$
	}
	
    public static String replaceAmpersandAndRemoveSpecialCharacters(String input)
    {
        return replaceString(replace(input, "&" , "_"), "", "`~!@#%^*()?.;'\"{}[]\\/|,*+!:\\/()<>="); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    }
    
	public static String replaceString(String originalStr, String replacedStr, String forbiddenCharacters)
	{
		if((originalStr == null) || (originalStr.length() == 0))
		{
			return EMPTY_STRING;
		}

		if((forbiddenCharacters == null) || (forbiddenCharacters.length() == 0))
		{
			return originalStr;
		}

		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < originalStr.length(); i++)
		{
			if(forbiddenCharacters.indexOf(originalStr.charAt(i)) != -1)
			{
				sb.append(replacedStr);
			}
			else
			{
				sb.append(originalStr.charAt(i));
			}
		}

		return sb.toString();
	}

	/**
	 * Method replace
	 *
	 * This methods replaces all occurances of a pattern in the input string.
	 * Unlike just a string replace it can replace strings, not just characters.
	 *
	 * @param input - this is the String to search
	 * @param pattern - not a good name, as this is only the string that we want to match
	 * @param replacement - this is replacement for the searched pattern
	 *
	 * @return String containing the input string with all replacements
	 */
	public static String replace(String input, String pattern, String replacement)
	{
		if(input.indexOf(pattern) >= 0)
		{
			String firstpart = input.substring(0, input.indexOf(pattern)) + replacement + replace(input.substring(input.indexOf(pattern) + pattern.length()), pattern, replacement);
			return firstpart;
		}

		return input;
	}

	/**
	 * Converts the current search string with the mapping provided
	 *
	 * For example:
	 *  *service? will be converted to %service?
	 *
	 *  when using a HashMap like
	 *
	 *  HashMap map = new HashMap();
	 *  map.put("*", "%");
	 *  map.put("?", "_");
	 *
	 * @param input
	 * @param mapping
	 * @return an SQL query equivalent to the search String using the mapping above
	 */
	public static String replace(String input, HashMap mapping)
	{
		Set keySet = mapping.keySet();
		for(Iterator iter = keySet.iterator(); iter.hasNext(); )
		{
			String key = (String) iter.next();
			String value = (String) mapping.get(key);

			input = StringTools.replace(input, key, value);
		}

		return input;
	}

	public static boolean containsIgnoreCase(String[] itemList, String item)
	{
		for(int i = 0; i < itemList.length; i++)
		{
			if(item.equalsIgnoreCase(itemList[i]))
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * Find the first index of <tt>item</tt> in the string <tt>s</tt>
	 * ignoring case. Thus:
	 * <pre>
	 * indexOfIgnoreCase(&quot;Hello World&quot;, &quot;LL&quot;) = 2
	 * </pre>
	 *
	 * @param s
	 *            The string to be searched for a substring.
	 * @param item
	 *            The substring to search for.
	 * @return The index for the first occurance of item in s, or -1 if it could
	 *         not be found.
	 */
	public static int indexOfIgnoreCase(String s, String item)
	{
		return s.toUpperCase().indexOf(item.toUpperCase());
	}

	/**
	 * This method interns a string.  It may or may not use String.intern()'s
	 * intern store, but offers a similar service.
	 *
	 *
	 * @param s
	 *
	 * @return an interned String
	 * @see String#intern()
	 */
	public static String intern(String s)
	{
		return s.intern();
	}

	/**
	 * Returns true if <tt>s</tt> is null or the empty-string.
	 *
	 * @param s
	 * @return true if <tt>s</tt> is null or the empty-string.
	 */
	public static boolean isEmpty(String s)
	{
		return (s == null) || (s.length() == 0);
	}

	/**
	 * Returns true if <tt>s</tt> s is null, the empty-string or if s
	 * only contains white-space.
	 *
	 * @param s
	 * @return true if <tt>s</tt> s is null, the empty-string or if s
	 * only contains white-space.
	 */
	public static boolean isEmptyOrSpaces(String s)
	{
		if(s == null)
		{
			return true;
		}

		int len = s.length();
		if(len == 0)
		{
			return true;
		}

		for(int i = 0; i < len; i++)
		{
			if(!Character.isWhitespace(s.charAt(i)))
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns true if <tt>s</tt> s is not null or the empty-string.
	 *
	 * @param s
	 * @return true if <tt>s</tt> s is not null or the empty-string.
	 */
	public static boolean isNotEmpty(String s)
	{
		return (s != null) && (s.length() > 0);
	}

	/**
	 * Method tokeniseCamelStringToCollection will convert Strings like
	 * DoNotTouch to Do, Not, Touch
	 * OR
	 * DoNOTTouch to Do, NOT, Touch
	 *
	 *
	 * @param store
	 * @param target
	 *
	 * @return Collection of tokens.
	 */
	public static Collection tokeniseCamelStringToCollection(Collection store, String target)
	{
		if((target == null) || (target.length() == 0))
		{
			return store;
		}

		char array[] = target.toCharArray();
		StringBuffer word = new StringBuffer();
		for(int i = 0; i < array.length; i++)
		{
			if(Character.isUpperCase(array[i]))
			{
				// new word starts with an upper case
				// end the old word
				if(word.length() > 0)
				{
					store.add(word.toString());
				}

				word = new StringBuffer();

				// this will handle the words that might be in upper case
				// such as word NOT in DoNOTTouch
				int next = i + 1;
				if((next < array.length) && Character.isUpperCase(array[next]))
				{
					do
					{
						word.append(array[i]);

						next++;

						if(((next < array.length) && Character.isUpperCase(array[next])) || (next == array.length))
						{
							i++;
						}
						else
						{
							break;
						}
					}
					while(true);
				}
				else
				{
					word.append(array[i]);
				}
			}
			else
			{
				// part of an old word
				word.append(array[i]);
			}
		}

		if(word.length() > 0)
		{
			store.add(word.toString());
		}

		return store;
	}

	public static String capitaliseFirstCharacter(String s)
	{
		if(StringTools.isEmpty(s))
		{
			return s;
		}

		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * Converts a byte array to a string using the UTF-8 encoding.
	 * Use instead of new String(byte[]) for portability and i18n safety.
	 * @param bytes
	 * @return The converted String.
	 */
	public static String newStringUTF8(byte[] bytes)
	{
		try
		{
			return new String(bytes, "UTF-8");		//$NON-NLS-1$
		}
		catch(UnsupportedEncodingException e)
		{
			// UTF-8 is guaranteed.  See java.nio.charset.Charset
			throw new RuntimeException(e);
		}
	}

	/**
	 * Converts a string to a byte array using the UTF-8 encoding.
	 * Use instead of String.getBytes() for portability and i18n safety.
	 * @param s
	 * @return the byte array
	 */
	public static byte[] getBytesUTF8(String s)
	{
		try
		{
			return s.getBytes("UTF-8");		//$NON-NLS-1$
		}
		catch(UnsupportedEncodingException e)
		{
			// UTF-8 is guaranteed.  See java.nio.charset.Charset
			throw new RuntimeException(e);
		}
	}

	/**
	 * @return
	 * @uml.property  name="collator"
	 */
	public static Collator getCollator()
	{
		return (Collator) collator.clone();
	}

	/**
	 * Uses the default locale's collator to compare the two strings
	 * @param source
	 * @param target
	 * @return Returns an integer value. Value is less than zero if source is less than
	 * target, value is zero if source and target are equal, value is greater than zero
	 * if source is greater than target.
	 */
	public static int compare(String source, String target)
	{
		return collator.compare(source, target);
	}

	/**
     * Uses the default locale's collator to compare the two strings, 
     * ignoring differences in case, accents and control chars.
     * @param source
     * @param target
     * @return Returns an integer value. Value is less than zero if source is less than
     * target, value is zero if source and target are equal, value is greater than zero
     * if source is greater than target.
     */
	public static int comparePrimary(String source, String target){
	    return collatorPrimary.compare(source, target);
	}
	
	/**
	 * Uses the default locale's collator to compare the two strings for equality
	 * @param source
	 * @param target
	 * @return true if the strings are equal according to the collation
	 * rules.  false, otherwise.
	 */
	public static boolean equals(String source, String target)
	{
		return collator.equals(source, target);
	}

	/**
	 * Uses the default locale's collator to compare the two strings for
	 * equality, ignoring differences in case, accents and control chars.
	 * @see Collator#PRIMARY
	 * @param source
	 * @param target
	 * @return true if the strings are equal according to the collation
	 * rules.  false, otherwise.
	 */
	public static boolean equalsPrimary(String source, String target)
	{
		return collatorPrimary.equals(source, target);
	}

	/**
	 * Returns <code>true</code> if the two Strings are equal.
	 * This takes care of both being <code>null</code>.
	 * @param string1
	 * @param string2
	 * @return <code>true</code> if the two Strings are equal.
	 */
	public static boolean equalStrings(String string1, String string2)
	{
		if(string1 == null)
		{
			return string2 == null;
		}

		if(string2 == null)
		{
			return false;
		}

		// use a Unicode-aware collator
		return equals(string1, string2);
	}

	/**
	 * Tests whether or not the passed String contains only 7-bit ASCII characters.
	 *
	 * @param str the String to test.
	 *
	 * @return <code>true</code> iff the passed String contains only 7-bit ASCII characters.
	 */

	public static boolean isAscii(String str)
	{
		if(str == null)
		{
			throw new NullPointerException("string is null");		//$NON-NLS-1$
		}

		for(int i = 0; i < str.length(); i++)
		{
			char ch = str.charAt(i);

			/*
			 * The first 128 characters (0x00 - 0x7f) of Unicode are = ASCII. Characters
			 * outside this range are not ASCII.
			 */

			if(!isASCIICharacter(ch))
			{
				return false;
			}
		}

		return true;
	}

	public static boolean isASCIICharacter(char ch)
	{
		return ((ch >= 0) && (ch < 128));
	}

	public static boolean endsWithIgnoreCase(String str, String suffix)
	{
		int len = suffix.length();
		return str.regionMatches(true, str.length() - len, suffix, 0, len);
	}

	/**
	 * Escape <tt>s</tt> by prefixing any character in s that is also in
	 * chars with a backslash. For example <tt>escape("Hello", "l")</tt> would
	 * generate the string <tt>he\l\lo</tt>.
	 *
	 * @param buf
	 *            Buffer used to build the escaped string. any previous contents
	 *            are lost.
	 * @param s
	 *            A string to be escaped.
	 * @param charsToEscape
	 *            A string containing the characters to be escaped.
	 * @return The escaped string.
	 */
	public static String escape(StringBuffer buf, String s, String charsToEscape)
	{
		if(isEmpty(s) || isEmpty(charsToEscape))	// Nothing to do?
		{
			return s;
		}

		buf.setLength(0);		// Clear the buffer.

		for(int i = 0; i < s.length(); i++)
		{
			char x = s.charAt(i);

			if(charsToEscape.indexOf(x) != -1 || x == '\\')
			{
				buf.append('\\');
			}

			buf.append(x);
		}

		return buf.toString();
	}

	/**
	 * Escape <tt>s</tt> by prefixing any character in s that is also in chars
	 * with a backslash. For example <tt>escape("Hello", "l")</tt> would
	 * generate the string <tt>he\l\lo</tt>.
	 *
	 * @param s
	 *            A string to be escaped.
	 * @param charsToEscape
	 *            A string containing the characters to be escaped.
	 * @return The escaped string.
	 */
	public static String escape(String s, String charsToEscape)
	{
		StringBuffer buf = new StringBuffer();
		return escape(buf, s, charsToEscape);
	}

	/**
	 * Returns a string between the start and end character.
	 * User can specify whether to look for the last or first occurence of the end character after the start character.
	 *
	 * See {@link #stringAfter(String, char)} for details of special cases when the start character is not found.
	 * See {@link #stringBefore(String, char, boolean)} for details of special cases when the end character is not found.
	 *
	 * @param token - source string
	 * @param start - start character
	 * @param end - end character
	 * @param isEndLastOccurence - whether to look for the last or first occurence of the end character after the start character.
	 * @return a string between the start and end character.
	 */
	public static String stringBetween(String token, char start, char end, boolean isEndLastOccurence)
	{
		String newString = stringAfter(token, start);
		return stringBefore(newString, end, isEndLastOccurence);
	}
	
	/** Its standard is <code>expr</code> contains at least one
	 * starting square bracket "[" and followed by at least one closing square bracket "]",
	 * which is the convention for VSM.
	 * 
	 * @param expr
	 * @return
	 */
	public static boolean isMacroExpression(String expr)
	{
		int startingSquareBracketPosition = expr.indexOf("[");  //$NON-NLS-1$
		if (startingSquareBracketPosition == -1)
		{
			return false;
		}
		else
		{
			int endingSquareBracketPosition = expr.indexOf("]", startingSquareBracketPosition);	//$NON-NLS-1$
			if (endingSquareBracketPosition == -1)
			{
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Converts a string to an integer.
	 * @param s
	 * @return The converted integer.
	 */
	public static int stringToInteger(String s, int defaultValue)
	{
		try
		{
			return Integer.parseInt(s);
		}
		catch(NumberFormatException e)
		{
			return defaultValue;
		}
	}
	/**
	 * Match a string with a regular expression pattern.
	 * @param pattern
	 * @param s
	 * @return boolean as the match result.
	 */
	public static boolean isPattenMatched(String pattern,String s)
	{
		Pattern condition = Pattern.compile(pattern);
		Matcher check = condition.matcher(s);
		return check.matches();
	}

	/**
	 * 
	 */
	public static void reset() 
	{
	    collator = Collator.getInstance();
	    collatorPrimary = (Collator)collator.clone();
	    collatorPrimary.setStrength(Collator.PRIMARY);
	}
}