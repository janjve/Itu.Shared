/**
 */
package mdsebook.printers.T4;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T4.T4Package
 * @generated
 */
public interface T4Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T4Factory eINSTANCE = mdsebook.printers.T4.impl.T4FactoryImpl.init();

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
	 * Returns a new object of class '<em>Color Printer Head</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Color Printer Head</em>'.
	 * @generated
	 */
	ColorPrinterHead createColorPrinterHead();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	T4Package getT4Package();

} //T4Factory
