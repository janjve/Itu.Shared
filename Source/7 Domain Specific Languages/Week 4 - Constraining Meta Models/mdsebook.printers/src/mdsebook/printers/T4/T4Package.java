/**
 */
package mdsebook.printers.T4;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T4.T4Factory
 * @model kind="package"
 * @generated
 */
public interface T4Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "T4";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.itu.dk/people/wasowski/mdsebook.printers.T4";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mdsebook.printers.T4";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T4Package eINSTANCE = mdsebook.printers.T4.impl.T4PackageImpl.init();

	/**
	 * The meta object id for the '{@link mdsebook.printers.T4.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T4.impl.PrinterPoolImpl
	 * @see mdsebook.printers.T4.impl.T4PackageImpl#getPrinterPool()
	 * @generated
	 */
	int PRINTER_POOL = 0;

	/**
	 * The feature id for the '<em><b>Printer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL__PRINTER = 0;

	/**
	 * The number of structural features of the '<em>Printer Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Printer Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mdsebook.printers.T4.impl.PrinterImpl <em>Printer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T4.impl.PrinterImpl
	 * @see mdsebook.printers.T4.impl.T4PackageImpl#getPrinter()
	 * @generated
	 */
	int PRINTER = 1;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__POOL = 0;

	/**
	 * The feature id for the '<em><b>Head</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__HEAD = 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__COLOR = 2;

	/**
	 * The number of structural features of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mdsebook.printers.T4.impl.ColorPrinterHeadImpl <em>Color Printer Head</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T4.impl.ColorPrinterHeadImpl
	 * @see mdsebook.printers.T4.impl.T4PackageImpl#getColorPrinterHead()
	 * @generated
	 */
	int COLOR_PRINTER_HEAD = 2;

	/**
	 * The feature id for the '<em><b>Printer</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_PRINTER_HEAD__PRINTER = 0;

	/**
	 * The number of structural features of the '<em>Color Printer Head</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_PRINTER_HEAD_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Color Printer Head</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COLOR_PRINTER_HEAD_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T4.PrinterPool <em>Printer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer Pool</em>'.
	 * @see mdsebook.printers.T4.PrinterPool
	 * @generated
	 */
	EClass getPrinterPool();

	/**
	 * Returns the meta object for the containment reference list '{@link mdsebook.printers.T4.PrinterPool#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Printer</em>'.
	 * @see mdsebook.printers.T4.PrinterPool#getPrinter()
	 * @see #getPrinterPool()
	 * @generated
	 */
	EReference getPrinterPool_Printer();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T4.Printer <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer</em>'.
	 * @see mdsebook.printers.T4.Printer
	 * @generated
	 */
	EClass getPrinter();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T4.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pool</em>'.
	 * @see mdsebook.printers.T4.Printer#getPool()
	 * @see #getPrinter()
	 * @generated
	 */
	EReference getPrinter_Pool();

	/**
	 * Returns the meta object for the containment reference '{@link mdsebook.printers.T4.Printer#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Head</em>'.
	 * @see mdsebook.printers.T4.Printer#getHead()
	 * @see #getPrinter()
	 * @generated
	 */
	EReference getPrinter_Head();

	/**
	 * Returns the meta object for the attribute '{@link mdsebook.printers.T4.Printer#isColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see mdsebook.printers.T4.Printer#isColor()
	 * @see #getPrinter()
	 * @generated
	 */
	EAttribute getPrinter_Color();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T4.ColorPrinterHead <em>Color Printer Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Color Printer Head</em>'.
	 * @see mdsebook.printers.T4.ColorPrinterHead
	 * @generated
	 */
	EClass getColorPrinterHead();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T4.ColorPrinterHead#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Printer</em>'.
	 * @see mdsebook.printers.T4.ColorPrinterHead#getPrinter()
	 * @see #getColorPrinterHead()
	 * @generated
	 */
	EReference getColorPrinterHead_Printer();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	T4Factory getT4Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link mdsebook.printers.T4.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T4.impl.PrinterPoolImpl
		 * @see mdsebook.printers.T4.impl.T4PackageImpl#getPrinterPool()
		 * @generated
		 */
		EClass PRINTER_POOL = eINSTANCE.getPrinterPool();

		/**
		 * The meta object literal for the '<em><b>Printer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER_POOL__PRINTER = eINSTANCE.getPrinterPool_Printer();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T4.impl.PrinterImpl <em>Printer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T4.impl.PrinterImpl
		 * @see mdsebook.printers.T4.impl.T4PackageImpl#getPrinter()
		 * @generated
		 */
		EClass PRINTER = eINSTANCE.getPrinter();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER__POOL = eINSTANCE.getPrinter_Pool();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER__HEAD = eINSTANCE.getPrinter_Head();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRINTER__COLOR = eINSTANCE.getPrinter_Color();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T4.impl.ColorPrinterHeadImpl <em>Color Printer Head</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T4.impl.ColorPrinterHeadImpl
		 * @see mdsebook.printers.T4.impl.T4PackageImpl#getColorPrinterHead()
		 * @generated
		 */
		EClass COLOR_PRINTER_HEAD = eINSTANCE.getColorPrinterHead();

		/**
		 * The meta object literal for the '<em><b>Printer</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLOR_PRINTER_HEAD__PRINTER = eINSTANCE.getColorPrinterHead_Printer();

	}

} //T4Package
