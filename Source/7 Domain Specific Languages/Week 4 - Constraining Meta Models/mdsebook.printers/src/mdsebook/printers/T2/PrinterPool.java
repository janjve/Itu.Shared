/**
 */
package mdsebook.printers.T2;

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
 *   <li>{@link mdsebook.printers.T2.PrinterPool#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T2.PrinterPool#getFax <em>Fax</em>}</li>
 *   <li>{@link mdsebook.printers.T2.PrinterPool#getCopier <em>Copier</em>}</li>
 *   <li>{@link mdsebook.printers.T2.PrinterPool#getScanner <em>Scanner</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T2.T2Package#getPrinterPool()
 * @model
 * @generated
 */
public interface PrinterPool extends EObject {
	/**
	 * Returns the value of the '<em><b>Printer</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T2.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Printer</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Printer</em>' containment reference.
	 * @see #setPrinter(Printer)
	 * @see mdsebook.printers.T2.T2Package#getPrinterPool_Printer()
	 * @see mdsebook.printers.T2.Printer#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Printer getPrinter();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T2.PrinterPool#getPrinter <em>Printer</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Printer</em>' containment reference.
	 * @see #getPrinter()
	 * @generated
	 */
	void setPrinter(Printer value);

	/**
	 * Returns the value of the '<em><b>Fax</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T2.Fax#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fax</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fax</em>' containment reference.
	 * @see #setFax(Fax)
	 * @see mdsebook.printers.T2.T2Package#getPrinterPool_Fax()
	 * @see mdsebook.printers.T2.Fax#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Fax getFax();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T2.PrinterPool#getFax <em>Fax</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Fax</em>' containment reference.
	 * @see #getFax()
	 * @generated
	 */
	void setFax(Fax value);

	/**
	 * Returns the value of the '<em><b>Copier</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T2.Copier#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Copier</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Copier</em>' containment reference.
	 * @see #setCopier(Copier)
	 * @see mdsebook.printers.T2.T2Package#getPrinterPool_Copier()
	 * @see mdsebook.printers.T2.Copier#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Copier getCopier();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T2.PrinterPool#getCopier <em>Copier</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Copier</em>' containment reference.
	 * @see #getCopier()
	 * @generated
	 */
	void setCopier(Copier value);

	/**
	 * Returns the value of the '<em><b>Scanner</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T2.Scanner#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scanner</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scanner</em>' containment reference.
	 * @see #setScanner(Scanner)
	 * @see mdsebook.printers.T2.T2Package#getPrinterPool_Scanner()
	 * @see mdsebook.printers.T2.Scanner#getPool
	 * @model opposite="pool" containment="true"
	 * @generated
	 */
	Scanner getScanner();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T2.PrinterPool#getScanner <em>Scanner</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scanner</em>' containment reference.
	 * @see #getScanner()
	 * @generated
	 */
	void setScanner(Scanner value);

} // PrinterPool
