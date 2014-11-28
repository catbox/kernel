/**
 * Created by: Gerald Franklin Thomas
 * Date of creation: 2010-07-06
 * Project: Opus Dei
 * Package: com.opusdei.common
 * File: BooleanProperty.java
 * Description: Boolean Property
 *
 */
package com.opusdei.common;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Class to simplify using boolean properties. The class recognises the
 * following strings as representing a recognised boolean value:
 * <PRE>
 *  Y, Yes, True,  On,  1
 *  N, No,  False, Off, 0
 * </PRE>
 * NB: All string comparisons are case-insensitive.
 */
public class BooleanProperty
{
	// = Constant strings recognised as TRUE values

	/** shortened "Yes" */
	public final static String Y = "Y";				//$NON-NLS-1$

	/** "Yes" */
	public final static String YES = "Yes";			//$NON-NLS-1$

	/** "True" */
	public final static String TRUE = "True";		//$NON-NLS-1$

	/** "1" */
	public final static String ONE = "1";			//$NON-NLS-1$

	/** "Up" */
	public final static String UP = "Up";			//$NON-NLS-1$

	// = Constant strings recognised as FALSE values

	/** shortened Field NO */
	public final static String N = "N";				//$NON-NLS-1$

	/** Field NO */
	public final static String NO = "No";			//$NON-NLS-1$

	/** Field FALSE */
	public final static String FALSE = "False";		//$NON-NLS-1$

	/** Field FALSE */
	public final static String ZERO = "0";			//$NON-NLS-1$

	/** "Down" */
	public final static String DOWN = "Down";		//$NON-NLS-1$

	/**
	 * The set of strings recognised as boolean "true".
	 * <PRE>
	 *  Yes, True,  On,  1
	 * </PRE>
	 */
	private static final Set trueSet = new HashSet<String>(Arrays.asList(new String[]
	{
		YES.toUpperCase(), Y.toUpperCase(), CoreConstants.ON.toUpperCase(), TRUE.toUpperCase(), ONE, UP.toUpperCase()
	}));

	/**
	 * The set of strings recognised as boolean "false".
	 * <PRE>
	 *  No,  False, Off, 0
	 * </PRE>
	 */
	private static final Set falseSet = new HashSet<String>(Arrays.asList(new String[]
	{
		NO.toUpperCase(), N.toUpperCase(), CoreConstants.OFF.toUpperCase(), FALSE.toUpperCase(), ZERO, DOWN.toUpperCase()
	}));

	/**
	 * Decides if the specified object defines a boolean value and returns
	 * it. If the value is not recognised the default is returned. the rules
	 * are:
	 * <ol>
	 *  <li>If <tt>value</tt> is <tt>null</tt> return <tt>false</tt>.
	 *  <li>If <tt>value</tt> is a <tt>Boolean</tt> return its value.
	 *  <li>Otherwise convert <tt>value</tt> to a string and process it
	 *        using {@link #examine(String, boolean)}.
	 *
	 * @param value        The value to be examined.
	 * @param defaultvalue The default if value is not recognised as a
	 *                       valid boolean.
	 *
	 * @return   The boolean value of <tt>value</tt> or the default if
	 *             <tt>value</tt> is unrecognised.
	 */
	public static boolean examine(Object value, boolean defaultvalue)
	{
		if(value == null)
		{
			return defaultvalue;
		}

		if(value instanceof Boolean)
		{
			return ((Boolean) value).booleanValue();
		}

		return examine(value.toString(), defaultvalue);
	}

	/**
	 * Decides if a string is a recognised boolean true or false value. If
	 * not the default is returned instead.
	 *
	 * @param boolstr       The string to examine
	 * @param defaultvalue  If the string value is not recognised (not one of
	 *                       the values listed above), then return this default.
	 *
	 * @return              Either true or false as specified by the
	 *                        argument, or the default if <tt>boolstr</tt>
	 *                        is not recognised.
	 */
	public static boolean examine(String boolstr, boolean defaultvalue)
	{
		if(boolstr == null)
		{
			return defaultvalue;
		}

		String value = boolstr.toUpperCase();
		if(trueSet.contains(value))
		{
			return true;
		}

		if(falseSet.contains(value))
		{
			return false;
		}

		return defaultvalue;
	}

	/**
	 * Examins the system property to see if it contains a recognised boolean
	 * value. Provides additional functionality over Boolean.getProperty()
	 * since this function only recognises "true".
	 *
	 * @param property   The System property to fetch and examine.
	 * @param defValue
	 *
	 * @return The boolean value of the named System Property.
	 */
	public static boolean examineSystemProperty(String property, boolean defValue)
	{
		return examine(System.getProperty(property), defValue);
	}

	/**
	 * Determines if the supplied <code>String</code> value can be parsed as a <i>boolean</i>.
	 * <p>If stringValue is null then it returns <code>false</code>.</p>
	 *
	 * @param stringValue  The <code>String</code> value to examine.
	 * @return    <code>true</code> if it matches a recognised boolean value, otherwise false.
	 * @see #isBoolean(String)
	 */
	public static boolean canBeParsed(String stringValue)
	{
		if(stringValue == null)
		{
			return false;
		}

		String value = stringValue.toUpperCase();
		return trueSet.contains(value) || falseSet.contains(value);
	}

	/**
	 * Determines if the supplied <code>String</code> value should be considered as a <i>boolean</i>.
	 * <p>
	 * Note that, unlike <code>canBeParsed()</code>, this method will return false for the Strings "0" and "1".
	 * </p>
	 *
	 * @param stringValue  The <code>String</code> value to examine.
	 * @return <code>true</code> if the supplied <code>String</code> value should be considered as a <i>boolean</i>.
	 * @see #canBeParsed(String)
	 */
	public static boolean isBoolean(String stringValue)
	{
		if(stringValue == null)
		{
			return false;
		}

		if(stringValue.equals("0"))		//$NON-NLS-1$
		{
			return false;
		}

		if(stringValue.equals("1"))		//$NON-NLS-1$
		{
			return false;
		}

		return canBeParsed(stringValue);
	}

	/**
	 * Does the supplied string contain a recognised true value?
	 *
	 * @param boolstr  The string to examine.
	 *
	 * @return    Yes if it matches a recognised true value, otherwise
	 *              false. If boolstr is null false is also returned.
	 */
	public static boolean isTrue(String boolstr)
	{
		if(boolstr == null)
		{
			return false;
		}

		String value = boolstr.toUpperCase();
		return trueSet.contains(value);
	}

	/**
	 * Does the supplied string contain a recognised false value?
	 *
	 * @param boolstr  The string to examine.
	 *
	 * @return    Yes if it matches a recognised false value, otherwise
	 *              false. If boolstr is null false is also returned.
	 */
	public static boolean isFalse(String boolstr)
	{
		if(boolstr == null)
		{
			return false;
		}

		String value = boolstr.toUpperCase();
		return falseSet.contains(value);
	}

	/**
	 * Converts a boolean value to the string "Yes" or "No". Compare to
	 * <tt>String.valueOf()</tt> which converts a boolean to "true" or "false".
	 *
	 * @param value   The boolean value.
	 *
	 * @return        "Yes" if <t>value</tt> is true, "No" otherwise.
	 */
	public static String toString(boolean value)
	{
		return value ? YES : NO;
	}

	/**
	 * Converts a boolean value to the string "On" or "Off". Compare to
	 * <tt>String.valueOf()</tt> which converts a boolean to "true" or "false".
	 *
	 * @param value   The boolean value.
	 *
	 * @return        "On" if <t>value</tt> is true, "Off" otherwise.
	 */
	public static String toOnOffString(boolean value)
	{
		return value ? CoreConstants.ON : CoreConstants.OFF;
	}

	/**
	 * Converts a boolean value to the string "True" or "False".
	 *
	 * @param value   The boolean value.
	 *
	 * @return        "True" if <t>value</tt> is true, "False" otherwise.
	 */
	public static String toTrueFalseString(boolean value)
	{
		return value ? TRUE : FALSE;
	}
}
