/**
 */
package mdsebook.printers.T4;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Printer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T4.Printer#getPool <em>Pool</em>}</li>
 *   <li>{@link mdsebook.printers.T4.Printer#getHead <em>Head</em>}</li>
 *   <li>{@link mdsebook.printers.T4.Printer#isColor <em>Color</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T4.T4Package#getPrinter()
 * @model
 * @generated
 */
public interface Printer extends EObject {
	/**
	 * Returns the value of the '<em><b>Pool</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T4.PrinterPool#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pool</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' container reference.
	 * @see #setPool(PrinterPool)
	 * @see mdsebook.printers.T4.T4Package#getPrinter_Pool()
	 * @see mdsebook.printers.T4.PrinterPool#getPrinter
	 * @model opposite="printer" required="true" transient="false"
	 * @generated
	 */
	PrinterPool getPool();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T4.Printer#getPool <em>Pool</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' container reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(PrinterPool value);

	/**
	 * Returns the value of the '<em><b>Head</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T4.ColorPrinterHead#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Head</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Head</em>' containment reference.
	 * @see #setHead(ColorPrinterHead)
	 * @see mdsebook.printers.T4.T4Package#getPrinter_Head()
	 * @see mdsebook.printers.T4.ColorPrinterHead#getPrinter
	 * @model opposite="printer" containment="true"
	 * @generated
	 */
	ColorPrinterHead getHead();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T4.Printer#getHead <em>Head</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Head</em>' containment reference.
	 * @see #getHead()
	 * @generated
	 */
	void setHead(ColorPrinterHead value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(boolean)
	 * @see mdsebook.printers.T4.T4Package#getPrinter_Color()
	 * @model required="true"
	 * @generated
	 */
	boolean isColor();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T4.Printer#isColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #isColor()
	 * @generated
	 */
	void setColor(boolean value);

} // Printer
