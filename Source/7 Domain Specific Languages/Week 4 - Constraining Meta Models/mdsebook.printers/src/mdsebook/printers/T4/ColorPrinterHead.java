/**
 */
package mdsebook.printers.T4;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Color Printer Head</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T4.ColorPrinterHead#getPrinter <em>Printer</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T4.T4Package#getColorPrinterHead()
 * @model
 * @generated
 */
public interface ColorPrinterHead extends EObject {
	/**
	 * Returns the value of the '<em><b>Printer</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T4.Printer#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Printer</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Printer</em>' container reference.
	 * @see #setPrinter(Printer)
	 * @see mdsebook.printers.T4.T4Package#getColorPrinterHead_Printer()
	 * @see mdsebook.printers.T4.Printer#getHead
	 * @model opposite="head" required="true" transient="false"
	 * @generated
	 */
	Printer getPrinter();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T4.ColorPrinterHead#getPrinter <em>Printer</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Printer</em>' container reference.
	 * @see #getPrinter()
	 * @generated
	 */
	void setPrinter(Printer value);

} // ColorPrinterHead
