/**
 */
package mdsebook.printers.T6;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T6.T6Package
 * @generated
 */
public interface T6Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T6Factory eINSTANCE = mdsebook.printers.T6.impl.T6FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Printer Pool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer Pool</em>'.
	 * @generated
	 */
	PrinterPool createPrinterPool();

	/**
	 * Returns a new object of class '<em>Printer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer</em>'.
	 * @generated
	 */
	Printer createPrinter();

	/**
	 * Returns a new object of class '<em>Scanner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scanner</em>'.
	 * @generated
	 */
	Scanner createScanner();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	T6Package getT6Package();

} //T6Factory
