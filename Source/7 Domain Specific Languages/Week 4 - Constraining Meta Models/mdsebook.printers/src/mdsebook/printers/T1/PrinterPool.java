/**
 */
package mdsebook.printers.T1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Printer Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T1.PrinterPool#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T1.PrinterPool#getFax <em>Fax</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T1.T1Package#getPrinterPool()
 * @model
 * @generated
 */
public interface PrinterPool extends EObject {
	/**
	 * Returns the value of the '<em><b>Printer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T1.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Printer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Printer</em>' containment reference.
	 * @see #setPrinter(Printer)
	 * @see mdsebook.printers.T1.T1Package#getPrinterPool_Printer()
	 * @see mdsebook.printers.T1.Printer#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Printer getPrinter();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T1.PrinterPool#getPrinter <em>Printer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Printer</em>' containment reference.
	 * @see #getPrinter()
	 * @generated
	 */
	void setPrinter(Printer value);

	/**
	 * Returns the value of the '<em><b>Fax</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T1.Fax#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fax</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fax</em>' containment reference.
	 * @see #setFax(Fax)
	 * @see mdsebook.printers.T1.T1Package#getPrinterPool_Fax()
	 * @see mdsebook.printers.T1.Fax#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Fax getFax();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T1.PrinterPool#getFax <em>Fax</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fax</em>' containment reference.
	 * @see #getFax()
	 * @generated
	 */
	void setFax(Fax value);

} // PrinterPool
