/**
 */
package mdsebook.printers.T2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T2.T2Package
 * @generated
 */
public interface T2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T2Factory eINSTANCE = mdsebook.printers.T2.impl.T2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Printer Pool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer Pool</em>'.
	 * @generated
	 */
	PrinterPool createPrinterPool();

	/**
	 * Returns a new object of class '<em>Copier</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Copier</em>'.
	 * @generated
	 */
	Copier createCopier();

	/**
	 * Returns a new object of class '<em>Scanner</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scanner</em>'.
	 * @generated
	 */
	Scanner createScanner();

	/**
	 * Returns a new object of class '<em>Fax</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Fax</em>'.
	 * @generated
	 */
	Fax createFax();

	/**
	 * Returns a new object of class '<em>Printer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer</em>'.
	 * @generated
	 */
	Printer createPrinter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	T2Package getT2Package();

} //T2Factory
